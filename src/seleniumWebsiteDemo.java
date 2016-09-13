import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class seleniumWebsiteDemo {
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\workspace\\TestUtilities\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        Topup(driver);
        
        Thread.sleep(14000);
        
        //Close the browser
        driver.quit();
    }
	
	public static void Topup(WebDriver driver){
		// And now use this to visit Google
        driver.get("https://1.sit.mylotto.co.nz/top-up");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        String str = driver.findElement(By.id("your-tickets-cost-amount")).getText();
        System.out.println(str);
	}
	public static void signUp(WebDriver driver){
		// And now use this to visit Google
        driver.get("https://1.sit.mylotto.co.nz/registration");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        driver.findElement(By.id("emailAddress")).sendKeys("test@test.com");
        driver.findElement(By.id("firstName")).sendKeys("lola");
        driver.findElement(By.id("lastName")).sendKeys("test");
        driver.findElement(By.id("password")).sendKeys("password1");
        driver.findElement(By.id("dobDay")).sendKeys("01");
        
		Select monthSelect = new Select(driver.findElement(By.id("dobMonth")));
		monthSelect.selectByValue("01");
		monthSelect.selectByVisibleText("February");
		driver.findElement(By.id("dobYear")).sendKeys("1981");
		driver.findElement(By.id("promotionReminders")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("jackpotReminder")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("IKReminder")).sendKeys(Keys.SPACE);
		driver.findElement(By.id("signup-button")).click();
	}
}
