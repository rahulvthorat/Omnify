package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddEmployeePage {
	
	public static WebElement element = null;
	
	public static WebElement firstName(WebDriver driver) {
		element=driver.findElement(By.xpath("//input[@name='firstName']"));
		return element;
	}
	
	public static WebElement middleName(WebDriver driver) {
		element=driver.findElement(By.xpath("//input[@name='middleName']"));
		return element;
	}
	
	public static WebElement lastName(WebDriver driver) {
		element=driver.findElement(By.xpath("//input[@name='lastName']"));
		return element;
	}

	public static WebElement save(WebDriver driver) {
		element=driver.findElement(By.xpath("//button[@type='submit']"));
		return element;
	}
}
