package BankAccountUtils;

public class ValidateNZBankAccount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * args[0]=""; args[1]=""; args[2]=""; args[3]="";
		 */
		if (args.length == 4) {

			System.out
					.println("Please input 4 variables - the BankID, BankBranch, BankAccountBase, Bank Suffix.");
			System.out
					.println("Default BankAccountBase Length Setting is 7, Default Bank Suffix Setting is 000.");
			if (args[0].equals("7") || args[0].equals("8"))
				BankAccountGenerator.accountBaseLengthSetting = args[0];
			if (args[1].equals("R4") || args[1].equals("R3")
					|| args[1].equals("000") || args[1].equals("0000"))
				BankAccountGenerator.suffixSetting = args[1];
		} else {
			/*
			String BankID = "09";
			String BankBranch = "0000";
			String BankAccount = "3232009";
			String BankSuffix = "0715";
*/

			String BankID = "26";
			String BankBranch = "2685";
			String BankAccount = "4643252";
			String BankSuffix = "0839";
			
			/*
			 * BankID = args[0]; BankBranch = args[1]; BankAccount = args[2];
			 * BankSuffix = args[3];
			 */
			if (BankSuffix == "" || BankAccount == "" || BankBranch == ""
					|| BankID == "") {
				String msg = "Please input  \r\n " + "BankID - 2 digits \r\n "
						+ "BankBranch - 4 digits \r\n "
						+ "BankAccount - 7 or 8 digits \r\n "
						+ "BankSuffix - 3 or 4 digits \r\n ";
				System.out.println(msg);
			} else {
				boolean resBankID = BankAccountValidator.validateBankID(BankID);
				boolean resBankBranch = BankAccountValidator
						.validateBankBranch(BankID, BankBranch);
				boolean resBankAccount = BankAccountValidator
						.validateBankAccountBase(BankID, BankBranch,
								BankAccount, BankSuffix);
				String alg = BankAccountValidator.AlgorithmType;
				String actualCheckDigit = BankAccountValidator.ActualCheckDigit;
				String expectCheckDigit = BankAccountValidator.ExpectCheckDigit;
				if (resBankID == false) {
					String msg = "Invalid Bank ID - " + BankID;
					System.out.println(msg);
				} else if (resBankBranch == false) {
					String msg = "Invalid Bank Branch - " + BankBranch;
					System.out.println(msg);
				} else if (resBankID == true && resBankBranch == true
						&& resBankAccount == true) {

					String msg = "Valid Bank Account! \r\n "
							+ "AlgorithmType: " + alg + " \r\n "
							+ "actualCheckDigit: " + actualCheckDigit
							+ " \r\n " + "expectCheckDigit: "
							+ expectCheckDigit + " \r\n ";
					System.out.println(msg);
				} else {
					String msg = "Invalid Bank Account! \r\n "
							+ "AlgorithmType: " + alg + " \r\n "
							+ "actualCheckDigit: " + actualCheckDigit
							+ " \r\n " + "expectCheckDigit: "
							+ expectCheckDigit + " \r\n ";
					System.out.println(msg);

				}
			}
		}
	}

	public static boolean IsDigitsOnly(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}
}
