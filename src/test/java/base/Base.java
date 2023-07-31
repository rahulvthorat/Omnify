package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import util.Action;

public class Base {

	private static final ExtentColor ExtentColor = null;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	public static WebDriver driver;
	public static Properties prop;

	@BeforeSuite(groups = { "Sanity", "Smoke", "Regression" })
	public void loadconfig() throws IOException {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(".//configuration//config.Properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public static void initialization() throws InterruptedException {
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(util.Constant.url);
		Action.implicitWait(driver, 20);
		Action.pageLoadTimeOut(driver, 10);

	}

	@BeforeTest
	public void extentReport() {
		sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + File.separator + "reports" + File.separator + "SDET");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		extent.setSystemInfo("HostName", "Rahul Thorat");
		extent.setSystemInfo("UserName", "*****");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("SDET");
	}

	@BeforeMethod
	public void beforMethod(Method testMethod) throws InterruptedException {
		logger = extent.createTest(testMethod.getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " -Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " -Test Case Failed", ExtentColor.RED));
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " -Test Case Skipped", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " -Test Case Pass", ExtentColor.GREEN));
		}
		;
	}

	@AfterSuite
	public void afterSuit() {
		extent.flush();
		driver.close();
	}
}
