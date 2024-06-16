package week1_Day1_VanillaScripting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class S11_37_CreateTaskWithoutMandatoryFields 
{
	public static void main(String[] args) throws InterruptedException 
	{	
		// Launch the Browser and Disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
				
		// 1. Launch the app and Click Login and Login with the credentials
		driver.get("https://login.salesforce.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
				

		// 2. Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
				
		// 3. Click view All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
				
		// Adding Explicitly wait for the WebElement to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']"))));
		driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']")).click();
		
		// 4. Click on task and click New Task
		driver.findElement(By.xpath("//one-app-nav-bar-item-root[@data-target-selection-name='sfdc:TabDefinition.standard-Task']")).click(); 	
		driver.findElement(By.xpath("//a[@title='Show 2 more actions']")).click();
		driver.findElement(By.xpath("//a[@title='New Task']")).click();
		
		// 5. Select Name from Dropdown
		driver.findElement(By.xpath("//input[@title='Search Contacts']")).click();
		driver.findElement(By.xpath("(//lightning-formatted-rich-text[@class='slds-rich-text-editor__output']/span)[1]")).click();
		
		// 6. Enter Comments as SALES FORCE Automation Using Selenium
		driver.findElement(By.xpath("//textarea[@role='textbox']")).sendKeys("SALES FORCE Automation");
		
		// 7. Click on Save
		driver.findElement(By.xpath("//button[@title='Save']/span")).click();
		
		// 8. Get the text of Error message Displayed and Verify the message
		String errorMsg = driver.findElement(By.xpath("//a[@class='errorsListLink']")).getText();
		System.out.println("The Error Message displayed is : " + errorMsg);
		
		// Verifying the Expected result: "Complete this field" text is displayed or not
		String errorMsgSubject = driver.findElement(By.xpath("//div[@class='slds-form-element__help']")).getText();
		System.out.println("The message under subject field is : " + errorMsgSubject);
		if(errorMsgSubject.equals("Complete this field."))
		{
			System.out.println("The Error Message" + "'" +  errorMsgSubject +  "'" + " is displayed as expected");
		}
		else
		{
			System.out.println("Error message is not displayed as expected");
		}			
	}
}
