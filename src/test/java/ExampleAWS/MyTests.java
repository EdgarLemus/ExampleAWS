package ExampleAWS;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyTests {

	// ... When you set up your test suite
	private static WebDriver driver;

	@BeforeAll
	public void setUp() {
		String myProjectARN = "arn:aws:devicefarm:us-west-2:072645797291:testgrid-project:c09aee72-93b4-4521-a631-75d95ff8440e";
		DeviceFarmClient client = DeviceFarmClient.builder().region(Region.US_WEST_2).build();
		CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder().expiresInSeconds(300)
				.projectArn(myProjectARN).build();
		CreateTestGridUrlResponse response = client.createTestGridUrl(request);
		URL testGridUrl = null;
		try {
			testGridUrl = new URL(response.url());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--start-maximized");
//		options.addArguments("--ignore-certificate-errors");
//		options.addArguments("--disable-infobars");
//		driver = new RemoteWebDriver(testGridUrl, options);
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("browserVersion", "latest");
		capabilities.setCapability("platform", "windows");
		driver = new RemoteWebDriver(testGridUrl, capabilities);
		
	}

	@Test
	public void chromeTest() {
		driver.get("http://www.google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Colombia" + Keys.ENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement searchResult = driver.findElement(By.id("result-stats"));
		System.out.println("Result displayed? " + searchResult.isDisplayed());
		Assert.assertTrue(searchResult.isDisplayed());
	}

	@AfterAll
	public void tearDown() {
		// make sure to close your WebDriver:
		driver.quit();
	}
}
