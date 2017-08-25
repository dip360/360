package news360Site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WelcomePage {

	private final WebDriver driver;

	public WelcomePage(WebDriver driver) {
		this.driver = driver;
	}

	By accountName = By.xpath("//span[@class='username ng-binding']");
	By startReadingButton = By.xpath("//div[@class='startReading ng-binding show']");
	By logo = By.xpath("//a[@class='logo']");

	public WebElement getAccountNameLink() {
		WebDriverFunctions wf = new WebDriverFunctions(driver);
		wf.waitForClickableElement(driver, 10, accountName);
		return driver.findElement(accountName);
	}

	public void clickAccountName() {
		getAccountNameLink().click();
	}

	public WebElement getStartReadingButton() {
		WebDriverFunctions wf = new WebDriverFunctions(driver);
		wf.waitForClickableElement(driver, 10, startReadingButton);
		return driver.findElement(startReadingButton);
	}

	public void clickStartReadingButton() {
		getStartReadingButton().click();
	}

	private WebElement getLogo() {
		WebDriverFunctions wf = new WebDriverFunctions(driver);
		wf.waitForClickableElement(driver, 10, logo);
		return driver.findElement(logo);
	}

	public void clickOnLogo() {
		getLogo().click();
	}
}