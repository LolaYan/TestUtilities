import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.python.modules.thread.thread;

public class CAT1MylotoSignup {
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numberChar = "0123456789";
	static WebDriver driver;

	/* db info parameters */
	public static final String jdbcClassName = "com.ibm.db2.jcc.DB2Driver";
	public static final String url = "jdbc:db2://192.168.101.74:50000/ESI_DB";
	public static final String user = "gtkinst1";
	public static final String password = "gtkinst1";

	public static void main(String[] args) throws InterruptedException {
		String chromePath = "C:\\Users\\User\\workspace\\TestUtilities\\lib\\chromedriver.exe";
		String Email = generateEmail(10);
		String password = "password1";
		if (args.length > 0) {
			chromePath = args[0];
			Email = args[1];
		}
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
		// driver = new FirefoxDriver();
		signUp(driver, Email, password);

		Thread.sleep(2000);

		String activationId = getActivationId(Email);

		ConfirmActivation(driver, activationId);

		Login(driver, Email, password);
		String BankPrefix = GetBankPrefix();
		String BranchNumber = GetBankRange(BankPrefix);
		String AccountBody = GetAccountNumberBody();
		System.out.println(BankPrefix);
		System.out.println(BranchNumber);
		System.out.println(AccountBody);

		//BankRegister(driver, BankPrefix, BranchNumber, AccountBody);

		Thread.sleep(2000);
		// Close the browser
		// driver.quit();
	}

	public static void signUp(WebDriver driver, String email, String password)
			throws InterruptedException {
		driver.get("https://1.cat.mylotto.co.nz/sign-up/");

		driver.findElement(By.id("Form_SignupForm_FirstName")).sendKeys("lola");
		driver.findElement(By.id("Form_SignupForm_LastName")).sendKeys("test");

		driver.findElement(By.xpath("//*[@id='BirthdayDay']//*[.='Day']"))
				.click();
		driver.findElement(By.xpath("//*[@id='BirthdayDay']//li[.='22']"))
				.click();
		driver.findElement(By.xpath("//*[@id='BirthdayMonth']//*[.='Month']"))
				.click();
		driver.findElement(By.xpath("//*[@id='BirthdayMonth']//li[.='August']"))
				.click();
		driver.findElement(By.xpath("//*[@id='BirthdayYear']//*[.='Year']"))
				.click();
		driver.findElement(By.xpath("//*[@id='BirthdayYear']//li[.='1980']"))
				.click();

		driver.findElement(By.id("Form_SignupForm_Gender_F")).click();

		driver.findElement(
				By.xpath("//*[@id='LocationArea']//div[@class='content']"))
				.click();
		driver.findElement(
				By.xpath("//*[@id='LocationArea']//li[.='Auckland']")).click();

		Thread.sleep(1000);
		// driver.findElement(By.xpath("//*[@id='Location']//div[@class='content']")).click();
		// driver.findElement(By.xpath("//*[@id='Location']//li[.='Rodney']")).click();

		driver.findElement(By.id("Form_SignupForm_PhoneAreaCode")).sendKeys(
				"021");
		driver.findElement(By.id("Form_SignupForm_PhoneNumber")).sendKeys(
				"2964399");

		driver.findElement(By.id("Form_SignupForm_Email")).sendKeys(email);
		driver.findElement(By.id("Form_SignupForm_ConfirmEmail")).sendKeys(
				email);
		driver.findElement(By.id("Form_SignupForm_Password"))
				.sendKeys(password);
		driver.findElement(By.id("Form_SignupForm_ConfirmPassword")).sendKeys(
				password);

		driver.findElement(
				By.xpath("//*[@id='Question']//div[@class='content']")).click();
		driver.findElement(
				By.xpath("//*[@id='Question']//li[@data-item-value='2']"))
				.click();
		driver.findElement(By.id("Form_SignupForm_Answer")).sendKeys("XT");

		driver.findElement(By.id("Form_SignupForm_TermsAndConditionsAgreed"))
				.click();
		// driver.findElement(By.id("signupButton")).sendKeys(Keys.SPACE);

		driver.findElement(By.id("signupButton")).click();

	}

