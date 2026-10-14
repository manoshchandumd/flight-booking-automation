package com.manosh.automation.data;

/** Test passenger. All values are dummy data for the public demo site. */
public record Passenger(String name, String address, String city, String state,
                        String zipCode, String cardType, String cardNumber) {
}
