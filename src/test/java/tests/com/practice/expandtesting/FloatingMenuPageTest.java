package tests.com.practice.expandtesting;

import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.FloatingMenuPage;
import pages.com.practice.expandtesting.HomePage;

public class FloatingMenuPageTest extends TestUtilities {
	@Test
	public void autoCompleteTesting() {

		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openFloatingMenuPage();

		FloatingMenuPage floatingmenu = new FloatingMenuPage(driver, log);
		Assert.assertEquals(floatingmenu.getfloatmenuClassAttribute(), "mt-3 mb-3");
		String initialFloatingMenuClassName = floatingmenu.getfloatmenuClassAttribute();

		// Get the position of the element
		Point intialMenuPosition = floatingmenu.getMenuLocation();
		log.info("The initial position of the floating menu: "+ intialMenuPosition);
		int initialY = intialMenuPosition.getY();

		floatingmenu.scrollThePageBottom();

		Assert.assertEquals(floatingmenu.getfloatmenuClassAttribute(), "mt-3 mb-3 floating-menu");
		String updatedFloatingMenuClassName = floatingmenu.getfloatmenuClassAttribute();

		// Assert that the class Attribute value has changed after scrolling
		Assert.assertNotEquals(initialFloatingMenuClassName, updatedFloatingMenuClassName);
		log.info("The initial attribute value of the floating menu- class: " + initialFloatingMenuClassName
				+ "| The updated attribute value of the floating menu- class after scrolling: "
				+ updatedFloatingMenuClassName);

		// Get the position of the element

		Point updatedMenuPosition = floatingmenu.getMenuLocation();
		int updatedY = updatedMenuPosition.getY();
		log.info("The updated position of the floating menu: "+updatedMenuPosition);

		// Assert that the Y-coordinate has changed after scrolling
		Assert.assertNotEquals(initialY, updatedY);
		log.info("The initial position(Y-coordinate) of the floating menu: " + initialY
				+ "| The updated position(Y-coordinate) of the floating menu after BOTTOM scrolling: " + updatedY);

		floatingmenu.scrollThePageTop();
		Point finalMenuPosition = floatingmenu.getMenuLocation();
		log.info("The final position of the floating menu after TOP scrolling: " + finalMenuPosition);

	}
}