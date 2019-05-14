

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.testng.Assert;

/** SignIN test to verify the error present in the modal window.Here we are using inheritance so as to get the properties of TestBase class */

public class SignInTest  extends TestBase{

 /** Before annotation will always execute before each and every test so here before our test method before will initialize the driver and load the url.*/
@Before
public void setup(){
	initializeDriver();
}

    @org.junit.Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() throws InterruptedException  {

       
       
        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        
       
       //Switiching to the frame
    	 driver.switchTo().frame("modal_window");
      
        waitAndClickElement(driver.findElement(By.id("signInButton")));
       
        WebElement errorsMessageElement = driver.findElement(By.xpath("//div[@id='errors1']/span"));
        WaitUntilWebElementIsVisible(errorsMessageElement);
     
        Assert.assertEquals("There were errors in your submission",errorsMessageElement.getText());
     
    }

   /** It will quit all the active driver. */
    @After
   public void tearDown() {
	   driver.quit();
   }


}
