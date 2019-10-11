package pages;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.StartUp;

public class BookShow extends StartUp {
	
	Map<String,Object> ShowDataObj;
	Map<String,Object> DateDataObj;
	String Day,Month,Year,Time;
	StartUp st=new StartUp();
	WebDriverWait wait = new WebDriverWait(driver,30);
	WebElement e;
	boolean result;
	
	public BookShow()
	{
		ShowDataObj=st.loadTestData("testData.json");
	}
	
	public boolean selectDateTime()
	{
		result=false;
		DateDataObj=(Map<String, Object>) ShowDataObj.get("DateOfShow");
		Day=DateDataObj.get("Day").toString();
		Month=DateDataObj.get("Month").toString();
		Year=DateDataObj.get("Year").toString();
		Time=DateDataObj.get("Time").toString();
		
		//click on the month
		
		while(driver.findElements(By.xpath("//a[contains(@class,'booking-calendar__navigation__link')]//span[contains(@class,'month') and contains(text(),'"+Month+"')]")).size()==0)
		{
			driver.findElement(By.xpath("//div[contains(@class,'booking-calendar__navigation__step--next')]")).click();
		}
		e=driver.findElement(By.xpath("//a[contains(@class,'booking-calendar__navigation__link')]//span[contains(@class,'month') and contains(text(),'"+Month+"')]"));
		e.click();
		
		//selecting the day
		
		e=driver.findElement(By.xpath("//div[contains(@class,'booking-calendar__day__number')]//span[contains(@class,'dn') and contains(text(),'"+Day+"')]"));
		
		//Select time
		
		e=driver.findElement(By.xpath("//div[contains(@class,'booking-calendar__day__number')]//span[contains(@class,'dn') and contains(text(),'"+Day+"')]/parent::div/following-sibling::a//span[contains(@class,'booking-calendar__link__content')]/strong[contains(text(),'"+Time+"')]"));
		e.click();
		result=true;
		return result;
	}
	
	public boolean verifySeatPlanLoad()
	{
		result=false;
		try 
		{
			Thread.sleep(4000);
			if(driver.findElements(By.xpath("//div[contains(@class,'ltd-seatplan__container ltd-seatplan')]")).size()!=0)
				result= true;
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
			result= false;
		}
		return result;
		
	}
	
	
	public boolean selectSeats()
	{
		WebElement e1;
		String seatNumber="";
		try
		{
			WebElement element=driver.findElement(By.xpath("//canvas[contains(@class,'ltd-seatplan__canvas')]"));
			result=false;
			ArrayList<String> seatnumbers=(ArrayList<String>) DataObj.get("seats");
			for(int i=0;i<seatnumbers.size();i++)
			{
				seatNumber=seatnumbers.get(i).toString();
				
				//driver.findElement(By.xpath("")).click();
				e=driver.findElements(By.tagName("canvas")).get(0);
				System.out.println("going to find the class");
				e.findElement(By.xpath("//div[contains(@class,'ltd-seatplan__wrap')]//span[contains(@class,'ltd--hidden ltd-seatplan__tooltip')]//span[contains(@class,'ltd-seatplan__tooltip__content')]//span[contains(@class,'ltd-seatplan__tooltip__seatdetails')]"));
				System.out.println("going to find next class now");
				e1=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'outer-canvas')]//div[contains(@id,'PlanCanvas')]//div[contains(@class,'ltd-seatplan__wrap')]//span[contains(@class,'ltd--hidden ltd-seatplan__tooltip')]//span[contains(@class,'ltd-seatplan__tooltip__content')]//span[contains(@class,'ltd-seatplan__tooltip__seatdetails')]")));
				
//				JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
//				jsExecutor.executeScript("arguments[0].setAttribute('style', 'height: 766px; width: 1044px; cursor: default;')",element);
				
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//	            js.executeScript("$(\"span:contains('M3')\").click();");
				
				e=driver.findElement(By.xpath("//div[contains(@class,'ltd-seatplan__wrap')]//span[contains(@class,'ltd-seatplan__tooltip__content')]//span[contains(@class,'ltd-seatplan__tooltip__areaname')]//span[(@class='ltd-seatplan__tooltip__seat') and contains(text(),'"+seatNumber+"')]"));
				
				e.click();
				
				result=true;
			}
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(ne,"SeatNumber:"+seatNumber+"not available to be booked" );
			return false;
		}
		
		
			return result;
	}

}
