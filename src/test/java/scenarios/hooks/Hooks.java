package scenarios.hooks;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import page.nativePages.ContactManagerPage;
import setup.DriverSetup;

public class Hooks extends DriverSetup {

    protected ContactManagerPage contactManagerPage;

    protected Hooks(String type) {
        super(type);
    }

    @BeforeSuite(description = "Prepare driver to run test(s)", groups = {"native", "web"})
    public void setUp() throws Exception {
        prepareDriver();
        System.out.println("Driver prepared");
        contactManagerPage = new ContactManagerPage(driver());
    }

    @AfterSuite(description = "Close driver on all tests completion", groups = {"native", "web"})
    public void tearDown() throws Exception {
        driver().closeApp();
        System.out.println("App closed");
    }
}
