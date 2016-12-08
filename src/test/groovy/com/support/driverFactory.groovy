package com.support

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.safari.SafariDriver

import java.util.concurrent.TimeUnit

/**
 * Created by Ania on 2016-12-06.
 */
class driverFactory {
    def static getDriver(String driver) {
        driver = driver.toLowerCase()
        def configuredDriver
        switch (driver) {
            case "firefox": return new FirefoxDriver()
                //TODO: configure me
                break
            case "safari": return new SafariDriver()
                //TODO: configure me
                break
            case "ie": return new InternetExplorerDriver()
                //TODO: configure me
                break
            default: //TODO: location of the driver (parameter in a config)
                System.setProperty("webdriver.chrome.driver", "D:\\tools\\SeleniumDrivers\\chromedriver.exe")
                configuredDriver = new ChromeDriver()
                configuredDriver.manage().timeouts().implicitlyWait 16, TimeUnit.SECONDS
                return configuredDriver
                break
        }
    }
}
