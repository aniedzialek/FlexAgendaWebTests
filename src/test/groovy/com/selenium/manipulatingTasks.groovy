package com.selenium

import com.support.Agendas
import com.support.Helpers
import com.support.SetUps
import org.openqa.selenium.By
import org.testng.Assert
import org.testng.annotations.Test
/**
 * Created by Ania on 2016-12-06.
 */
class ManipulatingTasks extends SetUps {
    @Test
    void addTasksToEmptyAgenda() {
        Helpers.openPage(Agendas.agendaEmpty, driver)

        def taskCount = findAllTasksInAgenda()
       // Assert.assertEquals(taskCount, [])

        addTasksToExistingAgenda(1)

        def tasksAfterAddingCount = findAllTasksInAgenda().size()

        //cleanup
        deleteAllTestTasks()

        Assert.assertEquals(tasksAfterAddingCount, 1 /*+1 for task in second agenda*/)
    }

    @Test
    void displayAgendaWithOneTask() {
        driver.get(Helpers.BASE_URL + "\\" + Agendas.agendaWithOneTask)

        def agendasCount

        try {
            agendasCount = driver.findElements(By.id("taskAddAbove")).size()
        }
        catch(e) {
            println("---------- We have a problem: \n" + e)
            Assert.fail()
        }

        Assert.assertEquals(agendasCount, 1)
    }

    @Test
    void tasksHaveAllData() {
        def url = Helpers.BASE_URL + "\\" + Agendas.agendaWithManyTasks
        driver.get(url)

        def tasksFound = findAllTasksInAgenda()

        Assert.assertEquals(tasksFound.size(), 3)
    }

    def addTasksToExistingAgenda(tasksCount) {
        tasksCount.times {
            driver.findElement(By.id("taskAddNewLast")).click()
        }
    }

    def deleteAllTestTasks() {
        while(findTask() != []) {
            findTask().click()
            Helpers.confirmDelete(driver)
        }

        Assert.assertEquals(tasks, [])
    }

    def findTask() {
        return driver.findElement(By.id("taskDelete"))
    }

//    def findNewTask() {
//        def task = driver.findElement(By.xpath("//*[@ng-reflect-value='NEWTITLE']/preceding-sibling::*[@id='taskDelete']"))
//
//        return task
//    }

    def findAllTasksInAgenda() {
        return driver.findElements(By.id("taskAddAbove"))
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