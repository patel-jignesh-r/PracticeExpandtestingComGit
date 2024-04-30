package pages.webpark.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.com.practice.expandtesting.BasePageObject;

public class BookingDetailsPage extends BasePageObject {

	public BookingDetailsPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By firstName = By.id("firstName");
	By lastName = By.id("lastName");
	By emailAddress = By.id("email");
	By phoneNo = By.id("phone");
	By vehicleSize = By.id("vehicleSize");
	By lpNumber = By.id("lpNumber");
	By continueButton = By.id("continue");

	public void fillBookingDetails(String[] bookingDetails) {
		log.info("Entering Booking Details : ");
		typeText(firstName, bookingDetails[0]);
		typeText(lastName, bookingDetails[1]);
		typeText(emailAddress, bookingDetails[2]);
		typeText(phoneNo, bookingDetails[3]);
		selectByText(vehicleSize, bookingDetails[4]);
		typeText(lpNumber, bookingDetails[5]);
	}

	public void clickContinueButton() {
		log.info("Click on Continue button.");
		waitForElementClick(continueButton);
		click(continueButton);
	}

	By invalidFirstName = By.xpath("(//div[@class='invalid-feedback'])[1]");
	By invalidLastName = By.xpath("(//div[@class='invalid-feedback'])[2]");
	By invalidEmail = By.xpath("(//div[@class='invalid-feedback'])[3]");
	By invalidPhone = By.xpath("(//div[@class='invalid-feedback'])[4]");
	By invalidVehicaleSize = By.xpath("(//div[@class='invalid-feedback'])[5]");
	By invalidIpnumber = By.xpath("(//div[@class='invalid-feedback'])[6]");

	public boolean invalidFirstnameErrorDisplayed() {
		return find(invalidFirstName).isDisplayed();
	}

	public String getInvalidFirstnameErrorText() {
		waitForElementToVisibile(invalidFirstName);
		return getvisibleText(invalidFirstName).trim();
	}

	public boolean invalidLastNameErrorDisplayed() {
		return find(invalidLastName).isDisplayed();
	}

	public String getInvalidLastNameErrorText() {
		waitForElementToVisibile(invalidLastName);
		return getvisibleText(invalidLastName).trim();
	}

	public boolean invalidEmailErrorDisplayed() {
		return find(invalidEmail).isDisplayed();
	}

	public String getInvalidEmailErrorText() {
		waitForElementToVisibile(invalidEmail);
		return getvisibleText(invalidEmail).trim();
	}

	public boolean invalidPhoneErrorDisplayed() {
		return find(invalidPhone).isDisplayed();
	}

	public String getInvalidPhoneErrorText() {
		waitForElementToVisibile(invalidPhone);
		return getvisibleText(invalidPhone).trim();
	}

	public boolean didNotselectVehicaleSizeErrorDisplayed() {
		return find(invalidVehicaleSize).isDisplayed();
	}

	public String getInvalidVehicaleSizeErrorText() {
		waitForElementToVisibile(invalidVehicaleSize);
		return getvisibleText(invalidVehicaleSize).trim();

	}

	public boolean invalidIpnumberErrorDisplayed() {
		return find(invalidIpnumber).isDisplayed();
	}

	public String getInvalidIpnumberErrorText() {
		waitForElementToVisibile(invalidIpnumber);
		return getvisibleText(invalidIpnumber).trim();
	}
}
