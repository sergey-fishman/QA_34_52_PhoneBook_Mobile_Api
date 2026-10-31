package screens;

import dto.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import utils.TestNGListener;

@Listeners(TestNGListener.class)

public class RegistrationLoginScreen extends BaseScreen{
    public RegistrationLoginScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputEmail")
    WebElement inputUsername;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputPassword")
    WebElement inputPassword;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/regBtn")
    WebElement registrationBtn;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/loginBtn")
    WebElement loginBtn;

    public void clickBtnRegistration(){
        clickWait(registrationBtn);
    }

    public void clickBtnLogin(){
        clickWait(loginBtn);
    }

    public void typeForm(User user){
        type(inputUsername, user.getUsername());
        type(inputPassword, user.getPassword());
    }

    public boolean isUserFormDisplayed() {
        return inputUsername.isDisplayed()
                && inputPassword.isDisplayed()
                && registrationBtn.isDisplayed()
                && loginBtn.isDisplayed();
    }
}
