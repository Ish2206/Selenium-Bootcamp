package week1_Day1_VanillaScripting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class S11_74_CreateNewTask
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
		
		// 2. Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		// 3. Click view All from App Launcher
		driver.findElement(By.xpath("//button[	text()='View All']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Content");

		// 4. Click on Content tab	
		driver.findElement(By.xpath("//div[@data-name='Content']")).click();
		
		// 5. Click View All from Today's Task
		driver.findElement(By.xpath("//a[@aria-label='View All Tasks']")).click();
		
		// 6. Click on Show one more Action and click New Task
		driver.findElement(By.xpath("//a[@title='Show 2 more actions']")).click();
		driver.findElement(By.xpath("//a[@title='New Task']")).click();
		
		// 7. Select a Account Name in Assigned to field 
		WebElement assignedTo = driver.findElement(By.xpath("//span[@class='pillText']"));
		assignedTo.click();
		Thread.sleep(2000);
		
		// 8. Select a subject as Email
		driver.findElement(By.xpath("//input[@aria-haspopup='listbox']")).click();
		driver.findElement(By.xpath("//span[@title='Email']")).click();
		
		// 9. Set Priority as High and Status as In Progress
		driver.findElement(By.xpath("(//span[@title='required']/parent::span/following-sibling::div)[2]")).click();
		driver.findElement(By.xpath("//a[@title='High']")).click();
		WebElement status = driver.findElement(By.xpath("//a[text()='Not Started']"));
		status.click();
		driver.findElement(By.xpath("//a[text()='In Progress']")).click();
		
		// 10. Click on the image of Name field, click on Contacts and select Contact
		WebElement contacts = driver.findElement(By.xpath("//img[@title='Contacts']"));
		contacts.click();
		driver.findElement(By.xpath("//span[text()='Contacts']")).click();
		driver.findElement(By.xpath("//input[@title='Search Contacts']")).click();
		driver.findElement(By.xpath("(//lightning-formatted-rich-text[@class='slds-rich-text-editor__output']/span)[1]")).click();
		
		// 11. Click on the image of Related To field, click on Product and Select Product
		WebElement relatedTo = driver.findElement(By.xpath("//img[@title='Accounts']"));
		relatedTo.click();
		driver.findElement(By.xpath("//a[@title='Products']")).click();
		driver.findElement(By.xpath("//input[@title='Search Products']")).click();
		driver.findElement(By.xpath("//img[@title='Product']")).click();
		
		// 12. Click Save
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		// Adding explicitly wait to verify whether the task has been created
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']"))));
		WebElement toastMessage = driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']"));
		
		// Expected Result:Task Send Quote should be created with the given Details.
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
