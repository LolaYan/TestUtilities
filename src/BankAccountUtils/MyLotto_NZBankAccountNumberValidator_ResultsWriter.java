package BankAccountUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyLotto_NZBankAccountNumberValidator_ResultsWriter {

	public static String strConn = "Server=192.168.101.87:50000;Database=ESI_DB;UID=gtkinst1;PWD=gtkinst1;";

	/* db info parameters */
	public static String jdbcClassName = "com.ibm.db2.jcc.DB2Driver";
	public static String url = "jdbc:db2://192.168.101.87:50000/ESI_DB";
	public static String user = "gtkinst1";
	public static String password = "gtkinst1";

	public static String strLottoUserSql = "SELECT BANK_ACCT_NUMBER, BANK_CODE, BANK_ACCOUNT,MODIFIED_BY, CREATION_DT, USER_TYPE, USER_ID, LOGIN_STATUS, WAGER_STATUS , WALLET_STATUS, BANK_ACCT_TYPE FROM NZDEV.ES_SECURITY c inner join (        SELECT * FROM NZDEV.ES_BANK_ACCOUNT WHERE CREATION_DT IN  (     SELECT MAX(CREATION_DT)    FROM NZDEV.ES_BANK_ACCOUNT GROUP BY MODIFIED_BY HAVING COUNT(MODIFIED_BY) > 1 OR COUNT(MODIFIED_BY) = 1)) m on c.USER_NAME = m.MODIFIED_BY WHere USER_TYPE =5";
	public static String BANK_ACCT_NUMBER;
	public static String BANK_CODE;
	public static String BANK_ACCOUNT;
	public static String MODIFIED_BY;
	public static String CREATION_DT;
	public static int USER_TYPE;
	public static String USER_ID;
	public static String LOGIN_STATUS;
	public static String WAGER_STATUS;
	public static String WALLET_STATUS;
	public static String BANK_ACCT_TYPE;

	public static String BankID;
	public static String BankBranch;
	public static String BankAccount;
	public static String BankSuffix;

	public static String result;
	public static String resultMsg;

	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String FILE_HEADER = "BANK_ACCT_NUMBER,BANK_CODE,BANK_ACCOUNT,MODIFIED_BY,USER_ID,result,resultMsg";

	public static String writerfileName = "CAT1_UserBankAccountValidation_All_Result.csv";
	public static String writerfileName_InvalidBank = "CAT1_UserBankAccountValidation_Invalid_Result.csv";
	public static FileWriter fileWriter = null;
	public static FileWriter fileWriter_InvalidBank = null;

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("db Connect info:" + strConn);			
		} else {
			url = args[0];
			user = args[1];
			password = args[2];
		}

		System.out.println("db Connect url:" + url);
		System.out.println("db Connect user:" + user);
		System.out.println("db Connect password:" + password);
		System.out.println("db query string:" + strLottoUserSql);
		LottoBankAccountNumberValidator_AllResult_Simple();
		
	}

	public static void LottoBankAccountNumberValidator_AllResult_Simple()
			throws IOException {
		createWriteFile_AllResult();
		createWriteFile_FalseResult();

		/* data info parameters */
		Statement stmt = null;
		String query = strLottoUserSql;
		System.out.println("query: " + query + "\t");
		Connection connection = null;
		try {
			// Load class into memory
			Class.forName(jdbcClassName);
			// Establish connection
			connection = DriverManager.getConnection(url, user, password);

			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				BANK_ACCT_NUMBER = rs.getString("BANK_ACCT_NUMBER");
				BANK_CODE = rs.getString("BANK_CODE");
				BANK_ACCOUNT = rs.getString("BANK_ACCOUNT");
				MODIFIED_BY = rs.getString("MODIFIED_BY");
				CREATION_DT = rs.getString("CREATION_DT");
				USER_TYPE = rs.getInt("USER_TYPE");
				USER_ID = rs.getString("USER_ID");

				System.out.println("No." + rs.getRow()
						+ "   BANK_ACCT_NUMBER: " + BANK_ACCT_NUMBER
						+ " BANK_CODE: " + BANK_CODE + " BANK_ACCOUNT: "
						+ BANK_ACCOUNT + " MODIFIED_BY: " + MODIFIED_BY
						+ " CREATION_DT: " + CREATION_DT + "\t");

				if (BANK_ACCT_NUMBER == ""
						|| BANK_CODE == ""
						|| BANK_ACCOUNT == ""
						|| BankAccountValidator
								.isStringNumeric(BANK_ACCT_NUMBER) == false
						|| BankAccountValidator.isStringNumeric(BANK_CODE) == false
						|| BankAccountValidator.isStringNumeric(BANK_ACCOUNT) == false
						|| BANK_CODE.length() != 6
						|| BANK_ACCT_NUMBER.length() <= 13) {
					result = "false";
					resultMsg = "Invalid Bank Value or length";
				} else {
					// Validate Bank Account
					BankID = BANK_CODE.substring(0, 2);
					BankBranch = BANK_CODE.substring(2, 6);
					BankAccount = BANK_ACCOUNT;
					int tlength = BANK_CODE.length() + BANK_ACCOUNT.length();

					System.out.println("BankID: " + BankID + "\t");
					System.out.println("BankBranch: " + BankBranch + "\t");
					System.out.println("BankAccount: " + BankAccount + "\t");
					System.out.println("BankSuffix: " + BankSuffix + "\t");
					// System.out.println("tlength: " + tlength + "\t");

					BankSuffix = BANK_ACCT_NUMBER.substring(tlength,
							BANK_ACCT_NUMBER.length());

					LottoBankAccountNumberValidator();

				}

				System.out.println("result: " + result + "\t");
				System.out.println("resultMsg: " + resultMsg + "\t");

				writeFileLine(fileWriter);

				System.out.println("No." + rs.getRow() + "Write Done!\t");

				if (result == "false") {

					writeFileLine(fileWriter_InvalidBank);
				}
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
			try {
				closeFile_AllResult();
				closeFile_FalseResult();
			} catch (IOException e) {
				System.out
						.println("Error while flushing/closing fileWriter !!!");
				System.out.println("db Connect info:" + strConn);
				System.out.println("db query string:" + strLottoUserSql);
				e.printStackTrace();
			}
		}

	}

	public static void LottoBankAccountNumberValidator() {
		boolean resBankID = BankAccountValidator.validateBankID(BankID);
		boolean resBankBranch = BankAccountValidator.validateBankBranch(BankID,
				BankBranch);
		boolean resBankAccount = BankAccountValidator.validateBankAccountBase(
				BankID, BankBranch, BankAccount, BankSuffix);
		String alg = BankAccountValidator.AlgorithmType;
		String actualCheckDigit = BankAccountValidator.ActualCheckDigit;
		String expectCheckDigit = BankAccountValidator.ExpectCheckDigit;

		System.out.println("AlgorithmType: " + alg + "\t");
		System.out.println("actualCheckDigit: " + actualCheckDigit + "\t");
		System.out.println("expectCheckDigit: " + expectCheckDigit + "\t");

		if (resBankID == false) {
			result = "false";
			resultMsg = "Invalid Bank ID";
		} else if (resBankBranch == false) {
			result = "false";
			resultMsg = "Invalid Bank Branch";
		} else if (resBankID == true && resBankBranch == true
				&& resBankAccount == true) {

			result = "true";
			resultMsg = "Valid Bank Account";
			String msg = "Valid Bank Account! \r\n " + "AlgorithmType: " + alg
					+ " \r\n " + "actualCheckDigit: " + actualCheckDigit
					+ " \r\n " + "expectCheckDigit: " + expectCheckDigit
					+ " \r\n ";
		} else {
			result = "false";
			resultMsg = "Invalid Bank Account";
			String msg = "Invalid Bank Account! \r\n " + "AlgorithmType: "
					+ alg + " \r\n " + "actualCheckDigit: " + actualCheckDigit
					+ " \r\n " + "expectCheckDigit: " + expectCheckDigit
					+ " \r\n ";

		}
	}

	public static void writeFileLine(FileWriter fw) throws IOException {
		fw.append(BANK_ACCT_NUMBER);
		fw.append(COMMA_DELIMITER);
		fw.append(BANK_CODE);
		fw.append(COMMA_DELIMITER);
		fw.append(BANK_ACCOUNT);
		fw.append(COMMA_DELIMITER);
		fw.append(MODIFIED_BY);
		fw.append(COMMA_DELIMITER);
		fw.append(USER_ID);
		fw.append(COMMA_DELIMITER);
		fw.append(result);
		fw.append(COMMA_DELIMITER);
		fw.append(resultMsg);
		fw.append(COMMA_DELIMITER);
		fw.append(NEW_LINE_SEPARATOR);
	}

	public static void createWriteFile_AllResult() throws IOException {
		fileWriter = new FileWriter(writerfileName);
		// Write the CSV file header
		fileWriter.append(FILE_HEADER.toString());
		// Add a new line separator after the header
		fileWriter.append(NEW_LINE_SEPARATOR);
	}

	public static void closeFile_AllResult() throws IOException {

		fileWriter.flush();
		fileWriter.close();
	}

	public static void createWriteFile_FalseResult() throws IOException {
		fileWriter_InvalidBank = new FileWriter(writerfileName_InvalidBank);
		// Write the CSV file header
		fileWriter_InvalidBank.append(FILE_HEADER.toString());
		// Add a new line separator after the header
		fileWriter_InvalidBank.append(NEW_LINE_SEPARATOR);

	}

	public static void closeFile_FalseResult() throws IOException {

		fileWriter_InvalidBank.flush();
		fileWriter_InvalidBank.close();
	}
}
