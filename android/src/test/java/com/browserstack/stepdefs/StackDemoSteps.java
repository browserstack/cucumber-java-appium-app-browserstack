package com.browserstack.stepdefs;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.AppiumBy;

public class StackDemoSteps {
    private WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "cucumber-java:appium-sample-main:v1.0");
        capabilities.setCapability("bstack:options", bstackOptions);
        driver = new AndroidDriver(
                new URL("https://hub.browserstack.com/wd/hub"), capabilities);
    }

    @Given("I try to search using Wikipedia App")
    public void I_try_to_search_wikipedia_app() throws Throwable {
        WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));
        searchElement.click();
    }


    @Then("I search with keyword BrowserStack")
    public void I_search_with_keyword_browserstack() throws InterruptedException {
        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("BrowserStack");
        Thread.sleep(5000);
    }

    @Then("The search results should be listed")
    public void search_results_should_be_listed() {
        List<WebElement> allProductsName = driver.findElements(AppiumBy.className("android.widget.TextView"));
        Assert.assertTrue(allProductsName.size() > 0);
    }

    @When("I start test on the Local Sample App")
    public void I_start_test_on_the_local_sample_app() {
        WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("com.example.android.basicnetworking:id/test_action")));
        searchElement.click();
        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.className("android.widget.TextView")));
    }

    @Then("I should see {string}")
    public void I_should_see(String localString) throws InterruptedException, IOException {
        WebElement testElement = null;
        List<WebElement> allTextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));
        Thread.sleep(10);
        for (WebElement textElement : allTextViewElements) {
            if (textElement.getText().contains("The active connection is")) {
                testElement = textElement;
            }
        }

        if (testElement == null) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "screenshot.png"));
            System.out.println("Screenshot stored at " + System.getProperty("user.dir") + "screenshot.png");
            throw new Error("Cannot find the needed TextView element from app");
        }
        String matchedString = testElement.getText();
        System.out.println(matchedString);
        Assert.assertTrue(matchedString.contains("The active connection is wifi"));
        Assert.assertTrue(matchedString.contains(localString));
    }

    @After
    public void teardown(Scenario scenario) throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
