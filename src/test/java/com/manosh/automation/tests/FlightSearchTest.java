package com.manosh.automation.tests;

import com.manosh.automation.base.BaseTest;
import com.manosh.automation.data.TestData;
import com.manosh.automation.pages.HomePage;
import com.manosh.automation.pages.ReservePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightSearchTest extends BaseTest {

    @Test(description = "Home page loads with the correct title", groups = "smoke")
    public void homePageLoads() {
        HomePage home = new HomePage(driver()).open();
        Assert.assertEquals(home.getTitle(), "BlazeDemo");
    }

    @Test(description = "Searching a route shows flights for that route",
            dataProvider = "routes", dataProviderClass = TestData.class, groups = "regression")
    public void searchShowsFlightsForRoute(String from, String to) {
        ReservePage results = new HomePage(driver()).open().searchFlights(from, to);

        Assert.assertEquals(results.getResultsHeading(), "Flights from " + from + " to " + to + ":",
                "Results heading should name the searched route");
        Assert.assertTrue(results.getFlightCount() > 0, "At least one flight should be listed");
    }
}
