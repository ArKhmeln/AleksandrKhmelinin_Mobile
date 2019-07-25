package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;

public class FirstSimpleTest extends DriverSetup {

    FirstSimpleTest() throws IOException {
        super();
    }

    @BeforeClass(description = "Prepare driver to run test(s)")
    public void setUp() throws Exception {
        //prepareAndroidNative();
        prepareAndroidWeb();
        //prepareDriver();
    }

    @Test(description = "Click on button 'Add contact'")
    public void simplestTest() {
        driver.findElement(By.id("com.example.android.contactmanager:id/addContactButton")).click();
        System.out.println("Simplest Appium test done");
    }

    @Test(description = "Open website")
    public void webTest() throws InterruptedException {
        driver.get("https://www.iana.org/");
        Thread.sleep(5000);
        System.out.println("Site opening done");
    }

    /*@Test(description = "Open website")
    public void webTest() throws InterruptedException {
        driver.get(SUT);
        wait.until(ExpectedConditions.urlToBe(SUT+"/"));
        //it's a form of assert as well
        System.out.println("Site opening done");
    }*/

    @AfterClass(description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driver.quit();
    }
}
