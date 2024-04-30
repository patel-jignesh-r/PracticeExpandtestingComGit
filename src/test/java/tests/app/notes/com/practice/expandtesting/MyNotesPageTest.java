package tests.app.notes.com.practice.expandtesting;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.com.practice.expandtesting.TestUtilities;
import pages.app.notes.com.practice.expandtesting.LoginPage;
import pages.app.notes.com.practice.expandtesting.MynotesPage;
import pages.com.practice.expandtesting.HomePage;

public class MyNotesPageTest extends TestUtilities {

	public void positiveloginTesting() {

		HomePage homepage = new HomePage(driver, log);
		homepage.openPage();
		homepage.openNotesAppPage();

		LoginPage login = new LoginPage(driver, log);
		String loginEmail = "user501@gmail.com";
		String loginPassword = "User@501";
		login.loginToNotesApp(loginEmail, loginPassword);
	}

	@Test(dataProvider = "getData")
	public void addNotePositiveTesting(String titleOfNote, boolean isCompleted, String descriptionOfNote) {
		positiveloginTesting();
		MynotesPage mynotes = new MynotesPage(driver, log);
		int initialCount = mynotes.countNotesCards();

		String[] categories = { "Work", "Home", "Personal" };
		// String titleOfNote = "Project 00 Tasks";
		// String descriptionOfNote = "Testing the project 00 tasks";

		for (String category : categories) {
			// boolean isCompleted = false; // Tick the checkbox for isCompleted = true
			mynotes.addNote(category, isCompleted, titleOfNote, descriptionOfNote);
			mynotes.createAddNote();
			sleep(2000);
		}

		int updatedCount = mynotes.countNotesCards();
		Assert.assertNotEquals(initialCount, updatedCount,
				"No new notes added. Initial notes count: " + initialCount + " Updated notes count: " + updatedCount);
	}

	@Test(dataProvider = "getNegativeData")
	public void addNoteNegativeTesting(String titleOfNote, String descriptionOfNote) {
		positiveloginTesting();
		SoftAssert softAssert = new SoftAssert();
		MynotesPage mynotes = new MynotesPage(driver, log);
		// String[] categories = { "Work", "Home", "Personal" };
		String category = "Home";
		// for (String category : categories) {
		boolean isCompleted = false;
		mynotes.addNote(category, isCompleted, titleOfNote, descriptionOfNote);
		mynotes.createAddNote();
		sleep(2000);

		if (titleOfNote.isEmpty() && descriptionOfNote.isEmpty()) {
			softAssert.assertEquals(mynotes.getTitleBlankMsg(), "Title is required", "Please Enter Title of Note");
			softAssert.assertEquals(mynotes.getDescriptionBlankMsg(), "Description is required",
					"Please Enter Description of Note");
			mynotes.closeAddNote();
		} else if (titleOfNote.isEmpty()) {
			softAssert.assertEquals(mynotes.getTitleBlankMsg(), "Title is required", "Please Enter Title of Note");
			mynotes.closeAddNote();
		} else if (descriptionOfNote.isEmpty()) {
			softAssert.assertEquals(mynotes.getDescriptionBlankMsg(), "Description is required",
					"Please Enter Description of Note");
			mynotes.closeAddNote();
		} else if (titleOfNote.length() < 4 || titleOfNote.length() > 100) {
			softAssert.assertEquals(mynotes.getTitleErrorMsg(), "Title should be between 4 and 100 characters",
					"Title Characters length constraints are violated");
			mynotes.closeAddNote();
		} else if (descriptionOfNote.length() < 4 || descriptionOfNote.length() > 1000) {
			softAssert.assertEquals(mynotes.getDescriptionErrorMsg(),
					"Description should be between 4 and 1000 characters",
					"Title characters length constraints are violated");
			mynotes.closeAddNote();
		} else {
			softAssert.assertEquals(mynotes.getTitleErrorMsg(), "Title should be between 4 and 100 characters");
			softAssert.assertEquals(mynotes.getDescriptionErrorMsg(),
					"Description should be between 4 and 1000 characters",
					"Title and Description characters length constraints are violated");
			mynotes.closeAddNote();
		}
		// }
		softAssert.assertAll();
	}

