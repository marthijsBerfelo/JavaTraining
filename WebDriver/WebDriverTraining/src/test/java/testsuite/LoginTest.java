package testsuite;

import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.PropertyConfigurator;
import org.hamcrest.Matchers;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import tools.DataProviders;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginTest extends TestCommon
{
	
	
	@Test
	public void Login() 
			throws IOException, SQLException
	{
		PropertyConfigurator.configure("src/log4j.properties");
		
		logger.info("Initiating Selenium webdriver");
		
		WebDriver driver = new FirefoxDriver();
		
		logger.info("Webdriver initiated");
		
		//open URL
		driver.get("http://selenium.polteq.com/testshop/index.php");
	
		//Verify correct page
		assertThat(driver.getCurrentUrl(), Matchers.is("http://selenium.polteq.com/testshop/index.php"));	
		
		logger.info("Testshop website loaded correctly");
		
		//Click Login
		driver.findElement(By.cssSelector("a.login")).click();
	
		//Verify loginPage
		boolean onLoginPage = 
			driver.findElement(By.cssSelector("form#create-account_form")).isDisplayed()
			&&
			driver.findElement(By.cssSelector("form#login_form")).isDisplayed();
		assertThat(onLoginPage, Matchers.is(true));
		
		logger.info("login page succesfully loaded");
		
		//Enter username
		driver.findElement(By.cssSelector("input#email")).sendKeys("tester@test.com");
	
		//Enter password
		driver.findElement(By.cssSelector("input#passwd")).sendKeys("tester");
	
		//Click to login
		driver.findElement(By.cssSelector("input#SubmitLogin")).click();
	
		//Verify Login success
		boolean loginSuccess = driver.findElement(By.cssSelector("ul.myaccount_lnk_list")).isDisplayed();
		
		assertThat(loginSuccess, Matchers.is(true));
		
		logger.info("login succesfull");
		
	}
	
	
	@Test (dataProviderClass=DataProviders.class, dataProvider = "LoginTestInput")
	public void LoginValidations(String testCase, String username, String password, String loginSuccessfull, String description) 
			throws IOException, SQLException
	{
		
		PropertyConfigurator.configure("src/log4j.properties");
		
		logger.info("Testcase ID:" + " " + testCase + " - " +  description);
		
		logger.info("Initiating selenium webdriver");
		
		WebDriver driver = new FirefoxDriver();
		
		logger.info("Webdriver intiated");
		
		driver.get("http://selenium.polteq.com/testshop/index.php");
		
		assertThat(driver.getCurrentUrl(), Matchers.is("http://selenium.polteq.com/testshop/index.php"));
		
		logger.info("Testshop website loaded correctly");
		
		driver.findElement(By.cssSelector("a.login")).click();
		
		boolean onLoginPage =
				driver.findElement(By.cssSelector("form#create-account_form")).isDisplayed()
				&&
				driver.findElement(By.cssSelector("form#login_form")).isDisplayed();
		assertThat(onLoginPage, Matchers.is(true));
		
		logger.info("Testshop login page loaded correctly");
		
		driver.findElement(By.cssSelector("input#email")).sendKeys(username);
		
		driver.findElement(By.cssSelector("input#passwd")).sendKeys(password);
		
		driver.findElement(By.cssSelector("input#SubmitLogin")).click();
		
		if(loginSuccessfull.equals("t")){
			
			boolean loginSuccess = driver.findElement(By.cssSelector("ul.myaccount_lnk_list")).isDisplayed();
			
			assertThat(loginSuccess, Matchers.is(true));
						
			logger.info("Testcase:" + " " + testCase + " " + "finished");
			
			driver.close();
			
		} else {
			
			boolean loginFail = driver.findElement(By.cssSelector("div.error")).isDisplayed();
			
			assertThat(loginFail, Matchers.is(true));
			
			assertThat(driver.findElement(By.cssSelector("div.error>p")).getText(), Matchers.is("There is 1 error"));
			
			if(loginSuccessfull.equals("fe")){
				
				assertThat(driver.findElement(By.cssSelector("div.error>ol>li")).getText(), Matchers.is("An email address required."));
				
				logger.info("Testcase:" + " " + testCase + " " + "finished");
				
				driver.close();
				
			} else if (loginSuccessfull.equals("fp")){
				
				assertThat(driver.findElement(By.cssSelector("div.error>ol>li")).getText(), Matchers.is("Password is required."));
				
				logger.info("Testcase:" + " " + testCase + " " + "finished");
				
				driver.close();
				
			} else {
				
				assertThat(driver.findElement(By.cssSelector("div.error>ol>li")).getText(), Matchers.is("Authentication failed."));
				
				logger.info("Testcase:" + " " + testCase + " " + "finished");
				
				driver.close();
			}
			
		}
		
	}

	
}
