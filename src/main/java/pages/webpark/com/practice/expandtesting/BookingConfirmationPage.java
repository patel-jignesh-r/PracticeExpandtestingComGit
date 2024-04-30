package pages.webpark.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.com.practice.expandtesting.BasePageObject;

public class BookingConfirmationPage extends BasePageObject {

	public BookingConfirmationPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By successText = By.xpath("//strong[normalize-space()='Thank you, your booking is confirmed!']");
	By bookingID = By.xpath("//span[@data-testid='booking-id']");
	By bookingBarcodeID = By.xpath("//*[name()='text' and contains(@text-anchor,'middle')]");
	
	public String getBookingConfirmationText() {
		return getvisibleText(successText);
	}
	
	public String getbookingID() {
		return getvisibleText(bookingID);
	}
	
	public String getbookingBarcodeID() {
		return getvisibleText(bookingBarcodeID);
	}
}
