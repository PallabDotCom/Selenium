import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class staticdropdown {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver","C:\\Users\\debnpal\\OneDrive - Manulife\\Documents\\Documents\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.cleartrip.com/");

		WebElement staticdropdown1 = driver.findElement(By.id("Adults"));
		Select dropdown1 = new Select(staticdropdown1);
		dropdown1.selectByIndex(3);

		WebElement staticdropdown2 = driver.findElement(By.id("Childrens"));
		Select dropdown2 = new Select(staticdropdown2);
		dropdown2.selectByIndex(3);

		WebElement staticdropdown3 = driver.findElement(By.id("Infants"));
		Select dropdown3 = new Select(staticdropdown3);
		dropdown3.selectByIndex(1);

		Thread.sleep(3000);
		driver.findElement(By.id("MoreOptionsLink")).click();

		WebElement staticdropdown4 = driver.findElement(By.id("Class"));
		Select dropdown4 = new Select(staticdropdown4);
		dropdown4.selectByIndex(2);

		driver.findElement(By.xpath("//input[@name='airline']")).sendKeys("Bang");
		Thread.sleep(3000);
		WebElement option = driver.findElement(By.cssSelector("li[class='list'] a"));
		option.click();

		driver.findElement(By.id("DepartDate")).click();
		driver.findElement(By.xpath("//a[contains(@class,'ui-state-active')]")).click();

		driver.findElement(By.id("SearchBtn")).click();

		System.out.println(driver.findElement(By.id("homeErrorMessage")).getText());

	}
}
