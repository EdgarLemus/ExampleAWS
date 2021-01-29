package tasks;

import static uis.InformacionPersonalUserInterface.*;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.events.internal.EventFiringKeyboard;

import interactions.Esperar;
import io.appium.java_client.android.nativekey.PressesKey;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class BuscarTemaLibreriaNacional implements Task{

	private String tema;
	
	public BuscarTemaLibreriaNacional(String tema) {
		this.tema = tema;
	}

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(
				Click.on(TXT_BUSCAR_TEMA),
				Enter.theValue(tema).into(TXT_BUSCAR_TEMA).thenHit(Keys.ENTER),
				Esperar.estosSegundos(2));
	}

	public static BuscarTemaLibreriaNacional deObjectivo(String tema) {
		return Instrumented.instanceOf(BuscarTemaLibreriaNacional.class).withProperties(tema);
	}
}
