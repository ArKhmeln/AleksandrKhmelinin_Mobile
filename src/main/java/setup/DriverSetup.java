package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DriverSetup extends TestProperties {

    private static AppiumDriver driverSingle = null; //allows us to work with Android and iOS both
    protected DesiredCapabilities capabilities;
    private static WebDriverWait waitSingle; //selenium structure to work with timeouts

    //properties to be read (we hardcoded until using them)
    protected String AUT; //(mobile) app under testing
    protected String SUT; // site under testing
    protected String TEST_PLATFORM;
    protected String DRIVER;
    protected String DEVICE_NAME;

    //Constructor initializes properties on driver creation
    protected DriverSetup(String type) throws IOException {
        AUT = getProp(type,"aut");
        String t_sut = getProp(type,"sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = getProp(type,"platform");
        DRIVER = getProp(type,"driver");
        DEVICE_NAME = getProp(type,"deviceName");
    }

    protected void prepareDriver() throws Exception {
        capabilities = new DesiredCapabilities();
        String browserName;

        switch(TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
                browserName = "Chrome";
                break;
            case "iOS":
                //no simulator, maybe in future -_-
                browserName = "Safari";
                break;
            default: throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application : mobile, web (or hybrid)
        if (AUT != null && SUT == null) {
            //Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        } else {
            throw new Exception("Unclear type of mobile app");
        }

        // Init driver for local Appium server with capabilities have been set
        if(driverSingle == null) {
            driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);
        }
        // Set an object to handle timeouts
        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driver(), 10);
        }
    }
    //
    public AppiumDriver driver() throws Exception {
        if (driverSingle == null) {
            prepareDriver();
        }
        return driverSingle;
    }

    // Set an Object to handle timeouts
    protected WebDriverWait driverWait() throws Exception {
        return waitSingle;
    }
}
