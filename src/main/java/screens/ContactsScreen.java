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
    @AndroidFindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    WebElement addContactBtn;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Contact was added!\"]")
    WebElement widgetToast;

    public boolean validateEmptyText(String text) {
        return isTextInElementPresent(emptyText, text);
    }

    public boolean validateWidgetText(String text) {
        return isTextInElementPresent(widgetToast, text);
    }

    public void clickAddContactBtn(){
        clickWait(addContactBtn);
    }

    public boolean isAddContactBtnDisplayed(){
        return addContactBtn.isDisplayed();
    }
}
