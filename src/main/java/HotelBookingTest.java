

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;


public class HotelBookingTest  extends TestBase{



    @FindBy(xpath = "//a[text()='Hotels']")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    
    

/** for using FindBy annotation we need to first initialize the webelements using PAgeFactory */
	@Before
	public void setup(){
    	
		
		initializeDriver();
		PageFactory.initElements(driver, this);
	}


    @org.junit.Test
    public void shouldBeAbleToSearchForHotels() throws InterruptedException {
    	
    	hotelLink.click();
        if(WaitUntilWebElementIsVisible(localityTextBox)){
        localityTextBox.sendKeys("Indiranagar, Bangalore");
        
        }
        
        waitAndClickElement(driver.findElement(By.xpath("//a[text()='Indiranagar, Bangalore, Karnataka, India']")));
       
        
        new Select(travellerSelection).selectByVisibleText("1 room, 1 adult");
      
        searchButton.click();
      
      
        

     

    }

    @After
    public void tearDown() {
 	   driver.quit();
    }

}
