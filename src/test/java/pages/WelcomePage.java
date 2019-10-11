package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.StartUp;

public class WelcomePage extends StartUp{
	
	boolean result;
	WebDriverWait wait = new WebDriverWait(driver,30);
	
	
	public boolean launchPage()
	{
		result=false;
		driver.get("https://www.londontheatredirect.com/");
		String currentTitle=driver.getTitle();
		if(currentTitle.equalsIgnoreCase("London Theatre Tickets – London Shows | London Theatre Direct"))
			result=true;
		
		return result;
		
	}
	
	public boolean selectShow()
	{
		
		result=false;
		try
		{
			String showname=DataObj.get("showToBook").toString();
			System.out.println("Show name="+showname);
			WebElement e=driver.findElement(By.xpath("//a[contains(@data-event-name,'"+showname+"')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
			e.click();
			result=true;
			}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(ne, "The show is unavailable");
			result=false;
		}
		
		return result;
	}
	
	

}