	@Test
	public void cancelCloseAddNoteTesting() {
		positiveloginTesting();
		MynotesPage mynotes = new MynotesPage(driver, log);
		int initialCount = mynotes.countNotesCards();

		String[] actions = { "Cancel", "Close" }; // cancel/close--> 'Add note' action
		String category = "Work";
		String titleOfNote = "Project XX Tasks";
		String descriptionOfNote = "Testing the project tasks";

		for (String action : actions) {
			boolean isCompleted = false; // Tick the checkbox for isCompleted = true
			mynotes.addNote(category, isCompleted, titleOfNote, descriptionOfNote);
			mynotes.actions(action);
			sleep(2000);
		}

		int updatedCount = mynotes.countNotesCards();
		Assert.assertEquals(initialCount, updatedCount,
				"No new notes added. Initial notes count: " + initialCount + " Updated notes count: " + updatedCount);
	}

	@Test
	public void categoryTabsTesting() {
		positiveloginTesting();

		MynotesPage mynotes = new MynotesPage(driver, log);

		String[] categories = { "all", "home", "work", "personal" };

		for (String category : categories) {
			mynotes.clickOnCategoryFilter(category);

			/* Validating visible text for having notes */
			String resultText = mynotes.getNotesViewResultsText();

			if (!resultText.isEmpty()) {
				if (resultText.equals("You have completed all notes in the " + category + " category")) {
					Assert.assertEquals(resultText, "You have completed all notes in the " + category + " category");
				} else if (resultText.equals("You have completed all notes")) {
					Assert.assertEquals(resultText, "You have completed all notes");
				} else if (resultText.contains("/")) {
					int cardsCount = mynotes.countNotesCards();
					int notCompletedCardsCountfromText = mynotes.getTotalNotCompletedNotesfromVisibleText();
					int totalCardscountFromText = mynotes.getTotalNotesfromVisibleText();

					/*
					 * The ? and : symbols are part of the conditional (ternary) operator in Java.
					 * It's a shorthand way of writing an if-else statement. (condition) ?
					 * trueExpression : falseExpression; If the condition is true, the expression
					 * before the : is evaluated (trueExpression), otherwise the expression after
					 * the : is evaluated (falseExpression).
					 */
					String expectedText = "You have " + notCompletedCardsCountfromText + "/" + totalCardscountFromText
							+ " notes completed in the "
							+ (category.equals("all") ? category.toLowerCase() + " categories"
									: category.toLowerCase() + " category");

					Assert.assertEquals(resultText, expectedText);
					Assert.assertEquals(cardsCount, totalCardscountFromText,
							"Note Progressinfo validation fail. Notes count from total cards: " + cardsCount
									+ " Notes count from Visible textinfo: " + totalCardscountFromText);
				}
			} else {
				/* Validating visible Text for not having any notes */
				String expectedNoNotesText = category.equals("all")
						? "You don't have any notes in " + category.toLowerCase() + " categories"
						: "You don't have any notes in the " + category.toLowerCase() + " category";

				Assert.assertEquals(mynotes.getNoNotesViewResultsText(), expectedNoNotesText);
			}
		}
	}

	@Test
	public void cardsColorBycategoryTesting() {
		positiveloginTesting();

		MynotesPage mynotes = new MynotesPage(driver, log);

		String[] categories = { "all", "home", "work", "personal" };

		for (String category : categories) {
			mynotes.clickOnCategoryFilter(category);

			int notCompletedCardsCountfromText = mynotes.countNotesCards() - mynotes.countGreyNotesCards();
			int actualNotCompletedCardsCount = mynotes.validateNoteCardsColorByCategory(category);

			Assert.assertEquals(actualNotCompletedCardsCount, notCompletedCardsCountfromText);
		}
	}

	@Test
	public void searchNotesTesting() {
		positiveloginTesting();

		SoftAssert softassert = new SoftAssert();
		MynotesPage mynotes = new MynotesPage(driver, log);
		String keyword = "Project";
		mynotes.searchNotes(keyword);
		String expectedText = "Search Results for " + '"' + keyword + '"' + ":";
		softassert.assertEquals(mynotes.getSearchResultsText(keyword), expectedText);
		int initialCount = mynotes.countNotesCards();
		int totalCardscountFromText = mynotes.getTotalNotesfromVisibleText();
		Assert.assertEquals(initialCount, totalCardscountFromText);
		softassert.assertAll();
	}

