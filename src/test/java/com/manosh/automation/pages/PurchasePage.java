package com.manosh.automation.pages;

import com.manosh.automation.data.Passenger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Passenger and payment details form. */
public class PurchasePage extends BasePage {

    private final By name = By.id("inputName");
    private final By address = By.id("address");
    private final By city = By.id("city");
    private final By state = By.id("state");
    private final By zipCode = By.id("zipCode");
    private final By cardType = By.id("cardType");
    private final By cardNumber = By.id("creditCardNumber");
    private final By nameOnCard = By.id("nameOnCard");
    private final By purchaseButton = By.cssSelector("input[type='submit']");

    public PurchasePage(WebDriver driver) {
        super(driver);
    }

    public PurchasePage fillPassengerDetails(Passenger p) {
        type(name, p.name());
        type(address, p.address());
        type(city, p.city());
        type(state, p.state());
        type(zipCode, p.zipCode());
        selectByVisibleText(cardType, p.cardType());
        type(cardNumber, p.cardNumber());
        type(nameOnCard, p.name());
        return this;
    }

    public ConfirmationPage purchaseFlight() {
        click(purchaseButton);
        return new ConfirmationPage(driver);
    }
}
