import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class DataGenerator {
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numberChar = "0123456789";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String Anum = "012429927060";
		// System.out.println(Anum+calc(Anum));

		// System.out.println(Anum+calcMod10(Anum));
		GetAccountNumber();
		GetPhoneNumber();
		generateEmail();
	}

	public static String GetPhoneNumber() {
		String phoneNo = "027" + GetRanNumber(100000000, 999009999);
		// System.out.println(phoneNo);
		return phoneNo;
	}

	public static String GetAccountNumber() {
		String AccountPrefix = GetAccountNumberPrefix();
		String AccountBody = GetAccountNumberBody();
		String AccountSuffix = GetAccountNumberSuffix();
		String AccountNumber = AccountPrefix + AccountBody + AccountSuffix;
		String AccountNumberStr = AccountPrefix + "-" + AccountBody + "-"
				+ AccountSuffix;
		// System.out.println(AccountNumberStr);
		return AccountNumber;
	}

	public static String GetAccountNumberBody() {
		int randomNum = GetRanNumber(100000, 999999);
		String AccountNumBody = randomNum
				+ calcMod11(Integer.toString(randomNum));
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
		// System.out.println("remainder is" + rem);
		if (rem == 0)
			validator = "0";
		// remainder is 1, subtraction is 11-1=10. if the check digit is 10,
		// then X is frequently used as the check digit.
		else if (rem == 1)
			validator = "n/a";
		else
			validator = (new Integer(11 - rem)).toString();
		// System.out.println("check digit is" + validator);
		return validator;
	}

	public static String calcMod10(String digStr) {
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

		rem = sum % 10;
		// remainder is 0, subtraction is 10-0=10. if the check digit is 10 then
		// 0 is used as the check digit.
		// System.out.println("remainder is" + rem);
		if (rem == 0)
			validator = "0";

		else
			validator = (new Integer(10 - rem)).toString();
		// System.out.println("check digit is" + validator);
		return validator;
	}

	public static String GetBankPrefix() {
		String[] BankPrefixArr = { "01", "02", "03", "06", "08", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24", "25", "29", "30", "31", "38" };
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
			branchNum = GetRanNumber(1, 5699);
			break;
		case "02":
			branchNum = GetRanNumber(1, 1299);
			break;
		case "03":
			branchNum = GetRanNumber(1, 1999);
			break;
		case "06":
			branchNum = GetRanNumber(1, 1499);
			break;
		case "08":
			branchNum = GetRanNumber(0, 9999);
			break;
		case "11":
			branchNum = GetRanNumber(5000, 8999);
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
		// System.out.println("BranchNumber is" + BranchNumber);
		// System.out.println("branchPrefix is" + branchPrefix);
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
	public static String generateEmail( ) {
		int length = 11;
		String ccxl = generateString(length);
		StringBuffer sb = new StringBuffer("PerfTest."+ccxl);
		sb.append("@test.com");
		//System.out.println(sb);
		return sb.toString();
	}

}
