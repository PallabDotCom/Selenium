import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class switchChildWindow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
					
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\debnpal\\OneDrive - Manulife\\Documents\\Documents\\chromedriver.exe");
	
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	driver.get("https://the-internet.herokuapp.com/");
	driver.findElement(By.xpath("//a[@href='/windows']")).click();
	driver.findElement(By.xpath("//a[@href='/windows/new']")).click();
	Set<String> windows=driver.getWindowHandles();
	Iterator<String> ite= windows.iterator();
	String parent=ite.next();
	String child= ite.next();
	driver.switchTo().window(child);
	System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());
	driver.switchTo().window(parent);
	System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());

	}

}
