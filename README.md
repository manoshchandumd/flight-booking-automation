# Flight Booking UI Automation Framework

A Selenium + Java + TestNG test automation framework for an end-to-end **flight search and booking** flow, built on the public demo site [BlazeDemo](https://blazedemo.com).

I work as a QA Engineer on travel-technology platforms (flights, hotels, visa), so I modelled this project on the flows I test every day: search, choose a flight, enter passenger and payment details, and verify the booking confirmation.

## What it covers

| Test | What it checks |
|------|----------------|
| `homePageLoads` | Home page opens with the correct title |
| `searchShowsFlightsForRoute` | Data-driven search for 3 routes; results heading and flight list are correct |
| `bookFlightEndToEnd` | Full booking for 2 passengers; confirmation heading, booking ID and status |
| `invalidFlightIndexIsRejected` | Negative test: choosing a flight that does not exist is rejected |

## Tech stack

- **Java 17**, **Selenium WebDriver 4** (Selenium Manager handles browser drivers)
- **TestNG**: groups (`smoke`, `regression`), data providers, soft assertions, parallel runs
- **Page Object Model** with a shared `BasePage` using explicit waits (no `Thread.sleep`)
- **ExtentReports** HTML report with a screenshot attached to every failed test
- **ThreadLocal WebDriver** for safe parallel execution
- **GitHub Actions** runs the suite in headless Chrome on every push

## Project structure

```
src/test/java/com/manosh/automation
├── base/        DriverFactory (ThreadLocal driver), BaseTest (setup/teardown)
├── config/      ConfigReader (config.properties + -D overrides)
├── data/        Passenger model and TestNG data providers
├── listeners/   TestListener (ExtentReports + failure screenshots)
├── pages/       HomePage, ReservePage, PurchasePage, ConfirmationPage
└── tests/       FlightSearchTest, FlightBookingTest
src/test/resources/config.properties
testng.xml
```

## How to run

Requirements: Java 17+, Maven, and Chrome (or Firefox/Edge).

```bash
# Run the full suite headless
mvn test

# Watch the browser
mvn test -Dheadless=false

# Run on Firefox
mvn test -Dbrowser=firefox
```

The HTML report is written to `target/reports/TestReport.html`.

## Design choices

- **Explicit waits only.** Every action waits for the element to be visible or clickable, which keeps tests fast and stable.
- **Config with overrides.** Defaults live in `config.properties`; any value can be overridden from the command line or CI with `-Dkey=value`.
- **Readable tests.** Page methods return the next page, so a test reads like the user journey:
  `open() → searchFlights() → chooseFlight() → fillPassengerDetails() → purchaseFlight()`.

All test data is dummy data for a public demo site.

---
**Manosh Chandu M D** · QA Engineer · [LinkedIn](https://www.linkedin.com/in/manosh-chandu-m-d-8b539a233)
