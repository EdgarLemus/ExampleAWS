package tasks;

import static uis.IniciarSesionUserInterface.*;

import interactions.Esperar;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class IniciarSesionLibreriaNacional implements Task{

	private String email, contrasena;

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Click.on(BTN_MI_CUENTA),
				Click.on(BTN_INICIAR_SESION),
				Click.on(BTN_INGRESAR_EMAIL_CONTRASENA),
				Enter.theValue(email).into(TXT_USUARIO),
				Enter.theValue(contrasena).into(TXT_CONTRASENA),
				Click.on(BTN_INGRESA_SESION),
				Esperar.estosSegundos(3));
		
	}
	
	public static IniciarSesionLibreriaNacional con() {
		return Instrumented.instanceOf(IniciarSesionLibreriaNacional.class).withProperties();
	}
	
	public IniciarSesionLibreriaNacional email(String email) {
		this.email = email;
		return this;
	}
	
	public IniciarSesionLibreriaNacional yContrasena(String contrasena) {
		this.contrasena = contrasena;
		return this;
	}
}
