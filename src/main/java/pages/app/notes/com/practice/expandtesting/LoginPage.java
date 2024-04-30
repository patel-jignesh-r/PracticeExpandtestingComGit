package pages.app.notes.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.com.practice.expandtesting.BasePageObject;

public class LoginPage extends BasePageObject {

	public LoginPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	By login = By.xpath("//a[normalize-space()='Login']");
	By emailField = By.cssSelector("#email");
	By passwordField = By.cssSelector("#password");
	By loginButton = By.cssSelector("button[type='submit']");

	public void loginToNotesApp(String email, String password) {
		click(login);
		typeText(emailField, email);
		typeText(passwordField, password);
		click(loginButton);
	}

}
