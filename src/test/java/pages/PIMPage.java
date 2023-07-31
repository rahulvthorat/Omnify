package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PIMPage {

	public static WebElement element = null;

	public static WebElement addEmployee(WebDriver driver) {
		element = driver.findElement(By.xpath("//nav[@role='navigation']/ul/li[3]"));
		return element;
	}

}
