package base.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {

	String browser;
	WebDriver driver;
	Logger log;

	BrowserDriverFactory(String browser, Logger log) {
		this.browser = browser;
		this.log = log;
	}

	public WebDriver createDriver() {
		log.info("Creating Driver: " + browser);

		switch (browser) {
		case "firefox":
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "chrome":
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "edge":
			//WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			log.info("Creating default driver: Chrome");
			driver = new ChromeDriver();

			break;
		}
		return driver;
	}

}
