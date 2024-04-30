package tests.com.practice.expandtesting;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.HomePage;
import pages.com.practice.expandtesting.SecurePasswordCheckerPage;

public class SecurePasswordCheckerTest extends TestUtilities {

	@Test(dataProvider = "getPassword")
	public void PasswordCheckerTesting(String password) {
		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openSecurePasswordCheckerPage();

		SecurePasswordCheckerPage passwordpage = new SecurePasswordCheckerPage(driver, log);

		//String password = "SecureP@ssw0rd"; // Example password
		passwordpage.enterPassword(password);

		if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9\\W]).{8,}$")) {
			log.info("Password is secure.");

			Assert.assertFalse(passwordpage.LengthValidationTextMsgNotDisplayed());
			Assert.assertFalse(passwordpage.LowercaseValidationTextMsgNotDisplayed());
			Assert.assertFalse(passwordpage.UppercaseValidationTextMsgNotDisplayed());
			Assert.assertFalse(passwordpage.specialValidationTextMsgNotDisplayed());

		} else if (password.length() < 8) {
			log.info("NOT SECURE | Password must be at least 8 characters long.");

			Assert.assertEquals(passwordpage.getLengthValidationText(), "Must be at least 8 characters long.",
					"Password is not secure.");

		} else if (!password.matches(".*[a-z].*")) {
			log.info("NOT SECURE | Password must contain a lowercase letter.");

			Assert.assertEquals(passwordpage.getLowercaseValidationText(), "Must contain a lowercase letter.",
					"Password is not secure.");

		} else if (!password.matches(".*[A-Z].*")) {
			log.info("NOT SECURE | Password must contain an uppercase letter.");

			Assert.assertEquals(passwordpage.getUppercaseValidationText(), "Must contain an uppercase letter.",
					"Password is not secure.");

		} else if (!password.matches(".*[0-9\\W].*")) {
			log.info("NOT SECURE | Password must contain a number or special character.");

			Assert.assertEquals(passwordpage.getspecialValidationText(), "Must contain a number or special character.",
					"Password is not secure.");
		}

	}
	@DataProvider
	public Object[][] getPassword() {
		Object[][] data = new Object[1][1];
		data[0][0] = "SecureP@assw0rd";
//		data[1][0] = "password";
//		data[2][0] = "Password";
//		data[3][0] = "passw0rd";
//		data[4][0] = "Pasw0rd";
		return data;
}
}