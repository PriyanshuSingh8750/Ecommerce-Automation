package MiniProject;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    // Method to retrieve data from an Excel file
    @SuppressWarnings("resource")
    public static String getExcelData() throws IOException {
        // Getting the path of the Excel file located in the project directory
        String s = System.getProperty("user.dir") + "\\Excel" + "\\TestData.xlsx";

        // Creating a FileInputStream object to read the Excel file
        FileInputStream file = new FileInputStream(s);

        // Creating an instance of XSSFWorkbook to work with the Excel workbook
        XSSFWorkbook work = new XSSFWorkbook(file);

        // Accessing the first sheet (index 0) from the workbook
        XSSFSheet sheet = work.getSheetAt(0);

        // Accessing the first row of the sheet
        XSSFRow row = sheet.getRow(0);

        // Accessing the first cell of the row
        XSSFCell cell = row.getCell(0);

        // Retrieving the data from the cell and converting it to a string
        String data = cell.toString();

        // Returning the extracted data from the Excel cell
        return data;
    }
}
