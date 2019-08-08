package scenarios.hooks;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import page.nativePages.ContactManagerPage;
import setup.DriverSetup;

public class Hooks extends DriverSetup {

    protected ContactManagerPage contactManagerPage;

    protected Hooks(String type) {
        super(type);
    }

    @BeforeSuite(description = "Prepare driver to run test(s)")
    public void setUp() throws Exception {
        setPropertiesAndPrepareDriver();
        System.out.println("Driver prepared");
        contactManagerPage = new ContactManagerPage(driver());
    }

    @BeforeGroups(description = "Prepare driver to run test(s)", groups = "native")
    public void setUpNative() throws Exception {
        setPropertiesAndPrepareDriver("native");
        System.out.println("Driver prepared");
        contactManagerPage = new ContactManagerPage(driver());
    }

    @BeforeGroups(description = "Prepare driver to run test(s)", groups = "web")
    public void setUpWeb() throws Exception {
        setPropertiesAndPrepareDriver("web");
        System.out.println("Driver prepared");
    }

    @AfterSuite(description = "Close driver on all tests completion", groups = {"native", "web"})
    public void tearDown() throws Exception {
        driver().closeApp();
        System.out.println("App closed");
    }
}
