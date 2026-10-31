package mobile;

import config.AppiumConfig;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.TestNGListener;

@Listeners(TestNGListener.class)

public class TestBase {
    protected static AppiumDriver driver;

    @BeforeMethod
    public void setup(){
        driver = AppiumConfig.createAppiumDriver();
    }

    @AfterMethod()
    public void teardown(){
        if (driver != null)
            driver.quit();
    }
}
