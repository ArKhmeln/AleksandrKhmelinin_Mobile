package scenarios.hooks;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.DriverSetup;

@Test(groups = {"native", "web"})
public class Hooks extends DriverSetup {

    protected Hooks(String type) throws Exception {
        super(type);
    }

    @BeforeClass(description = "Prepare driver to run test(s)")
    public void setUp() throws Exception {
        prepareDriver();
        System.out.println("Driver prepared");
    }

    @AfterClass(description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driver().quit();
        System.out.println("Driver closed");
    }
}
