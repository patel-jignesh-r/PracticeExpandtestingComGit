package pages.com.practice.expandtesting;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {

	protected WebDriver driver;
	protected Logger log;

	public BasePageObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	public void waitForElementClick(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(locator));

	}

	public void waitForElementToVisibile(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	protected void openUrl(String url) {
		driver.get(url);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void pageRefresh() {
		log.info("Refreshing the Page.");
		driver.navigate().refresh();
	}

	public WebElement find(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}

	public void click(By locator) {
		// waitForElementClick(locator);
		find(locator).click();
	}

	public String getvisibleText(By locator) {
		return find(locator).getText();
	}

	public void scrollToElement(By locator) {
		log.info("Scrolling to the Element on the page");
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		// you can scroll to a specific element
		jsexecutor.executeScript("arguments[0].scrollIntoView(true);", find(locator));
	}

	public void switchToiFrame(By locator) {
		driver.switchTo().frame(find(locator)); // switching to iframe
	}

	public void typeText(By locator, String text) {
		find(locator).clear();
		find(locator).sendKeys(text);
	}

	public void clearTextField(By locator) {
		find(locator).clear();
	}

	public void moveToTargetElement(By locator) {
		Actions action = new Actions(driver);
		waitForElementToVisibile(locator);
		action.moveToElement(find(locator)).build().perform();
		;
	}

	public void hoverOverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public void scrollToBottom() {
		log.info("Scrolling to the Bottom of the page");
		// JavaScript executor for scrollbar action
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		jsexecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollToTop() {
		log.info("Scrolling to the Top of the page");
		// JavaScript executor for scrollbar action
		JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
		jsexecutor.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}

	public void selectByText(By locator, String text) {
		Select select = new Select(find(locator));
		select.selectByVisibleText(text);
	}
}
