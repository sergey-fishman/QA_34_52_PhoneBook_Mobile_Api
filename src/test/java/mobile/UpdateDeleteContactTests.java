package mobile;

import dto.Contact;
import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import screens.*;

import java.util.List;

import static utils.ContactFactory.positiveContact;
import static utils.PropertiesReader.getProperty;

public class UpdateDeleteContactTests extends TestBase {
    RegistrationLoginScreen registrationLoginScreen;
    ContactsScreen contactsScreen;
    AddNewContactScreen addNewContactScreen;
    Contact contact;
    SoftAssert softAssert = new SoftAssert();
    EditContactScreen editContactScreen;

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

//        contactsScreen.clickAddContactBtn();
//        addNewContactScreen = new AddNewContactScreen(driver);
//        contact = positiveContact();
//        addNewContactScreen.typeContactForm(contact);
//        addNewContactScreen.clickBtnCreate();
//        softAssert.assertTrue(contactsScreen.validateWidgetAddContactText
//                ("Contact was added!"), "Has widget text Contact was added!");
    }

    @Test
    public void updateContactPositiveTest(){
        contactsScreen.updateContactSwipe();
        editContactScreen = new EditContactScreen(driver);
        contact.setName("Updated");
        editContactScreen.typeContactForm(contact);
        editContactScreen.clickBtnUpdate();
        Assert.assertTrue(contactsScreen.validateWidgetUpdateContactText
                ("Contact was updated!"), "Widget text Contact was updated!");
    }

    @Test
    public void deleteContactPositiveTest(){
        List<String> namesBefore = contactsScreen.getNamesList();
        System.out.println(namesBefore);
        contactsScreen.deleteContactSwipe();
        AlertScreen alertScreen = new AlertScreen(driver);
        alertScreen.clickBtnYes();
        List<String> namesAfter = contactsScreen.getNamesList();
        System.out.println(namesAfter);
        Assert.assertNotEquals(namesBefore, namesAfter);
    }
}
