package page.nativePages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

public class ContactManagerPage {

    public ContactManagerPage(AppiumDriver driver) throws Exception{
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Getter
    @AndroidFindBy(id = "com.example.android.contactmanager:id/addContactButton")
    AndroidElement addButton;

    @Getter
    @AndroidFindBy(id = "android:id/title")
    AndroidElement addContactTitle;

    @Getter
    @AndroidFindBy(id = "com.example.android.contactmanager:id/accountSpinner")
    AndroidElement targetAccountField;

    @Getter
    @AndroidFindBy(id = "com.example.android.contactmanager:id/contactNameEditText")
    AndroidElement ContactNameEditField;

    @Getter
    @AndroidFindBy(id = "com.example.android.contactmanager:id/contactPhoneEditText")
    AndroidElement contactPhoneEditField;
}