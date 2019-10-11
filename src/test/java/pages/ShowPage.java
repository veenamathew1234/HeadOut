package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.StartUp;

public class ShowPage extends StartUp {
	
	WebElement e;
	WebDriverWait wait = new WebDriverWait(driver,30);
	boolean result;
	
	public boolean verifyShowPage(String showName)
	{
		result=false;
		String pageTitle=driver.getTitle();
		if(driver.findElements(By.xpath("//span[contains(@id,'event-title')]/strong")).size()!=0)
			result=true;
		return result;
	}
	
	public boolean clickOnBookTickets()
	{
		result=false;
		try
		{
		e=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains(@class,'submit ltd-cta event-booking-box__cta')]/span"))));
		e.click();
		result=true;
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(ne, "Book tickets button not found");
			result=false;
		}
		return result;
	}
	

}
