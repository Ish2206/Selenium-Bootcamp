package week1_Day1_VanillaScripting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class S11_34_CreateTaskFunctionality
{
	public static void main(String[] args) throws InterruptedException
	{	 
		// Launch the Browser and Disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		// 1) Launch the app and Click Login and Login with the credentials
		driver.get("https://login.salesforce.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		
		// 2) Click on Global Actions SVG icon
		driver.findElement(By.xpath("//lightning-icon[@icon-name='utility:add']/span")).click();
		
		// 3) Click New Task
		driver.findElement(By.xpath("//span[text()='New Task']")).click();
		
		// 4) Enter subject as "Bootcamp"
		driver.findElement(By.xpath("(//input[@role='combobox'])[2]")).sendKeys("Bootcamp");
		
		// 5) Select Contact from DropDown
		WebElement contacts = driver.findElement(By.xpath("//img[@title='Contacts']"));
		contacts.click();
		driver.findElement(By.xpath("//span[text()='Contacts']")).click();
		
		// 6) Select status as 'Waiting on someone else'
		WebElement status = driver.findElement(By.xpath("//a[text()='Not Started']"));
		status.click();
		driver.findElement(By.xpath("//a[text()='Waiting on someone else']")).click();
		
		// 7) Save and verify the 'Task created' message
		driver.findElement(By.xpath("(//button[@type='button']/span[text()='Save'])[2]")).click();
		
		// Adding explicitly wait to verify whether the task has been created
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']"))));
		WebElement toastMessage = driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']"));
		
		// Verify whether the task has been created
		boolean msgDisplayed = toastMessage.isDisplayed();
		System.out.println("Is the Task Created? : " + msgDisplayed);
		String message = toastMessage.getText();
		System.out.println("The toast message is " + message);
		if(message.contains("was created"))
		{
			System.out.println("Task was created");
		}
		else
		{
			System.out.println("Task is not created");
		}
		
		// Closing the browser
		driver.close();
	}
}
