import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SecondChanceAdminLoginReset {
	static String username="nancy@lottonz.co.nz";
	static String password="lotto@123";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bdc-cat2-2cadm.cat.nzlc.co.nz//login?logout");
		
		//login
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//driver.findElement(By.xpath("//a[.='See all users']")).click();
	}

}
