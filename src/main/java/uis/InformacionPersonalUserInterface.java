package uis;

import net.serenitybdd.screenplay.targets.Target;

public class InformacionPersonalUserInterface {
	
	public static final Target BTN_CERRAR_SESION = Target.the("").locatedBy("//span[contains(text(),'Cerrar')]//parent::a");
	public static final Target TXT_BUSCAR_TEMA = Target.the("").locatedBy("//header//input[contains(@placeholder,'Buscar')]");
	public static final Target BTN_BUSCAR_TEMA = Target.the("").locatedBy("//header//button[@class='border-0 bs-none outline-0 d-flex justify-content-between align-items-center bg-red-dark']");
	public static final Target LIST_RESULTADOS_TEMA = Target.the("").locatedBy("//div[@class='row mx-md-0 content-books']//div[@class='col-12 px-0 mb-2']//a");
}
