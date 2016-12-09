package com.selenium

import com.support.Browsers
import com.support.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test

import static com.support.Helpers.logIn
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import static org.testng.Assert.fail
/**
 * Created by Ania on 2016-12-09.
 */
class BasicTests {
    WebDriver driver
    StringBuffer verificationErrors = new StringBuffer()

    @BeforeSuite(alwaysRun = true)
    void setUp() {
        driver = DriverFactory.getDriver(Browsers.CHROME)
    }

    @Test
    void agendasPageDisplayedWhenEmpty() {
        logIn(driver)

        if(driver.findElements(By.id("agenda")).size() != 0) {
            removeAllAgendas()
        }

        try {
            new WebDriverWait(driver, 1)
                    .until presenceOfElementLocated(By.id("agendaAddNew"))
        }
        catch (e) {
            println("---------- We have a problem: " + e)
            Assert.fail()
        }

        //also valid fail when Times Out
    }

    @AfterMethod(alwaysRun = true)
    void tearDownAfterTest() {
        removeAllAgendas()

        String verificationErrorString = verificationErrors.toString()
        if ("" != verificationErrorString) {
            fail(verificationErrorString)
        }
    }

    @AfterSuite(alwaysRun = true)
    void tearDown() {
        driver.quit()
    }
}

/*
BASIC
logInToFlexAgendaSuccessful //The same as display flexagenda page with/without agendas successful
logOutFromFlexAgendaSuccessful
changeUserInOneBrowserSession
autoLogIn	//cookies? FF-yes, Chrome-no
 */
