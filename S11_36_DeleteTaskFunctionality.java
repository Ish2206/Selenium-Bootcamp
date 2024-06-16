package week1_Day1_VanillaScripting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class S11_36_DeleteTaskFunctionality
{
	public static void main(String[] args) throws InterruptedException 
	{
		
		//Launch the Browser and Disable the notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		//1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("bootcamp_2024@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(3000);
		
		//Creating multiple Tasks to verify the delete functionality
		for(int i=0;i<3;i++)
		{
			driver.findElement(By.xpath("//lightning-icon[@icon-name='utility:add']/span")).click();
			driver.findElement(By.xpath("//span[text()='New Task']")).click();
			driver.findElement(By.xpath("(//input[@role='combobox'])[2]")).sendKeys("Bootcamp" + i);
			WebElement contacts = driver.findElement(By.xpath("//img[@title='Contacts']"));
			contacts.click();
			driver.findElement(By.xpath("//span[text()='Contacts']")).click();
			WebElement status = driver.findElement(By.xpath("//a[text()='Not Started']"));
			status.click();
			driver.findElement(By.xpath("//a[text()='Waiting on someone else']")).click();
			driver.findElement(By.xpath("(//button[@type='button']/span[text()='Save'])[2]")).click();
			Thread.sleep(3000);
		}
		
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
		
		// Printing the recently viewed subject name in order to verify delete functionality in the end
		String taskSubject = driver.findElement(By.xpath("(//li[@data-aura-class='forceSplitViewListSelectableRecord']//span)[4]")).getText();
		System.out.println("The subject name is " + taskSubject);
		
		// 6. Click the Dropdown for Bootcamp task and select Delete
		driver.findElement(By.xpath("//a[@title='Show 6 more actions']")).click();
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		
		// 7. Verify the Task is Deleted
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']"))));
		WebElement toastMessage = driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']"));
		boolean msgDisplayed = toastMessage.isDisplayed();
		System.out.println("Is the Task Deleted? : " + msgDisplayed);
		String message = toastMessage.getText();
		System.out.println("The toast message is " + message);
		if(message.contains(taskSubject))
		{
			System.out.println("Task got deleted successfully");
		}
		else
		{
			System.out.println("Task is not deleted");
		}
		
		// Getting the Recently viewed Task subject to verify the deletion of the previous Task
		String taskSubject1 = driver.findElement(By.xpath("(//li[@data-aura-class='forceSplitViewListSelectableRecord']//span)[4]")).getText();
		System.out.println("The subject name is " + taskSubject1);
		if(taskSubject.equals(taskSubject1))
		{
			System.out.println("Deletion is not successful");
		}
		else
		{
			System.out.println("Task got deleted successfully");
		}
		
		// Closing the browser
		driver.close();
	}
}
