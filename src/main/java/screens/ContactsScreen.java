package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ContactsScreen extends BaseScreen{
    public ContactsScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    WebElement emptyText;

    public boolean validateEmptyText(String text) {
        return isTextInElementPresent(emptyText, text);
    }
}
