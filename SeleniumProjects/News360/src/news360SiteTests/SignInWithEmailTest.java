package news360SiteTests;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import news360Site.AccountPage;
import news360Site.HomePage;
import news360Site.WelcomePage;

public class SignInWithEmailTest extends TestBaseClass {

	@BeforeTest
	public void SetUp() throws Exception {
		super.setUp("http://news360.com");
	}

	@AfterTest
	public void TearDown() {
		super.tearDown();
	}

	@DataProvider(name = "invalidLogin")
	public final static Object[][] invalidLogin() {
		return new Object[][] { { "", "", "This value is required." }, { "abc", "", "Invalid login or password" },
				{ "abc", "abc", "Invalid login or password" }, { "", "abc", "This value is required." } };
	}

	@Test(dataProvider = "invalidLogin")
	public void InvalidLoginTests(String email, String password, String expectedResult) {

		/**
		 * This tests invalid login based on the data provider For validation, this test
		 * verifies specific error message returned
		 */

		// setting up page objects relevant to the password recovery process
		HomePage homePage = new HomePage(driver);

		// go to sign in page and enter required fields
		homePage.signInWithEmail();
		homePage.enterSignInEmail(email);
		homePage.enterSignInPassword(password);
		homePage.clickSignInButton();

		// verify error messages
		if (email == "") {
			Assert.assertEquals(homePage.getRequiredEmailText().getText(), expectedResult);
		} else {
			Assert.assertEquals(homePage.getErrorMessageText().getText(), expectedResult);
		}
		driver.get("http://news360.com");

	}

	@Test
	public void SignInWithExistingEmail() {

		/**
		 * This tests sign in with existing email For validation, this test verifies
		 * existing user can sign in with email
		 */

		// setting up page objects relevant to the sign in with email process
		HomePage homePage = new HomePage(driver);
		WelcomePage welcomePage = new WelcomePage(driver);
		AccountPage accountPage = new AccountPage(driver);

		// declare email and password
		String email = "charlotteweb@gmail.com";
		String password = "test1234";

		// go to sign in page and enter required fields
		homePage.signInWithEmail();
		homePage.enterSignInEmail(email);
		homePage.enterSignInPassword(password);
		homePage.clickSignInButton();

		welcomePage.clickOnLogo();
		welcomePage.clickAccountName();

		// verify existing username and email in the account page
		Assert.assertEquals(accountPage.getUsernameTextBox().getAttribute("value"), "charlotteweb");
		Assert.assertEquals(accountPage.getEmailTextBox().getAttribute("value"), email);

	}
}
