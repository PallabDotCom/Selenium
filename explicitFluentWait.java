import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class explicitFluentWait {
	public static void main(String[] args)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\debnpal\\OneDrive - Manulife\\Documents\\Documents\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		WebDriverWait eWait=new WebDriverWait(driver,5);
		
		driver.get("https://www.itgeared.com/demo/1506-ajax-loading.html");
		
		driver.findElement(By.xpath("//div[@id='content']/a[2]")).click();
		eWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='results']")));				
		
		System.out.println(driver.findElement(By.xpath("//div[@id='results']")).getText());
		
		
	}


}
