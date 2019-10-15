package com.freeCrm.testUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;



import java.util.function.Function;

import org.apache.commons.collections4.FactoryUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.freeCrm.baseClass.BaseClass;



public class CommonTestUtil extends BaseClass{
	
	
	static Workbook book;
	static Sheet sheet;
	
	public static void assertMessage(String Expected, String Actual) {
		try {
			Assert.assertEquals(Expected, Actual);
			System.out.println("Title Matched  " + Expected + "," + Actual);
		} catch (Exception e) {
		}

	}

	
	public static Object[][] getTestData(String sheetName)
	{
		FileInputStream fp = null;
		try{
			fp = new FileInputStream("C:/Users/AjithB/workspace/Ecom/src/main/java/com/ecom/testData/EcomTestData.xlsx");
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try{
			book = WorkbookFactory.create(fp);
					
					
		}catch(InvalidFormatException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i =0; i<sheet.getLastRowNum();i++){
			for(int k = 0; k<sheet.getRow(0).getLastCellNum();k++){
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
			
		}
			
		return data;
	}
	
	public static void scroll() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		System.out.println("Scrolled down...!!!");
	}
	
	
	public static void listViewScroll() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,850)", "");

		System.out.println("Scrolled down...!!!");
	}

	public static String getConfigProperties(String key) {
		Properties configfile = new Properties();

		try {
			configfile.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return configfile.getProperty(key);
	}

	public static void switchWindow() {

		String mainWindow = driver.getWindowHandle();

		driver.switchTo().window(mainWindow);

	}

	public static void waitForElementPresent(By locator, int waitTime, String message) {

		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		for (int i = 0; i < 60; i++) {
			try {
				Thread.sleep(1000);
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				break;
			} catch (Exception e) {
				System.out.println(message);
				Assert.fail(message);
			}
		}
	}

	public static void framewait(String frameName) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public static void isElementToBeClickable2(WebElement locator, int waitTime,
			String message) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		for (int i = 0; i < 60; i++) {
			if (i <= 60) {

				// wait.until(ExpectedConditions.visibilityOfElementLocated((By)
				// locator));
				wait.until(ExpectedConditions.elementToBeClickable(locator));
				Thread.sleep(500);
				break;
			} else {
				System.out.println(message);
				Assert.fail(message);
			}
		}

	}

/*	public static void isElementToBeClickable(final WebElement locator,
			int waitTime, String message) throws Exception {

		final FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		// wait.pollingEvery(2, TimeUnit.SECONDS);
		// wait.withTimeout(waitTime, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);

		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver dr) {
				// System.out.println("Wait.......");
				wait.until(ExpectedConditions.visibilityOf(locator));
				wait.until(ExpectedConditions.elementToBeClickable(locator));
				return locator;
			}
		};
		Thread.sleep(1000);
		wait.until(function);
	}*/

	public static void spinner() {

		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(".//*[@id='ProcessingBox']/img")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated((By
				.xpath(".//*[@id='ProcessingBox']/img"))));
		/*
		 * if (locator != null) {
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 * System.out.println("Locator found"); }
		 */
		System.out.println("Spinner captured...");

	}

	// returns only date
	public static String Dateonly(String fetchdate) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date d = new Date();
		String Currentdate = dateFormat.format(d);
		return Currentdate;
	}

	public static String nonZeroDate(String fetchdate) {

		DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date d = new Date();
		String Currentdate = dateFormat.format(d);
		return Currentdate;
	}

	/*public static boolean isClickable(WebElement webe) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(webe));
			return true;
		} catch (Exception e) {
			return false;
		}
	}*/

	/*public static void isAlertPresent(int waitTime) {

		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();

		} catch (Exception e) {
			System.out.println("No alert present");
		}
	}*/

	public static int Randomno() {
		Random rn = new Random();
		int num = rn.nextInt(10000);
		return num + 1;

	}
	
	
	public static void takeScreenShot() throws IOException
	{
		File fp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		//FactoryUtils.copyFile(fp, new File(currentDir+"\\Screenshots\\"+System.currentTimeMillis()+".png"));
		
		//FileHandler.copy(fp, new File(currentDir+"\\Screenshots\\"+System.currentTimeMillis()+".png"));
		
	}
	
	
	public static void click(WebDriver driver, WebElement element, int timeout)
	{
		new WebDriverWait(driver, timeout).
		until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public static void sendKeys(WebDriver driver, WebElement element, String value, int timeout)
	{
		new WebDriverWait(driver, timeout).
		until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
		
	}
	
	public static void wait(WebDriver driver, WebElement element,int timeout)
	{
		new WebDriverWait(driver, timeout).
		until(ExpectedConditions.visibilityOf(element));
		//element.sendKeys(value);
		
	}
	
	public static void waitForElement(final WebElement locator,
			int waitTime, String message) throws Exception {

		final FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		//wait.pollingEvery(2, TimeUnit.SECONDS);
	//	wait.withTimeout(waitTime, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);

		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver dr) {
				// System.out.println("Wait.......");
				wait.until(ExpectedConditions.visibilityOf(locator));
				wait.until(ExpectedConditions.elementToBeClickable(locator));
				return locator;
			}
		};
		//Thread.sleep(1000);
		wait.until(function);
	}
	
}
