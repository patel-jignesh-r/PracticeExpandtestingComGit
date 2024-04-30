package tests.webpark.com.practice.expandtesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.com.practice.expandtesting.CsvDataProviders;
import base.com.practice.expandtesting.ExcelDataProviders;
import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.HomePage;
import pages.webpark.com.practice.expandtesting.BookingConfirmationPage;
import pages.webpark.com.practice.expandtesting.BookingDetailsPage;
import pages.webpark.com.practice.expandtesting.ParkingCostCalculatorPage;
import pages.webpark.com.practice.expandtesting.PaymentDetailsPage;

public class WebParkPageTest extends TestUtilities {

	@Test(testName = "Positive-Test-Web Parking", groups = { "smoke" })
	public void webParkingTesting() {
		log.info("Web Parking Page Test Started.");
		extentTest.info("Web Parking Page Test Started.");
		/* Home/Parking Cost Calculator */
		parkingCostCalculatorTesting();
		/* Home/Parking Cost Calculator/Booking Details */
		bookingDetailsTesting();
		/* Home/Parking Cost Calculator/Payment Details */
		paymentDetailsTesting();
		/* Home/Parking Cost Calculator/Booking Confirmation */
		bookingConfirmationTesting();

	}

	public void parkingCostCalculatorTesting() {
		log.info("Web Parking Cost Calculator screen Test Started.");
		extentTest.info("Web Parking Cost Calculator screen Test Started.");
		
		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openWebParkingPage();

		ParkingCostCalculatorPage parkingCC = new ParkingCostCalculatorPage(driver, log);
		String parking_type = "Economy Parking";

		String entry_date = parkingCC.getCurrentEntry_date();
		String entry_time = parkingCC.getCurrentEntry_time();
		String exit_date = parkingCC.getCurrentExit_date();
		String exit_time = entry_time; // Same time as entry time

		parkingCC.calculateParkingCostByTime(parking_type, entry_date, entry_time, exit_date, exit_time);
		parkingCC.clickCalculateCostButton();
		sleep(1000);

		double actalCost = parkingCC.getVisibleCostText();
		double expectedCost = parkingCC.calculateParkingCostManually(parking_type, entry_date, entry_time, exit_date,
				exit_time);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actalCost, expectedCost, "Total cost value mismatched.");

		parkingCC.clickBookNowButton();

