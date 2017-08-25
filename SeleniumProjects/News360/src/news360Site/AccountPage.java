package news360Site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage {

	private final WebDriver driver;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
	}

	By logoutButton = By.xpath("//div[text()='Logout']");
	By email = By.xpath("//input[@ng-value='user.email']");
	By username = By.xpath("//input[@ng-value='user.username']");

	private WebElement getLogoutButton() {
		WebDriverFunctions wf = new WebDriverFunctions(driver);
		wf.waitForClickableElement(driver, 10, logoutButton);
		return driver.findElement(logoutButton);
	}

	public void clickLogoutButton() {
		getLogoutButton().click();
	}

	public WebElement getUsernameTextBox() {
		return driver.findElement(username);
	}

	public WebElement getEmailTextBox() {
		return driver.findElement(email);
	}

}