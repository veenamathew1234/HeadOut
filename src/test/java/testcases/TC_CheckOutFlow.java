package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.BookShow;
import pages.ShowPage;
import pages.WelcomePage;
import utils.StartUp;

public class TC_CheckOutFlow extends StartUp {
	WelcomePage wc=new WelcomePage();
	ShowPage sh=new ShowPage();
	BookShow bks=new BookShow();
	String showname=DataObj.get("showToBook").toString();
	boolean result;
  @Test(priority=1)
  public void navigateToHomePage() {
	  result=wc.launchPage();
	  Assert.assertEquals(result, true, "Website did not launch properly");
	  
	  
  }
  
  @Test(priority=2) 
  public void selectShowToBook(){
	  
	  result=wc.selectShow();
	  Assert.assertEquals(result, true, "Show you are looking for is NOT found");
	  
  }
  
  @Test (priority=3)
  public void verifyTheShowPage(){
	  result=sh.verifyShowPage(showname);
	  Assert.assertEquals(result, true, "Show Page Not Loaded properly");  
  }
  
  @Test(priority=4)
  public void clickOnBookTickets() {
	  result=sh.clickOnBookTickets();
	  Assert.assertEquals(result, true, "User unable to click on Book Tickets properly");
  }
  
  @Test(priority=5)
  public void selectDateTimeForShow() {
	  
	  result=bks.selectDateTime();
	  Assert.assertEquals(result, true, "User unable to select date and time");
  }
  
  @Test(priority=6)
  public void verifySeatPlanLoad() {
	  result=bks.verifySeatPlanLoad();
	  Assert.assertEquals(result, true, "Seat Plan Not loaded");
  }
  @Test(priority=7)
  public void selectSeats() {
	  result=bks.selectSeats();
	  Assert.assertEquals(result, true, "Seats not available");
  }
  
}
