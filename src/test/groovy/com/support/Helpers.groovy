package com.support

import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
/**
 * Created by Ania on 2016-12-09.
 */
class Helpers {
    def static String BASE_URL = "http://localhost:4200/agendas"

    def static logIn(driver) {
        driver.get(BASE_URL)

        driver.findElement(By.cssSelector("#md-input-0-input")).sendKeys("anna.bckwabb@gmail.com")  //TODO: extract?
        driver.findElement(By.cssSelector("#md-input-1-input")).sendKeys("T3st3r!")                 //TODO: extract?
        driver.findElement(By.id("login")).click()

        waitForAgendasPageToLoad(driver)

        //driver.findElement(By.id("agendas-list"))
    }

    def static boolean isElementPresent(By by, driver) {
        try {
            driver.findElement(by)
            return true
        } catch (NoSuchElementException e) {
            return false
        }
    }

    def static addNewAgendas(agendasCount, WebDriver driver) {
        agendasCount.times {
            driver.findElement(By.id("agendaAddNew")).click()
        }

        println("Added " + agendasCount + " agendas")    //TODO: added == agendaCount is success
                                                        //TODO: (can be checked by counting agendas before and after)
    }

    def static waitForAgendasPageToLoad(driver) {
       waitForElement(By.tagName("agendas-list"), driver)
    }

    def static confirmDelete(driver)
    {
        driver.findElement(By.id("confirmDelete")).click
    }

    def static waitForDialogButtonClickable(driver) { //FIXME: element selected is covered by another
        //Works sometimes
        waitForElement(By.id("confirmDelete"), driver)
    }

    def static waitForElement(By by, driver) {
        try {
            new WebDriverWait(driver, 1)
                    .until presenceOfElementLocated(by)
        }
        catch (e) {
            println("---------- We have a problem: " + e)
            Assert.fail()
        }
    }

    def static openPage(relativeUrl, driver) {
        driver.get(BASE_URL + "\\" + relativeUrl)

        //TODO assert on page

    }
}
