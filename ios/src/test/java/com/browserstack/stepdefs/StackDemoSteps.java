package com.browserstack.stepdefs;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
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

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.AppiumBy;

public class StackDemoSteps {
    private IOSDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        MutableCapabilities capabilities = new XCUITestOptions();
        HashMap<String, String> bstackOptions = new HashMap<>();
        bstackOptions.putIfAbsent("source", "cucumber-java:appium-sample-main:v1.0");
        capabilities.setCapability("bstack:options", bstackOptions);
        driver = new IOSDriver(
                new URL("https://hub.browserstack.com/wd/hub"), capabilities);
    }

    @Given("I try to find Text Button in Sample App")
    public void I_try_to_find_text_sample_app() throws Throwable {
        WebElement textButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Button")));
        textButton.click();
    }


    @When("I type in 'hello@browserstack.com' in the Text Input field")
    public void I_type_hello_text_input_field() throws InterruptedException {
        WebElement textInput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Input")));
        textInput.sendKeys("hello@browserstack.com"+"\n");
        Thread.sleep(5000);
    }

    @Then("I should get the entered text in the Text Output field")
    public void I_should_get_entered_text_in_text_output() {
        WebElement textOutput = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Text Output")));
        Assert.assertEquals(textOutput.getText(),"hello@browserstack.com");
    }

    @When("I start test on the Local Sample App")
    public void I_start_test_on_the_local_sample_app() {
        WebElement testButton = (WebElement) new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("TestBrowserStackLocal")));
        testButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                String result = d.findElement(AppiumBy.accessibilityId("ResultBrowserStackLocal")).getAttribute("value");
                return result != null && result.length() > 0;
            }
        });
    }

    @Then("I should see {string}")
    public void I_should_see(String localString) throws InterruptedException, IOException {
        WebElement resultElement = (WebElement) driver.findElement(AppiumBy.accessibilityId("ResultBrowserStackLocal"));

        String resultString = resultElement.getText().toLowerCase();
        System.out.println(resultString);
        if(resultString.contains("not working")) {
            File scrFile = (File) ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/screenshot.png"));
            System.out.println("Screenshot stored at " + System.getProperty("user.dir") + "/screenshot.png");
            throw new Error("Unexpected BrowserStackLocal test result");
        }
        Assert.assertTrue(resultString.contains(localString.toLowerCase()));
    }

    @After
    public void teardown(Scenario scenario) throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
