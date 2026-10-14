package com.manosh.automation.tests;

import com.manosh.automation.base.BaseTest;
import com.manosh.automation.data.Passenger;
import com.manosh.automation.data.TestData;
import com.manosh.automation.pages.ConfirmationPage;
import com.manosh.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FlightBookingTest extends BaseTest {

    @Test(description = "End-to-end booking: search, choose flight, pay, confirm",
            dataProvider = "bookings", dataProviderClass = TestData.class, groups = {"smoke", "regression"})
    public void bookFlightEndToEnd(String from, String to, int flightIndex, Passenger passenger) {
        ConfirmationPage confirmation = new HomePage(driver())
                .open()
                .searchFlights(from, to)
                .chooseFlight(flightIndex)
                .fillPassengerDetails(passenger)
                .purchaseFlight();

        SoftAssert soft = new SoftAssert();
        soft.assertEquals(confirmation.getHeading(), "Thank you for your purchase today!");
        soft.assertFalse(confirmation.getDetail("Id").isBlank(), "Booking id should be generated");
        soft.assertEquals(confirmation.getDetail("Status"), "PendingCapture");
        soft.assertAll();
    }

    @Test(description = "Choosing a flight index that does not exist is rejected", groups = "regression")
    public void invalidFlightIndexIsRejected() {
        var results = new HomePage(driver()).open().searchFlights("Boston", "Rome");
        int count = results.getFlightCount();
        Assert.assertThrows(IllegalArgumentException.class, () -> results.chooseFlight(count));
    }
}
