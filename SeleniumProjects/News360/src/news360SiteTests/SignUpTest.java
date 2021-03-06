package news360SiteTests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import news360Site.AccountPage;
import news360Site.HomePage;
import news360Site.SignUpPage;
import news360Site.WebDriverFunctions;
import news360Site.WelcomePage;

public class SignUpTest extends TestBaseClass {

	@BeforeTest
	public void SetUp() throws Exception {
		super.setUp("http://news360.com");
	}

	@AfterTest
	public void TearDown() {
		super.tearDown();
	}

	@DataProvider(name = "invalidSignUp")
	public final static Object[][] invalidSignUp() {
		return new Object[][] { { "", "", "", "This value is required." }, { "abc", "", "", "This value is required." },
				{ "abc", "abc", "", "This value is required." },
				{ "abc", "abc123456", "abc", "This value should be the same." },
				{ "abc", "ab", "ab", "This value is too short. It should have 6 characters or more." } };
	}

	@Test(dataProvider = "invalidSignUp")
	public void InvalidSignUpTests(String email, String password, String confirmPassword, String expectedResult)
			throws Exception {
		/**
		 * This test a new account sign up with invalid input for username and password
		 * For validation, this test verifies the error message returned.
		 */

		// setting up page objects relevant to the sign up process
		HomePage homePage = new HomePage(driver);
		SignUpPage signUpPage = new SignUpPage(driver);

		// navigate to sign up page
		homePage.signInWithEmail();
		homePage.ClickSignUpLink();

		// enter required fields
		signUpPage.enterSignUpEmail(email);
		signUpPage.enterSignUpPassword(password);
		signUpPage.enterConfirmPassword(confirmPassword);
		signUpPage.clickSignUpButton();

		// verify each combination based on the data provider
		if (email == "") {
			Assert.assertEquals(signUpPage.getRequiredEmailText().getText(), expectedResult);
			Assert.assertEquals(signUpPage.getRequiredPasswordText().getText(), expectedResult);
			Assert.assertEquals(signUpPage.getRequiredConfirmPasswordText().getText(), expectedResult);
		} else if (email == "abc" && password == "" && confirmPassword == "") {
			Assert.assertEquals(signUpPage.getRequiredValue("1").getText(), expectedResult);
			Assert.assertEquals(signUpPage.getRequiredValue("2").getText(), expectedResult);
		} else if (email == "abc" && password == "abc" && confirmPassword == "") {
			Assert.assertEquals(signUpPage.getRequiredValue("1").getText(), expectedResult);
		} else if (email == "abc" && password == "abc123456" && confirmPassword == "abc") {
			Assert.assertEquals(signUpPage.getShouldBeTheSameText().getText(), expectedResult);
		} else if (email == "abc" && password == "ab" && confirmPassword == "ab") {
			Assert.assertEquals(signUpPage.getPasswordTooShortText().getText(), expectedResult);
		} else {
			Assert.assertEquals(signUpPage.getErrorMessageText().getText(), expectedResult);
		}
		driver.get("http://news360.com");

	}

	@DataProvider(name = "signUpWithAccoutAlreadyExists")
	public final static Object[][] signUpWithAccoutAlreadyExists() {
		return new Object[][] { { "dipautotest360@gmail.com", "test1234", "test1234", "Invalid login or password" } };
	}

	@Test(dataProvider = "signUpWithAccoutAlreadyExists")
	public void signUpWithAccountAlreadyExists(String email, String password, String confirmPassword,
			String expectedResult) throws Exception {

		/**
		 * This test a new account sign up with account already exists. For validation,
		 * this test verifies the error message returned.
		 */

		// setting up page objects relevant to the sign up process
		HomePage homePage = new HomePage(driver);
		SignUpPage signUpPage = new SignUpPage(driver);

		// go to sign up page
		homePage.signInWithEmail();
		homePage.ClickSignUpLink();

		// enter required fields
		signUpPage.enterSignUpEmail(email);
		signUpPage.enterSignUpPassword(password);
		signUpPage.enterConfirmPassword(confirmPassword);
		signUpPage.clickSignUpButton();

		// verify error message returned correctly
		Assert.assertEquals(signUpPage.getErrorMessageText().getText(), expectedResult);
	}

	@Test
	public void newSignUp() throws Exception {
		/**
		 * This test a new account sign up using dynamically generated user name and
		 * email. For validation, this test verifies the username and email from the
		 * account information page that is accessible only after sign up prcess was
		 * successful.
		 */

		// setting up page objects relevant to the sign up process
		HomePage homePage = new HomePage(driver);
		SignUpPage signUpPage = new SignUpPage(driver);
		WelcomePage welcomePage = new WelcomePage(driver);
		AccountPage accountPage = new AccountPage(driver);

		// Create WebDriverFunctions object
		WebDriverFunctions timeStamp = new WebDriverFunctions(driver);

		// Generate username and email based on timestamp
		String signUpName = timeStamp.generateTimeStamp();
		String newSignUpEmail = signUpName + "@123.com";
		String password = "teste1234";
		String confirmPassword = password;

		// go to sign up page
		homePage.signInWithEmail();
		homePage.ClickSignUpLink();

		// enter required fields
		signUpPage.enterSignUpEmail(newSignUpEmail);
		signUpPage.enterSignUpPassword(password);
		signUpPage.enterConfirmPassword(confirmPassword);
		signUpPage.clickSignUpButton();

		// navigate through welcome page
		welcomePage.clickStartReadingButton();
		welcomePage.clickAccountName();

		// verify new username and email in the account page
		Assert.assertEquals(accountPage.getUsernameTextBox().getAttribute("value"), signUpName);
		Assert.assertEquals(accountPage.getEmailTextBox().getAttribute("value"), newSignUpEmail);

		// log out
		accountPage.clickLogoutButton();

		// print out new sign up email for reference
		System.out.println("Sign Up email: " + newSignUpEmail);
	}
}
