package pages.com.practice.expandtesting;

import java.util.List;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ColorWheelPage extends BasePageObject {

	public ColorWheelPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By playbutton = By.id("playBtn");
	By resetbutton = By.id("resetBtn");

	By colorPicker = By.id("picker");

	public void startPlaying() {
		log.info("Game Started.");
		click(playbutton);
	}

	public void reSetPlaying() {
		log.info("Game Resets.");
		click(resetbutton);
	}

	public int getRotationDegreeofWheel() {
		String canvasStyle = find(colorPicker).getAttribute("style");
		log.info(canvasStyle);
		String[] parts = canvasStyle.split("\\(|deg");
		// Parsing the second part of the split result (which should contain the
		// rotation degree)
		int rotation = Integer.parseInt(parts[1].trim());
		log.info(rotation);
		return rotation;
	}

	public void clickOnCorrectColorAnswer() {
		// int number1 = specificColorToClick() + 2;
		log.info("Finding the answer");
		By colorButtons = By.xpath("//div/button");
		waitForElementToVisibile(colorButtons);
		List<WebElement> colors = findAll(colorButtons);
		// log.info("Finding the answer");
		// int wheelRotation = getRotationDegreeofWheel();
		int number = specificColorToClick();
		for (int i = 0; i < colors.size(); i++) {
			WebElement color ;
			if (i == number) {
				log.info(i + "In the loop.");
				log.info(colors.get(i+2).findElement(colorButtons).getText());
				colors.get(i+2).findElement(colorButtons).click();
				break;
			}
		}
	}

	public int specificColorToClick() {
		int wheelRotation = getRotationDegreeofWheel();
		int colorPartsMoved = (int) Math.ceil(((wheelRotation + 90) % 360) / (360.0 / 6));
		// double specificColor = Math.ceil(((wheelRotation + 90) % 360) / (360 / 6));
		log.info(colorPartsMoved);
		return colorPartsMoved;
	}
}
