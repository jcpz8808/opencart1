package login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FeaturedTests extends BaseTest {


    @Test(priority = 1, suiteName = "Regression")
    public void test01AddingItemToCartSuccesfully() throws InterruptedException {
        loginPage.loginCustomer("jcpz8808@gmail.com", "password");
        webDriver.navigate().to("https://opencart.abstracta.us/index.php?route=product/category&path=20");
        Assert.assertEquals(featuredPage.AddElementToTheCart(),"Success: You have added iPhone to your shopping cart!\n" +
                "×", "Error, the item was not added to the cart");
        loginPage.takeScreenShot("test01AddingItemToCartSuccesfully");
    }


}
