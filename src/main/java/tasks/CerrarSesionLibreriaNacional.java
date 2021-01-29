package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import uis.InformacionPersonalUserInterface;

public class CerrarSesionLibreriaNacional implements Task{

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(JavaScriptClick.on(InformacionPersonalUserInterface.BTN_CERRAR_SESION));
	}
	
	public static CerrarSesionLibreriaNacional deUsuarioLogueado() {
		return Instrumented.instanceOf(CerrarSesionLibreriaNacional.class).withProperties();
	}
}
