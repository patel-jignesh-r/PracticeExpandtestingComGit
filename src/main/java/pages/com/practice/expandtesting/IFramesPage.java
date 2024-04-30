package pages.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IFramesPage extends BasePageObject {

	public IFramesPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By videoiFrame = By.id("iframe-youtube");
	By editoriFrame = By.id("mce_0_ifr");
	By emailiFrame = By.id("email-subscribe");

	public void switchToEditoriFrame() {
		log.info("Switching to : External IFrame: TinyMCE Editor");
		waitForElementToVisibile(editoriFrame);
		switchToiFrame(editoriFrame);
	}

	public void switchToEmailiFrame() {
		log.info("Switching to :Internal IFrame: Email Subscription");
		waitForElementToVisibile(emailiFrame);
		switchToiFrame(emailiFrame);
	}
	
	public void switchToVideoiFrame() {
		log.info("Switching to :External IFrame: YouTube Video Player");
		waitForElementToVisibile(videoiFrame);
		switchToiFrame(videoiFrame);
	}

	By emailField = By.xpath("//input[@id='email']");
	By subscribe = By.xpath("//button[@id='btn-subscribe']");
	By successText = By.id("success-message");

	public void emailSubscription(String email) {
		log.info("Entering email: " + email + " and clicking on Subscribe button.");
		waitForElementToVisibile(emailField);
		typeText(emailField, email);
		waitForElementClick(subscribe);
		click(subscribe);
	}

	public String getSuccessText() {
		waitForElementToVisibile(successText);
		String actualText= getvisibleText(successText);
		driver.switchTo().defaultContent();
		return actualText;
	}
	
	By editorBox = By.id("tinymce");
	
	public void enterTextToEditor(String text) {
		log.info("Clearing the text field and entering text: " + text );
		waitForElementToVisibile(editorBox);
		click(editorBox);
		clearTextField(editorBox);
		typeText(editorBox, text);
		driver.switchTo().defaultContent();
	}
	
	By youtubePlay = By.xpath("//*[@id='player']");
	public void clickVideoPlay() {
		log.info("Clicking on Play button.");
		waitForElementClick(youtubePlay);
		click(youtubePlay);
		driver.switchTo().defaultContent();
	}
	
}
