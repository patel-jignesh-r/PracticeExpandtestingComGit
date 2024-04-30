package pages.com.practice.expandtesting;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MultipleWindowsPage extends BasePageObject {

	public MultipleWindowsPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By text = By.xpath("//h1[normalize-space()='Opening a new window']");
	By clickhere = By.xpath("//a[@href='/windows/new']");
	By newWindowText = By.cssSelector("div[class='example'] h1");

	public String getText() {
		waitForElementToVisibile(text);
		return getvisibleText(text);
	}

	public void openNewWindow() {
		log.info("Clicking on 'Click here' link for opnening new window.");
		click(clickhere);
	}

	public void switchToNewWindowHandel() {
		Set<String> handels = driver.getWindowHandles();
		Iterator<String> iterate = handels.iterator();
		iterate.next(); // parentID
		String childID = iterate.next(); // childID
		log.info("Switching to new window.");
		driver.switchTo().window(childID);
	}

	public String getNewWindowText() {
		waitForElementToVisibile(newWindowText);
		return getvisibleText(newWindowText);
	}

	public void switchToMainWindowHandel() {
		Set<String> handels = driver.getWindowHandles();
		Iterator<String> iterate = handels.iterator();
		String parentID = iterate.next();
		log.info("Switching back to main window.");
		driver.switchTo().window(parentID);
	}
}
