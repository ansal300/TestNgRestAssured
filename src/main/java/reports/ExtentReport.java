package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReport {

    public static ExtentReports reports;

    public static ExtentReports initExtentReport(String fileName,String reportName,String docTitle)
    {
        reports=new ExtentReports();
        ExtentSparkReporter spark=new ExtentSparkReporter(fileName);
        spark.config().setReportName(reportName);
        spark.config().setDocumentTitle(docTitle);
        spark.config().setTheme(Theme.DARK);
        spark.config().setEncoding("utf-8");

        reports.attachReporter(spark);

        return reports;
    }

    public static String getReportName() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime localDate = LocalDateTime.now();
        String formattedTime = dateTimeFormatter.format(localDate);
        String fileName = "TestReport" + formattedTime + ".html";
        return fileName;
    }

    public static void logPassDetails(String log)
    {
        SetUp.getTest().pass(MarkupHelper.createLabel(log, ExtentColor.GREEN));
    }

    public static void logFailDetails(String log)
    {
        SetUp.getTest().fail(MarkupHelper.createLabel(log, ExtentColor.RED));
    }

    public static void logInfoDetails(String log)
    {
        SetUp.getTest().info(MarkupHelper.createLabel(log, ExtentColor.GREY));
    }

    public static void logWarningDetails(String log)
    {
        SetUp.getTest().warning(MarkupHelper.createLabel(log, ExtentColor.YELLOW));
    }
}
