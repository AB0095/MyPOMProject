package com.freeCrm.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;




import org.testng.annotations.Test;

import com.freeCrm.baseClass.BaseClass;
import com.freeCrm.pages.HomePage;
import com.freeCrm.pages.LoginPage;

public class LoginPageTest extends BaseClass{
	
	LoginPage lp ;
	HomePage hp;
	String user;
	public LoginPageTest()
	{
		super();// this loads the config properties
	}
	
	
	@BeforeMethod
	public void setUp()
	{
		launchBrowser();
		lp = new LoginPage();
	}
	
	@Test(priority = 1)
	public void loginTest() throws Exception
	{
		boolean result = false;
		System.out.println("username : "+propconfig.getProperty("username"));
		
		System.out.println("password : "+propconfig.getProperty("password"));
		
		hp = lp.login(propconfig.getProperty("username"),propconfig.getProperty("password"));
		
		user = hp.validateCategoryTitle();
		
		Assert.assertEquals(user, "Sales Dashboard");


	}
	
	@AfterMethod
	public void quitBrowser()
	{
		driver.quit();
	}

}
