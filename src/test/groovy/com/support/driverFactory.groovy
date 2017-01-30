package com.support

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.safari.SafariDriver

import java.util.concurrent.TimeUnit

import static com.support.Browsers.*
/**
 * Created by Ania on 2016-12-06.
 */
class DriverFactory {
    def getDriver(browserName) {
        browserName = browserName.toLowerCase()
        def driver
        switch (browserName) {
            case FIREFOX: return new FirefoxDriver()
                //TODO: implement me
                break
            case SAFARI: return new SafariDriver()
                //TODO: implement me
                break
            case IE: return new InternetExplorerDriver()
                //TODO: implement me
                break
            default: //TODO: location of the driver (parameter in a config)
                System.setProperty("webdriver.chrome.driver", "D:\\tools\\SeleniumDrivers\\chromedriver.exe")
                driver = new ChromeDriver()
                driver.manage().timeouts().implicitlyWait 16, TimeUnit.SECONDS
                return driver
                break
        }
    }
}
