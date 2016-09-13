package Excel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.NumberToTextConverter;

import BankAccountUtils.BankAccountGenerator;
import BankAccountUtils.BankAccountValidator;

public class CsvFileHandler {
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	// BankAccount attributes index
	private static final int BankAccount_bankAccount_IDX = 0;
	private static final int BankAccount_bankCode_IDX = 1;
	private static final int BankAccount_accountBase_IDX = 2;
	private static final int BankAccount_bankSuffix_IDX = 3;
	private static final int BankAccount_email_IDX = 4;
	private static final int BankAccount_result_IDX = 5;
	private static final int BankAccount_resultMsg_IDX = 6;

	//
	private static String email;
	private static String BankAccount;
	private static String BankID;
	private static String BankBranch;
	private static String AccountBase;
	private static String BankSuffix;
	private static String BankCode;
	private static String result = "UNKOWN";
	private static String resultMsg = "UNKOWN";

	protected static String readfileName;
	protected static String writerfileName;

	// Create a new list of student to be filled by CSV file data
	public static List<BankAccount> bankAccountsList = new ArrayList<BankAccount>();

	// CSV file header
	private static final String FILE_HEADER = "bankAccount,bankCode,accountBase,bankSuffix,email,result,resultMsg";

	public static void main(String[] args) {
		String readfileName;
		String writerfileName;
		readfileName = "C:/Data/cat1_UserBankAccount_Java.csv";
		writerfileName = "UserBankAccountResult.csv";
		if (args.length != 1) {

			System.out
					.println("Please put the csv file at the same folder of Validator file.");
			System.out
					.println("Please input the whole filename, e.g. cat1_UserBankAccount_Java.csv or C:/Data/cat1_UserBankAccount_Java.csv");
		} else {
			readfileName = args[0];
			CsvFileReader(readfileName);
			CsvFileWriter(writerfileName);
		}
	}

	public static void CsvFileReader(String fileName) {
		BufferedReader fileReader = null;
		DataFormatter df = new DataFormatter();

		try {

			String line = "";

			// Create the file reader
			fileReader = new BufferedReader(new FileReader(fileName));

			// Read the CSV file header to skip it
			fileReader.readLine();

			// Read the file line by line starting from the second line
			while ((line = fileReader.readLine()) != null) {
				// Get all tokens available in line
				String[] tokens = line.split(COMMA_DELIMITER);
				if (tokens.length > 0) {
					// Create a new student object and fill his data
					BankAccount bankAccount = new BankAccount(
							tokens[BankAccount_bankAccount_IDX],
							tokens[BankAccount_bankCode_IDX],
							tokens[BankAccount_accountBase_IDX],
							tokens[BankAccount_bankSuffix_IDX],
							tokens[BankAccount_email_IDX], "", "");
					bankAccountsList.add(bankAccount);
				}
			}

			// Print the new student list
			for (BankAccount bankAccount : bankAccountsList) {
				System.out.println(bankAccount.toString());
			}

		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader !!!");
				e.printStackTrace();
			}
		}

	}

	public static void CsvFileWriter(String writerfileName) {

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(writerfileName);

			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write a new student object list to the CSV file
			for (BankAccount bankAccount : bankAccountsList) {
				BankAccount = bankAccount.getBankAccount();
				BankCode = bankAccount.getBankCode();
				AccountBase = bankAccount.getAccountBase();
				// BankSuffix = bankAccount.getBankSuffix();
				int tlength = BankCode.length() + AccountBase.length();
				if (BankAccount.length() >= tlength) {
					BankSuffix = BankAccount.substring(tlength,
							BankAccount.length());

					//System.out.println("BankSuffix1:" + BankSuffix);
				} else {
					BankSuffix = bankAccount.getBankSuffix();
				}
				if (BankAccount == ""
						|| BankCode == ""
						|| AccountBase == ""
						|| BankAccountValidator.isStringNumeric(BankAccount) == false
						|| BankAccountValidator.isStringNumeric(BankCode) == false
						|| BankAccountValidator.isStringNumeric(AccountBase) == false
						|| BankCode.length() != 6 || BankSuffix.length() > 4
						|| AccountBase.length() > 8
						|| BankAccount.length() > 18
						|| BankAccount.length() <= 13) {
					result = "false";
					resultMsg = "Invalid Bank length or no digit";
				} else {

					bankAccountValidate(BankCode, AccountBase, BankSuffix);
				}

				fileWriter.append(bankAccount.getBankAccount());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(BankCode);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(AccountBase);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(BankSuffix);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(bankAccount.getEmail());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(result);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(resultMsg);
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(NEW_LINE_SEPARATOR);

			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out
						.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}

		}
	}

	public static void bankAccountValidate(String bankcode, String accountbase,
			String banksuffix) {
		BankCode = BankAccountValidator.PaddingLeftWithZero(bankcode, 6);
		BankID = BankCode.substring(0, 2);
		BankBranch = BankCode.substring(2, 6);
		AccountBase = BankAccountValidator.PaddingLeftWithZero(accountbase, 8);
		BankSuffix = BankAccountValidator.PaddingLeftWithZero(banksuffix, 4);

		boolean resBankID = BankAccountValidator.validateBankID(BankID);
		boolean resBankBranch = BankAccountValidator.validateBankBranch(BankID,
				BankBranch);
		boolean resBankAccount = BankAccountValidator.validateBankAccountBase(
				BankID, BankBranch, AccountBase, BankSuffix);
		String alg = BankAccountValidator.AlgorithmType;
		String actualCheckDigit = BankAccountValidator.ActualCheckDigit;
		String expectCheckDigit = BankAccountValidator.ExpectCheckDigit;

		System.out.println("resBankID: " + resBankID + " resBankBranch: "
				+ resBankBranch + " resBankAccount: " + resBankAccount + "\t");

		if (resBankID == true && resBankBranch == true
				&& resBankAccount == true) {

			result = "true";
			resultMsg = "Valid Bank Account";
			String msg = "Valid Bank Account! \r\n " + "AlgorithmType: " + alg
					+ " \r\n " + "actualCheckDigit: " + actualCheckDigit
					+ " \r\n " + "expectCheckDigit: " + expectCheckDigit
					+ " \r\n ";
			System.out.println("msg: " + msg + "\t");
		} else {
			result = "false";
			resultMsg = "Invalid Bank Account";
			String msg = "Invalid Bank Account! \r\n " + "AlgorithmType: "
					+ alg + " \r\n " + "actualCheckDigit: " + actualCheckDigit
					+ " \r\n " + "expectCheckDigit: " + expectCheckDigit
					+ " \r\n ";
			System.out.println("msg: " + msg + "\t");

		}
	}
}
