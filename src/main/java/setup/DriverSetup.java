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

    protected static String AUT; //(mobile) app under testing
    protected static String SUT; // site under testing
    protected static String TEST_PLATFORM;
    protected static String DRIVER;
    protected static String DEVICE_NAME;
    protected static String UDID;
    protected static String APP_PACKAGE;
    protected static String APP_ACTIVITY;
    private static String type;


    //Constructor initializes properties on driver creation
    /**
     * Set appropriate capabilities to Appium driver on platform and application
     * @param type
     * @throws IOException
     */
    protected DriverSetup(String type) {
        this.type = type;
    }

    protected void setPropertiesAndPrepareDriver(String type) throws Exception {
        setProperties(type);
        prepareDriver();
    }

    protected void setPropertiesAndPrepareDriver() throws Exception {
        setProperties(this.type);
        prepareDriver();
    }

    private void setProperties(String type) throws Exception {
        AUT = getProp(type,"aut");
        String t_sut = getProp(type,"sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = getProp(type,"platform");
        DRIVER = getProp(type,"driver");
        DEVICE_NAME = getProp(type,"deviceName");
        UDID = getProp(type, "udid");
        APP_PACKAGE = getProp(type, "appPackage");
        APP_ACTIVITY = getProp(type, "appActivity");
    }

    private void prepareDriver() throws Exception {
        capabilities = new DesiredCapabilities();
        String browserName;

        switch(TEST_PLATFORM) {
            case "Android":
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default: throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);
        capabilities.setCapability(MobileCapabilityType.UDID, UDID);
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);


        // Setup type of application : mobile, web (or hybrid)
        if (AUT != null && SUT == null) {
            //Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability("appActivity", APP_ACTIVITY);
            capabilities.setCapability("appPackage", APP_PACKAGE);
            //capabilities.setCapability("autoLaunch", "true");
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
    protected AppiumDriver driver() throws Exception {
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
