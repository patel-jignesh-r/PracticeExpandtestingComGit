package tests.app.notes.com.practice.expandtesting;

import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.app.notes.com.practice.expandtesting.LoginPage;
import pages.com.practice.expandtesting.HomePage;

public class LoginPageTest extends TestUtilities {
	
	@Test
	public void loginTesting() {
		
		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openNotesAppPage();
		
		LoginPage login = new LoginPage(driver, log);
		String loginEmail = "user501@gmail.com";
		String loginPassword = "User@501";
		login.loginToNotesApp(loginEmail, loginPassword);
	}

}
