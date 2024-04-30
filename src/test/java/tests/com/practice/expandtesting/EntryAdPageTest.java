package tests.com.practice.expandtesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.EntryAdPage;
import pages.com.practice.expandtesting.HomePage;

public class EntryAdPageTest extends TestUtilities {
	@Test
	public void entryAdTesting() {

		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openEntryADPage();

		// SoftAssert softAssert = new SoftAssert();

		EntryAdPage entryAd = new EntryAdPage(driver, log);

		Assert.assertTrue(entryAd.isEntryAdDisplayed(), "Entry ad doesn't displayed");
		Assert.assertEquals(entryAd.getEntryAdLableText(), "THIS IS A MODAL WINDOW", "Entry ad doesn't displayed");

		entryAd.closeEntryAd();

		entryAd.pageRefresh();
//		sleep(2000);
//		Assert.assertFalse(entryAd.isEntryAdDisplayed(), "Entry ad doesn't displayed");
//		sleep(5000);
		entryAd.reEnbleEntryAd();
		entryAd.pageRefresh();
		Assert.assertTrue(entryAd.isEntryAdDisplayed(), "Entry ad doesn't displayed");
		entryAd.closeEntryAd();
	}

	@Test
	public void ExitIntentTesting() {

		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openExitIntentPage();
		
		EntryAdPage entryAd = new EntryAdPage(driver, log);
		
		entryAd.mouseOutOfViewport();
	}
}