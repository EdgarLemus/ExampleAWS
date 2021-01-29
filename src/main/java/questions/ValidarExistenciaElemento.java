package questions;

import java.util.List;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ValidarExistenciaElemento implements Question<Boolean>{

	private Target target;
	
	public ValidarExistenciaElemento(Target target) {
		this.target = target;
	}

	@Override
	public Boolean answeredBy(Actor actor) {
		List<WebElementFacade> listaElemenetos = target.resolveAllFor(actor);
		if(listaElemenetos.size() >= 1) {
			return true;
		}else {
			return false;
		}
	}

	public static ValidarExistenciaElemento conTarget(Target target) {
		return new ValidarExistenciaElemento(target);
	}
}
