package drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;

public class DriverRemoteBrowser {
	
	public static WebDriver driver;

	public static DriverRemoteBrowser chromeHisBrowserWeb() {
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
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("browserVersion", "latest");
		capabilities.setCapability("platform", "windows");

		driver = new RemoteWebDriver(testGridUrl,capabilities);
		return new DriverRemoteBrowser();
	}
	
	public static DriverRemoteBrowser firefoxHisBrowserWeb()
	{
		driver = new FirefoxDriver();
		return new DriverRemoteBrowser();
	}
	
	public static DriverRemoteBrowser internetExplorerHisBrowserWeb()
	{
		driver = new InternetExplorerDriver();
		return new DriverRemoteBrowser();
	}
	
	public static WebDriver on(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		return driver;
	}
}
