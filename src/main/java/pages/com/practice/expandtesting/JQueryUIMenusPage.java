package pages.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JQueryUIMenusPage extends BasePageObject {

	public JQueryUIMenusPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By enbled = By.id("ui-id-2");
	By downloads = By.id("ui-id-4");
	By pdf = By.id("ui-id-6");
	By csv = By.id("ui-id-7");
	By excel = By.id("ui-id-8");

	public void selectFilefromMenu(String fileType) {

		switch (fileType) {
		case "pdf":
			log.info("Selecting (pdf) from menu.");
			moveToTargetElement(enbled);
			moveToTargetElement(downloads);
			moveToTargetElement(pdf);
			click(pdf);
			break;
		case "csv":
			log.info("Selecting (csv) from menu.");
			moveToTargetElement(enbled);
			moveToTargetElement(downloads);
			moveToTargetElement(csv);
			click(csv);
			break;
		case "excel":
			log.info("Selecting (excel) from menu.");
			moveToTargetElement(enbled);
			moveToTargetElement(downloads);
			moveToTargetElement(excel);
			click(excel);
			break;
		default:
			log.info("Please select from menu (pdf,csv,excel): ");
			break;
		}

	}
}
