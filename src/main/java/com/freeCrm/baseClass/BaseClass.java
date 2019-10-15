package com.freeCrm.baseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.freeCrm.testUtil.DriverEventListener;



public class BaseClass {
	
	public String Expected_Title;
	public String Actual_Title;
	public static WebDriver driver;
	//protected String agreementno;
	protected String totalamount;
	public static String currentdir;
	public String configFileLoc;
	public static Properties propconfig;
	public static EventFiringWebDriver event;
	public static DriverEventListener driverListener;
	public static TreeSet<String> products; // used for end to end scenario and incase of mulitple products in cart
	
	
	public static ArrayList<String> prodNum;// used to store single product number 
	
	
	
	public BaseClass()
	{
		try {
		//System.out.println("BaseClass constructor called....");
		currentdir = System.getProperty("user.dir");
		
		System.out.println("Current Directory...."+currentdir);
		propconfig = new Properties();
		
			FileInputStream fp = new FileInputStream(currentdir+"/src/main/java/com/freeCrm/configFile/config.properties");
			propconfig.load(fp);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}		
		
	}
	
	public static void launchBrowser()
	{
		// launch different browsers based on value of Browser property in property file.
		if (propconfig.getProperty("Browser").equalsIgnoreCase("Chrome")) {

			System.setProperty(propconfig.getProperty("ChromeSet"), currentdir
					+ propconfig.getProperty("ChromeDriverPath"));
			driver = new ChromeDriver();
			
		}/* else if (propconfig.getProperty("Browser").equalsIgnoreCase("IE")) {

			System.setProperty(propconfig.getProperty("IEset"), currentdir
					+ propconfig.getProperty("IEDriverPath"));

			DesiredCapabilities capability = DesiredCapabilities
					.internetExplorer();
			capability
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capability.setCapability(
			InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capability.setCapability("nativeEvents", false);
			capability.setCapability("unexpectedAlertBehaviour", "accept");
			capability.setCapability("ignoreProtectedModeSettings", true);
			capability.setCapability("disable-popup-blocking", true);
			capability.setCapability("enablePersistentHover", true);
			driver = new InternetExplorerDriver(capability);
			// driver = new FirefoxDriver();
	
		}

		else if (propconfig.getProperty("Browser").equalsIgnoreCase("edge")) {

			System.setProperty(propconfig.getProperty("EDGEset"), currentdir
					+ propconfig.getProperty("EDGEDriverPath"));

			driver = new EdgeDriver();
			
		} else if (propconfig.getProperty("Browser")
				.equalsIgnoreCase("Firefox")) {

			System.setProperty(propconfig.getProperty("Firefoxset"), currentdir
					+ propconfig.getProperty("FirefoxDriverPath"));
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile myprofile = profile.getProfile("test");

			myprofile.setAcceptUntrustedCertificates(true);
			myprofile.setAssumeUntrustedCertificateIssuer(false);

			driver = new FirefoxDriver(myprofile);
			// driver.navigate().to(propconfig.getProperty("EnvironmentURL"));
			
		}*/
		
		event = new EventFiringWebDriver(driver);
		
		driverListener = new DriverEventListener();
		event.register(driverListener);
		driver = event;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	
		driver.get(propconfig.getProperty("EnvironmentURL"));
		//System.out.println("Browser launched....");
		
	}
	
}
