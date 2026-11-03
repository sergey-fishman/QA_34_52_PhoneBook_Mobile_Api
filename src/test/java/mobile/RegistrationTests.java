package mobile;

import data_providers.UserDataProvider;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import screens.ContactsScreen;
import screens.DialogScreen;
import screens.RegistrationLoginScreen;
import screens.SplashScreen;
import utils.PropertiesReader;

import static utils.UserFactory.*;

public class RegistrationTests extends TestBase {
    RegistrationLoginScreen registrationLoginScreen;
    User user;
    SoftAssert softAssert;

    @BeforeMethod
    public void setRegistrationLoginScreen() {
        new SplashScreen(driver).waitForRegistrationScreen();
        registrationLoginScreen = new RegistrationLoginScreen(driver);
    }

    @Test
    public void registrationPositiveTest() {
        user = positiveUser();
        registrationLoginScreen.typeForm(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new ContactsScreen(driver).validateEmptyContactsText
                ("No Contacts. Add One more!"), "No Contacts. Add One more!");
    }

    @Test(dataProvider = "dataProviderPositive", dataProviderClass = UserDataProvider.class)
    public void registrationDataProviderPositiveTest(User user) {
        registrationLoginScreen.typeForm(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new ContactsScreen(driver).validateEmptyContactsText
                ("No Contacts. Add One more!"), "No Contacts. Add One more!");
    }

    @Test
    public void registrationEmptyFieldsNegativeTest() {
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new DialogScreen(driver)
                        .validateTextAlertMessage("Wrong email or password"),
                "Wrong email or password is present in alert message");
    }

    @Test
    public void registrationEmptyEmailNegativeTest() {
        user = positiveUser();
        user.setUsername("");
        registrationLoginScreen.typeForm(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new DialogScreen(driver)
                        .validateTextAlertMessage("Wrong email or password"),
                "Wrong email or password is present in alert message");
    }

    @Test
    public void registrationEmptyPasswordNegativeTest() {
        user = positiveUser();
        user.setPassword("");
        registrationLoginScreen.typeForm(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new DialogScreen(driver)
                        .validateTextAlertMessage("Wrong email or password"),
                "Wrong email or password is present in alert message");
    }

    @Test(dataProvider = "dataProviderWrongEmail", dataProviderClass = UserDataProvider.class)
    public void registrationWrongEmailFormatNegativeTest(User user) {
        registrationLoginScreen.typeForm(user);
        System.out.println(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new DialogScreen(driver)
                        .validateTextAlertMessage("{username=must be a well-formed email address}"),
                "{username=must be a well-formed email address} is present in alert message");
    }

    @Test(dataProvider = "dataProviderWrongPassword", dataProviderClass = UserDataProvider.class)
    public void registrationWrongPasswordFormatNegativeTest(User user) {
        registrationLoginScreen.typeForm(user);
        System.out.println(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new DialogScreen(driver)
                        .validateTextAlertMessage("{password= At least 8 characters;"),
                "{password= At least 8 characters; is present in alert message");
    }

    @Test(dataProvider = "dataProviderWrongPassword", dataProviderClass = UserDataProvider.class)
    public void registrationEmptyEmailWrongPasswordFormatNegativeTest(User user) {
        softAssert = new SoftAssert();
        user.setUsername("");
        registrationLoginScreen.typeForm(user);
        System.out.println(user);
        registrationLoginScreen.clickBtnRegistration();
        DialogScreen dialogScreen = new DialogScreen(driver);
        softAssert.assertTrue(dialogScreen.validateTextAlertMessage("{password= At least 8 characters;"),
                "{password= At least 8 characters; is present in alert message");
        softAssert.assertTrue(dialogScreen.validateTextAlertMessage("username=must not be blank"),
                "username=must not be blank is present in alert message");
        softAssert.assertAll();
    }

    @Test
    public void registrationExistingUserNegativeTest() {
        user = User.builder()
                .username(PropertiesReader.getProperty("base.properties", "username"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        registrationLoginScreen.typeForm(user);
        System.out.println(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new DialogScreen(driver)
                        .validateTextAlertMessage("User already exists"),
                "User already exists is present in alert message");
    }
}
