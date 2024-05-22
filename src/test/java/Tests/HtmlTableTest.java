package Tests;

import Pages.HtmlTablePage;
import Utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class HtmlTableTest extends BaseTest {

    WebElement element;

    HtmlTablePage htmlTablePage;
    By table = By.id("customers");

    @Test(dataProvider = "getData")
    public void tableCheck(String searchColumn, String searchText, String returnColumnText, String expectedResult) throws IOException {
        boolean isVerify;
        element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(table)));
        htmlTablePage = new HtmlTablePage(driver);
        int searchColumn1 = Integer.parseInt(searchColumn);
        int returnColumnText1 = Integer.parseInt(returnColumnText);
        isVerify = htmlTablePage.verifyTableCellText(element, searchColumn1, searchText, returnColumnText1, expectedResult);
        Assert.assertTrue(isVerify);
    }

    @DataProvider()
    public Object[][] getData() {
        ExcelUtil excel = new ExcelUtil("\\src\\test\\resources\\Excel\\tableData.xlsx", "sheet1");
        Object array[][] = excel.testData();
        System.out.println(array.length);
        return array;
    }
}
