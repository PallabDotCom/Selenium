import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class Optimization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\debnpal\\OneDrive - Manulife\\Documents\\Documents\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		driver.get("http://www.ebay.com/");
		//Count of the Link in the Entire Page
	System.out.println("Links in the Page");
	System.out.println(driver.findElements(By.tagName("a")).size());
	//Count of links in the footer section of the page
	WebElement footer=driver.findElement(By.xpath(".//*[@id='glbfooter']"));
	System.out.println("Links in the footer section");
	System.out.println(footer.findElements(By.tagName("a")).size());
	//count of links in a column
	WebElement col=driver.findElement(By.xpath(".//*[@id='gf-BIG']/table/tbody/tr/td[2]/ul"));
	System.out.println("Links in the 2nd coloumn of the section");
	System.out.println(col.findElements(By.tagName("a")).size());
	
	String Beforeclicking = null;
	String Afterclicking;

	for(int i=0;i<col.findElements(By.tagName("a")).size();i++)
	{
		//Click on each link in coulmn and check if the pages are opening
		String clickonlinkTab=Keys.chord(Keys.CONTROL,Keys.ENTER);
		col.findElements(By.tagName("a")).get(i).sendKeys(clickonlinkTab);

		//Get the title of each page
		Set<String> abc=driver.getWindowHandles();
		Iterator<String> it=abc.iterator();
		while(it.hasNext())
		{
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());
		}
		

		if(col.findElements(By.tagName("a")).get(i).getText().contains("Site map"))
		{
			Beforeclicking = driver.getTitle();
		
			col.findElements(By.tagName("a")).get(i).click();
			break;
			}
	}

	Afterclicking =driver.getTitle();
	if(Beforeclicking!=Afterclicking)
	{
		if(driver.getPageSource().contains("sitemap"))
	
			System.out.println("PASS");
	}
	else
	{
		System.out.println("FAIL");
	}
		
}
	                   
	}


