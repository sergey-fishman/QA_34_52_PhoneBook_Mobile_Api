package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("start test -> {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("test success -> {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable t = result.getThrowable();
        if (t != null) {
            logger.error("Test failed: {}\nMessage: {}",
                    result.getName(), t.getMessage());
        } else
            logger.error("Test failed: {}, Status: {}",
                    result.getName(), result.getStatus());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Throwable t = result.getThrowable();
        logger.warn("test skipped -> {}{}", result.getName(),
                t != null ? ", reason: " + t : "");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.error("test failed with timeout -> {}", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("{} start at {}", context.getName(), context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("{} finished at {}", context.getName(), context.getEndDate());
    }
}
