package com.selenium

import com.support.Browsers
import com.support.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import org.testng.annotations.Test
import sun.reflect.generics.reflectiveObjects.NotImplementedException

import static com.support.Helpers.addNewAgendas
import static com.support.Helpers.logIn
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated
import static org.testng.Assert.fail
/**
 * Created by Ania on 2016-12-06.
 */
class ManipulatingTasks {
    WebDriver driver
    StringBuffer verificationErrors = new StringBuffer()

    @BeforeSuite(alwaysRun = true)
    void setUp() {
        driver = DriverFactory.getDriver(Browsers.CHROME)
        logIn(driver)
        addNewAgendas(2, driver)
    }

    @Test
    void displayListOfManyTasks() {
        int taskCount = 10
        addTasksToExistingAgenda(taskCount)

        Assert.assertEquals(taskCount+1 /*+1 for task in second agenda*/, driver.findElements(By.id("taskAddAbove")).size())
        //TODO: mark one agenda and add+count tasks in that one.
    }

    @Test
    void displayAgendaWithTasks() {
        try {
            driver.findElement(By.id("taskAddAbove"))
        }
        catch(e) {
            println("---------- We have a problem: \n" + e)
            Assert.fail()
        }
    }

    @AfterSuite(alwaysRun = true)
    void tearDown() {
        driver.quit()
        String verificationErrorString = verificationErrors.toString()      //TODO: after test or after suite?
        if ("" != verificationErrorString) {
            fail(verificationErrorString)
        }
    }

    private void addTasksToExistingAgenda(tasksCount) {
        tasksCount.times {
            driver.findElement(By.id("taskAddAbove")).click()
        }
    }

    private void deleteAllTasks() {
        throw NotImplementedException()
    }

    private void waitForAgendasPageToLoad() {
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

/*
TASKS
v agendaWithManyTasks(int count)
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
addTaskWithTaskAbove
addTaskWithAgendaTaskPlus
*/