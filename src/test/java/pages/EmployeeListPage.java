package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmployeeListPage {

	public static WebElement element = null;

	public static WebElement employeeList(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[text()='Employee List']"));
		return element;
	}

}
