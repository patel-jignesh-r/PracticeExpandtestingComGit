package pages.webpark.com.practice.expandtesting;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.com.practice.expandtesting.BasePageObject;

public class ParkingCostCalculatorPage extends BasePageObject {

	public ParkingCostCalculatorPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	By parkinglot = By.id("parkingLot");
	By entryDate = By.id("entryDate");
	By exitDate = By.id("exitDate");
	By entryTime = By.id("entryTime");
	By exitTime = By.id("exitTime");
	By calculateCost = By.id("calculateCost");
	By bookNow = By.id("reserveOnline");

	By resultCostValue = By.id("resultValue");
	By resultDurationMessage = By.id("resultMessage");

	public void calculateParkingCostByTime(String parkingType, String entry_date, String entry_time, String exit_date,
			String exit_time) {

		selectByText(parkinglot, parkingType);
		typeText(entryDate, entry_date);
		typeText(entryTime, entry_time);
		typeText(exitDate, exit_date);
		typeText(exitTime, exit_time);
	}

	public void clickCalculateCostButton() {
		log.info("Click on Calculate Cost button");
		waitForElementClick(calculateCost);
		click(calculateCost);
	}
	
	public void clickBookNowButton() {
		log.info("Click on Book now button");
		waitForElementToVisibile(bookNow);
		if(find(bookNow).isDisplayed())
		click(bookNow);
	}
	
	public double getVisibleCostText() {
		waitForElementToVisibile(resultCostValue);
		String[] result = getvisibleText(resultCostValue).split("€");
		String cost = result[0];
		double totalCost = Double.parseDouble(cost);
		log.info("Total visible cost: " + totalCost);
		return totalCost;

	}
	 // Get current date and time
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();

    // Get next day's date
    LocalDate nextDayDate = currentDate.plusDays(1);

    // Format the dates and times
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	
	public String getCurrentEntry_date() {
        // Reassign values
        String entry_date = dateFormatter.format(currentDate);
		return entry_date;
	}
	public String getCurrentEntry_time() {
        // Reassign values
        String entry_time = timeFormatter.format(currentTime);
		return entry_time;
	}
	
	public String getCurrentExit_date() {
        // Reassign values
        String exit_date = dateFormatter.format(nextDayDate);
		return exit_date;
	}
	public double calculateParkingCostManually(String parking_type, String entryDateStr, String entryTimeStr,
			String exitDateStr, String exitTimeStr) {
	//	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	//	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

		LocalDate entryDate = LocalDate.parse(entryDateStr, dateFormatter);
		LocalTime entryTime = LocalTime.parse(entryTimeStr, timeFormatter);
		LocalDate exitDate = LocalDate.parse(exitDateStr, dateFormatter);
		LocalTime exitTime = LocalTime.parse(exitTimeStr, timeFormatter);

		LocalDateTime entryDateTime = LocalDateTime.of(entryDate, entryTime);
		LocalDateTime exitDateTime = LocalDateTime.of(exitDate, exitTime);

		Duration duration = Duration.between(entryDateTime, exitDateTime);
		
		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		log.info(hours);
		log.info(minutes);
		// Calculate parking cost based on the selected parking option
		double totalCost = 0.0;

		switch (parking_type) {
		case "Valet Parking":
			totalCost = Math.min(hours, 24) * 18.00; // 18€ per day
			break;
		case "Short-Term Parking":
			if (hours <= 5) {
				totalCost = 12.00;
			} else {
				long additionalHalfHours = (hours - 5) * 2; // Additional half-hour blocks after the first 5 hours
				totalCost = 12.00 + additionalHalfHours * 1.00;
				if (totalCost > 24.00) {
					totalCost = 24.00; // Maximum daily cost
				}
			}
			break;
		case "Long-Term Garage Parking":
			totalCost = Math.min(hours, 24) * 2.00; // 2.00€ per hour
			if (hours > 24) {
				totalCost += 12.00; // 12.00€ daily maximum
			}
			if (hours >= 168) { // 168 hours in a week
				totalCost = (hours / 168) * 60.00 + (hours % 168) * 2.00; // 72.00€ per week (7th day free)
			}
			break;
		case "Long-Term Surface Parking":
			totalCost = Math.min(hours, 24) * 2.00; // 2.00€ per hour
			if (hours > 24) {
				totalCost += 10.00; // 10.00€ daily maximum
			}
			if (hours >= 168) { // 168 hours in a week
				totalCost = (hours / 168) * 60.00 + (hours % 168) * 2.00; // 60.00€ per week (7th day free)
			}
			break;
		case "Economy Parking":
			if (hours <= 24) {
				totalCost = Math.min(hours, 24) * 2.00;
			} else {
				long days = hours / 24;
				long remainingHours = hours % 24;

				totalCost = days * 9.00;
				if (days >= 7) {
					totalCost = ((days / 7) * 6 * 9.00) + (days % 7 * 9.00);
				} else {
					totalCost += Math.min(remainingHours, 24) * 2.00;
				}
			}
			break;
		}
		log.info("Total manually cost: " + totalCost);
		return totalCost;
	}
}
