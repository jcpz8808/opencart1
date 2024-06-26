package login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;
import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;
import java.io.IOException;
import java.time.Duration;

public class LoginTests extends BaseTest {

    /* Test case 01: Login correctly (Possitive test case)
    * Given the customer email and password when the user complete correctly de login form then get to My account page
    * */

   @Test(priority = 1, suiteName = "Regression")
    public void test01LoginCustomerSuccessfully(){
        webDriver.navigate().to("https://opencart.abstracta.us/index.php?route=account/login");
        Assert.assertEquals(loginPage.loginCustomer("jcpz8808@gmail.com", "password"), "Login successfully", "Error: the login failed");
        loginPage.takeScreenShot("test01LoginCustomerSuccessfully");
        loginPage.logoutCustomer();

   }

    /* Test case 02: Try to navegate after the customer had logged out (Negative test case)
     * Given the customer is logged in when the customer logout and try to navegate to another link then get
     * redirected to the log in page.
     * */

    @Test(priority = 2, suiteName = "Regression")
    public void test02ExpectedLoginPageWhenBrowserBackButtonAfterLogout(){

        //Given the customer is logged
        loginPage.loginCustomer("jcpz8808@gmail.com", "password");

        //When the customer logout
        loginPage.logoutCustomer();

        //When the user clic the back button of the explorer
        webDriver.navigate().back();

        //When the user try to access to another url after logout
        webDriver.navigate().to("https://opencart.abstracta.us/index.php?route=account/edit");

        // Then the user must be sent to the login page
        Assert.assertEquals(webDriver.getCurrentUrl().toString(), "https://opencart.abstracta.us/index.php?route=account/login", "Error, the user can navegate even after logout");
        loginPage.takeScreenShot("test02ExpectedLoginPageWhenBrowserBackButtonAfterLogout");
    }

    /* Test case 03: Login incorrectly, invalid password (Negative test case)
     * Given the customer email and the wrong password when the user complete de login form and clic en login then get
     * an error message telling "Warning: No match for E-Mail Address and/or Password".
     * */

    @Test(priority = 3, suiteName = "Smoke")
    public void test03ExpectedWarningMessageWhenLoginCustomerWithInvalidPassword(){

        Assert.assertEquals(loginPage.loginCustomer("jcpz8808@gmail.comlo", "123456"), "Warning: No match for E-Mail Address and/or Password.", "Error in the expected message for wrong login");
        loginPage.takeScreenShot("test03ExpectedWarningMessageWhenLoginCustomerWithInvalidPassword");
    }

    /* Test case 04: Login incorrectly, invalid username (Negative test case)
     * Given the customer email and the wrong password when the user complete de login form and clic en login then get
     * an error message telling "Warning: No match for E-Mail Address and/or Password".
     * */

    @Test(priority = 4, suiteName = "Smoke")
    public void test04ExpectedWarningMessageWhenLoginCustomerWithInvalidUsername(){

        Assert.assertEquals(loginPage.loginCustomer("jcpz8808658@gmail.comlo", "password"), "Warning: No match for E-Mail Address and/or Password.", "Error in the expected message for wrong login");
        loginPage.takeScreenShot("test04ExpectedWarningMessageWhenLoginCustomerWithInvalidUsername");
    }

    /* Test case 05: Login incorrectly, empty username (Negative test case)
     * Given just the customer password when the user complete de login form without username and clic en login then get
     * an error message telling "Warning: No match for E-Mail Address and/or Password".
     * */

    @Test(priority = 5, suiteName = "Smoke")
    public void test05ExpectedWarningMessageWhenLoginCustomerWithEmptyUsername(){

        Assert.assertEquals(loginPage.loginCustomer("", "password"), "Warning: No match for E-Mail Address and/or Password.", "Error in the expected message for wrong login");
        loginPage.takeScreenShot("test05ExpectedWarningMessageWhenLoginCustomerWithEmptyUsername");
    }

    /* Test case 06: Login incorrectly, empty password (Negative test case)
     * Given just the customer password when the user complete de login form without username and clic en login then get
     * an error message telling "Warning: No match for E-Mail Address and/or Password".
     * */

    @Test(priority = 6, suiteName = "Smoke")
    public void test06ExpectedWarningMessageWhenLoginCustomerWithEmptyPassword(){

        Assert.assertEquals(loginPage.loginCustomer("jcpz8808@gmail.com", ""), "Warning: No match for E-Mail Address and/or Password.", "Error in the expected message for wrong login");
        loginPage.takeScreenShot("test06ExpectedWarningMessageWhenLoginCustomerWithEmptyPassword");
    }


    /* Test case 07: Login incorrectly multiple times(Negative test case)
     * Given the customer email and the wrong password when the user complete de login form and clic en login then get
     * an error message telling "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.".
     * */

    @Test(priority = 7, suiteName = "Regression")
    public void test07ExpectedWarningMessageWhenLoginCustomerWithInvalidCredentialsMultipleTimes(){

        Assert.assertEquals(loginPage.loginCustomer("jcpz8po808@gmail.comlo", "1263456"), "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.", "Error in the expected message for wrong login");
        loginPage.takeScreenShot("test07ExpectedWarningMessageWhenLoginCustomerWithInvalidCredentialsMultipleTimes");
    }

    /* Test case 08: Login components are visible
     * When the user enters to the login address then he will see all the components for the login.
     * */

    @Test(priority = 0, suiteName = "Smoke")
    public void test08AllLoginComponentsAreVisible(){
        Assert.assertEquals(loginPage.LoginEmailComponentVisible(), true, "Error in the email component is not visible");
        Assert.assertEquals(loginPage.LoginPasswordComponentVisible(), true, "Error in the password component is not visible");
        Assert.assertEquals(loginPage.LoginSubmitComponentVisible(), true, "Error in the login submit button component is not visible");
        loginPage.takeScreenShot("test08AllLoginComponentsAreVisible");
    }

    /* Test case 09: Performance
     * given the login adress When the user access it then it should open in less than 5 seconds.
     * */
    @Test(priority = 0, suiteName = "Smoke")
    public void test09PreformanceExecutationTimeUnder() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(2, 10,
                        httpSampler("https://opencart.abstracta.us/index.php?route=account/login")
                )
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }
}
