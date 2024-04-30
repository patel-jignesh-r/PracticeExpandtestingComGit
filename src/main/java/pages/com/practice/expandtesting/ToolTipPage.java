package pages.com.practice.expandtesting;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToolTipPage extends BasePageObject{

	public ToolTipPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By onTop = By.id("btn1");
	By onTopTT = By.xpath("//*[starts-with(@aria-describedby, 'tooltip')]");
	
	public String getOnTopTooltipText() {
		
		hoverOverElement(find(onTop));
		waitForElementToVisibile(onTopTT);
		return getvisibleText(onTopTT);
	}
}
