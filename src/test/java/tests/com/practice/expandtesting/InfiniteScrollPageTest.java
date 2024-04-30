package tests.com.practice.expandtesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.HomePage;
import pages.com.practice.expandtesting.InfiniteScrollPage;

public class InfiniteScrollPageTest extends TestUtilities {

	@Test
	public void infiniteScrollTesting() {

		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openinfiniteScrollPage();

		InfiniteScrollPage infinitescroll = new InfiniteScrollPage(driver, log);

		int initialCount = infinitescroll.elementsCountCheck();
		int newCount = infinitescroll.infiniteScrollcheck();

		Assert.assertNotEquals(initialCount, newCount);

		infinitescroll.infiniteScrolltest();
	}
}