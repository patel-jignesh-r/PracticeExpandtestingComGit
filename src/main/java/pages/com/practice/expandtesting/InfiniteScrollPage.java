package pages.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class InfiniteScrollPage extends BasePageObject {

	public InfiniteScrollPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By textParagraphs = By.className("jscroll-added");

	public int elementsCountCheck() {
		int elementsCount = findAll(textParagraphs).size();
		log.info("Total count of Text paraghraphs elements present: " + elementsCount);
		return elementsCount;
	}

	public int infiniteScrollcheck() {
		int initialCount = findAll(textParagraphs).size();

		while (true) {
			waitForElementToVisibile(textParagraphs);
			scrollToBottom();

			try {
				Thread.sleep(5000); // Adjust the sleep time according to your page loading time
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//waitForElementToVisibile(textParagraphs);

			int newCount = findAll(textParagraphs).size();
			if (newCount > initialCount) {
				log.info("Exiting loop.");
				break;
			} else if (newCount <= initialCount) {
				log.info("No new elements loaded. Exiting loop.");
				break;
			}
			initialCount = newCount;
		}
		int newCount = findAll(textParagraphs).size();
		log.info("Total count of Text paraghraphs elements present after scrolling: " + newCount);
		return newCount;
	}

	public void infiniteScrolltest() {
		// Get the current scroll height
		long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
		log.info(lastHeight);
		// Loop until we reach the bottom of the page or a certain depth
		while (true) {
			// Scroll down the page
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

			// Wait for some time to allow content loading (You may need to adjust this)
			try {
				Thread.sleep(2000); // Adjust the sleep time according to your page loading time
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Calculate new scroll height and compare with the last scroll height
			long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
			log.info(newHeight);
//			if (newHeight == lastHeight) {
//				// If the scroll height hasn't changed, we've reached the bottom of the page
//				break;
//			}
			if (newHeight > lastHeight) {
				log.info("The scroll height has changed, Exiting loop.");
				break;
			} else if (newHeight == lastHeight) {
				log.info("We've reached the bottom of the page.");
				break;
			}
			lastHeight = newHeight;
		}
	}

}