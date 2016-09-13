package BankAccountUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ValidateNZBankAccountFile {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelResauceFile;
	private static XSSFWorkbook ExcelResultFile;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static String ResauceFilePath;
	private static String ResultFilePath;

	private static String email;
	private static String BankAccount;
	private static String BankID;
	private static String BankBranch;
	private static String AccountBase;
	private static String BankSuffix;
	private static String BankCode;
	private static String result;
	private static String resultMsg;

	protected static String sheetName = "cat1_UserBankAccount_Java";

	protected static int sheetIndex = 0;
	private static int BankAccountIndex = 0;
	private static int BankCodeIndex = 1;
	private static int AccountBaseIndex = 2;
	private static int BankSuffixIndex = 3;
	private static int emailIndex = 4;
	private static int resultIndex = 5;
	private static int resultMsgIndex = 6;

	public static void main(String[] args) throws Exception {
		
		ResauceFilePath = "C:/Data/cat1_UserBankAccount_Java.xlsx";
		ResultFilePath = "C:/Data/cat1_UserBankAccount_Java_Result.xlsx";

		setExcelFile(ResauceFilePath);
		setSheetName(sheetName);
		int rowNum = getRowCount();
				
		//Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet ResultSheet = workbook.createSheet("BankValidateResultData");
        int ResultRownum = 0;
        
		for (int i = 1; i <= rowNum; i++) {
			System.out.println("\r\nNo: " + i + "\t");
			XSSFRow row = ExcelWSheet.createRow(i);
			
			email = getCellData(i, emailIndex);
			BankAccount = getCellData(i,BankAccountIndex);
			BankCode = getCellData(i, BankCodeIndex);
			AccountBase = getCellData(i, AccountBaseIndex);
			BankSuffix = getCellData(i, BankSuffixIndex);

			BankCode = BankAccountValidator.PaddingLeftWithZero(BankCode, 6);
			BankID = BankCode.substring(0, 2);
			BankBranch = BankCode.substring(2, 6);
			AccountBase = BankAccountValidator.PaddingLeftWithZero(AccountBase,
					8);
			BankSuffix = BankAccountValidator
					.PaddingLeftWithZero(BankSuffix, 4);

			boolean resBankID = BankAccountValidator.validateBankID(BankID);
			boolean resBankBranch = BankAccountValidator.validateBankBranch(
					BankID, BankBranch);
			boolean resBankAccount = BankAccountValidator
					.validateBankAccountBase(BankID, BankBranch, AccountBase,
							BankSuffix);
			String alg = BankAccountValidator.AlgorithmType;
			String actualCheckDigit = BankAccountValidator.ActualCheckDigit;
			String expectCheckDigit = BankAccountValidator.ExpectCheckDigit;
			
			System.out.println("resBankID: " + resBankID + " resBankBranch: " + resBankBranch + " resBankAccount: " + resBankAccount
					+ "\t");
			
			if (resBankID == true && resBankBranch == true
					&& resBankAccount == true) {

				result = "true";
				resultMsg = "Valid Bank Account";
				String msg = "Valid Bank Account! \r\n " + "AlgorithmType: "
						+ alg + " \r\n " + "actualCheckDigit: "
						+ actualCheckDigit + " \r\n " + "expectCheckDigit: "
						+ expectCheckDigit + " \r\n ";
				System.out.println("msg: " + msg + "\t");
			} else {
				result = "false";
				resultMsg = "Invalid Bank Account";
				String msg = "Invalid Bank Account! \r\n " + "AlgorithmType: "
						+ alg + " \r\n " + "actualCheckDigit: "
						+ actualCheckDigit + " \r\n " + "expectCheckDigit: "
						+ expectCheckDigit + " \r\n ";
				System.out.println("msg: " + msg + "\t");

			}
			XSSFCell Cell = row.createCell(emailIndex);
			Cell.setCellValue(email);
			setCellData(ResultSheet,row,emailIndex,email);
			setCellData(ResultSheet,row,BankAccountIndex,BankAccount);
			setCellData(ResultSheet,row,BankCodeIndex,BankCode);
			setCellData(ResultSheet,row,AccountBaseIndex,AccountBase);
			setCellData(ResultSheet,row,BankSuffixIndex,BankSuffix);
			setCellData(ResultSheet,row,resultIndex,result);
			setCellData(ResultSheet,row,resultMsgIndex,resultMsg);
			
			//setCellData(resultMsg, i, resultMsgIndex);
			System.out.println("BankAccount: " + BankAccount + " BankCode: "
					+ BankCode + "\t");
			System.out.println("BankID: " + BankID + " BankBranch: "
					+ BankBranch + " AccountBase: " + AccountBase
					+ " BankSuffix: " + BankSuffix + "\t");
			System.out.println("result: " + result + " resultMsg: " + resultMsg
					+ "\t");
		}
		
		try
        {
            //Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(ResultFilePath));
            workbook.write(out);
            out.close();
            System.out.println("write successfully !!!!");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
		

	}

	// This method is to set the File path and to open the Excel file
	// Pass Excel Path and SheetName as Arguments to this method
	public static void setExcelFile(String Path) throws Exception {
		try {

			System.out.println("Path: " + Path + "\t");
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelResauceFile = new XSSFWorkbook(ExcelFile);
		} catch (Exception e) {

		}
	}

	public static void setSheetName(String SheetName) throws Exception {
		try {
			ExcelWSheet = ExcelResauceFile.getSheet(SheetName);
			ExcelWSheet = ExcelResauceFile.getSheetAt(0);
			System.out.println("ExcelWSheet: " + ExcelWSheet.getSheetName()
					+ "\t");
		} catch (Exception e) {

		}
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			//System.out.println("RowNum: " + RowNum + " ColNum:" + ColNum +"\t");
			String CellValue = "";
			DataFormatter df = new DataFormatter();
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			switch (Cell.getCellType()) {
			case XSSFCell.CELL_TYPE_BOOLEAN:
				CellValue = Boolean.toString(Cell.getBooleanCellValue());
				//System.out.println("CELL_TYPE_BOOLEAN CellValue: " + CellValue + "\t");
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				String str = NumberToTextConverter.toText(Cell.getNumericCellValue());
				CellValue = str;
				//System.out.println("CELL_TYPE_NUMERIC CellValue: " + CellValue + "\t");
				break;
			case XSSFCell.CELL_TYPE_STRING:
				CellValue = Cell.getStringCellValue();
				//System.out.println("CELL_TYPE_STRING CellValue: " + CellValue + "\t");
				break;
			}

			return CellValue;
		} catch (Exception e) {
			return "";
		}

	}

	public static int getRowCount() {
		int number = ExcelWSheet.getLastRowNum() + 1;
		return number;
	}
	public static void setCellData(XSSFSheet ExcelWSheet,XSSFRow row, int ColNum, String value)
	{
		
		XSSFCell Cell = row.createCell(ColNum);
		Cell.setCellValue((String)value);
	}
	public static void setCellData(String value, int RowNum, int ColNum)
			throws Exception {
		Row = ExcelWSheet.getRow(RowNum);
		Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
		if (Cell == null) {
			Cell = Row.createCell(ColNum);
			Cell.setCellValue(value);
		} else {
			Cell.setCellValue(value);
		}
		
		// Constant variables Test Data path and Test Data file name
		FileOutputStream fileOut = new FileOutputStream(ResauceFilePath);
		ExcelResauceFile.write(fileOut); // fileOut.flush(); fileOut.close();
		ExcelResauceFile = new XSSFWorkbook(new
		FileInputStream(ResauceFilePath));
		 
	}

	public static boolean IsDigitsOnly(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}
}
