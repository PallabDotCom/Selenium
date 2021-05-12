import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SelectStaticdropdown {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\debnpal\\OneDrive - Manulife\\Documents\\Documents\\chromedriver.exe");
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.xpath("//label[@for='benz']/input")).click();
		String label =driver.findElement(By.xpath("//label[@for='benz']")).getText();
		WebElement staticdropdown= driver.findElement(By.id("dropdown-class-example"));
		Select dropdown= new Select(staticdropdown);
		dropdown.selectByVisibleText(label);
		driver.findElement(By.id("name")).sendKeys(label);
		driver.findElement(By.id("alertbtn")).click();
		String text=  driver.switchTo().alert().getText();
		if(text.contains(label))
	     {
			System.out.println("Pass");
	     }
	     else
	    	System.out.println("Fail");
	}		
		
}


