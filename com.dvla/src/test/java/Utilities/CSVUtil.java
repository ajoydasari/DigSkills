package Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVUtil {
	private static String FILE_PATH="";
	
	
	// This method is to set the File path and to open the Excel file, Pass
	// Excel File Path as Argument to this method
	public static void setCSVFile(String Path) {
			FILE_PATH = Path;
	}	
	
	public static String[][] Read_Data() {
        String[] currentrow;
        String[][] ExcelData=null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            BufferedReader br1 = new BufferedReader(new FileReader(FILE_PATH));
            String nextLine;
            int Row = 0;

            while (br1.readLine() != null) {
                Row++;
            }
            br1.close();

            ExcelData = new String[Row][3];
            int Row1 = 0;
            while ((nextLine = br.readLine()) != null) {
                currentrow = nextLine.split(",");
                ExcelData[Row1][0] = currentrow[0];
                ExcelData[Row1][1] = currentrow[1];
                ExcelData[Row1][2] = currentrow[2];
                Row1++;
            }
            br.close();

        } catch (IOException exception) {
            System.out.println(exception.getStackTrace());
        }
        return ExcelData;
    }
}

