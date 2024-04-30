package tests.com.practice.expandtesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.HomePage;
import pages.com.practice.expandtesting.ToolTipPage;

public class ToolTipPageTest extends TestUtilities{
	@Test
	public void toolTipTesting() {

		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openTooltipPage();
		
		ToolTipPage tooltip = new ToolTipPage(driver, log);
		
		log.info(tooltip.getOnTopTooltipText());
		Assert.assertEquals(tooltip.getOnTopTooltipText(), "Tooltip on top", "Tooltip text is not as expected.");
}
}