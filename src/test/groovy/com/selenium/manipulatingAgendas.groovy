package com.selenium

import com.support.SetUps
import org.apache.commons.lang3.NotImplementedException
import org.openqa.selenium.By
import org.testng.Assert
import org.testng.annotations.Test

import static com.support.Agendas.agendaEmpty
import static com.support.Helpers.openPage
import static com.support.Helpers.waitForDialogButtonClickable
/**
 * Created by Ania on 2016-12-06.
 */

class ManipulatingAgendas extends SetUps {
    @Test
    void agendaDisplayedWhenEmpty() {
        openPage(agendaEmpty, driver)
        //Assert empty agenda displayed on the list   //TODO

        //assert one agenda only
        //assert no tasks
        Assert.assertNotNull(driver.findElement(By.tagName("agenda")))
        //TODO
    }

    @Test(enabled=false)
    void agendaDisplayedWithTasks() {
        //Assert agenda with tasks displayed

        throw new NotImplementedException()
    }

    @Test(enabled=false)
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

    @Test(enabled=false)
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

    def removeAllAgendas() { //FIXME: can't click the confirm button?
        def agendasCount = driver.findElements(By.id("agenda")).size()

        agendasCount.times {
            driver.findElement(By.id("agendaDelete")).click()

            waitForDialogButtonClickable()
            driver.findElement(By.id("confirmDelete")).click()
        }

        //println("Removed " + agendasCount + " agendas")
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
