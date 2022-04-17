package testsuite;

import browserfactory.BaseTest;
import graphql.Assert;
import graphql.VisibleForTesting;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setup(){
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully(){
        //find sign in link and click on SignInButton
        WebElement SignInbutton = driver.findElement(By.linkText("Sign in"));
        SignInbutton.click();

        //This is from requirement
        String expectedMessage = "Welcome Back!";

        //find the welcome back text element and get the text
        WebElement actualMessageElement = driver.findElement(By.xpath("//h1[contains(text(),'Welcome Back!')]"));
        String actualMessage = actualMessageElement.getText();
        System.out.println(actualMessage);
        //validate actual and expected message

      //  Assert.asserts("Not navigate to login page",expectedMessage,actualMessage);
    }

    @Test
    public void verifyErrorMessageWithInvalidCredentials(){
        //Find Sign in link and click on sign in link
        WebElement SignInbutton = driver.findElement(By.linkText("Sign in"));
        SignInbutton.click();

        //Enter invalid username
        WebElement username = driver.findElement(By.name("user[email]"));
        //sending field invalid username
        username.sendKeys("jalpa");

        // Find the invalid password field element
               WebElement passwordField = driver.findElement(By.name("user[password]"));
        //     Sending invalid Password to password field element
              passwordField.sendKeys("jalpa123");


        //Find the login button and click on it
        WebElement loginButton = driver.findElement(By.xpath("//body/main[@id='main-content']/div[1]/div[1]/article[1]/form[1]/div[4]/input[1]"));
        loginButton.click();

        //This is from requirement
        String expectedMessage = "Invalid email or password.";

        //invalid email or password text element and get the text
        WebElement actualMessageElement = driver.findElement(By.xpath("//li[contains(text(),'Invalid email or password.')]"));
        String actualMessage = actualMessageElement.getText();
        System.out.println(actualMessage);
        //validate actual and expected message
        org.junit.Assert.assertEquals("Not navigate to login page", expectedMessage, actualMessage);
        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n"
                + "No customer account found";
        String actualErrorMessage = driver.findElement(By.className("button button-primary g-recaptcha")).getText();
    }




    @After
    public void tearDown(){
        closeBrowser();
    }
}
