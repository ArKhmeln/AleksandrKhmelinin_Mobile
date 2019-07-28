package scenarios.nativeTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import scenarios.hooks.Hooks;

import static enums.ContactManagerSelectors.*;
import static org.testng.Assert.assertTrue;

@Test(groups = "native")
public class ContactManagerTest extends Hooks {

   ContactManagerTest() throws Exception {
       super("native");
   }

    @Test(description = "Check fields are displayed in ContactManager")
    public void simplestTest() throws Exception {
        // I have doubts about decisions below (enums), but using Page Object Pattern just for those locators would probably be strange
        driver().findElement(By.id(ADD_BUTTON.getSelector())).click();
        // Check fields of "AddContact"
        assertTrue(driver().findElement(By.id(ADD_CONTACT_TITLE.getSelector())).isDisplayed());
        assertTrue(driver().findElement(By.id(TARGET_ACCOUNT_FIELD.getSelector())).isDisplayed());
        assertTrue(driver().findElement(By.id(CONTACT_NAME_FIELD.getSelector())).isDisplayed());
        assertTrue(driver().findElement(By.id(CONTACT_PHONE_FIELD.getSelector())).isDisplayed());
        System.out.println("Simplest Appium test done");
    }
}
