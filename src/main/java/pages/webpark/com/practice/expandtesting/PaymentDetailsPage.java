package pages.webpark.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.com.practice.expandtesting.BasePageObject;

public class PaymentDetailsPage extends BasePageObject {

	public PaymentDetailsPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By cardNoText = By.xpath("//strong[@class='badge text-bg-warning']");
	By cardNumber = By.id("cardNumber");
	By expirationDate = By.id("expirationDate");

	By securityCode = By.id("securityCode");
	By completeReservation = By.id("completeReservation");

	public void fillPaymentDetails(String expiration_Date, String security_Code) {
		log.info("Enter Payment Details: ");

		typeText(cardNumber, getvisibleText(cardNoText));
		typeText(expirationDate, expiration_Date);
		typeText(securityCode, security_Code);
		log.info("Clicking on Complete reservation button");
		click(completeReservation);
	}

	By invalidCreditCard = By.xpath("(//div[@class='invalid-feedback'])[1]");
	By invalidExpireDate = By.xpath("(//div[@class='invalid-feedback'])[2]");
	By invalidCVV = By.xpath("(//div[@class='invalid-feedback'])[3]");
	
	public boolean invalidCreditCardErrorDisplayed() {
		return find(invalidCreditCard).isDisplayed();
	}

	public String getInvalidCreditCardErrorText() {
		waitForElementToVisibile(invalidCreditCard);
		return getvisibleText(invalidCreditCard).trim();
	}

	public boolean invalidExpireDateErrorDisplayed() {
		return find(invalidExpireDate).isDisplayed();
	}

	public String getInvalidExpireDateErrorText() {
		waitForElementToVisibile(invalidExpireDate);
		return getvisibleText(invalidExpireDate).trim();
	}

	public boolean invalidCVVErrorDisplayed() {
		return find(invalidCVV).isDisplayed();
	}

	public String getInvalidCVVErrorText() {
		waitForElementToVisibile(invalidCVV);
		return getvisibleText(invalidCVV).trim();
	}
}
