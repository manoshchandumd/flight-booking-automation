package com.manosh.automation.pages;

import com.manosh.automation.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Landing page: choose departure and destination cities and search. */
public class HomePage extends BasePage {

    private final By fromCity = By.name("fromPort");
    private final By toCity = By.name("toPort");
    private final By findFlightsButton = By.cssSelector("input[type='submit']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(ConfigReader.get("baseUrl"));
        waitForVisible(fromCity);
        return this;
    }

    public ReservePage searchFlights(String from, String to) {
        selectByVisibleText(fromCity, from);
        selectByVisibleText(toCity, to);
        click(findFlightsButton);
        return new ReservePage(driver);
    }
}
