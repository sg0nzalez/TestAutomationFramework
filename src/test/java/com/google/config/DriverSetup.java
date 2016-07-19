package com.google.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by Santi on 7/15/2016.
 */
public interface DriverSetup {

    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities();
}
