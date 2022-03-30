package hooks;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Hooks {

    @BeforeSuite
    public void configOption(){
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 60000;
    }

}
