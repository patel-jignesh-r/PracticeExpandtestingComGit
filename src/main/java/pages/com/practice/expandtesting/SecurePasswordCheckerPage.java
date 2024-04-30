package pages.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePasswordCheckerPage extends BasePageObject {

	public SecurePasswordCheckerPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By passwordField = By.cssSelector("input[name='password']");
	By lengthValidationText = By.cssSelector(".length");
	By lowercaseValidationText = By.cssSelector(".lowercase");
	By uppercaseValidationText = By.cssSelector(".uppercase");
	By specialValidationText = By.cssSelector(".special");

	public void enterPassword(String password) {
		log.info("Entering password as: " + password);
		waitForElementToVisibile(passwordField);
		typeText(passwordField, password);
	}

	public String getLengthValidationText() {
		waitForElementToVisibile(lengthValidationText);
		return find(lengthValidationText).getText();
	}

	public String getLowercaseValidationText() {
		waitForElementToVisibile(lowercaseValidationText);
		return find(lowercaseValidationText).getText();
	}

	public String getUppercaseValidationText() {
		waitForElementToVisibile(uppercaseValidationText);
		return find(uppercaseValidationText).getText();
	}

	public String getspecialValidationText() {
		waitForElementToVisibile(specialValidationText);
		return find(specialValidationText).getText();
	}

	public boolean LengthValidationTextMsgNotDisplayed() {
		return find(lengthValidationText).isDisplayed();
	}

	public boolean LowercaseValidationTextMsgNotDisplayed() {
		return find(lowercaseValidationText).isDisplayed();
	}

	public boolean UppercaseValidationTextMsgNotDisplayed() {
		return find(uppercaseValidationText).isDisplayed();
	}

	public boolean specialValidationTextMsgNotDisplayed() {
		return find(specialValidationText).isDisplayed();
	}
}
