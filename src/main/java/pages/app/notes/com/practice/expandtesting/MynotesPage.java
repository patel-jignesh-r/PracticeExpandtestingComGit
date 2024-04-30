package pages.app.notes.com.practice.expandtesting;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.com.practice.expandtesting.BasePageObject;

public class MynotesPage extends BasePageObject {

	public MynotesPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	/* Locators for my note homepage */
	// search field
	By searchField = By.id("search-input");
	By searchBtn = By.id("search-btn");
//	By searchResultText = By.xpath("//p[contains(.,'Search Results for \"project\":')]");
	// filter result text
	By progressInfoText = By.xpath("//*[@data-testid='progress-info']");
	By noNotesText = By.xpath("//*[@data-testid='no-notes-message']");
	// Category filter
	By categoryAllBtn = By.xpath("//button[@data-testid='category-all']");
	By categoryHomeBtn = By.xpath("//button[@data-testid='category-home']");
	By categoryWorkBtn = By.xpath("//button[@data-testid='category-work']");
	By categoryPersonalBtn = By.xpath("//button[@data-testid='category-personal']");
	// created note cards
	By noteCards = By.xpath("//*[@data-testid='note-card']");
	By noteCardsTitle = By.xpath("//*[@data-testid='note-card-title']");
	By noteCardsDescription = By.xpath("//*[@data-testid='note-card-description']");
	By noteCardsUpdateTime = By.xpath("//*[@data-testid='note-card-updated-at']");
	// cards actions button
	By checkBox = By.id("switch");
	By noteViewBtn = By.xpath("//*[@data-testid='note-view']");
	By noteEditBtn = By.xpath("//*[@data-testid='note-edit']");
	By noteDeleteBtn = By.xpath("//*[@data-testid='note-delete']");

	By noteDeleteConfirm = By.xpath("//*[@data-testid='note-delete-confirm']");
	By noteDeleteCancel = By.xpath("//*[@data-testid='note-delete-cancel-2']");
	By noteDeleteClose = By.xpath("//*[@data-testid='note-delete-cancel-1']");

	/* Locators for create a note */
	By addNoteBtn = By.xpath("//*[@data-testid='add-new-note']");
	By categoryDropdown = By.id("category");
	By completedCheckBox = By.id("completed");
	By noteTitle = By.cssSelector("#title");
	By noteDescription = By.cssSelector("#description");
	By createNoteBtn = By.xpath("//*[@data-testid='note-submit']");
	By cancelNoteBtn = By.xpath("//*[@data-testid='note-cancel']");
	By closeModalIcon = By.xpath("//button[@aria-label='Close']");

	/* Create new note method */
	public void addNote(String category, boolean isCompleted, String titleOfNote, String descriptionOfNote) {

		log.info("Adding new note with following details-- Category: " + category + ", is Completed?: " + isCompleted
				+ ", Title of Note: " + titleOfNote + ", Description of Note: " + descriptionOfNote);

		waitForElementClick(addNoteBtn);
		click(addNoteBtn);

		waitForElementToVisibile(categoryDropdown);
		selectByText(categoryDropdown, category);

		if (isCompleted == true) {
			click(completedCheckBox);
			log.info("Note marked as completed.");
		} else
			log.info("Note did not mark as completed.");

		typeText(noteTitle, titleOfNote);
		typeText(noteDescription, descriptionOfNote);

		// click(createNoteBtn);
	}

	public void createAddNote() {
		waitForElementClick(createNoteBtn);
		log.info("Creating a note.");
		click(createNoteBtn);
	}

	public void cancelAddNote() {
		waitForElementClick(cancelNoteBtn);
		log.info("Cancelling the add note action.");
		click(cancelNoteBtn);
	}

	public void closeAddNote() {
		waitForElementClick(closeModalIcon);
		log.info("Closing the add note modal.");
		click(closeModalIcon);
	}

	public void viewNote() {
		waitForElementClick(noteViewBtn);
		log.info("Viewing the note.");
		click(noteViewBtn);
		editNote();
	}

