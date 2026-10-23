package mobile;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ContactsScreen;
import screens.RegistrationLoginScreen;
import screens.SplashScreen;
import utils.UserFactory;

public class RegistrationTests extends TestBase {

    @Test
    public void registrationPositiveTest(){
        new SplashScreen(driver).waitForRegistrationScreen();
        RegistrationLoginScreen screen = new RegistrationLoginScreen(driver);
        User user = UserFactory.positiveUser();
        screen.typeForm(user);
        screen.clickBtnRegistration();
        Assert.assertTrue(new ContactsScreen(driver).validateEmptyText
                ("No Contacts. Add One more!"), "No Contacts. Add One more!");
    }
}
