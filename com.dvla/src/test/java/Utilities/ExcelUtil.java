package Utilities;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

public class ExcelUtil {

	public static String xlsFilePath;
	public static String xlsSheet;
	public static int xlsRowCount;
	public static int xlsColCount;
	public static int xlsActiveRowNum;
	public static String[] columnNames;
	public static String[] currentTestValues;
	public static String[][] ExcelData;

	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static XSSFCell Cell;
	public static XSSFRow Row;
	static boolean debug = true;

	// Returns the max number of used rows in the selected excel worksheet
	public static int getxlRowCount() {
		return xlsRowCount;
	}

	// This method is to set the File path and to open the Excel file, Pass
	// Excel File Path as Argument to this method
	public static void setExcelFile(String Path) {

		try {
			xlsFilePath = Path;

			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
            System.out.println(xlsFilePath);
			// Access the required test data file
			ExcelWBook = new XSSFWorkbook(ExcelFile);

		} catch (Exception e) {

			try {
				throw (e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// This method is to select the worksheet in the selected Excel file, Pass
	// sheet name as Argument to this method
	@SuppressWarnings("static-access")
	public static void setExcelSheet(int SheetNumber) {

		String CellData;

		try {
			ExcelWSheet = ExcelWBook.getSheetAt(0);

			xlsRowCount = ExcelWSheet.getLastRowNum();
			Row = ExcelWSheet.getRow(0);
			xlsColCount = Row.getLastCellNum();

			columnNames = new String[xlsColCount];

			for (int colNum = 0; colNum < xlsColCount; colNum++) {				
				Cell = Row.getCell(colNum, Row.CREATE_NULL_AS_BLANK);
				CellData = StringUtils.trim(Cell.getStringCellValue());
				columnNames[colNum] = CellData;
			}

		} catch (Exception e) {

			try {
				throw (e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// This function selects the Test Data row based on the testcaseID
	public static void setRow(int rowNumber) {

		try {
			currentTestValues = new String[xlsColCount];

			Row = ExcelWSheet.getRow(rowNumber);
			if (Row == null) {
				// This whole row is empty
				// Handle it as needed
				//System.out.println("Row Number '" + rowNumber
				//		+ "' NOT Found in the selected worksheet");
			} else {

				String CellData;

				xlsActiveRowNum = rowNumber;
				int Cols = xlsColCount;
				for (int colNum = 0; colNum < Cols; colNum++) {
					Cell = Row.getCell(colNum, Row.CREATE_NULL_AS_BLANK);
					if (Cell != null) {
						CellData = StringUtils.trim(Cell.getStringCellValue());
						currentTestValues[colNum] = CellData;
					}

				}
			}

		} catch (Exception e) {
			if (debug) {
				System.out.println("Exception Occurred: " + e.getMessage());
			}

		}
	}

	public static int getColumnNumber(String ColumnName) {
		int colNum;
		boolean found = false;
		ColumnName = StringUtils.trim(ColumnName);
		for (colNum = 0; colNum < xlsColCount; colNum++) {

			if (StringUtils.equals(columnNames[colNum], ColumnName)) {
				found = true;
				break;
			}
		}

		if (found) {
			return colNum;
		} else {
			return -1;
		}
	}

	// This method is to read the test data from the Excel cell based on the
	// column name
	// Prerequisite: Excel File, Sheet and testname is already set using the
	// other functions in this file
	public static String getCellData(String ColumnName) {
		String cellData = "";
		try {

			int colNumber = getColumnNumber(ColumnName);
			if (colNumber > -1) {
				cellData = currentTestValues[colNumber];
				if (cellData == null) {
				} else {
					cellData = StringUtils.trim(cellData);
				}
			} else {
				if (debug) {
					System.out.println("getCellData() - ColumnName "
							+ ColumnName + " not found in the sheet : "
							+ xlsSheet);
				}
			}
		} catch (Exception e) {
			if (debug) {
				System.out.println("Exception Occurred: " + e.getMessage());
			}
		}
		return cellData;
	}

	// This method reads the Excel Data to be used later
	public static String[][] Read_Data() {
		int rowNum, colNum;
		int rowCount = ExcelUtil.xlsRowCount;
		int colCount = ExcelUtil.xlsColCount;
		String CellValue="";
		ExcelData = new String[rowCount][colCount];
		try {
			for (rowNum = 0; rowNum < rowCount; rowNum++) {				
				Row = ExcelWSheet.getRow(rowNum + 1);
				for (colNum = 0; colNum < colCount; colNum++) {				
					Cell = Row.getCell(colNum, Row.CREATE_NULL_AS_BLANK);
					CellValue = StringUtils.trim(Cell.getStringCellValue());
					ExcelData[rowNum][colNum] = CellValue;
				}
			}
		} catch (Exception e) {
			if (debug) {
				System.out.println("Exception Occurred: " + e.getMessage());
			}
		}
		return ExcelData;
	}

}
