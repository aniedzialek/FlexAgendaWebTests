package com.selenium

import com.support.Browsers
import com.support.DriverFactory
import org.apache.commons.lang3.NotImplementedException
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test

import static com.support.Helpers.addNewAgendas
import static com.support.Helpers.logIn
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import static org.testng.Assert.fail
/**
 * Created by Ania on 2016-12-06.
 */

class ManipulatingAgendas {
    WebDriver driver
    StringBuffer verificationErrors = new StringBuffer()

    @BeforeSuite(alwaysRun = true)
    void setUp() {
        driver = DriverFactory.getDriver(Browsers.CHROME)
        logIn(driver)
    }

    @Test
    void agendaDisplayedWhenEmpty() {
        addNewAgendas(1, driver)

        //Assert.   //TODO
        throw new NotImplementedException()
    }

    @Test
    void agendaDisplayedWithTasks() {
        addNewAgendasWithTasks() //TODO

        throw new NotImplementedException()
    }

    @Test
    void displayExistingAgendas() {
        addNewAgendas(1, driver)

        def agendasFound
        try {
            agendasFound = driver.findElements(By.id("agenda")).size()
        }
        catch(e) {
            println("---------- We have a problem: " + e)
            Assert.fail()
        }

        println("---------- Agendas found: " + agendasFound)

        Assert.assertEquals(1, agendasFound)
    }

    @Test
    // @Parameters("count")
    // https://www.tutorialspoint.com/testng/testng_parameterized_test.htm  //count=1
    void deleteExistingAgenda() {
        addNewAgendas(1, driver)

        def agendasCountBeforeDelete = driver.findElements(By.id("agenda")).size()
        println("Agendas found before delete: " + agendasCountBeforeDelete)

        driver.findElement(By.id("agendaDelete")).click()
        driver.findElement(By.id("confirmDelete")).click()
        println("Deleted 1 agenda")

        def agendasCountAfterDelete = driver.findElements(By.id("agenda")).size()
        println("Agendas found after delete: " + agendasCountAfterDelete)

        Assert.assertEquals(agendasCountBeforeDelete-1, agendasCountAfterDelete)
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

    void removeAllAgendas() { //FIXME: can't click the confirm button?
        def agendasCount = driver.findElements(By.id("agenda")).size()

        agendasCount.times {
            driver.findElement(By.id("agendaDelete")).click()

            waitForDialogButtonClickable()
            driver.findElement(By.id("confirmDelete")).click()
        }

        //println("Removed " + agendasCount + " agendas")
    }

    void waitForDialogButtonClickable() { //FIXME: clicking the wrong thing; debug with Selenium IDE. CSS selector confirms id
        //Works sometimes
        try {
            new WebDriverWait(driver, 1)
                    .until presenceOfElementLocated(By.id("confirmDelete"))
        }
        catch (e) {
            println("---------- We have a problem: " + e)
            Assert.fail()
        }
    }
}

/*
AGENDAS
//agendasListPageDisplayedWhenEmpty
v agendaDisplayedWhenEmpty
//addManyEmptyAgendas
/addManyAgendasWithTasks

v deleteExistingAgendas(1)  //parameter int count
//addAgendaToExistingList
//moveAgendasAround
updateAgendasTitle      //?
updateAgendasStartTime  //?
 */
