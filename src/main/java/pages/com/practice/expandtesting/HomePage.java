package pages.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePageObject {

	public HomePage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	String pageUrl = "https://practice.expandtesting.com/";

	public void openPage() {
		log.info("Opening page: " + pageUrl);
		openUrl(pageUrl);
		log.info("Page opened!");
	}

	By windows = By.xpath("//a[normalize-space()='Multiple Windows']");
	By Iframes = By.xpath("//a[normalize-space()='IFrame']");
	By jQueryUIMenu = By.xpath("//a[normalize-space()='JQuery UI Menus']");
	By toolTip = By.xpath("//a[normalize-space()='Tooltips']");
	By autocomplete = By.xpath("//a[normalize-space()='Autocomplete']");

	public void openMultipleWindowPage() {
		log.info("Clicking Multiple windows page link on HomePage");
		moveToTargetElement(windows);
		waitForElementToVisibile(windows);
		waitForElementClick(windows);
		click(windows);
		log.info("Page opened! " + getCurrentUrl());
	}

	public void openIframePage() {
		log.info("Clicking IFrame page link on HomePage");
		moveToTargetElement(Iframes);
		waitForElementToVisibile(Iframes);
		waitForElementClick(Iframes);
		click(Iframes);
		log.info("Page opened! " + getCurrentUrl());
	}

	public void openjQueryUIMenuPage() {
		log.info("Clicking jQueryUIMenu page link on HomePage");
		moveToTargetElement(jQueryUIMenu);
		waitForElementToVisibile(jQueryUIMenu);
		waitForElementClick(jQueryUIMenu);
		click(jQueryUIMenu);
		log.info("Page opened! " + getCurrentUrl());
	}

	public void openTooltipPage() {
		log.info("Clicking Tooltip page link on HomePage");
		moveToTargetElement(toolTip);
		waitForElementToVisibile(toolTip);
		waitForElementClick(toolTip);
		click(toolTip);
		log.info("Page opened! " + getCurrentUrl());
	}

	By scrollbarlink = By.xpath("//a[normalize-space()='Scrollbars']");

	public void openAutoCompletePage() {
		log.info("Clicking AutoComplete page link on HomePage");
		scrollToElement(autocomplete);
		moveToTargetElement(scrollbarlink);
		waitForElementToVisibile(autocomplete);
		waitForElementClick(autocomplete);
		click(autocomplete);
		log.info("Page opened! " + getCurrentUrl());
	}

	By floatingmenu = By.xpath("//a[normalize-space()='Floating Menu']");

	public void openFloatingMenuPage() {
		log.info("Clicking Floating Menu page link on HomePage");
		scrollToElement(floatingmenu);
		moveToTargetElement(floatingmenu);
		waitForElementToVisibile(floatingmenu);
		waitForElementClick(floatingmenu);
		click(floatingmenu);
		log.info("Page opened! " + getCurrentUrl());
	}

	By infiniteScroll = By.xpath("//a[normalize-space()='Infinite Scroll']");

	public void openinfiniteScrollPage() {
		log.info("Clicking Infinite Scroll page link on HomePage");
		scrollToElement(infiniteScroll);
		moveToTargetElement(infiniteScroll);
		waitForElementToVisibile(infiniteScroll);
		waitForElementClick(infiniteScroll);
		click(infiniteScroll);
		log.info("Page opened! " + getCurrentUrl());
	}

	By entryAD = By.xpath("//a[normalize-space()='Entry Ad']");

	public void openEntryADPage() {
		log.info("Clicking EntryADs page link on HomePage");
		moveToTargetElement(entryAD);
		waitForElementToVisibile(entryAD);
		waitForElementClick(entryAD);
		click(entryAD);
		log.info("Page opened! " + getCurrentUrl());
	}

	By exitIntent = By.xpath("//a[normalize-space()='Exit Intent']");

	public void openExitIntentPage() {
		log.info("Clicking Exit Intent page link on HomePage");
		moveToTargetElement(exitIntent);
		waitForElementToVisibile(exitIntent);
		waitForElementClick(exitIntent);
		click(exitIntent);
		log.info("Page opened! " + getCurrentUrl());
	}

	By notesApp = By.xpath("//a[normalize-space()='Notes App | React']");

	public void openNotesAppPage() {
		log.info("Clicking on Notes App | React page link on HomePage");
		moveToTargetElement(notesApp);
		waitForElementToVisibile(notesApp);
		waitForElementClick(notesApp);
		click(notesApp);
		log.info("Page opened! " + getCurrentUrl());
	}

	By SecurePasswordChecker = By.xpath("//a[normalize-space()='Secure Password Checker']");

	public void openSecurePasswordCheckerPage() {
		log.info("Clicking on Secure Password Checker page link on HomePage");

		// scrollToElement(colorWheel);
		moveToTargetElement(SecurePasswordChecker);
		waitForElementClick(SecurePasswordChecker);
		click(SecurePasswordChecker);
		log.info("Page opened! " + getCurrentUrl());
	}

	By webPark = By.xpath("//a[normalize-space()='Web Parking']");

	public void openWebParkingPage() {
		log.info("Clicking on Web Parking page link on HomePage");

		// scrollToElement(colorWheel);
		moveToTargetElement(webPark);
		waitForElementClick(webPark);
		click(webPark);
		log.info("Page opened! " + getCurrentUrl());
	}

	By colorWheel = By.xpath("//a[normalize-space()='Color Wheel']");

	public void openColorWheelPage() {
		log.info("Clicking on color Wheel page link on HomePage");
		moveToTargetElement(colorWheel);

		waitForElementClick(colorWheel);
		click(colorWheel);
		log.info("Page opened! " + getCurrentUrl());
	}

}
