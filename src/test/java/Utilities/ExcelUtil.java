package Utilities;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelUtil(String excelPath, String sheetName) {
        try {
            String projectPath = System.getProperty("user.dir");
            workbook = new XSSFWorkbook(projectPath + "\\src\\test\\resources\\Excel\\tableData.xlsx");
            sheet = workbook.getSheet(sheetName);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public static int getRowCount() {
        int rowCount = 0;
        try {
            rowCount = sheet.getPhysicalNumberOfRows();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return rowCount;
    }

    public static int getColCount() {
        int colCount = 0;
        try {
            colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return colCount;
    }

    public static String getCellDataString(int rowNum, int colNum) {
        String cellData = null;
        try {
            cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return cellData;
    }

    public static double getCellDataNumber(int rowNum, int colNum) {
        double cellData = 0;
        try {
            cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return cellData;
    }

    public static Object[][] testData() {
        DataFormatter formatter = new DataFormatter();
        int rowNum = getRowCount();
        int colNum = getColCount();
        Object array[][] = new Object[rowNum - 1][colNum];
        for (int i = 1; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                array[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }
        return array;
    }
}
