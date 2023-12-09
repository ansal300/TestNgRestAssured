package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class SetUp implements ITestListener {

    private ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();

    public void onStart(ITestContext context) {
        String filename=ExtentReport.getReportName();
        String fullPath=System.getProperty("user.dir")+"\\reports\\"+filename;
        extentReports=ExtentReport.initExtentReport(fullPath,"API Test Report","Test Execution");
    }
    public void onFinish(ITestContext context) {
        if(extentReports!=null)
            extentReports.flush();
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test=extentReports.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public static ExtentTest getTest()
    {
        return extentTest.get();
    }

}
