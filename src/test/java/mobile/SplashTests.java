package mobile;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

import java.time.Duration;

public class SplashTests extends TestBase {

    @Test
    public void versionPositiveTest(){
        Assert.assertTrue(new SplashScreen(driver).validateVersionText
                        ("Version 1.0.0"), "Version 1.0.0");
    }
}
