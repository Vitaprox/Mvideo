package hooks;

import com.codeborne.selenide.Configuration;
import listenersMethods.ListenersMethods;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class Hooks {

    @BeforeSuite
    public void configOption() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 8000;
    }

    @BeforeMethod
    public void openHome() {
        open("https://www.mvideo.ru/");
    }

    @AfterMethod
    public void closeDriver() {
        closeWebDriver();
    }

}
