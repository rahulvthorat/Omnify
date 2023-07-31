package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public static WebElement element = null;

	public static WebElement username(WebDriver driver) {
		element = driver.findElement(By.name("username"));
		return element;
	}

	public static WebElement password(WebDriver driver) {
		element = driver.findElement(By.name("password"));
		return element;
	}

	public static WebElement login(WebDriver driver) {
		element = driver.findElement(By.xpath("//button[@type='submit']"));
		return element;
	}
}
