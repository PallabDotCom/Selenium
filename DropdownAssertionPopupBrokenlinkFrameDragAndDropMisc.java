import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DropdownAssertionPopupBrokenlinkFrameDragAndDropMisc {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub			
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\debnpal\\OneDrive - Manulife\\Documents\\Documents\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");		
		
		//Handling Static Drop Down with select
		WebElement staticdropdown=driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select dropdown=new Select(staticdropdown);
		dropdown.selectByIndex(3);
		System.out.println(dropdown.getFirstSelectedOption().getText());
		// We can use assertion as well
		Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "USD");
		dropdown.selectByVisibleText("AED");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByValue("INR");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		// Drop down & looping 
		driver.findElement(By.id("divpaxinfo")).click();
		Thread.sleep(2000L);
		/*int i=1;
		while(i<5)
		{
		driver.findElement(By.id("hrefIncAdt")).click();
		i++;
		} */
		System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
		for(int i=1;i<5;i++)
		{
			driver.findElement(By.id("hrefIncAdt")).click();
		}
		System.out.println(driver.findElement(By.id("divpaxinfo")).getText());
		driver.findElement(By.id("btnclosepaxoption")).click();
		
		//Handling Dynamic drop down with indexes
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//a[@value='BLR']")).click();
		Thread.sleep(2000);
		//Parent child relationship 
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
		//using indexing
		driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click();
		
		//AutoSuggestive Drop down
		driver.findElement(By.id("autosuggest")).sendKeys("ind");
		Thread.sleep(3000);		
		
		java.util.List<WebElement> options= driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
		System.out.println(options);
		for(WebElement option :options)
		{
			System.out.println(option);
			System.out.println("Hello");
			if(option.getText().equalsIgnoreCase("India"))
			{
				option.click();
			}
		}
		//assertion test
		Assert.assertFalse(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
		driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
		System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isDisplayed());
		System.out.println(driver.findElements(By.cssSelector("input[type='checkbox']")).size());
		
		//Check enable or disable using attribute
		System.out.println(driver.findElement(By.name("ctl00$mainContent$view_date2")).isEnabled());
		System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
		System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));
		if(driver.findElement(By.id("Div1")).getAttribute("style").contains("1"))
		{
		System.out.println("its enabled");
		Assert.assertTrue(true);
		}
		else
		{
		Assert.assertTrue(false);
		}
		//Handling pop up alerts
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.id("name")).sendKeys("Pallab");
		driver.findElement(By.cssSelector("[id='alertbtn']")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		driver.findElement(By.id("confirmbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();
		// To maximize the browser
		driver.manage().window().maximize();
		//Delete Cookies
		driver.manage().deleteAllCookies();
		driver.manage().deleteCookieNamed("sessionkey");
		//Can be use for screenshot 			
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			//copy the screenshot in desired location
			FileUtils.copyFile(src,new File("C:\\Users\\debnpal\\screenshot.png"));	
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		
		//How to check broken link
		//Step1: Get all URLs tied up to the link using selenium
		//Step2: Java methods will call URL's and get you the status code
		//If status code is >400 means URL not working. Manually we can see from network tab (all or XHR)
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");  
		java.util.List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
	      SoftAssert a =new SoftAssert(); //Soft assertion
	      for(WebElement link : links)
	      {
	    	  String url= link.getAttribute("href");
	    	  HttpURLConnection   conn= (HttpURLConnection)new URL(url).openConnection();
	          conn.setRequestMethod("HEAD");
	          conn.connect();
	          int respCode = conn.getResponseCode();
	          System.out.println(respCode);
	          a.assertTrue(respCode<400, "The link with Text"+link.getText()+" is broken with code" +respCode);
	      }
	      a.assertAll();
	      
	      //FrameDragAndDrop
	      	driver.get("https://jqueryui.com/droppable/");
			driver.findElements(By.tagName("iframe")).size();
			driver.switchTo().frame(0);
			driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[class='demo-frame']")));
			Actions ac=new Actions(driver);
			WebElement source=driver.findElement(By.id("draggable"));
			WebElement target=driver.findElement(By.id("droppable"));
			ac.dragAndDrop(source, target).build().perform();
			driver.switchTo().defaultContent();
	    
	      
	
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	}
	

}
