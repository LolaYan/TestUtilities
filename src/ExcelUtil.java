

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Project : AutoLotto Created : java Date : 09/07/15 Note : Some code may come
 * from the internet Author: Lola Yan
 */
public class ExcelUtil {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	private static String FilePath = "C:/Data/PerfData.xlsx";

	public static void main(String[] args) throws Exception {
		setExcelFile(FilePath);
		
		
	}
	// This method is to set the File path and to open the Excel file
	// Pass Excel Path and SheetName as Arguments to this method
	public static void setExcelFile(String Path) throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			// ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {

		}
	}

	// This method is to read the test data from the Excel cell
	// In this we are passing parameters/arguments as Row Num and Col Num
	public static String getCellData(int RowNum, int ColNum, String SheetName)
			throws Exception {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			return "";
		}

	}

	// This method is to get the row count used of the excel sheet
	public static int getRowCount(String SheetName) {
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number = ExcelWSheet.getLastRowNum() + 1;
		return number;
	}

	// This method is to get the Row number of the test case
	// This methods takes three arguments(Test Case name , Column Number & Sheet
	// name)
	public static int getRowContains(String sTestCaseName, int colNum,
			String SheetName) throws Exception {
		int i;
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int rowCount = ExcelUtil.getRowCount(SheetName);
		for (i = 0; i < rowCount; i++) {
			if (ExcelUtil.getCellData(i, colNum, SheetName).equalsIgnoreCase(
					sTestCaseName)) {
				break;
			}
		}
		return i;
	}

	// This method is to get the count of the test steps of test case
	// This method takes three arguments (Sheet name, Test Case Id & Test case
	// row number)
	public static int getTestStepsCount(String SheetName, String sTestCaseID,
			int iTestCaseStart) throws Exception {
		int columnNo = 1;
		for (int i = iTestCaseStart; i <= ExcelUtil.getRowCount(SheetName); i++) {
			if (!sTestCaseID.equals(ExcelUtil.getCellData(i,
					columnNo, SheetName))) {
				int number = i - iTestCaseStart;
				return number;
			}
		}
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		int number = ExcelWSheet.getLastRowNum() + 1;
		return number;
	}

	public static void setCellData(String Result, int RowNum, int ColNum,
			String SheetName) throws Exception {

		ExcelWSheet = ExcelWBook.getSheet(SheetName);
		Row = ExcelWSheet.getRow(RowNum);
		Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
		if (Cell == null) {
			Cell = Row.createCell(ColNum);
			Cell.setCellValue(Result);
		} else {
			Cell.setCellValue(Result);
		}
		// Constant variables Test Data path and Test Data file name
		FileOutputStream fileOut = new FileOutputStream(FilePath);
		ExcelWBook.write(fileOut);
		// fileOut.flush();
		fileOut.close();
		ExcelWBook = new XSSFWorkbook(new FileInputStream(FilePath));

	}

}
