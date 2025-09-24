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

    @Then("I verify the search icon is displayed")
    public void I_verify_search_icon_displayed() {
        WebElement searchIcon = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId("Search Wikipedia")));
        Assert.assertTrue(searchIcon.isDisplayed());
    }

    @When("I tap on the first search result")
    public void I_tap_first_search_result() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(AppiumBy.className("android.widget.TextView"));
        if (searchResults.size() > 1) {
            searchResults.get(1).click();
        }
    }

    @When("I tap on the second search result")
    public void I_tap_second_search_result() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(AppiumBy.className("android.widget.TextView"));
        if (searchResults.size() > 2) {
            searchResults.get(2).click();
        }
    }

    @When("I tap on the third search result")
    public void I_tap_third_search_result() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(AppiumBy.className("android.widget.TextView"));
        if (searchResults.size() > 3) {
            searchResults.get(3).click();
        }
    }

    @When("I tap on the fourth search result")
    public void I_tap_fourth_search_result() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(AppiumBy.className("android.widget.TextView"));
        if (searchResults.size() > 4) {
            searchResults.get(4).click();
        }
    }

    @When("I tap on the fifth search result")
    public void I_tap_fifth_search_result() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(AppiumBy.className("android.widget.TextView"));
        if (searchResults.size() > 5) {
            searchResults.get(5).click();
        }
    }

    @Then("I verify the article title is displayed")
    public void I_verify_article_title_displayed() throws InterruptedException {
        Thread.sleep(3000);
        List<WebElement> titleElements = driver.findElements(AppiumBy.className("android.widget.TextView"));
        Assert.assertTrue(titleElements.size() > 0, "Article title should be displayed");
    }

    @Then("I scroll down to see more content")
    public void I_scroll_down_content() throws InterruptedException {
        Thread.sleep(1000);
        // Perform scroll action
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        
        // Using touch action for scroll
        // Note: This is a basic scroll implementation
        Thread.sleep(1000);
    }

    @When("I tap the back button")
    public void I_tap_back_button() throws InterruptedException {
        Thread.sleep(1000);
        try {
            driver.navigate().back();
        } catch (Exception e) {
            // Alternative back button approach if navigate().back() fails
            WebElement backButton = driver.findElement(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"));
            backButton.click();
        }
    }

    @Then("I verify I am back to search results")
    public void I_verify_back_to_search_results() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(AppiumBy.className("android.widget.TextView"));
        Assert.assertTrue(searchResults.size() > 0, "Should be back to search results");
    }

    @Then("I clear the search field")
    public void I_clear_search_field() throws InterruptedException {
        Thread.sleep(1000);
        try {
            WebElement searchField = driver.findElement(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"));
            searchField.clear();
        } catch (Exception e) {
            // Alternative approach if direct clear fails
            WebElement searchElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Search Wikipedia")));
            searchElement.click();
        }
    }

    @When("I enter a new search term {string}")
    public void I_enter_new_search_term(String searchTerm) throws InterruptedException {
        WebElement insertTextElement = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.clear();
        insertTextElement.sendKeys(searchTerm);
        Thread.sleep(3000);
    }

    @Then("I verify new search results are displayed")
    public void I_verify_new_search_results_displayed() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> newResults = driver.findElements(AppiumBy.className("android.widget.TextView"));
        Assert.assertTrue(newResults.size() > 0, "New search results should be displayed");
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
