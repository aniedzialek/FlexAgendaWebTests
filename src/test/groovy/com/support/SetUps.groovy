package com.support

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeClass

import static com.support.Helpers.logIn
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated

/**
 * Created by Ania on 2017-01-16.
 */
class SetUps {
    def driverFactory = new DriverFactory()
    WebDriver driver
    StringBuffer verificationErrors = new StringBuffer()

    @BeforeClass(alwaysRun = true)
    void setUp() {
        driver = driverFactory.getDriver(Browsers.CHROME)
        logIn(driver)

        //TODO: assert login successful
        waitForAgendasPageToLoad()
    }

    @AfterTest(alwaysRun = true)
    void testTearDown() {
        //driver
    }

    @AfterClass(alwaysRun = true)
    void tearDown() {
        driver.quit()
        String verificationErrorString = verificationErrors.toString()
        if ("" != verificationErrorString) {
            fail(verificationErrorString)
        }
    }

    def waitForAgendasPageToLoad() {
        try{
            new WebDriverWait(driver, 1)
                    .until(presenceOfElementLocated(By.cssSelector("agendas-list")))
        }
        catch(e)
        {
            println("---------- We have a problem: \n" + e)
            Assert.fail()
        }
    }
}
