package news360SiteTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBaseClass {

	public WebDriver driver = null;

	public void setUp(String url) throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\SeleniumProjects\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);

		try {
			driver = new FirefoxDriver();
			driver.get(url);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
