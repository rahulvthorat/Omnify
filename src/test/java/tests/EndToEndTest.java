package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.Base;
import pages.EmployeeListPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PIMPage;

public class EndToEndTest extends Base {

	@Test(priority = 1)
	public void Login() {

		logger.info("Enter the valid username");
		LoginPage.username(driver).sendKeys(util.Constant.username);
		logger.info("Enter the valid password");
		LoginPage.password(driver).sendKeys(util.Constant.password);
		logger.info("click on login button");
		LoginPage.login(driver).click();
		logger.info("Home Page");
		String Actualtitle = driver.getTitle();
		String Expectedtitle = "OrangeHRM";
		Assert.assertEquals(Actualtitle, Expectedtitle, "title is not matched");

	}

	@Test(dependsOnMethods = "Login")
	public void mouseHover() {
		Actions act = new Actions(driver);
		logger.info("mouseHover to Admin module");
		act.moveToElement(HomePage.adminModule(driver)).build().perform();
		logger.info("mouseHover to PIM module and click");
		act.moveToElement(HomePage.pimModule(driver)).click().build().perform();

	}

	@Test(dependsOnMethods = "mouseHover")
	public void addEmployee() {
		logger.info("click on addEmployee");
		PIMPage.addEmployee(driver).click();
	}

	@Test(dependsOnMethods = "addEmployee")
	public void employeeList() {
		logger.info("click on EmployeeList");
		EmployeeListPage.employeeList(driver).click();
		logger.info("Scroll and look for the employees added and verify with name.");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		js.executeScript("window.scrollBy(0,500)");

	}

	@Test(dependsOnMethods = "employeeList")
	public void logout() {
		logger.info("Logout from the dashboard");
		HomePage.logoutlink(driver).click();
		List<WebElement> list = driver.findElements(By.xpath("//ul[@role='menu']/li"));
		for (WebElement listitem : list) {

			if (listitem.getText().equals("Logout")) {
				listitem.click();
				break;
			}
		}

	}

}
