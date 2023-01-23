package tests;

import models.Contacts;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(User.builder().email("bob1@gmail.com").password("Bob1234$").build());

        }
    }
    @Test
    public void addContactSuccessAllFields(){
       Random random=new Random();
       int r= random.nextInt(1000)+1000;
        Contacts contacts= Contacts.builder()
                .name("Bob"+r)
                .lastName("Marley")
                .address("New york")
                .phone("1234556676"+r)
                .email("vov"+r+"@gmail.com")
                .description("The best friend").build();
        //System.out.println(contacts.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().FillContactForm(contacts);
        app.getHelperContact().submitContactForm();
       // app.getHelperContact().clickByContactForm();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contacts.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contacts.getPhone()));

    }
    @Test
    public void addContactByOnlyRequiredFields(){
        Random random=new Random();
        int r= random.nextInt(1000)+1000;
        Contacts contacts= Contacts.builder()
                .name("Vladimir"+r).lastName("Ozer")
                .address("Ariel").phone("23243453563"+r)
                .email("vov"+r+"@gmail.com").build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().FillContactForm(contacts);
        app.getHelperContact().submitContactForm();
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contacts.getPhone()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contacts.getName()));
    }
    @Test
    public void addWrongNameContact(){
        Random random=new Random();
        int r= random.nextInt(1000)+1000;
        Contacts contacts= Contacts.builder()
                .name("").lastName("Ozer")
                .address("Ariel").phone("23243453563"+r)
                .email("vov"+r+"@gmail.com").build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().FillContactForm(contacts);
        app.getHelperContact().submitContactForm();
        app.getHelperContact().pause(1000);
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(contacts.getPhone()));
        Assert.assertTrue(app.getHelperContact().buttonSaveUnClickable());

    }
    @Test
    public void addWrongLastNameContact(){
        Random random=new Random();
        int r= random.nextInt(1000)+1000;
        Contacts contacts= Contacts.builder()
                .name("Vovan").lastName("")
                .address("Ariel").phone("23243453563"+r)
                .email("vov"+r+"@gmail.com").build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().FillContactForm(contacts);
        app.getHelperContact().submitContactForm();
        app.getHelperContact().pause(1000);
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(contacts.getPhone()));
        Assert.assertTrue(app.getHelperContact().buttonSaveUnClickable());
    }
    @Test
    public void addWrongEmailContact(){
        Random random=new Random();
        int r= random.nextInt(1000)+1000;
        Contacts contacts= Contacts.builder()
                .name("Vovan").lastName("Ozer")
                .address("Ariel").phone("23243453563"+r)
                .email("vovgmail.com").build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().FillContactForm(contacts);
        app.getHelperContact().submitContactForm();
        app.getHelperContact().pause(1000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid"));
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(contacts.getPhone()));
        Assert.assertTrue(app.getHelperContact().buttonSaveUnClickable());
    }
    @Test
    public void addWrongPhoneContact(){
        Random random=new Random();
        int r= random.nextInt(1000)+1000;
        Contacts contacts= Contacts.builder()
                .name("Vovan").lastName("Ozer")
                .address("Ariel").phone("")
                .email("vovan"+r+"@gmail.com").build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().FillContactForm(contacts);
        app.getHelperContact().submitContactForm();
        app.getHelperContact().pause(1000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(contacts.getPhone()));
        Assert.assertTrue(app.getHelperContact().buttonSaveUnClickable());
    }
    @Test
    public void addWrongAddressContact(){
        Random random=new Random();
        int r= random.nextInt(1000)+1000;
        Contacts contacts= Contacts.builder()
                .name("Vovan").lastName("Ozer")
                .address("").phone("23243453563"+r)
                .email("vov"+r+"@gmail.com").build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().FillContactForm(contacts);
        app.getHelperContact().submitContactForm();
        app.getHelperContact().pause(1000);
        Assert.assertFalse(app.getHelperContact().isContactAddedByPhone(contacts.getPhone()));
        Assert.assertTrue(app.getHelperContact().buttonSaveUnClickable());

    }
    @AfterMethod
    public void goHome(){
        app.getHelperContact().refreshPage();
    }

}
