package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Driver extends TestProperties {

    protected AppiumDriver driver; //allows us to work with Android and iOS both
    protected DesiredCapabilities capabilities;
    protected WebDriverWait wait; //selenium structure to work with timeouts

    //properties to be read (we hardcoded until using them)
    protected String AUT; //(mobile) app under testing
    protected String SUT; // site under testing
    protected String TEST_PLATFORM;
    protected String DRIVER;
    protected String DEVICE_NAME;

    //Constructor initializes properties on driver creation
    protected Driver() throws IOException {
        AUT = getProp("aut");
        String t_sut = getProp("sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = getProp("platform");
        DRIVER = getProp("driver");
        DEVICE_NAME = getProp("deviceName");
    }

    protected void prepareDriver() throws Exception {
        capabilities = new DesiredCapabilities();
        String browserName;

        if (TEST_PLATFORM.equals("Android")) {
            browserName = "Chrome";
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        } else if (TEST_PLATFORM.equals("iOS")) {
            browserName = "Safari";
        } else {
            throw new Exception("Unknown mobile platform");
        }

        /*switch(TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
                browserName = "Chrome";
                break;
            case "iOS":
                //no simulator -__- because it's a billet
                browserName = "Safari";
                break;
            defaut: throw new Exception("Unknown mobile platform");
        }*/
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
        driver = new AppiumDriver(new URL(DRIVER), capabilities);

        // Set an Object to handle timeouts
        wait = new WebDriverWait(driver, 10);
    }
}
