package mobile;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactsScreen;
import screens.RegistrationLoginScreen;
import screens.SplashScreen;
import static utils.UserFactory.*;

public class RegistrationTests extends TestBase {
    RegistrationLoginScreen registrationLoginScreen;
    User user;

    @BeforeMethod
    public void setRegistrationLoginScreen() {
        new SplashScreen(driver).waitForRegistrationScreen();
        registrationLoginScreen = new RegistrationLoginScreen(driver);
    }

    @Test
    public void registrationPositiveTest(){
        user = positiveUser();
        registrationLoginScreen.typeForm(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new ContactsScreen(driver).validateEmptyText
                ("No Contacts. Add One more!"), "No Contacts. Add One more!");
    }
}
