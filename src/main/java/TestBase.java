import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.javafx.PlatformUtil;

/** Base class to intantiate the driver and have common wait methods which could be used in all three classes */
public class TestBase {

	//Static varaible so that no Object is required to intantiate them
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	
	/**Initialize method would initailize the webdriver ,select the platform , have generic method like maximizing the window, having a pageload time and implicit timeout which would be used for all the elements present across the pages. */
	public static void initializeDriver() {
		
		 if (PlatformUtil.isMac()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver");
	        }
		 else if (PlatformUtil.isWindows()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	        }
		 else if (PlatformUtil.isLinux()) {
	            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
	        }
			Map<String, Object> prefs = new HashMap<String, Object>();
            
            // Set the notification setting it will override the default setting
	prefs.put("profile.default_content_setting_values.notifications", 2);

            // Create object of ChromeOption class
	ChromeOptions options = new ChromeOptions();

            // Set the experimental option
	options.setExperimentalOption("prefs", prefs);
		 driver=new ChromeDriver(options);
		 
		 driver.manage().window().maximize();
		 driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 wait = new WebDriverWait(driver, 10);
		 driver.get("https://www.cleartrip.com/");
	}
	
	/** This method will perform explicit wait on an element so that driver waits before clicking an element */
	public void waitAndClickElement(WebElement element) throws InterruptedException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {
				this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				System.out.println("Successfully clicked on the WebElement");
				clicked = true;
			} catch (Exception e) {
				System.out.println("Unable to wait and click on WebElement, Exception: " + e.getMessage());
				Assert.fail("Unable to wait and click on the WebElement");
			}
			attempts++;
		}
	}
	
	/** This method will perform explicit wait on an element so that driver waits until element is visible on the page */
	public boolean WaitUntilWebElementIsVisible(WebElement element) {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(element));
			System.out.println("WebElement is visible using locator:");
			return true;
		} catch (Exception e) {
			System.out.println("WebElement is NOT visible, using locator");
			Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}
	
	
}
