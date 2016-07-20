package com.google;

import com.google.config.DriverType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.google.config.DriverType.FIREFOX;

/**
 * Created by Santi on 7/15/2016.
 */
public class WebDriverThread {

    private WebDriver webdriver;
    private DriverType selectedDriverType;

    private final DriverType defaultDriverType = FIREFOX;
    private final String browser = System.getProperty("browser").toUpperCase();
    private final String operatingSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch");
    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");

    public WebDriver getDriver() throws Exception {
        if(null == webdriver) {
            selectedDriverType = determineEffectiveDriverType();
            DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities();
            instantiateWebDriver(desiredCapabilities);
        }

        return webdriver;
    }

    public void quitDriver() {
        if(null != webdriver) {
            webdriver.quit();
            webdriver = null;
        }
    }

    private DriverType determineEffectiveDriverType() {
        DriverType driverType = defaultDriverType;

        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.printf("No driver specified, defaulting to '" + driverType + "'...");
        }

        return driverType;
    }

    private void instantiateWebDriver (DesiredCapabilities desiredCapabilities) throws MalformedURLException {
        System.out.println(" ");
        System.out.println("Current Operating System: " + operatingSystem);
        System.out.println("Current Architecture: " + systemArchitecture);
        System.out.println("Current Browser Selection: " + selectedDriverType);
        System.out.println(" ");

        if(useRemoteWebDriver) {
            URL seleniumGridURL = new URL(System.getProperty("seleniumGridURL"));
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }

            webdriver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
        }

        webdriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
    }
}
