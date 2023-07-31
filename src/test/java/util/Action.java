package util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.Base;

public class Action extends Base {

	public static void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
	}

	public static void explicitWait(WebDriver driver, WebElement element, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}