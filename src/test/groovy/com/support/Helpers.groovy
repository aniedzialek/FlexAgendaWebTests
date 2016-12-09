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
    def static String BASE_URL = "http://localhost:4200/"

    def static void logIn(driver) {
        driver.get(BASE_URL)

        driver.findElement(By.cssSelector("#md-input-0-input")).sendKeys("anna.bckwabb@gmail.com")  //FIXME: extract
        driver.findElement(By.cssSelector("#md-input-1-input")).sendKeys("T3st3r!")                 //FIXME: extract
        driver.findElement(By.id("login")).click()

        driver.findElement(By.id("agendasListShow")).click()
    }

    def static void addNewAgendas(agendasCount, WebDriver driver) {
        agendasCount.times {
            driver.findElement(By.id("agendaAddNew")).click()
        }

        println("Added " + agendasCount + " agendas")    //TODO: added == agendaCount is success
                                                        //TODO: (can be checked by counting agendas before and after)
    }

    def static void waitForAgendasPageToLoad(driver) {
        try {
            new WebDriverWait(driver, 1)
                    .until presenceOfElementLocated(By.id("agendaAddNew"))
        }
        catch (e) {
            println("---------- We have a problem: " + e)
            Assert.fail()
        }
    }

    def static boolean isElementPresent(By by, driver) {
        try {
            driver.findElement(by)
            return true
        } catch (NoSuchElementException e) {
            return false
        }
    }
}
