package scenarios.hooks;

import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.nativePages.ContactManagerPage;
import setup.DriverSetup;

@Test(groups = {"native", "web"})
public class Hooks extends DriverSetup {

    protected ContactManagerPage contactManagerPage;
    protected AppiumDriver driver;

    protected Hooks(String type) throws Exception {
        super(type);
    }

    @BeforeClass(description = "Prepare driver to run test(s)", groups = {"native", "web"})
    public void setUp() throws Exception {
        prepareDriver();
        System.out.println("Driver prepared");
        contactManagerPage = new ContactManagerPage(driver());
    }

    @AfterClass(description = "Close driver on all tests completion", groups = {"native", "web"})
    public void tearDown() throws Exception {
        driver().closeApp();
        System.out.println("App closed");
    }
}
