package tests.com.practice.expandtesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.HomePage;
import pages.com.practice.expandtesting.IFramesPage;

public class IFramePageTest extends TestUtilities {
	@Test
	public void editorIframeTesting() {

		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openIframePage();

		IFramesPage iFrame = new IFramesPage(driver, log);
		iFrame.switchToEmailiFrame();
		iFrame.emailSubscription("user501@gmail.com");
		String actualText = "You are now subscribed!";
		Assert.assertEquals(iFrame.getSuccessText(), actualText, "Subscription Unsuccessful.");
		
		iFrame.switchToEditoriFrame();
		iFrame.enterTextToEditor("Hello");
		iFrame.switchToVideoiFrame();
		iFrame.clickVideoPlay();

	}
}
