package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	public static WebElement element = null;

	public static WebElement adminModule(WebDriver driver) {
		element = driver.findElement(By.xpath("//ul[@class='oxd-main-menu']/li[1]"));
		return element;
	}

	public static WebElement pimModule(WebDriver driver) {
		element = driver.findElement(By.xpath("//ul[@class='oxd-main-menu']/li[2]"));
		return element;
	}

	public static WebElement logoutlink(WebDriver driver) {
		element = driver.findElement(By.xpath("//p[text()='Febrian Rizky']"));
		return element;
	}

}