	public static void ConfirmActivation(WebDriver driver, String activationId)
			throws InterruptedException {
		String url = "https://1.cat.mylotto.co.nz/activation?activationid="
				+ activationId;
		driver.get(url);

		System.out.println("Activationv URL: " + url + "\t");
		Thread.sleep(2000);
		/*
		 * driver.findElement(By.id("loginForm_Password_")).sendKeys(password);
		 * driver.findElement(By.id("loginForm_action_login_")).click();
		 * Thread.sleep(2000);
		 */
	}

	public static void Login(WebDriver driver, String email, String password)
			throws InterruptedException {
		driver.get("https://1.cat.mylotto.co.nz");
		Thread.sleep(2000);

		driver.findElement(By.id("loginLink")).click();
		Thread.sleep(2000);
		System.out.println("Input Email: " + email + "\t");
		driver.findElement(By.id("loginForm_UserName")).clear();
		driver.findElement(By.id("loginForm_UserName")).sendKeys(email);
		driver.findElement(By.id("loginForm_Password")).sendKeys(password);
		driver.findElement(By.id("loginForm_action_login")).click();
	}

	public static void BankRegister(WebDriver driver, String Account1,
			String Account2, String Account3) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("Form_saveAccountSetUp_Account1")).sendKeys(
				Account1);
		driver.findElement(By.id("Form_saveAccountSetUp_Account2")).sendKeys(
				Account2);
		driver.findElement(By.id("Form_saveAccountSetUp_Account3")).sendKeys(
				Account3);
		driver.findElement(By.id("Form_saveAccountSetUp_Account4")).sendKeys(
				"000");
		driver.findElement(
				By.id("Form_saveAccountSetUp_action_saveAccountSetUp")).click();
	}

	// Generate the string with the length required
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	// Generate random gmail email address
	public static String generateEmail(int length) {
		String ccxl = generateString(length);
		StringBuffer sb = new StringBuffer(ccxl);
		sb.append("@autotest.com");
		System.out.println(sb);
		return sb.toString();
	}

	public static String getActivationId(String email) {

		/* data info parameters */
		Statement stmt = null;
		String query = "Select * From NZDEV.ES_SECURITY where NZDEV.ES_SECURITY.USER_NAME = '"
				+ email + "'";
		Connection connection = null;
		String activationId = "";
		try {
			// Load class into memory
			Class.forName(jdbcClassName);
			// Establish connection
			connection = DriverManager.getConnection(url, user, password);

			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// Extract data from result set
			while (rs.next()) {
				activationId = rs.getString("ACTIVATION_ID");
				System.out.println("Activation_ID: " + activationId + "\t");
			}

			// Clean-up environment
			rs.close();
			stmt.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				System.out.println("Connected successfully.");
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return activationId;

	}

	public static String GetAccountNumber() {
		String AccountPrefix = GetAccountNumberPrefix();
		String AccountBody = GetAccountNumberBody();
		String AccountSuffix = GetAccountNumberSuffix();
		String AccountNumber = AccountPrefix + AccountBody + AccountSuffix;
		String AccountNumberStr = AccountPrefix + "-" + AccountBody + "-"
				+ AccountSuffix;
		System.out.println(AccountNumberStr);
		return AccountNumber;
	}

	public static String GetAccountNumberBody() {
		int randomNum = GetRanNumber(100000, 999999);
		String validator = calcMod11(Integer.toString(randomNum));
		do {
			randomNum = GetRanNumber(100000, 999999);
			validator = calcMod11(Integer.toString(randomNum));
		} while (validator.equals("n/a"));

		String AccountNumBody = randomNum + validator;
		return AccountNumBody;
	}

	public static String calcMod11(String digStr) {
		int len = digStr.length();
		int sum = 0, rem = 0, baseNum = 0, weightNum = 0, USum = 0;
		String validator;
		int[] digArr = new int[len];
		for (int k = 1; k <= len; k++) // compute weighted sum
		{
			baseNum = Character.getNumericValue(digStr.charAt(k - 1));
			weightNum = (len + 1 - k + 1);
			USum = weightNum * baseNum;
			sum += USum;
			// System.out.println("No." + k + "  baseNum:" + baseNum +
			// " x weightNum:" + weightNum + "=" + USum + "    Sum:" + sum);
		}

		rem = sum % 11;
		// remainder is 0, subtraction is 11-0=11. if the check digit is 11 then
		// 0 is used as the check digit.
		System.out.println("remainder is" + rem);
		if (rem == 0)
			validator = "0";
		// remainder is 1, subtraction is 11-1=10. if the check digit is 10,
		// then X is frequently used as the check digit.
		else if (rem == 1)
			validator = "n/a";
		else
			validator = (new Integer(11 - rem)).toString();
		System.out.println("check digit is" + validator);
		return validator;
	}

	public static String GetBankPrefix() {
		String[] BankPrefixArr = { "01", "02", "03", "06", "08", "11" };
		int len = BankPrefixArr.length;
		int min = 0, max = len - 1, branchNum = 0;
		int ran = GetRanNumber(min, max);
		String BankPrefix = BankPrefixArr[ran];
		return BankPrefix;
	}

	public static String GetBankRange(String BankPrefix) {
		int branchNum;
		switch (BankPrefix) {
		case "01":
			branchNum = GetRanNumber(1, 999);
			break;
		case "02":
			branchNum = GetRanNumber(1, 999);
			break;
		case "03":
			branchNum = GetRanNumber(1, 999);
			break;
		case "06":
			branchNum = GetRanNumber(1, 999);
			break;
		case "08":
			branchNum = GetRanNumber(6500, 6599);
			break;
		case "11":
			branchNum = GetRanNumber(5000, 6499);
			break;
		case "12":
			branchNum = GetRanNumber(3000, 3499);
			break;
		case "13":
			branchNum = GetRanNumber(4900, 4999);
			break;
		case "14":
			branchNum = GetRanNumber(4700, 4799);
			break;
		case "15":
			branchNum = GetRanNumber(3900, 3999);
			break;
		case "16":
			branchNum = GetRanNumber(4400, 4499);
			break;
		case "17":
			branchNum = GetRanNumber(3300, 3399);
			break;
		case "18":
			branchNum = GetRanNumber(3500, 3599);
			break;
		case "19":
			branchNum = GetRanNumber(4600, 4649);
			break;
		case "20":
			branchNum = GetRanNumber(4800, 4899);
			break;
		case "21":
			branchNum = GetRanNumber(4100, 4199);
			break;
		case "22":
			branchNum = GetRanNumber(4000, 4049);
			break;
		case "23":
			branchNum = GetRanNumber(4300, 4349);
			break;
		case "24":
			branchNum = GetRanNumber(3700, 3799);
			break;
		case "25":
			branchNum = GetRanNumber(2500, 2599);
			break;
		case "29":
			branchNum = GetRanNumber(0000, 9999);
			break;
		case "30":
			branchNum = GetRanNumber(2900, 2956);
			break;
		case "31":
			branchNum = GetRanNumber(2800, 2849);
			break;
		case "38":
			branchNum = GetRanNumber(9000, 9499);
			break;

		default:
			branchNum = 4999;
			break;
		}

		String BranchNumber = LeftPaddingZeros(branchNum);
		return BranchNumber;
	}

	public static String GetAccountNumberPrefix() {
		String BankPrefix = GetBankPrefix();
		String BranchNumber = GetBankRange(BankPrefix);
		String branchPrefix = BankPrefix + BranchNumber;
		System.out.println("BranchNumber is" + BranchNumber);
		System.out.println("branchPrefix is" + branchPrefix);
		return branchPrefix;

	}

	public static String GetAccountNumberSuffix() {
		return "000";

	}

	public static int GetRanNumber(int min, int max) {
		int res = (int) (min + (Math.random() * (max - min)));
		return res;
	}

	public static String LeftPaddingZeros(int num) {
		String result = StringUtils.leftPad(Integer.toString(num), 4, "0");
		return result;
	}
}
