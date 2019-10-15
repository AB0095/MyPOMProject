package com.freeCrm.pages;

import lombok.Getter;
import lombok.Setter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



import com.freeCrm.baseClass.BaseClass;
import com.freeCrm.testUtil.CommonTestUtil;

@Getter
@Setter
public class HomePage extends BaseClass{

	//url https://ui.cogmento.com
	

	@FindBy(how = How.XPATH, using = "//h1[@class='private-page__title']")
	private WebElement user;
	
	
	public HomePage()
	{
		 PageFactory.initElements(driver, this);
	}
	
	public String validateCategoryTitle()
	{
		CommonTestUtil.wait(driver, user, 120);
		String userName = user.getText();
		return userName;
	}
	
	
}
