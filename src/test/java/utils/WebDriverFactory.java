package utils;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
	
    private static WebDriverFactory factory;

    public static WebDriverFactory getInstance() {
        if (factory == null)
            factory = new WebDriverFactory();
        return factory;
    }
 
    public WebDriver getDriver(String browser){
        if(browser.equals(BrowserType.CHROME.toString()))
            return createChromeDriver();
        else if(browser.equals(BrowserType.FIREFOX.toString()))
            return createFirefoxDriver();
        else if(browser.equals(BrowserType.IEXPLORE.toString()))
        	return createInternetExplorerDriver();
        else if(browser.equals(BrowserType.HTMLUNIT.toString()))
            return createHtmlUnitDriver();
        return null;
    }

    private WebDriver createChromeDriver(){
    	String driverPath = "src/test/resources"+File.separator;
    	if(SystemUtils.IS_OS_MAC_OSX) driverPath += "chromedriver-mac";
    	else if(SystemUtils.IS_OS_WINDOWS) driverPath += "chromedriver.exe"; 
    	else if(SystemUtils.IS_OS_LINUX) driverPath += "chromedriver";
    	
    	System.setProperty("webdriver.chrome.driver", driverPath);
    	
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--incognito");
    	options.addArguments("--start-maximized");
    	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.102/"), capabilities);

        // Selenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://my.test.site.org/");


        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.0.102/"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return (ChromeDriver) driver;
    }

    private WebDriver createFirefoxDriver(){
    	String driverPath = "src/test/resources"+File.separator;
    	if(SystemUtils.IS_OS_MAC_OSX) driverPath += "geckodriver-mac";
    	else if(SystemUtils.IS_OS_WINDOWS) driverPath += "geckodriver.exe"; 
    	else if(SystemUtils.IS_OS_LINUX) driverPath += "geckodriver-linux64";
    	
    	System.setProperty("webdriver.gecko.driver", driverPath);
    	
        return new FirefoxDriver();
    }
    
    private WebDriver createInternetExplorerDriver(){
    	String driverPath = "src/test/resources"+File.separator;
    	if(SystemUtils.IS_OS_WINDOWS) driverPath += "IEDriverServer.exe"; 
    	
    	System.setProperty("webdriver.ie.driver", driverPath);
    	
    	return new InternetExplorerDriver();
    }

    private WebDriver createHtmlUnitDriver(){
        return new HtmlUnitDriver();
    }
}
