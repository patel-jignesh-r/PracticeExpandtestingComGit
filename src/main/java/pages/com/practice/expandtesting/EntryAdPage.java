package pages.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class EntryAdPage extends BasePageObject {

	public EntryAdPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By adContaioner = By.className("modal-content");
	By closeButton = By.id("close-modal-btn");
	By adLable = By.cssSelector("#exampleModalLabel");
	By clickhere = By.cssSelector("#restart-ad");

	public boolean isEntryAdDisplayed() {
		waitForElementToVisibile(adContaioner);
		return find(adContaioner).isDisplayed();
	}

	public String getEntryAdLableText() {
		return find(adLable).getText();
	}

	public void closeEntryAd() {

		if (isEntryAdDisplayed() == true) {
			waitForElementClick(closeButton);
			click(closeButton);
		} else {
			log.info("Entry Ad doesn't present.");
		}
	}

	public void reEnbleEntryAd() {
		waitForElementClick(clickhere);
		log.info("To re-enable Entry Ad, Clicking on 'click here' link.");
		click(clickhere);
	}

	public void mouseOutOfViewport() {
		// scrollToBottom();
		Actions actions = new Actions(driver);
		actions.moveByOffset(0, 800).perform();
	}
}