	public void editNote() {
		waitForElementClick(noteEditBtn);
		log.info("Editing the note.");
		String[] actions = { "Cancel", "Close", "Save" };
		for (String action : actions) {

			waitForElementClick(noteEditBtn);
			click(noteEditBtn);
			actions(action);
		}
	}

	public void deleteNote() {
		String[] actions2 = { "Delete-cancel", "Delete-close", "Delete-confirm" };
		for (String action1 : actions2) {
			waitForElementClick(noteDeleteBtn);
			click(noteDeleteBtn);
			actions(action1);
		}
	}

	public void actions(String action) {
		switch (action) {
		case "Close":
			waitForElementClick(closeModalIcon);
			log.info("Closing the add note modal.");
			click(closeModalIcon);
			break;
		case "Cancel":
			waitForElementClick(cancelNoteBtn);
			log.info("Cancelling the add note action.");
			click(cancelNoteBtn);
			break;
		case "Save":
			waitForElementClick(createNoteBtn);
			log.info("Saving the edited note.");
			click(createNoteBtn);
			break;
		case "Checkbox":
			waitForElementClick(checkBox);
			log.info("Checkbox Tick/Untick.");
			click(checkBox);
			break;
		case "Delete-cancel":
			waitForElementClick(noteDeleteCancel);
			log.info("Cancelling Delete action.");
			click(noteDeleteCancel);
			break;
		case "Delete-close":
			waitForElementClick(noteDeleteClose);
			log.info("Closing Delete modal.");
			click(noteDeleteClose);
			break;
		case "Delete-confirm":
			waitForElementClick(noteDeleteConfirm);
			log.info("Deleting the Note.");
			click(noteDeleteConfirm);
			break;
		default:
			log.info("Please choose action to perform.");
			break;
		}
	}

	public int countNotesCards() {
		if (!findAll(noteCards).isEmpty()) {
			waitForElementToVisibile(noteCards);
			log.info("Total Notes count: " + findAll(noteCards).size());
			return findAll(noteCards).size();
		} else {
			log.info("Total Notes count: 0");
			return 0;
		}
	}

	public void clickOnCategoryFilter(String category) {
		log.info("Clicking on category button: " + category);
		if (category == "all") {
			waitForElementClick(categoryAllBtn);
			click(categoryAllBtn);
		} else if (category == "home") {
			waitForElementClick(categoryHomeBtn);
			click(categoryHomeBtn);
		} else if (category == "personal") {
			waitForElementClick(categoryPersonalBtn);
			click(categoryPersonalBtn);
		} else if (category == "work") {
			waitForElementClick(categoryWorkBtn);
			click(categoryWorkBtn);
		}
	}

	public void searchNotes(String noteKeyword) {
		log.info("Searching for notes with keyword: " + noteKeyword);
		waitForElementToVisibile(searchField);
		typeText(searchField, noteKeyword);
		waitForElementClick(searchBtn);
		click(searchBtn);

	}

	public String getSearchResultsText(String noteKeyword) {
		By searchResultText = By
				.xpath("//p[contains(.," + "'" + "Search Results for " + '"' + noteKeyword + '"' + ":')]");
		waitForElementToVisibile(searchResultText);
		return find(searchResultText).getText();
	}

	/* You have 0/1 notes completed in the [] category */
	public String getNotesViewResultsText() {
		try {
			// waitForElementToVisibile(progressInfoText);
			return find(progressInfoText).getText();
		} catch (TimeoutException e) {
			// Element not visible within 15 seconds
			return "";
		}
	}

	/* You don't have any notes in [] categories */
	public String getNoNotesViewResultsText() {
		waitForElementToVisibile(noNotesText);
		return find(noNotesText).getText();
	}

	/*
	 * You have X/Y notes completed in the [] category. --> getting value of 'Y'
	 */
	public int getTotalNotesfromVisibleText() {
		waitForElementToVisibile(progressInfoText);
		String[] infoText = find(progressInfoText).getText().split("/");
		String[] infoText2 = infoText[1].split(" ");
		String notesCounts = infoText2[0];
		int notesCountNumber = Integer.parseInt(notesCounts);
		log.info("Notes count from Visible textinfo: " + notesCountNumber);
		return notesCountNumber;
	}

