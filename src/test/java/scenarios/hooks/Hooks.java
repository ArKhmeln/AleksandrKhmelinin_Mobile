package scenarios.hooks;

import org.testng.annotations.*;
import setup.DriverSetup;

public class Hooks extends DriverSetup {

    protected Hooks(String type) {
        super(type);
    }

    @BeforeSuite(description = "Prepare driver to run test(s)")
    public void setUp() throws Exception {
        setPropertiesToPrepareDriver();
        System.out.println("Driver prepared");
    }

    @BeforeGroups(description = "Prepare driver to run test(s)", groups = "native")
    public void setUpNative() throws Exception {
        setPropertiesToPrepareDriver("native");
        System.out.println("Driver prepared");
    }

    @BeforeGroups(description = "Prepare driver to run test(s)", groups = "web")
    public void setUpWeb() throws Exception {
        setPropertiesToPrepareDriver("web");
        System.out.println("Driver prepared");
    }

    @AfterSuite(description = "Close driver on all tests completion", groups = {"native", "web"})
    public void tearDown() throws Exception {
        driver().quit();
        System.out.println("Driver closed");
    }
}
