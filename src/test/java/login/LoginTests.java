package login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    //@BeforeTest
    /*public void setUp(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://opencart.abstracta.us/index.php?route=account/login");
        //webDriver.manage().window().maximize();
    }*/



    /* Test case 01: Login correctly (Possitive test case)
    * Given the customer email and password when the user complete correctly de login form then get to My account page
    * */

   @Test(priority = 1)
    public void test01LoginCustomerSuccessfully(){

        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.loginCustomer("jcpz8808@gmail.com", "password"), "Login successfully", "Error: the login failed");
    }

    /* Test case 02: Try to navegate after the customer had logged out (Negative test case)
     * Given the customer is logged in when the customer logout and try to navegate to another link then get
     * redirected to the log in page.
     * */

    @Test(priority = 2)
    public void test02ExpectedLoginPageWhenBrowserBackButtonAfterLogout(){

        //Given the customer is logged
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginCustomer("jcpz8808@gmail.com", "password");

        //When the customer logout
        loginPage.logoutCustomer();

        //When the user clic the back button of the explorer
        webDriver.navigate().back();

        //When the user try to access to another url after logout
        webDriver.navigate().to("https://opencart.abstracta.us/index.php?route=account/edit");

        // Then the user must be sent to the login page
        Assert.assertEquals(webDriver.getCurrentUrl().toString(), "https://opencart.abstracta.us/index.php?route=account/login", "Error, the user can navegate even after logout");
    }

    /* Test case 03: Login incorrectly, invalid password (Negative test case)
     * Given the customer email and the wrong password when the user complete de login form and clic en login then get
     * an error message telling "Warning: No match for E-Mail Address and/or Password".
     * */

    @Test(priority = 3)
    public void test03ExpectedWarningMessageWhenLoginCustomerWithInvalidPassword(){

        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.loginCustomer("jcpz8808@gmail.comlo", "123456"), "Warning: No match for E-Mail Address and/or Password.", "Error in the expected message for wrong login");
    }

    /* Test case 04: Login incorrectly, invalid username (Negative test case)
     * Given the customer email and the wrong password when the user complete de login form and clic en login then get
     * an error message telling "Warning: No match for E-Mail Address and/or Password".
     * */

    @Test(priority = 4)
    public void test04ExpectedWarningMessageWhenLoginCustomerWithInvalidUsername(){

        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.loginCustomer("jcpz8808658@gmail.comlo", "password"), "Warning: No match for E-Mail Address and/or Password.", "Error in the expected message for wrong login");
    }

    /* Test case 05: Login incorrectly, empty username (Negative test case)
     * Given just the customer password when the user complete de login form without username and clic en login then get
     * an error message telling "Warning: No match for E-Mail Address and/or Password".
     * */

    @Test(priority = 5)
    public void test05ExpectedWarningMessageWhenLoginCustomerWithEmptyUsername(){

        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.loginCustomer("", "password"), "Warning: No match for E-Mail Address and/or Password.", "Error in the expected message for wrong login");
    }

    /* Test case 06: Login incorrectly, empty password (Negative test case)
     * Given just the customer password when the user complete de login form without username and clic en login then get
     * an error message telling "Warning: No match for E-Mail Address and/or Password".
     * */

    @Test(priority = 6)
    public void test06ExpectedWarningMessageWhenLoginCustomerWithEmptyPassword(){

        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.loginCustomer("jcpz8808@gmail.com", ""), "Warning: No match for E-Mail Address and/or Password.", "Error in the expected message for wrong login");
    }


    /* Test case 07: Login incorrectly multiple times(Negative test case)
     * Given the customer email and the wrong password when the user complete de login form and clic en login then get
     * an error message telling "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.".
     * */

    @Test(priority = 7)
    public void test07ExpectedWarningMessageWhenLoginCustomerWithInvalidCredentialsMultipleTimes(){

        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertEquals(loginPage.loginCustomer("jcpz8po808@gmail.comlo", "1263456"), "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.", "Error in the expected message for wrong login");
    }
}
