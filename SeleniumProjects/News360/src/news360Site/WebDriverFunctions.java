package news360Site;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverFunctions {

	private final WebDriver driver;

	public WebDriverFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElement(WebDriver driver, int timeoutInSeconds, By element) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void waitForClickableElement(WebDriver driver, int timeoutInSeconds, By element) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void switchToTab(WebDriver driver, int numberOfTabs, int tabToSwitch) throws Exception {

		int count = 0;
		while (count < 3) {
			ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			if (newTab.size() == numberOfTabs) {
				driver.switchTo().window(newTab.get(tabToSwitch));
				break;
			}
			count++;
		}
	}

	public String generateTimeStamp() {
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	}

}