	// You have 0/1 notes completed in the work category
	/*
	 * You have X/Y notes completed in the [] category. --> getting value of 'X'
	 */
	public int getTotalNotCompletedNotesfromVisibleText() {
		waitForElementToVisibile(progressInfoText);
		String[] infoText = find(progressInfoText).getText().split("/");
		String[] infoText2 = infoText[0].split("have");
		String notesCounts = infoText2[1].trim();
		int notesCountNumber = Integer.parseInt(notesCounts);
		log.info("Not completed Notes count from Visible textinfo: " + notesCountNumber);
		return notesCountNumber;
	}

	/* Validating cards based on color code */
	public int validateNoteCardsColorByCategory(String category) {
		String orangeHome = "background-color: rgb(255, 145, 0); color: rgb(255, 255, 255);";
		String purpleWork = "background-color: rgb(92, 107, 192); color: rgb(255, 255, 255);";
		String greenPersonal = "background-color: rgb(50, 140, 160); color: rgb(255, 255, 255);";
		String grayCompleted = "background-color: rgba(40, 46, 41, 0.6); color: rgb(255, 255, 255);";

		// waitForElementToVisibile(noteCards);

		if (!findAll(noteCards).isEmpty()) {
			int j = 0;
			List<WebElement> cards = findAll(noteCardsTitle);
			for (WebElement card : cards) {
				String cardStyle = card.getAttribute("style");
				if (category.equals("home") && cardStyle.contains(orangeHome)) {
					j++;
				} else if (category.equals("work") && cardStyle.contains(purpleWork)) {
					j++;
				} else if (category.equals("personal") && cardStyle.contains(greenPersonal)) {
					j++;
				} else if (category.equals("all") && !cardStyle.contains(grayCompleted)) {
					j++;
				}
			}

			log.info("Total Notes in the category '" + category + "' is :" + cards.size());
			log.info("You have total Notes NOT completed in the category [" + category + "] is :" + j);
			int completedNotesCount = cards.size() - j;
			log.info("You have total Notes completed in the category [" + category + "] is :" + completedNotesCount);

			return j;
		} else {
			return 0;
		}
	}

	public int countGreyNotesCards() {
		String grayCompleted = "background-color: rgba(40, 46, 41, 0.6); color: rgb(255, 255, 255);";
		// waitForElementToVisibile(noteCards);
		if (!findAll(noteCards).isEmpty()) {
			int j = 0;
			List<WebElement> cards = findAll(noteCardsTitle);
			for (WebElement card : cards) {
				String cardStyle = card.getAttribute("style");
				if (cardStyle.contains(grayCompleted)) {
					j++;
				}
			}
			return j;
		} else {
			log.info("Total Notes count: 0");
			return 0;
		}
	}

	public boolean markingAsCompleted() {
		String grayCompleted = "background-color: rgba(40, 46, 41, 0.6); color: rgb(255, 255, 255);";

		waitForElementToVisibile(checkBox);
		String cardStyleColor = find(noteCardsTitle).getAttribute("Style");
		if (!cardStyleColor.contains(grayCompleted)) {
			log.info("Marked as completed.");
			click(checkBox);
			return true;
		} else {
			return false;
		}
	}

	By titleErrorText = By.xpath("//div[normalize-space()='Title should be between 4 and 100 characters']");
	By descriptionErrorText = By
			.xpath("//div[normalize-space()='Description should be between 4 and 1000 characters']");
	By titleBlankText = By.xpath("//div[normalize-space()='Title is required']");
	By descriptionBlankText = By.xpath("//div[normalize-space()='Description is required']");

	public String getTitleErrorMsg() {
		waitForElementToVisibile(titleErrorText);
		return find(titleErrorText).getText();
	}

	public String getTitleBlankMsg() {
		waitForElementToVisibile(titleBlankText);
		return find(titleBlankText).getText();
	}

	public String getDescriptionErrorMsg() {
		waitForElementToVisibile(descriptionErrorText);
		return find(descriptionErrorText).getText();
	}

	public String getDescriptionBlankMsg() {
		waitForElementToVisibile(descriptionBlankText);
		return find(descriptionBlankText).getText();
	}
}
