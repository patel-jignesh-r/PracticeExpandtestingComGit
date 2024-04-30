package tests.com.practice.expandtesting;

import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.HomePage;
import pages.com.practice.expandtesting.JQueryUIMenusPage;

public class JQueryUIMenuPageTest extends TestUtilities {
	
	@Test
	public void jQueryUIMenuTesting() {
		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openjQueryUIMenuPage();
		
		JQueryUIMenusPage jMenu = new JQueryUIMenusPage(driver, log);
		jMenu.selectFilefromMenu("csv");
	}

}
