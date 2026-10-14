package com.manosh.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/** Search results page listing available flights for the chosen route. */
public class ReservePage extends BasePage {

    private final By resultsHeading = By.tagName("h3");
    private final By flightRows = By.cssSelector("table.table tbody tr");
    private final By chooseFlightButton = By.cssSelector("input[type='submit']");

    public ReservePage(WebDriver driver) {
        super(driver);
    }

    public String getResultsHeading() {
        return getText(resultsHeading);
    }

    public int getFlightCount() {
        waitForVisible(flightRows);
        return driver.findElements(flightRows).size();
    }

    /** Selects a flight by its position in the results table (0 = first flight). */
    public PurchasePage chooseFlight(int index) {
        waitForVisible(flightRows);
        List<WebElement> rows = driver.findElements(flightRows);
        if (index < 0 || index >= rows.size()) {
            throw new IllegalArgumentException("No flight at index " + index + "; found " + rows.size());
        }
        rows.get(index).findElement(chooseFlightButton).click();
        return new PurchasePage(driver);
    }
}
