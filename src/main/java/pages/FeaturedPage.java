package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FeaturedPage extends BasePage{

   private List<WebElement> elements;
   private List<WebElement> cartButton;
   private WebElement messageAlert;

    public FeaturedPage(WebDriver webDriver){
        super(webDriver);
    }

    public String AddElementToTheCart(){
        elements = webDriver.findElements(By.xpath("//*[@class=\"product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12\"]"));
        System.out.println("el tama;o de la lista elementos es " + elements.size());
        cartButton= webDriver.findElements(By.xpath("//button[contains(@onclick, 'cart.add')]"));
        cartButton.get(4).click();
        messageAlert = webDriver.findElement(By.xpath("//*[@class=\"alert alert-success alert-dismissible\"]"));
        return messageAlert.getText();
    }
}
