package mobile;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactsScreen;
import screens.RegistrationLoginScreen;
import screens.SplashScreen;
import static utils.PropertiesReader.*;

public class LoginTests extends TestBase {
    RegistrationLoginScreen registrationLoginScreen;
    User user;

    @BeforeMethod
    public void setRegistrationLoginScreen() {
        new SplashScreen(driver).waitForRegistrationScreen();
        registrationLoginScreen = new RegistrationLoginScreen(driver);
    }

    @Test
    public void loginPositiveTest(){
        user = User.builder()
                .username(getProperty("base.properties", "username"))
                .password(getProperty("base.properties", "password"))
                .build();
        registrationLoginScreen.typeForm(user);
        registrationLoginScreen.clickBtnLogin();
        Assert.assertTrue(new ContactsScreen(driver).isAddContactBtnDisplayed(),
                "AddContactBtnDisplayed");
    }
}
