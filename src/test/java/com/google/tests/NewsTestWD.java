package com.google.tests;

import com.google.DriverFactory;
import com.google.page_objects.HomePage;
import com.google.page_objects.NewsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Santi on 7/14/2016.
 */
public class NewsTestWD extends DriverFactory {

    private void googleExampleThatSearchesFor(final String searchString) throws Exception {

        WebDriver driver = getDriver();

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

    @Test
    public void goToTheNewsPage() throws Exception {

        WebDriver driver = getDriver();

        driver.get("http://www.velocitypartners.net/");

        WebElement newsLink = driver.findElement(HomePage.newsLinkLocator);

        newsLink.click();

        WebElement newsHeading = driver.findElement(NewsPage.heading);

        assertThat(newsHeading.getText(), is(equalTo("NEWS")));
    }
}
