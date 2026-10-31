package mobile;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import screens.ContactsScreen;
import screens.RegistrationLoginScreen;
import screens.SplashScreen;
import static utils.PropertiesReader.*;

public class LoginTests extends TestBase {
    RegistrationLoginScreen registrationLoginScreen;
    User user;
    SoftAssert softAssert = new SoftAssert();

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
                "AddContact Btn is Displayed");
    }

    @Test
    public void logoutPositiveTest() {
        user = User.builder()
                .username(getProperty("base.properties", "username"))
                .password(getProperty("base.properties", "password"))
                .build();
        registrationLoginScreen.typeForm(user);
        registrationLoginScreen.clickBtnLogin();
        ContactsScreen contactsScreen = new ContactsScreen(driver);
        softAssert.assertTrue(contactsScreen.isAddContactBtnDisplayed(),
                "AddContact Btn is Displayed");
        contactsScreen.clickOptionsMenu();
        contactsScreen.clickBtnLogout();
        softAssert.assertTrue(registrationLoginScreen.isUserFormDisplayed(),
                "User form is displayed");
        softAssert.assertAll();
    }
}
