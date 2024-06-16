package week1_Day1_VanillaScripting;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class S11_35_EditTaskFunctionality 
{
	
	public static void main(String[] args) throws InterruptedException 
	{
		// Launch the Browser and Disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		// 1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		driver.findElement(By.id("Login")).click();
		
		// 2. Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		// 3. Click view All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
		
		// Adding Explicitly wait for the WebElement to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']"))));
		driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']")).click();
		
		// 4. Click on Tasks tab 
		driver.findElement(By.xpath("//one-app-nav-bar-item-root[@data-target-selection-name='sfdc:TabDefinition.standard-Task']")).click(); 	
		
		// 5.Click on Dropdown icon available under tasks and select value as Recently viewed
		driver.findElement(By.xpath("//button[@title='Select a List View: Tasks']")).click();
		driver.findElement(By.xpath("//li[@data-aura-class='forceVirtualAutocompleteMenuOption']//span[text()='Recently Viewed']")).click();
		Thread.sleep(2000);
		
		// Getting the Recently viewed Task Subject name
		String taskSubject = driver.findElement(By.xpath("(//li[@data-aura-class='forceSplitViewListSelectableRecord']//span)[4]")).getText();
		System.out.println("The subject name is " + taskSubject);
		
		// 6. Click the Dropdown for Bootcamp task and select Edit
		driver.findElement(By.xpath("//a[@title='Show 6 more actions']")).click();
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		
		//  7. Select todays date as Due date
		driver.findElement(By.xpath("//lightning-datepicker[@variant='label-hidden']")).click();
		driver.findElement(By.xpath("//button[@name='today']")).click();
		
		// 8. Select Priority as low
		driver.findElement(By.xpath("(//span[@title='required']/parent::span/following-sibling::div)[2]")).click();
		driver.findElement(By.xpath("//a[@title='Low']")).click();
		
		// 10. Click save and verify Subject
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		
		// Adding explicitly wait for the Toast Message to be visible
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']"))));
		WebElement toastMessage = driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']"));
		
		// Verifying whether the Toast Message gets displayed
		boolean msgDisplayed = toastMessage.isDisplayed();
		System.out.println("Is the Task Edited? : " + msgDisplayed);
		String message = toastMessage.getText();
		System.out.println("The toast message is " + message);
		
		//Verifying whether the subject name given is present in the Toast Message
		if(message.contains(taskSubject))
		{
			System.out.println("Task got edited successfully");
		}
		else
		{
			System.out.println("Task is not edited");
		}
		
		// Closing the browser
		driver.close();	
	}

}
