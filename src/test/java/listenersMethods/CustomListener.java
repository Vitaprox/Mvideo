package listenersMethods;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;


public class CustomListener extends AllureTestNg {

    private ListenersMethods listenersMethods = new ListenersMethods();

    @Override
    public void afterInvocation(final IInvokedMethod method, final ITestResult testResult){
        if (testResult.getStatus() != ITestResult.SUCCESS){
            listenersMethods.takeScreenshot();
        }
    }

}
