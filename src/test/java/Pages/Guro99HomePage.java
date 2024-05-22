package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static Tests.BaseTest.wait;

public class Guro99HomePage {

    WebDriver driver;

    //Locators
    @FindBy(xpath = "//div[@class = 'collapse navbar-collapse']/ul/li/a")
    private List<WebElement> topMenu;
    @FindBy(xpath = "//div[@class = 'collapse navbar-collapse']/ul/li/ul/li")
    private List<WebElement> subMenu;


    //Constructors
    public Guro99HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Methods
    public void chooseMenuByOneParameters(String chosenMenu) {

        String[] arr = chosenMenu.split(";");
        if (arr.length == 2) {
            chooseMenuByTwoParameters(arr[0], arr[1]);
        } else {
            chooseMenuByTwoParameters(chosenMenu, "");
        }
    }

    public void chooseMenuByTwoParameters(String topMenuName, String subMenuName) {
        boolean isTopMenuVerify = false;
        for (WebElement element : topMenu) {
            if (element.getText().equals(topMenuName)) {
                isTopMenuVerify = true;
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                break;
            }
        }
        Assert.assertTrue(isTopMenuVerify);
        if (subMenuName != "") {
            boolean isSubMenuVerify = false;
            for (WebElement element : subMenu) {
                if (element.getText().equals(subMenuName)) {
                    isSubMenuVerify = true;
                    element.click();
                    break;
                }
            }
            Assert.assertTrue(isSubMenuVerify);
        }
    }
}
