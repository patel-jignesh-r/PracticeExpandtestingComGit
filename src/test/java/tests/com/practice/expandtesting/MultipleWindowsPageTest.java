package tests.com.practice.expandtesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.HomePage;
import pages.com.practice.expandtesting.MultipleWindowsPage;

public class MultipleWindowsPageTest extends TestUtilities {

	@Test
	public void multipleWindowsTesting() {

		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openMultipleWindowPage();
		
		MultipleWindowsPage multiple = new MultipleWindowsPage(driver, log);
		
		multiple.openNewWindow();
		multiple.switchToNewWindowHandel();
		String actualNewWindowText ="Example of a new window";
		Assert.assertEquals(multiple.getNewWindowText(), actualNewWindowText, "User is not on new window");
		
		multiple.switchToMainWindowHandel();
		String actualText = "Opening A New Window";
		Assert.assertEquals(multiple.getText(), actualText, "User is not on main window");
		
		
	}

}
