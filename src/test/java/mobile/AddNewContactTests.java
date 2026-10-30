package mobile;

import dto.Contact;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactsScreen;
import screens.RegistrationLoginScreen;
import screens.SplashScreen;
import static utils.ContactFactory.*;

import static utils.PropertiesReader.getProperty;

public class AddNewContactTests extends TestBase {
    RegistrationLoginScreen registrationLoginScreen;
    ContactsScreen contactsScreen;
    AddNewContactScreen addNewContactScreen;
    Contact contact;

    @BeforeMethod
    public void setAddNewContactScreen() {
        new SplashScreen(driver).waitForRegistrationScreen();
        registrationLoginScreen = new RegistrationLoginScreen(driver);
        User user = User.builder()
                .username(getProperty("base.properties", "username"))
                .password(getProperty("base.properties", "password"))
                .build();
        registrationLoginScreen.typeForm(user);
        registrationLoginScreen.clickBtnLogin();
        contactsScreen = new ContactsScreen(driver);
        contactsScreen.clickAddContactBtn();
        addNewContactScreen = new AddNewContactScreen(driver);
    }

    @Test
    public void addNewContactPositiveTest(){
        contact = positiveContact();
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(contactsScreen.validateWidgetAddContactText
                ("Contact was added!"), "Has widget text Contact was added!");
    }
}
