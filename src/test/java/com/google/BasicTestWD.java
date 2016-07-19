package com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by Santi on 7/14/2016.
 */
public class BasicTestWD extends DriverFactory {

    private void googleExampleThatSearchesFor(final String searchString) throws Exception {

        WebDriver driver = DriverFactory.getDriver();

        driver.get("http://www.google.com");

        WebElement searchField = driver.findElement(By.name("q"));

        searchField.clear();

        searchField.sendKeys(searchString);

        System.out.println("Page title is: " + driver.getTitle());

        searchField.submit();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
            }
        });

        System.out.println("Page title is: " + driver.getTitle());
    }

    @Test
    public void googlePenarolExample() throws Exception {
        googleExampleThatSearchesFor("Pe\u00f1arol");
    }

    @Test
    public void googleCampeonExample() throws Exception {
        googleExampleThatSearchesFor("Campe\u00f3n del Siglo");
    }

    @Test
    public void googleCraftBeerExample() throws Exception {
        googleExampleThatSearchesFor("Craft Beer");
    }

    @Test
    public void googleIndianFoodExample() throws Exception {
        googleExampleThatSearchesFor("Indian Food");
    }

    @Test
    public void googlePuntaDelEsteExample() throws Exception {
        googleExampleThatSearchesFor("Punta del Este");
    }

    @Test
    public void googlePuntaDelDiabloExample() throws Exception {
        googleExampleThatSearchesFor("Punta del Diablo");
    }
}
