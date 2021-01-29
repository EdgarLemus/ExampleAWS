package stepsDefinitions;

import java.io.IOException;

import org.hamcrest.Matchers;

import cucumber.api.java.Before;
import cucumber.api.java.es.*;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import questions.ValidarExistenciaElemento;
import tasks.BuscarTemaLibreriaNacional;
import uis.InformacionPersonalUserInterface;

public class BuscarTemaLibreriaNacionalStepDefinitions {

	@Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }
	
	@Cuando("^busque el tema (.*) en la pagina$")
	public void busqueElTemaFilosofiaEnLaPagina(String tema) {
		OnStage.theActorInTheSpotlight().attemptsTo(BuscarTemaLibreriaNacional.deObjectivo(tema));
	}

	@Entonces("^podre ver que obtuvo (.*) en resultados de la busqueda$")
	public void podreVerQueObtuvoTrueEnResultadosDeLaBusqueda(String respuesta) {
	OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarExistenciaElemento.conTarget(InformacionPersonalUserInterface.LIST_RESULTADOS_TEMA), Matchers.equalTo(Boolean.valueOf(respuesta))));
	}
}
