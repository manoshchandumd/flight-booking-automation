package com.manosh.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Booking confirmation page shown after a successful purchase. */
public class ConfirmationPage extends BasePage {

    private final By heading = By.tagName("h1");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getHeading() {
        return getText(heading);
    }

    /** Reads a value from the confirmation table, e.g. "Id" or "Status". */
    public String getDetail(String label) {
        By valueCell = By.xpath("//td[normalize-space()='" + label + "']/following-sibling::td[1]");
        return getText(valueCell);
    }
}
