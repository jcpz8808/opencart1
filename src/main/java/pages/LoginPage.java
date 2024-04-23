package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage{
    private By emailInput = By.name("email");
   private By passwordInput = By.name("password");
   private By loginSubmit = By.xpath("//input[@value=\"Login\"]");
   private By myAccountDropdown = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a");

   public LoginPage(WebDriver webDriver){
       super(webDriver);
   }

   /* Given the user and password when the user complete the form
      and clic into login then open my account page
    */

    public boolean LoginEmailComponentVisible(){
        webDriver.navigate().to("https://opencart.abstracta.us/index.php?route=account/login");
        return webDriver.findElement(emailInput).isDisplayed();
    }
    public boolean LoginPasswordComponentVisible(){
        webDriver.navigate().to("https://opencart.abstracta.us/index.php?route=account/login");
        return webDriver.findElement(passwordInput).isDisplayed();
    }
    public boolean LoginSubmitComponentVisible(){
        webDriver.navigate().to("https://opencart.abstracta.us/index.php?route=account/login");
        return webDriver.findElement(loginSubmit).isDisplayed();
    }
   public String loginCustomer(String user, String pass){
       webDriver.navigate().to("https://opencart.abstracta.us/index.php?route=account/login");
       webDriver.findElement(emailInput).sendKeys(user);
       webDriver.findElement(passwordInput).sendKeys(pass);
       webDriver.findElement(loginSubmit).click();
       webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       //By alertText = By.xpath("//*[@class=\"alert alert-danger alert-dismissible\"]");
       if (webDriver.getCurrentUrl().equals("https://opencart.abstracta.us/index.php?route=account/account"))
           return "Login successfully";
       else{
           return webDriver.findElement(By.xpath("//*[@class=\"alert alert-danger alert-dismissible\"]")).getText();
       }

   }

   public void logoutCustomer(){
       webDriver.findElement(myAccountDropdown).click();
       webDriver.findElement(new By.ByLinkText("Logout")).click();
   }



}
