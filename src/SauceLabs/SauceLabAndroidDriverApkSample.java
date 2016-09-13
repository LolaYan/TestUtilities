package SauceLabs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceLabAndroidDriverApkSample {
	private AppiumDriver driver;
	//private IOSDriver driver;
	private String sessionId;
	public Wait<WebDriver> wait;
	public static String username = "lottotestnz"; //venvarikuppala 		LYanNZ			lottotestnz
	public static String key = "4441f476-39c5-419f-9b4b-437e8323d461"; //33c1fbce-0e3c-4c16-a502-f0f07a331483 		d37f4f7c-3806-4ce1-8bbc-5a5025253434	4441f476-39c5-419f-9b4b-437e8323d461
	public String jobId;

	public void setUp() throws MalformedURLException {
		
		DesiredCapabilities caps = DesiredCapabilities.android();
		caps.setCapability("appiumVersion", "1.5.1");
		caps.setCapability("deviceName","Android Emulator");
		caps.setCapability("deviceType","phone");
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("browserName", "");
		caps.setCapability("platformVersion","5.1");
		caps.setCapability("platformName","Android");
		caps.setCapability("app", "sauce-storage:au.com.webjet.apk"); //sauce-storage:au.com.webjet.apk
		caps.setCapability("appPackage", "co.nz.mylotto.sit"); 		//au.com.webjet
		caps.setCapability("appActivity", "co.nz.mylotto.MainActivity");	//au.com.webjet.activity.MainActivity
        
		caps.setCapability("name", "Android APK Test");
		  
		//ondemand.saucelabs.com:80
		//192.168.200.70:4445
			
		this.driver = new AndroidDriver(new URL("http://" + username + ":"
				+ key + "@192.168.200.70:4445/wd/hub"), caps); this.wait = new WebDriverWait(driver, 120);
		this.jobId = ((RemoteWebDriver) this.driver).getSessionId().toString();
}

	public static void main(String[] args) throws MalformedURLException {
    	SauceLabAndroidDriverApkSample sample = new SauceLabAndroidDriverApkSample();
    	sample.setUp();
        sample.driver.get("https://m.mylotto.co.nz/");

    }
	
	public boolean firstPageContainsSauce() {
		// type search query
		this.driver.findElement(By.name("q")).sendKeys("sauce labs\n");
		// click search
		this.driver.findElement(By.name("btnG")).click();
		// Wait for search to complete
		this.wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				System.out.println("Searching ...");
				return webDriver.findElement(By.id("resultStats")) != null;
			}
		});
		// Look for saucelabs.com in the results
		return driver.findElement(By.tagName("body")).getText()
				.contains("saucelabs.com");
	}

	public void updateJobData(boolean result) {
    	try {
    		URL url = new URL("http://saucelabs.com/rest/v1/" + username + "/jobs/" + this.jobId);
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setDoOutput(true);
    		conn.setRequestMethod("PUT");
    		conn.setRequestProperty("Content-Type", "application/json");

    		byte[] authEncBytes = Base64.encodeBase64((username + ":" + key).getBytes());
    		conn.setRequestProperty("Authorization", "Basic " + new String(authEncBytes));
     
    		String input = "{\"passed\":" + result +
    				", \"custom-data\": {\"java-test\": {\"failed\": 0, \"total\": 5, \"passed\": 5, \"runtime\": 20}}}";
     
    		OutputStream os = conn.getOutputStream();
    		os.write(input.getBytes());
    		os.flush();
     
    		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
    			throw new RuntimeException("Failed : HTTP error code : "
    				+ conn.getResponseCode());
    		}
    		BufferedReader br = new BufferedReader(new InputStreamReader(
    				(conn.getInputStream())));
    		String output;
    		System.out.println("Output from Server .... \n");
    		while ((output = br.readLine()) != null) {
    			System.out.println(output);
    		}
    		conn.disconnect();
    	  } catch (MalformedURLException e) {
    		e.printStackTrace();
    	  } catch (IOException e) {
    		e.printStackTrace();
    	 }
    }
}
