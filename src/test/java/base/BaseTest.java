package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.FeaturedPage;
import pages.LoginPage;

public class BaseTest {
    protected WebDriver webDriver;
    protected LoginPage loginPage;
    protected FeaturedPage featuredPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://opencart.abstracta.us/index.php?route=account/login");
        webDriver.manage().window().maximize();
        loginPage = new LoginPage(webDriver);
        featuredPage = new FeaturedPage(webDriver);
        //webDriver.get("http://opencart.abstracta.us/");
    }
    @AfterMethod
    public void tearDown(){
        if (webDriver!=null){
            webDriver.quit();
        }
    }
}
