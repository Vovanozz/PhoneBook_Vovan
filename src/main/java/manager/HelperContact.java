package manager;

import models.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        click(By.xpath("//a[text()='ADD']"));
    }

    public void FillContactForm(Contacts contacts) {
        type(By.cssSelector("input[placeholder='Name']"),contacts.getName());
        type(By.cssSelector("input[placeholder='Last Name']"),contacts.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"),contacts.getPhone());
        type(By.cssSelector("input[placeholder='email']"),contacts.getEmail());
        type(By.cssSelector("input[placeholder='Address']"),contacts.getAddress());
        type(By.cssSelector("input[placeholder='description']"),contacts.getDescription());
    }

    public void submitContactForm() {
       click(By.xpath("//b[text()='Save']"));
    }

    public boolean isContactAddedByName(String name) {
   List<WebElement> list= wd.findElements(By.cssSelector("h2"));
   for(WebElement el: list ){
       if(el.getText().equals(name)){
           return true;
       }
   }
   return false;
    }

    public boolean isContactAddedByPhone(String phone) {
       List<WebElement > list=wd.findElements(By.cssSelector("h3"));
       for(WebElement el:list){
           if(el.getText().equals(phone)){
               return true;
           }
       }
       return false;
    }

public void clickByContactForm(){
    click(By.cssSelector("div[class='contact-item_card__2SOIM'] "));
}
    public boolean isContactAddedByEmail(String email) {
       List<WebElement>list= wd.findElements(By.cssSelector("(//img[@alt='media'])[contains(@alt,'media')]"));
       for (WebElement el:list){
           if(el.getText().equals(email)){
               return true;
           }
       }
       return false;
    }
    public boolean buttonSaveUnClickable() {
        List<WebElement> list = wd.findElements(By.cssSelector(".add_form__2rsm2 button"));
        return list.size()>0;
    }
    public void refreshPage() {
        click(By.cssSelector("a[href = '/home']"));
    }
    public boolean isAddPageStillDisplayed() {
        return wd.findElements(By.cssSelector("a.active[href='/add']")).size()>0;
    }
}
