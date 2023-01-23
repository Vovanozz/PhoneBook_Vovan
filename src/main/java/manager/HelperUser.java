package manager;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
        click(By.cssSelector("a[href='/login']"));
    }

    public void fillLoginRegistrationForm(String email,String password){
        // for email
        type(By.name("email"),email);
        // for password
        type(By.name("password"),password);

    }
    public void fillLoginRegistrationForm(User user){
        // for email
        type(By.name("email"),user.getEmail());
        // for password
        type(By.name("password"),user.getPassword());

    }

    public void submitLogin(){
        click(By.cssSelector("[name='login']"));

    }

    public boolean isLogged() {
//        try {
//            wd.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            return true;
//        }catch (Exception e){
//            return false;
//        }
        List<WebElement> list  = wd.findElements(By.xpath("//button[text()='Sign Out']"));
        return list.size() > 0;
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public boolean isErrorMessageDisplayed(String message) {
        Alert alert = wd.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);

        // click ok
        alert.accept();
        //click cancel
        //alert.dismiss();
        //alert.sendKeys("Hello");
        return text.contains(message);
    }

    public void submitRegistration() {
        click(By.cssSelector("[name='registration']"));
    }
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
