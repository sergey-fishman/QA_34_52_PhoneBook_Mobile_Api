package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class AlertScreen extends BaseScreen{
    public AlertScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "android:id/button1")
    WebElement btnYes;
    @AndroidFindBy(id = "android:id/button2")
    WebElement btnCancel;

    public void clickBtnYes(){
        btnYes.click();
    }
}
