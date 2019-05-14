

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;

import org.testng.Assert;

/** To check the Flight booking functionality */
public class FlightBookingTest extends TestBase{

	@Before
	public void setup(){
		initializeDriver();
	}


    @org.junit.Test
    public void testThatResultsAppearForAOneWayJourney() throws InterruptedException {

     
        driver.findElement(By.id("OneWay")).click();

        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Ban");
      
      
      
        
//not using indexes as it could change in future but text would remain the same.
       waitAndClickElement(driver.findElement(By.xpath("//a[text()='Bangalore, IN - Kempegowda International Airport (BLR)']")));
       
       

        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Del");
        
        //wait for the auto complete options to appear for the destination

       
        waitAndClickElement(driver.findElement(By.xpath("//a[text()='New Delhi, IN - Indira Gandhi Airport (DEL)']")));

        driver.findElement(By.xpath("//div[@class='monthBlock first']//a[text()='19']")).click();

        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        //verify that result appears for the provided journey search
        Assert.assertTrue(WaitUntilWebElementIsVisible(driver.findElement(By.className("searchSummary"))));
       

        

    }


   

   

    @After
    public void tearDown() {
 	   driver.quit();
    }
}