		// softAssert.assertAll();

	}

	public void bookingDetailsTesting() {
		log.info("Booking details screen Test Started.");
		extentTest.info("Booking details screen Test Started.");
		
		BookingDetailsPage booking = new BookingDetailsPage(driver, log);
		String[] bookingDetails = { "Saam", "Patel", "hello@world.com", "9876543210", "Small car", "123456ABC" };

		booking.fillBookingDetails(bookingDetails);
		booking.clickContinueButton();

	}

	public void paymentDetailsTesting() {
		log.info("Payment details screen Test Started.");
		extentTest.info("Payment details screen Test Started.");
		
		PaymentDetailsPage payment = new PaymentDetailsPage(driver, log);
		
		String expiration_Date = "1027"; // MMYY
		String security_Code = "123";
		
		payment.fillPaymentDetails(expiration_Date, security_Code);
	}

	public void bookingConfirmationTesting() {
		log.info("Booking Confirmation details screen Test Started.");
		extentTest.info("Booking Confirmation details screen Test Started.");
		
		BookingConfirmationPage bookingConfirm = new BookingConfirmationPage(driver, log);
		
		String actualText = bookingConfirm.getBookingConfirmationText();
		Assert.assertTrue(actualText.contains("Thank you, your booking is confirmed!"),
				"Something went wrong. Booking Not confirmed");

		Assert.assertEquals(bookingConfirm.getbookingID(), bookingConfirm.getbookingBarcodeID(),
				"Error: Booking ID mismatched.");
	}

	@Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
	public void bookingDetailsNegativeTesting(Map<String, String> testData) {
		log.info("Booking details screen Negative Test Started.");
		extentTest.info("Booking details screen Negative Test Started.");

		parkingCostCalculatorTesting();

		BookingDetailsPage booking = new BookingDetailsPage(driver, log);
		
		String[] bookingDetails = { testData.get("firstName"), testData.get("lastName"), testData.get("emailID"),
				testData.get("phoneNo"), testData.get("carSize"), testData.get("lpNumber") };
		// String[] bookingDetails = { "Saaam", "Pa", "helloworld", "90", "Small car",
		// "123" };

		booking.fillBookingDetails(bookingDetails);
		booking.clickContinueButton();
		sleep(1000);
		SoftAssert softassert = new SoftAssert();
		List<String> actualErrorMessages = new ArrayList<>();
		if (booking.invalidFirstnameErrorDisplayed()) {
			actualErrorMessages.add(booking.getInvalidFirstnameErrorText());
		} else
			actualErrorMessages.add("");
		if (booking.invalidLastNameErrorDisplayed()) {
			actualErrorMessages.add(booking.getInvalidLastNameErrorText());
		} else
			actualErrorMessages.add("");
		if (booking.invalidEmailErrorDisplayed()) {
			actualErrorMessages.add(booking.getInvalidEmailErrorText());
		} else
			actualErrorMessages.add("");
		if (booking.invalidPhoneErrorDisplayed()) {
			actualErrorMessages.add(booking.getInvalidPhoneErrorText());
		} else
			actualErrorMessages.add("");
		if (booking.didNotselectVehicaleSizeErrorDisplayed()) {
			actualErrorMessages.add(booking.getInvalidVehicaleSizeErrorText());
		} else
			actualErrorMessages.add("");
		if (booking.invalidIpnumberErrorDisplayed()) {
			actualErrorMessages.add(booking.getInvalidIpnumberErrorText());
		} else
			actualErrorMessages.add("");

		// Ensure at least one error message is displayed
		Assert.assertFalse(actualErrorMessages.isEmpty(), "No error message displayed.");
		
		String[] expectedErrorMessages = { "A valid first name (3-20 characters) is required.",
				"A valid last name (3-20 characters) is required.", "A valid email address is required.",
				"A valid phone number (5-15 digits) is required.", "A valid car size is required.",
				"A valid LPN (5-20 characters / digits) is required." };

		for (int i = 0; i < actualErrorMessages.size(); i++) {
			String error = actualErrorMessages.get(i);
			if (error != "") {
				softassert.assertEquals(error, expectedErrorMessages[i], "Error message mismatch at index " + i);
			}
		}
		softassert.assertAll();
	}

	@Test(priority = 2, dataProvider = "excelReader", dataProviderClass = ExcelDataProviders.class)
	public void paymentDetailsNegativeTesting(Map<String, String> testData) {
		extentTest.info("Payment details screen Negative Test Started.");
		log.info("Payment details screen Negative Test Started.");
		parkingCostCalculatorTesting();
		bookingDetailsTesting();

		PaymentDetailsPage payment = new PaymentDetailsPage(driver, log);
		
		String expiration_Date = testData.get("expirationDate"); // MMYY
		String security_Code = testData.get("CVV");
		
		payment.fillPaymentDetails(expiration_Date, security_Code);
		
		SoftAssert softassert = new SoftAssert();

		List<String> actualErrorMessages = new ArrayList<>();
		if (payment.invalidCreditCardErrorDisplayed()) {
			actualErrorMessages.add(payment.getInvalidCreditCardErrorText());
		} else
			actualErrorMessages.add("");
		if (payment.invalidExpireDateErrorDisplayed()) {
			actualErrorMessages.add(payment.getInvalidExpireDateErrorText());
		} else
			actualErrorMessages.add("");
		if (payment.invalidCVVErrorDisplayed()) {
			actualErrorMessages.add(payment.getInvalidCVVErrorText());
		} else
			actualErrorMessages.add("");
		
		// Ensure at least one error message is displayed
		softassert.assertFalse(actualErrorMessages.isEmpty(), "No error message displayed.");
		String[] expectedErrorMessages = { "A valid card number is required.", "A valid expiration date is required.",
				"A valid security code is required." };

		for (int i = 0; i < actualErrorMessages.size(); i++) {

			String error = actualErrorMessages.get(i);
			if (error != "") {
				softassert.assertEquals(error, expectedErrorMessages[i], "Error message mismatch at index " + i);
			}
		}
		softassert.assertAll();

	}
}
