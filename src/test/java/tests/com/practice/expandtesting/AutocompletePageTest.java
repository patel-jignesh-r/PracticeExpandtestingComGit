package tests.com.practice.expandtesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.com.practice.expandtesting.TestUtilities;
import pages.com.practice.expandtesting.AutoCompletePage;
import pages.com.practice.expandtesting.HomePage;

public class AutocompletePageTest extends TestUtilities {
	@Test
	public void autoCompleteTesting() {

		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openAutoCompletePage();

		AutoCompletePage autocomplete = new AutoCompletePage(driver, log);

		String keyword = "si";
		String countryName = "South Africa";
		autocomplete.searchCountryName(keyword, countryName);

		Assert.assertEquals(autocomplete.getSelectedCountryText(), "You selected: " + countryName, "Desired country '"
				+ countryName + "' not found in autocomplete suggestions with keyword:' " + keyword + "' |");
	}
}