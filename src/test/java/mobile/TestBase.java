package mobile;

import config.AppiumConfig;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    protected static AppiumDriver driver;

    @BeforeMethod
    public void setup(){
        driver = AppiumConfig.createAppiumDriver();
    }

    @AfterMethod(enabled = false)
    public void teardown(){
        if (driver != null)
            driver.quit();
    }
}
