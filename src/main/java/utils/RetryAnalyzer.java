package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count=0;
    private static int maxCount = 0;
    static {
        maxCount = Integer.parseInt(System.getProperty("retryCount","1"));
    }


    @Override
    public boolean retry(ITestResult result) {
        if (count<maxCount){
            count++;
            System.out.println("Retrying test: " + result.getName() +
                    " Count: " + count);
            return true;
        }
        return false;
    }
}
