package com.selenium

import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

import java.util.concurrent.TimeUnit

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import static org.testng.Assert.fail

class manipulatingAgendas {
    WebDriver driver
    String baseUrl
    boolean acceptNextAlert = true
    StringBuffer verificationErrors = new StringBuffer()

    @BeforeClass(alwaysRun = true)
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\tools\\SeleniumDrivers\\chromedriver.exe")
        driver = new ChromeDriver()
        baseUrl = "http://localhost:4200/"
        driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS)
    }

    @Test
    void displayExistingAgendas() {
        logIn()
        waitForAgendasPageToLoad()
        addNewAgendas(1)        //may not be needed
        //assert at least 1 agenda displayed
    }

    @Test
    // @Parameters("count")      // https://www.tutorialspoint.com/testng/testng_parameterized_test.htm  //count=1
    void deleteExistingAgendas(int count) {
        driver.findElement(By.cssSelector(".trash.md-accent")).click()
    }

    @AfterClass(alwaysRun = true)
    void tearDown() {
        driver.quit()
        String verificationErrorString = verificationErrors.toString()
        if ("" != verificationErrorString) {
            fail(verificationErrorString)
        }
    }

    private void addNewAgendas(count) {
        driver.findElement(By.cssSelector(".root > .labels > .md-primary")).click()
    }

    private void logIn() {
        driver.get(baseUrl)

        driver.findElement(By.cssSelector("#md-input-0-input")).sendKeys("anna.bckwabb@gmail.com")
        driver.findElement(By.cssSelector("#md-input-1-input")).sendKeys("T3st3r!")
        driver.findElement(By.cssSelector(".md-primary")).click()
    }

    private void waitForAgendasPageToLoad() {
        try {
            new WebDriverWait(driver, 1)
                    .until(presenceOfElementLocated(By.cssSelector("agendas-list")))
        }
        catch (e) {
            println("---------- We have a problem: " + e)
            Assert.fail()
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by)
            return true
        } catch (NoSuchElementException e) {
            return false
        }
    }
}

/*
AGENDAS
//agendasListPageDisplayedWhenEmpty
agendaDisplayedWhenEmpty
//addManyEmptyAgendas
/addManyAgendasWithTasks

v deleteExistingAgendas(1)  //parameter int count
//addAgendaToExistingList
//moveAgendasAround
updateAgendasTitle      //?
updateAgendasStartTime  //?


TIME CALCULATIONS/DISPLAY
setStartTimeToNothing
setStartTimeToZero
setStartTimeToLetter
setStartTimeToSpecialCharacter
setStartTimeTo2400


BASIC
logInToFlexAgendaSuccessful
logOutFromFlexAgendaSuccessful
changeUserInOneBrowserSession
autoLogIn	//cookies? FF-yes, Chrome-no




 */
