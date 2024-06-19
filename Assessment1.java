package sprint1_Assessment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assessment1
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
		driver.findElement(By.id("username")).sendKeys("ragothamanu@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Gr@salesforce202404");
		driver.findElement(By.id("Login")).click();
		Thread.sleep(5000);
		
		 // 2. Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		// 3. Click view All 
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");
		
		//4. Click Sales from App Launcher
		// Adding Explicitly wait for the WebElement to be visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']"))));
		driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']")).click();
		
		// 6. Add CLOSED + OPEN values and result should set as the GOAL (If the result is less than 10000 then set the goal as 10000)
		String closedValue = driver.findElement(By.xpath("(//lightning-formatted-text[@class='metricAmount'])[1]")).getText();
		String close = closedValue.replace("$", "");
		String openValue = driver.findElement(By.xpath("(//lightning-formatted-text[@class='metricAmount'])[2]")).getText();
		String open = openValue.replace("$", "");
		int closeValue = Integer.parseInt(close);
		int openValues = Integer.parseInt(open);
		int goalValue = closeValue+openValues;
		System.out.println("The sum of open and closed value is " + goalValue);
		String goal = Integer.toString(goalValue);
		if(goalValue<10000)	
		{
			driver.findElement(By.xpath("//button[@title='Edit Goal']/lightning-primitive-icon")).click();
			driver.findElement(By.xpath("//input[@class='slds-input']")).clear();
			driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("10000");
			driver.findElement(By.xpath("//button[text()='Save']")).click();
		}
		else
		{
			driver.findElement(By.xpath("//button[@title='Edit Goal']/lightning-primitive-icon")).click();
			driver.findElement(By.xpath("//input[@class='slds-input']")).clear();
			driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys(goal);
			driver.findElement(By.xpath("//button[text()='Save']")).click();
		}
		
		// 7. Navigate to Dashboard tab
		driver.findElement(By.xpath("//one-app-nav-bar-item-root[@data-target-selection-name='sfdc:TabDefinition.standard-Dashboard']")).click();
		
		// 8. Click on New Dashboard
		driver.findElement(By.xpath("//div[@title='New Dashboard']")).click();
		Thread.sleep(5000);
		
		// 9. Enter the Dashboard name as "YourName_Workout"
		String dashboardName = "Ishwarya_Workout";
		WebElement iframe = driver.findElement(By.xpath("//iframe[@title='dashboard']"));
		driver.switchTo().frame(iframe);
		WebElement name = driver.findElement(By.xpath("//*[@id='dashboardNameInput']"));
		wait.until(ExpectedConditions.visibilityOf(name));
		name.sendKeys(dashboardName);
		
		// 10. Enter Description as Testing and Click on Create
		driver.findElement(By.id("dashboardDescriptionInput")).sendKeys("Testing");
		
		// 11. Click on Create
		driver.findElement(By.xpath("//button[@id='submitBtn']")).click();
		
		Thread.sleep(3000);
		
		// 12. Click on Done
		driver.switchTo().defaultContent();
		WebElement doneIframe = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(doneIframe);
		driver.findElement(By.xpath("//button[text()='Done']")).click();
		
		// 13. Click on Dashboard tab
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//one-app-nav-bar-item-root[@data-target-selection-name='sfdc:TabDefinition.standard-Dashboard']")).click();
		
		// 14. Verify the Dashboard is Created
		WebElement dashName = driver.findElement(By.xpath("(//th[@data-label='Dashboard Name'])[1]"));
		String name1 = dashName.getText();
		if(name1.equals(dashboardName))
		{
			System.out.println("The Dashboard " + name1 + " has been created");
		}
		else
		{
			System.out.println("Dashboard is not created");
		}
		
		// 15. Click on the newly created Dashboard
		dashName.click();
		
		// 16. Click on Subscribe
		Thread.sleep(3000);
		WebElement subscribeIframe = driver.findElement(By.xpath("(//iframe[@title='dashboard'])[2]"));
		driver.switchTo().frame(subscribeIframe);
		driver.findElement(By.xpath("//button[text()='Subscribe']")).click();
		
		// 14. Select Frequency as "Daily"
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//span[text()='Daily']")).click();
		
		// 15. Time as 10:00 AM
		WebElement time = driver.findElement(By.id("time"));
		time.click();
		Select timeDD = new Select(time);
		timeDD.selectByValue("10");
		
		// 16. Click on Save
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		
		// 17. Verify "You started Dashboard Subscription" message displayed or not
		WebElement message = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));
		String dashMsg = message.getText();
		System.out.println("The message displayed after subscribing is " + dashMsg);
		if(dashMsg.equals("You started a dashboard subscription."))
		{
			System.out.println("Subscription is successful and the message is displayed");
		}
		else 
		{
			System.out.println("Subscription didn't happen");
		}
		
		// 18. Click on Dashboards tab
		driver.findElement(By.xpath("//one-app-nav-bar-item-root[@data-target-selection-name='sfdc:TabDefinition.standard-Dashboard']")).click();
		
		// 19. Verify the newly created Dashboard is available
		WebElement dashName1 = driver.findElement(By.xpath("(//th[@data-label='Dashboard Name'])[1]"));
		String name2 = dashName1.getText();
		if(name2.equals(dashboardName))
		{
			System.out.println("The Dashboard " + name2 + " has been created and subscribed");
		}
		else
		{
			System.out.println("Dashboard is not created");
		}
	
		wait.until(ExpectedConditions.invisibilityOf(message));
		// 20. Click on dropdown for the item
		driver.findElement(By.xpath("(//lightning-primitive-cell-actions[@data-action-triggers='enter,space'])[1]")).click();
		
		// 21. Select Delete
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		
		// 22. Confirm the Delete
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		WebElement deleteMsg = driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']"));
		String delMsg = deleteMsg.getText();
		System.out.println("The message displayed after deletion is " + delMsg);
		if(delMsg.contains("was deleted"))
		{
			System.out.println("The Dashboard " + name2 + " was deleted");
		}
		else
		{
			System.out.println("The Dashboard" + name2 + " was not deleted");
		}
		
		// 23. Verify the item is not available under Private Dashboard folder
		driver.findElement(By.xpath("//a[@title='Private Dashboards']")).click();
		WebElement firstNameAfterDeletion = driver.findElement(By.xpath("//lightning-primitive-cell-factory[@data-label='Dashboard Name']//a"));
		String firstName = firstNameAfterDeletion.getText();
		System.out.println("The first Name after Deletion is " + firstName);
		if(firstName.equals(dashboardName))
		{
			System.out.println("Deletion is not successful");
		}
		else
		{
			System.out.println("Deletion is successful");
		}
	}
}
