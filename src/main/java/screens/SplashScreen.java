package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SplashScreen extends BaseScreen{
    public SplashScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/version_text")
    WebElement versionText;

    public boolean validateVersionText(String text){
        return isTextInElementPresent(versionText, text);
    }

    public void waitForRegistrationScreen(){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.presenceOfElementLocated
                            (By.id("com.sheygam.contactapp:id/action_bar")));
        } catch (TimeoutException e) {
            throw new AssertionError("Registration screen did not open within 10s", e);
        }
    }
}