	@Test
	public void cardsActionsTesting() {
		positiveloginTesting();

		SoftAssert softAssert = new SoftAssert();
		MynotesPage mynotes = new MynotesPage(driver, log);

		int initialCount = mynotes.countNotesCards();

		for (int i = initialCount; i <= initialCount; i--) {
			mynotes.editNote();
			sleep(1000);
			mynotes.viewNote();
			sleep(1500);
			softAssert.assertTrue(mynotes.markingAsCompleted(), "Note not marked as completed");
			sleep(1500);
			mynotes.deleteNote();
			sleep(2000);
			int updatedCount = mynotes.countNotesCards();
			softAssert.assertNotEquals(initialCount, updatedCount,
					"Delete card action performing failed. Initial notes count: " + initialCount
							+ " Updated notes count: " + updatedCount);

			softAssert.assertAll();
		}
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[3][3];
		data[0][0] = "Project Task 01";
		data[0][1] = true;
		data[0][2] = "Assign home tasks";

		data[1][0] = "Project Task 02";
		data[1][1] = false;
		data[1][2] = "Assign work tasks";

		data[2][0] = "Project Task 03";
		data[2][1] = false;
		data[2][2] = "Assign Personal tasks";
		return data;
	}

	@DataProvider
	public Object[][] getNegativeData() {
		Object[][] data = new Object[7][2];
		data[0][0] = "Pro";
		data[0][1] = "Valid";

		data[1][0] = "This is a string with exactly 105 characters. It is meant for demonstration purposes in response to your request.";
		data[1][1] = "Valid";

		data[2][0] = "Valid";
		data[2][1] = "Ass";

		data[3][0] = "Valid";
		data[3][1] = "azvZbM3DDQiIbioc9kuFx8C5BmUQThDkSBSs2KT40G2BwAPkIizc9ZDOg9WL4cx8rwrcHiYdKlCv3XNE8Ke229ZGtV7033YIIWxb9n6c2Y3zuO236bFPjfAJ9dt"
				+ "2GVX9YBNLMjcl5cFniygSoemSvdKhePPL3pohwj2uEp6s6zYdf75sFFgxm2OiOoLQWzRTxf38DmC2VEzEnFSVqoWp2P4LBkoEVKGO9tfY00pEp59ppSCcx2I7fAPTnLu2rbE0MCMhzjW6HedM8pu1cdnZkRN"
				+ "7U2SzcEJ8gkw9oWuR9uOR0aJ1xcpHDhktLnHKuKbCL5kiqexoZkRWkMEsFPA3lZJR8TuTTdYlNrLJ0ajFKxww5IYyzsEnwWKsro4yXWxCeUgAZ4TWVth4HBfgLYu9qvEuxApR97mYK4AGleWuSE4DeTPQDgdU"
				+ "9obhQIvHv0fm7dL7FFq7BdtQyR34rwKiIj3uCjkxz3f5WhKITAlRMNpCnECzkHdpIlCLELFI8roErpJICp4BsOQvNF4USkKFZKB6bQtSLDkR3PPdeuA3E52TM1fyg92gqGFVrFwYPUw6TEXSJlGsXwVetlBGgfGai4d0NJP9pk"
				+ "qe3DNx29DOGxYAC4tsRadhZRMTlwu6yXkTcDvaLfujMIXXgigjbffeQ7AYaOx6wCZ4AIjqawCmO3uwLWIlIZeUImGFw84YgLOoMCsrR5daWjZEzInYZI2SnPM8o8IvMAseOwoV6Dze1QYKeT4T4hGfoyZGJLPQ5d0QkFUIfyEWwI"
				+ "Iellz71Aqw2lUyTT9lklyujGEMrVxxjslBPDjqsmCBr8EpLcXf84T7U73FR8zT9uR8n6vs1MPfOPD3Vf9yIDLytxgQ48DaWj9qyNT3JXEEUZq18MgqLGUvi08AU8SCos3Sk9GUHXJzvCSRklR2D96yYSpNVRF01ef0CewG"
				+ "eXRbr88qQgzKgBe8137iT2mRCRFJ7pfrtoAPc0BTS0Il26E7CkVMK7YcOuNYytrmshrtyisnghttt";

		data[4][0] = "";
		data[4][1] = "";

		data[5][0] = "";
		data[5][1] = "Valid";

		data[6][0] = "Valid";
		data[6][1] = "";
		return data;
	}
}