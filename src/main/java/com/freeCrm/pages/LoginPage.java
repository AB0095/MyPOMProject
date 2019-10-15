package com.freeCrm.pages;

import lombok.Getter;
import lombok.Setter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;




import com.freeCrm.baseClass.BaseClass;
import com.freeCrm.testUtil.CommonTestUtil;

@Getter
@Setter
public class LoginPage extends BaseClass {
	
	@FindBy(how = How.XPATH, using = "//input[@id='username']")
	private WebElement usernameTextBox;

	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	private WebElement passwordTextBox;

	@FindBy(how = How.NAME, using = "//button[@id='loginBtn']//i18n-string[contains(text(),'Log in')]")
	private WebElement loginButton;
	
	/*@FindBy(how = How., using = "//button[@id='loginBtn']")
	private WebElement loginButton;
*/
	public LoginPage()
	{
		PageFactory.initElements(driver, this); // this will initialze the elements
	}

	
	public HomePage login(String username, String password) throws Exception
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;

		CommonTestUtil.sendKeys(driver, usernameTextBox, username, 60);
		Thread.sleep(2000);
		
		//js.executeScript("document.getElementById('input-qty-1').value='1';");
		CommonTestUtil.sendKeys(driver, passwordTextBox, password, 60);

		Thread.sleep(2000);
		CommonTestUtil.click(driver, loginButton, 60);
	
		
		return new HomePage();
		
		
	}
	

}
