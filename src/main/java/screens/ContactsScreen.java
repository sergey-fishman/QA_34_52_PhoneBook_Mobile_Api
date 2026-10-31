package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactsScreen extends BaseScreen{
    public ContactsScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    WebElement emptyText;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    WebElement addContactFAB;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Contact was added!\"]")
    WebElement widgetToastAdd;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text=\"Contact was updated!\"]")
    WebElement widgetToastUpdate;
    @AndroidFindBy(accessibility = "More options")
    WebElement optionsMenu;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.sheygam.contactapp:id/title\" and @text=\"Logout\"]")
    WebElement btnLogout;

    @AndroidFindBy(id="com.sheygam.contactapp:id/rowName")
    List<WebElement> rowNamesList;

    public List<String> getNamesList(){
        List<String> names = new ArrayList<>();
        for (WebElement e : rowNamesList){
            names.add(e.getText());
        }
        return names;
    }

    public void clickOptionsMenu() {
        optionsMenu.click();
    }

    public void clickBtnLogout() {
        btnLogout.click();
    }

    public boolean validateEmptyContactsText(String text) {
        return isTextInElementPresent(emptyText, text);
    }

    public boolean validateWidgetAddContactText(String text) {
        return isTextInElementPresent(widgetToastAdd, text);
    }

    public boolean validateWidgetUpdateContactText(String text) {
        return isTextInElementPresent(widgetToastUpdate, text);
    }

    public void updateContactSwipe(){
        swipe(width/10*9, height/6,width/10, height/6);
    }

    public void deleteContactSwipe(){
        swipe(width/10, height/6,width/10*9, height/6);
    }

    public void clickAddContactBtn(){
        clickWait(addContactFAB);
    }

    public boolean isAddContactBtnDisplayed(){
        return addContactFAB.isDisplayed();
    }
}
