package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

import static Tests.BaseTest.wait;

public class HtmlTablePage {
    WebDriver driver;
    //Locators
    @FindBy(id = "customers")
    private WebElement Table;


    //Constructors
    public HtmlTablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Methods
    public String getTableCellText(WebElement table, int searchColumn,
                                   String searchText, int returnColumnText) {
        int index = 2;
        List<WebElement> rowElements;
        WebElement desiredElement;
        rowElements = table.findElements(By.xpath(".//tbody/tr/td[" + searchColumn + "]"));
        System.out.println(rowElements.size());
        for (WebElement element : rowElements) {
            if (!element.getText().equals(searchText)) {
                index++;
            } else {
                break;
            }
        }
        desiredElement = table.findElement(By.xpath("//tbody/tr[" + index + "]/td[" + returnColumnText + "]"));
        return desiredElement.getText();
    }

    public boolean verifyTableCellText(WebElement table, int searchColumn,
                                       String searchText, int returnColumnText, String expectedResult) throws IOException {
        boolean isEqual;
        String tableCellText;
        tableCellText = getTableCellText(table, searchColumn, searchText, returnColumnText);
        isEqual = tableCellText.equals(expectedResult);
        return isEqual;
    }


    public String getTableCellTextByXpath(WebElement table, int searchColumn,
                                          String searchText, int returnColumnText) {
        WebElement element;
        List<WebElement> list;
        int rowElement;
        element = table.findElement(By.xpath(".//tbody/tr/td[" + searchColumn + "]" + "[text()='" + searchText + "']"));
        list = table.findElements(By.xpath(".//tbody/tr/td[" + searchColumn + "]"));
        rowElement = list.indexOf(element) + 2;
        element = table.findElement(By.xpath("//tbody/tr[" + rowElement + "]/td[" + returnColumnText + "]"));
        return element.getText();

    }
}
