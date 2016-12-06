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
import sun.reflect.generics.reflectiveObjects.NotImplementedException

import java.util.concurrent.TimeUnit

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import static org.testng.Assert.fail

/**
 * Created by Ania on 2016-12-06.
 */
class manipulatingTasks {
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
    void displayListOfManyTasks() {
        logIn()
        waitForAgendasPageToLoad()
        addTasksToExistingAgenda(100)

        ///for howMany
        Assert.assertNotNull(driver.findElement(By.cssSelector("agendas-list > ul > li")))
    }

    @Test
    void displayAgendaWithTasks() {
        Assert.assertNotNull(driver.findElement(By.cssSelector("agendas-list > ul > li")))
    }

    @AfterClass(alwaysRun = true)
    void tearDown() {
        driver.quit()
        String verificationErrorString = verificationErrors.toString()
        if ("" != verificationErrorString) {
            fail(verificationErrorString)
        }
    }

    private void addTasksToExistingAgenda(int tasksCount) {
        //for tasksCount

        //name
        //other
    }

    private void deleteAllTasks() {
        throw NotImplementedException()
    }

    private void logIn() {
        driver.get(baseUrl)

        driver.findElement(By.cssSelector("#md-input-0-input")).sendKeys("anna.bckwabb@gmail.com")
        driver.findElement(By.cssSelector("#md-input-1-input")).sendKeys("T3st3r!")
        driver.findElement(By.cssSelector(".md-primary")).click()
    }

    private void waitForAgendasPageToLoad() {
        try{
            new WebDriverWait(driver, 1)
                    .until(presenceOfElementLocated(By.cssSelector("agendas-list")))
        }
        catch(e)
        {
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
TASKS
agendaWithManyTasks(int count)
createTaskWithoutName
createTaskWithoutDescription
createTaskWithoutDuration
removeOneTaskFromManyFromAgenda
removeAllTasksFromAgenda
moveTaskDown
moveTaskUp
fillInTaskDetails
updateTasksName
updateTasksNameToEmpty
updateTasksNameWithSpecialCharacters
updateTasksDuration(Time time)
updateTasksDescription
markTaskAsDone
unmarkTaskAsDone
addTaskWithTaskPlus
addTaskWithAgendaTaskPlus
*/