package stepsDefinitions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.hamcrest.Matchers;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import drivers.DriverRemoteBrowser;
import models.Usuario;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import questions.ValidarExistenciaElemento;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;
import tasks.IniciarSesionLibreriaNacional;
import uis.InformacionPersonalUserInterface;
import uis.IniciarSesionUserInterface;

public class IniciarSesionLibreriaNacionalStepDefinitions {

	@Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }
	
	@Dado("^que me encuentro en la pagina de la libreria nacional$")
	public void queMeEncuentroEnLaPaginaDeLaLibreriaNacional() {
		OnStage.theActorCalled("Duvan").can(BrowseTheWeb.with(DriverRemoteBrowser.chromeHisBrowserWeb().on("https://librerianacional.com/")));
	}


	@Cuando("^realizo el logueo en la pagina con las credenciales$")
	public void realizoElLogueoEnLaPaginaConLasCredenciales(List<Usuario> datosUsuario) {
		OnStage.theActorInTheSpotlight().attemptsTo(
				IniciarSesionLibreriaNacional.con()
				.email(datosUsuario.get(0).getEmail())
				.yContrasena(datosUsuario.get(0).getContrasena()));
	}
	
	@Entonces("^podre obtener (.*) al ver el mensaje (.*) en la pagina$")
	public void podreObtenerTrueAlVerElMensajeBienvenidoATuCuentaEnLaPagina(String validacion, String mensaje) {
		OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarExistenciaElemento.conTarget(IniciarSesionUserInterface.TXT_VALIDACION.of(mensaje)), Matchers.equalTo(Boolean.valueOf(validacion))));
	}
}
