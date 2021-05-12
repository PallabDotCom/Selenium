import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ImplicitExplicitWait {

	public static void main(String[] args) throws InterruptedException {

		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\debnpal\\OneDrive - Manulife\\Documents\\Documents\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		//implicit wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//Class for explicit wait
		WebDriverWait eWait =new WebDriverWait(driver,5);
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		
		String[] vegNeed= {"Brocolli","Cucumber","Brinjal"};
		addItems(driver,vegNeed);
		
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		//explicit wait - WebDriver Wait
		eWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		
		//explicit wait - Fluent Wait
		Wait<WebDriver> fWait=new FluentWait<WebDriver>(driver).withTimeout(30,TimeUnit.SECONDS).pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
		WebElement foo=fWait.until(new Function<WebDriver,WebElement>() {
			public WebElement apply(WebDriver driver) {
				if (driver.findElement(By.cssSelector("span.promoInfo")).isDisplayed())
						{
						return driver.findElement(By.cssSelector("span.promoInfo"));
						}
				else
					return null;
			}

			@Override
			public Object apply(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			});
		//eWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		
		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
				
	}
		
		
		private static void addItems(WebDriver driver, String[] vegNeed) 		
		{
		
		List<WebElement> products = driver.findElements(By.xpath("//h4[@class='product-name']"));
		
		int j=0;	
		for (int i=0; i<products.size() ;i++)
		{
			String[] SplitProductname = products.get(i).getText().split("-");
			String formattedProductname = SplitProductname[0].trim();
			
			
			List<String> vegNeedList=Arrays.asList(vegNeed);
						
			if (vegNeedList.contains(formattedProductname))
					{
					j++;
					driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
					System.out.println(formattedProductname);
					if (j == vegNeed.length)
					{
					
					System.out.println(j);
					System.out.println(vegNeed.length);
						break;
					}
					}
																
		}
		
		}

		
	}
