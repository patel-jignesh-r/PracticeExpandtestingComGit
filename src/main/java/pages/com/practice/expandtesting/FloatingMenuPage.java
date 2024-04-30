package pages.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class FloatingMenuPage extends BasePageObject {

	public FloatingMenuPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By menu = By.id("menu");
	By inbox = By.cssSelector(".btn.btn-primary.mb-2");
	By sent = By.cssSelector(".btn.btn-info.mb-2");
	By spam = By.cssSelector(".btn.btn-danger.mb-2");
	By important = By.cssSelector(".btn.btn-success.mb-2");
	By starred = By.cssSelector(".btn.btn-warning.mb-2");

	public Point getMenuLocation() {
		waitForElementToVisibile(menu);
		return find(menu).getLocation();
	}

	public void scrollThePageBottom() {
		scrollToBottom();
	}

	public void scrollThePageTop() {
		scrollToTop();
	}

	public String getfloatmenuClassAttribute() {
		waitForElementToVisibile(menu);
		return find(menu).getAttribute("class");
	}
}
