package com.manosh.automation.data;

import org.testng.annotations.DataProvider;

/** Data-driven inputs shared by the test classes. */
public final class TestData {

    private TestData() {
    }

    /** Routes available on the demo site: {from, to}. */
    @DataProvider(name = "routes", parallel = true)
    public static Object[][] routes() {
        return new Object[][]{
                {"Paris", "Buenos Aires"},
                {"Boston", "London"},
                {"Mexico City", "Rome"},
        };
    }

    /** Booking scenarios: {from, to, flight index, passenger}. */
    @DataProvider(name = "bookings")
    public static Object[][] bookings() {
        return new Object[][]{
                {"Philadelphia", "Berlin", 0,
                        new Passenger("Test Traveller", "12 MG Road", "Bengaluru", "Karnataka",
                                "560001", "Visa", "4111111111111111")},
                {"San Diego", "Cairo", 2,
                        new Passenger("Demo User", "45 Lake View", "Mysuru", "Karnataka",
                                "570001", "American Express", "378282246310005")},
        };
    }
}
