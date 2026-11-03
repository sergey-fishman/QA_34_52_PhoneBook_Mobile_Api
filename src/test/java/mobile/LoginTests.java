package mobile;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import screens.ContactsScreen;
import screens.DialogScreen;
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
        user = User.builder()
                .username(getProperty("base.properties", "username"))
                .password(getProperty("base.properties", "password"))
                .build();
    }

    @Test
    public void loginPositiveTest(){
        registrationLoginScreen.typeForm(user);
        registrationLoginScreen.clickBtnLogin();
        Assert.assertTrue(new ContactsScreen(driver).isAddContactBtnDisplayed(),
                "AddContact Btn is Displayed");
    }

    @Test
    public void logoutPositiveTest() {
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

    @Test
    public void loginEmptyEmailNegativeTest() {
        user.setUsername("");
        registrationLoginScreen.typeForm(user);
        System.out.println(user);
        registrationLoginScreen.clickBtnLogin();
        Assert.assertTrue(new DialogScreen(driver).validateTextAlertMessage("Login or Password incorrect"),
                "Login or Password incorrect is present in alert message");
    }

    @Test
    public void loginEmptyPasswordNegativeTest() {
        user.setPassword("");
        registrationLoginScreen.typeForm(user);
        System.out.println(user);
        registrationLoginScreen.clickBtnLogin();
        Assert.assertTrue(new DialogScreen(driver).validateTextAlertMessage("Login or Password incorrect"),
                "Login or Password incorrect is present in alert message");
    }

    @Test
    public void loginEmptyFieldsNegativeTest() {
        registrationLoginScreen.clickBtnLogin();
        Assert.assertTrue(new DialogScreen(driver).validateTextAlertMessage("Login or Password incorrect"),
                "Login or Password incorrect is present in alert message");
    }

    @Test
    public void loginUnregisteredEmailNegativeTest() {
        user.setUsername("camie.kurzmannn+12345@gmail.com");
        registrationLoginScreen.typeForm(user);
        System.out.println(user);
        registrationLoginScreen.clickBtnLogin();
        Assert.assertTrue(new DialogScreen(driver).validateTextAlertMessage("Login or Password incorrect"),
                "Login or Password incorrect is present in alert message");
    }
}
