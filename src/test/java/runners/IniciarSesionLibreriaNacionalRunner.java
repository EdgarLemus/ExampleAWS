package runners;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/IniciarSesionLibreriaNacional.feature",
glue = "stepsDefinitions",
snippets = SnippetType.CAMELCASE)
public class IniciarSesionLibreriaNacionalRunner {

	@BeforeAll
	void testSuit() {
		String myProjectARN = "arn:aws:devicefarm:us-west-2:072645797291:testgrid-project:c09aee72-93b4-4521-a631-75d95ff8440e";
		DeviceFarmClient client  = DeviceFarmClient.builder().region(Region.US_WEST_2).build();
	    CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder()
	      .expiresInSeconds(300)
	      .projectArn(myProjectARN)
	      .build();
	    CreateTestGridUrlResponse response = client.createTestGridUrl(request);
	    URL testGridUrl = null;
		try {
			testGridUrl = new URL(response.url());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
