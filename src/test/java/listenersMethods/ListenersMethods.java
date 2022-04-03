package listenersMethods;

import com.codeborne.selenide.*;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.HeaderPage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ListenersMethods {

    private HeaderPage headerPage = Selenide.page(new HeaderPage());

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot(){
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
