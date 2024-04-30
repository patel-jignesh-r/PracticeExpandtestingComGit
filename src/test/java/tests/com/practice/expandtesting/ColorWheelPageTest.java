package tests.com.practice.expandtesting;

import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.ColorWheelPage;
import pages.com.practice.expandtesting.HomePage;

public class ColorWheelPageTest extends TestUtilities {

	@Test
	public void colorWheelTesting() {
		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openColorWheelPage();

		ColorWheelPage colorWheel = new ColorWheelPage(driver, log);
		colorWheel.startPlaying();
		sleep(10000);
		colorWheel.clickOnCorrectColorAnswer();
	}
}
