package uis;

import net.serenitybdd.screenplay.targets.Target;

public class IniciarSesionUserInterface {
	public static final Target BTN_MI_CUENTA = Target.the("").locatedBy("//div[@class='col-md-6 d-flex justify-content-end']//button[contains(text(),'Mi cuenta')]");
	public static final Target BTN_INICIAR_SESION = Target.the("").locatedBy("//div[@class='col-md-6 d-flex justify-content-end']//a[contains(text(),'Iniciar')]");
	public static final Target BTN_INGRESAR_EMAIL_CONTRASENA = Target.the("").locatedBy("//a[@href='/usuario/iniciar-sesion']//div[contains(text(),'Ingresar con Mail y contrase')]");
	public static final Target TXT_USUARIO = Target.the("").locatedBy("//input[@name='usuario']");
	public static final Target TXT_CONTRASENA = Target.the("").locatedBy("//input[@name='contrasena']");
	public static final Target BTN_INGRESA_SESION = Target.the("").locatedBy("//button");
	public static final Target TXT_VALIDACION = Target.the("").locatedBy("//div[contains(text(),'{0}')]");
}
