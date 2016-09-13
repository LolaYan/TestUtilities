import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleniumWebsiteDemoGetAllElements {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\User\\workspace\\TestUtilities\\lib\\chromedriver.exe");
		driver = new ChromeDriver();

		LoopAllElements();
		Thread.sleep(4000);

		// Close the browser
		driver.quit();
	}

	public static void LoopAllElements() {
		// LoopAllPageElements("https://1.sit.mylotto.co.nz/registration");
		// LoopAllPageElements("https://1.sit.mylotto.co.nz/top-up");
		LoopAllPageElements("https://login.xero.com");
	}

	public static void LoopAllPageElements(String url) {
		System.out.println("Loop all the elements of Page: " + url);
		driver.get(url);
		LoopTagElements("div");
		LoopTagElements("input");
		LoopTagElements("a");
		LoopTagElements("button");
		LoopTagElements("select");
	}

	public static void LoopTagElements(String Tagname) {
		System.out.println("Loop all the elements of " + Tagname + "......");
		List<WebElement> buttonElements = driver.findElements(By
				.tagName(Tagname));
		String[] buttonText = new String[buttonElements.size()];
		int indexButton = 0;
		for (WebElement element : buttonElements) {
			buttonText[indexButton++] = element.getText();
			if (element.getAttribute("id").isEmpty() == false) {
				/*
				 * System.out.println("lottoRegistrationUrl." +
				 * element.getAttribute("id") + "=Id:" +
				 * element.getAttribute("id"));
				 
				System.out.println("public static By BySearch"
						+ element.getAttribute("id") + "() {By res = By.id(\""
						+ element.getAttribute("id") + "\");return res;}");
						
						*/
				
				printSearchElementCode(element.getAttribute("id"));
			} else if (Tagname.equals("a")) {
				System.out.println("lottoRegistrationUrl." + element.getText()
						+ "=Location:" + element.getLocation());
			}

		}
		indexButton = 0;
		for (WebElement element : buttonElements) {
			buttonText[indexButton++] = element.getText();
			if (element.getAttribute("id").isEmpty() == false) {
			
				printMethodElementCode(element.getAttribute("id"));
				
			}

		}
		
	}

	public static void printObjectPropertyList(String value) {

		System.out.println("lottoRegistrationUrl." + value + "=Id:" + value);

	}

	public static void printSearchElementCode(String value) {
		System.out.println("public static By BySearch" + value
				+ "() {By res = By.id(\"" + value + "\");return res;}");
	}

	public static void printMethodElementCode(String value) {
		System.out.println("public static void click" + value
				+ "() {driver.findElement(BySearch" + value + "()).click();}");
	}
}
