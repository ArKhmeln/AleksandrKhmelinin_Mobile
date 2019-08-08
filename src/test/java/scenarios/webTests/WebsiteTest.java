package scenarios.webTests;

import enums.WebsiteData;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.hooks.Hooks;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.*;

@Test(groups = "web")
public class WebsiteTest extends Hooks {

    WebsiteTest() {
        super("web");
    }

    @Test(description = "Open website and check if it's opened")
    public void webTest() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT+"/")); //it's a form of assert as well
        //check Title
        assertEquals(driver().getTitle(), WebsiteData.WEBSITE_TITLE.getText());
        //check http status code
        URL url = new URL(WebsiteData.URL.getText());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        assertEquals(connection.getResponseCode(),200);
        System.out.println("Site opening done");
    }
}
