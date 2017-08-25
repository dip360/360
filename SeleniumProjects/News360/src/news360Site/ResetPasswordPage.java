package news360Site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResetPasswordPage {

	private final WebDriver driver;

	public ResetPasswordPage(WebDriver driver) {
		this.driver = driver;
	}

	By resetPasswordTextBox = By.xpath("//input[@class='password textbox']");
	By confirmPasswordTextBox = By.xpath("//input[@class='confirmpassword textbox']");
	public By savePasswordButton = By.xpath("(//button[text()='Save password'])[1]");
	By resetEmailTextBox = By.xpath("//input[@id='resetemail']");
	By resetPasswordButton = By.xpath("//button[text()='Reset password']");
	By resetPasswordTextConfirmation = By.xpath("//div[text()='We emailed you a link to reset your password']");

	private WebElement getResetPasswordTextbox() {
		WebDriverFunctions wf = new WebDriverFunctions(driver);
		wf.waitForElement(driver, 10, resetPasswordTextBox);
		return driver.findElement(resetPasswordTextBox);
	}

	public void enterPassword(String password) {
		getResetPasswordTextbox().sendKeys(password);
	}

	private WebElement getConfirmPasswordTextbox() {
		return driver.findElement(confirmPasswordTextBox);
	}

	public void enterConfirmPassword(String confirmPassword) {
		getConfirmPasswordTextbox().sendKeys(confirmPassword);
	}

	public WebElement getSavePasswordButton() {
		return driver.findElement(savePasswordButton);
	}

	public void clickSavePassword() {
		getSavePasswordButton().click();
	}

	private WebElement getResetEmailTextBox() {
		return driver.findElement(resetEmailTextBox);
	}

	public void enterResetEmail(String email) {
		getResetEmailTextBox().sendKeys(email);
	}

	private WebElement getResetPasswordButton() {
		return driver.findElement(resetPasswordButton);
	}

	public void clickResetPassword() {
		getResetPasswordButton().click();
	}

	public WebElement getResetPasswordConfirmationText() {
		WebDriverFunctions wf = new WebDriverFunctions(driver);
		wf.waitForElement(driver, 10, resetPasswordTextConfirmation);
		return driver.findElement(resetPasswordTextConfirmation);
	}
}
