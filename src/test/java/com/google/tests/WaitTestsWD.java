package com.google.tests;

import com.google.AdditionalConditions;
import com.google.DriverFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Santi on 7/20/2016.
 */
public class WaitTestsWD extends DriverFactory {

    @Test
    public void loadJquerySite() throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.get("http://jquery.com");

        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
        wait.until(AdditionalConditions.jQueryAJAXCallsHaveCompleted());
    }

    @Test
    public void loadAngularJSSite() throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.get("https://angularjs.org");

        WebDriverWait wait = new WebDriverWait(driver, 15, 100);
        wait.until(AdditionalConditions.angularHasFinishedProcessing());
    }

    @Test
    public void fluentWaitIgnoringSingleException() throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.get("https://angularjs.org");

        System.out.println("Page title is: " + driver.getTitle());

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .withMessage("The message you will see in if a TimeoutException is thrown");


        wait.until(AdditionalConditions.angularHasFinishedProcessing());
    }

    @Test
    public void fluentWaitIgnoringMultipleExceptions() throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.get("https://angularjs.org");

        System.out.println("Page title is: " + driver.getTitle());

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("The message you will see in if a TimeoutException is thrown");


        wait.until(AdditionalConditions.angularHasFinishedProcessing());
    }

    @Test
    public void fluentWaitIgnoringAListOfExceptions() throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.get("https://angularjs.org");

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoreAll(Arrays.asList(NoSuchElementException.class, StaleElementReferenceException.class))
                .withMessage("The message you will see in if a TimeoutException is thrown");


        wait.until(AdditionalConditions.angularHasFinishedProcessing());
    }

    @Test
    public void fluentWaitIgnoringACollectionOfExceptions() throws Exception {
        WebDriver driver = DriverFactory.getDriver();

        driver.get("https://angularjs.org");
        List<Class<? extends Throwable>> exceptionsToIgnore = new ArrayList<Class<? extends Throwable>>() {
            {
                add(NoSuchElementException.class);
                add(StaleElementReferenceException.class);
            }
        };

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(15, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoreAll(exceptionsToIgnore)
                .withMessage("The message you will see in if a TimeoutException is thrown");


        wait.until(AdditionalConditions.angularHasFinishedProcessing());
    }
}
