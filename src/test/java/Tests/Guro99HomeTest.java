package Tests;

import Pages.Guro99HomePage;
import org.testng.annotations.Test;

public class Guro99HomeTest extends BaseTest {

    Guro99HomePage guro99HomePage;

    @Test
    public void chooseMenu() {
        guro99HomePage = new Guro99HomePage(driver);
        guro99HomePage.chooseMenuByOneParameters("Selenium;Login");
    }
}
