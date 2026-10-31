package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class DialogScreen extends BaseScreen{
    public DialogScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "android:id/button1")
    WebElement btnYes;
    @AndroidFindBy(id = "android:id/button2")
    WebElement btnCancel;

    @AndroidFindBy(id = "android:id/alertTitle")
    WebElement alertTitle;
    @AndroidFindBy(id = "android:id/message")
    WebElement alertMessage;

    public boolean validateTextAlertTitle(String text) {
        return isTextInElementPresent(alertTitle, text);
    }

    public boolean validateTextAlertMessage(String text) {
        return isTextInElementPresent(alertMessage, text);
    }

    public void clickBtnYes(){
        btnYes.click();
    }
}
