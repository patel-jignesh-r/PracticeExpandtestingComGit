package pages.com.practice.expandtesting;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AutoCompletePage extends BasePageObject {

	public AutoCompletePage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By typeCountry = By.xpath("//input[@id='country']");
	By autocompleteList = By.xpath("//div[@id='countryautocomplete-list']//div");
	By submitButton = By.xpath("//button[@class='btn btn-primary mx-2']");
	By result = By.id("result");

	public void searchCountryName(String keyword, String country) {
		waitForElementToVisibile(typeCountry);
		log.info("Typing keyword '" + keyword + "' in autocomplete suggestions for desired country name: " + country);
		typeText(typeCountry, keyword);

		List<WebElement> countries = findAll(autocompleteList);
		
		boolean countryFound = false;
		for (int i = 0; i < countries.size(); i++) {

			String countryName = countries.get(i).getText();
			if (countryName.contains(country)) {
				countryFound = true;
				log.info(country + " country name found in autocomplete suggestions with keyword: " + keyword);

				findAll(autocompleteList).get(i).click();
				waitForElementClick(submitButton);
				click(submitButton);
				break;
			}
		}
		if (!countryFound) {
			log.info(country + " country name NOT found in autocomplete suggestions with keyword: " + keyword);
			click(submitButton);
		}

	}

	public String getSelectedCountryText() {
		if (find(result).isDisplayed()) {
			waitForElementToVisibile(result);
			return getvisibleText(result);
		} else
			return null;
	}

}
