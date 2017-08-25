package news360Site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginEmail {

	private final WebDriver driver;

	// Constructor
	public LoginEmail(WebDriver driver) {
		this.driver = driver;
	}

	By resetPasswordLink = By
			.xpath("//p[text()='If you do wish to reset your password, please click on this link: ']/a");
	By gmailUsernameTextBox = By.xpath("//input[@id='identifierId']");
	By gmailNextButton = By.xpath("//span[text()='Next']");
	By gmailPasswordTextBox = By.xpath("//input[@name='password']");
	By gmailFirstMessageChkbox = By.xpath("//div[@role='checkbox']/div");
	By gmailMessageDeleteButton = By.xpath("//input[@value='Delete']");
	By gmailMessageLink = By.xpath("//span[@class='ts']//b");

	public WebElement getResetPasswordLink() {
		return driver.findElement(resetPasswordLink);
	}

	public void clickResetPasswordLink() {
		getResetPasswordLink().click();
	}

	public void gotoGmail() {
		driver.get("http://mail.google.com");
	}

	public WebElement getGmailUsernameTextbox() {
		return driver.findElement(gmailUsernameTextBox);
	}

	public void enterGmailUserName(String username) {
		getGmailUsernameTextbox().sendKeys(username);
	}

	public WebElement getNextButton() {
		WebDriverFunctions wf = new WebDriverFunctions(driver);
		wf.waitForClickableElement(driver, 10, gmailNextButton);
		return driver.findElement(gmailNextButton);
	}

	public void clickNext() {
		getNextButton().click();
	}

	public WebElement getPasswordTextBox() {
		WebDriverFunctions wf = new WebDriverFunctions(driver);
		wf.waitForElement(driver, 10, gmailPasswordTextBox);

		return driver.findElement(gmailPasswordTextBox);
	}

	public void enterPassword(String password) {

		getPasswordTextBox().sendKeys(password);
	}

	public WebElement getFirstMessageChkbox() {
		return driver.findElement(gmailFirstMessageChkbox);
	}

	public void checkFirstGmailMessage() {
		getFirstMessageChkbox().click();
	}

	public WebElement getGmailMessageDeleteButton() {
		return driver.findElement(gmailMessageDeleteButton);
	}

	public void deleteGmailMessage() {
		getGmailMessageDeleteButton().click();
	}

	public WebElement getGmailMessageLink() {
		WebDriverFunctions wf = new WebDriverFunctions(driver);
		wf.waitForElement(driver, 10, gmailMessageLink);

		return driver.findElement(gmailMessageLink);
	}

	public void clickFirstGmailMessage() {
		getGmailMessageLink().click();
	}

	public void loginGmail(String username, String password) {

		enterGmailUserName(username);
		clickNext();
		enterPassword(password);
		clickNext();
	}

}
