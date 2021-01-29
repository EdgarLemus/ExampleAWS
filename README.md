# LibreriaNacional

Realiza la automatización en la pagina [Libreria Nacional](https://librerianacional.com/) de los prcesos de Loguin, Buscar Tema y Cerrar Sesion todo realizado con [Gradle](https://gradle.org/), [Java](https://www.java.com/es/), [SerenityBDD](https://serenity-bdd.github.io/theserenitybook/latest/index.html), [Cucumber](https://cucumber.io/) y Screenplay.

## Estructura Codigo Fuente

La estructura del codigo fue realizada con Screenplay de la siguiente forma:
<table>
<tr>
  <th>Tasks</th>
  <td>
    <h6>Contiene todas las tareas que se ejecutaran en la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Interactions</th>
  <td>
    <h6>Contiene todas las interaciones que se ejecutaran en la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Models</th>
  <td>
    <h6>Contiene todos los modelos que se utilizaran para la construccion de la automatizacion</h6>
  </td>
</tr>
  <tr>
  <th>Uis</th>
  <td>
    <h6>Contiene todos los elementos de la interface usuario mapeados en la pagina</h6>
  </td>
</tr>
  <tr>
  <th>Drivers</th>
  <td>
    <h6>Contiene todos los drivers de los navegadores</h6>
  </td>
</tr>
  <tr>
  <th>Runners</th>
  <td>
    <h6>Contiene todos los ejecutores de las pruebas automatizadas</h6>
  </td>
</tr>
  <tr>
  <th>Steps Definitions</th>
  <td>
    <h6>Contiene todos los pasos de la ejecucion de cada prueba automatizada</h6>
  </td>
</tr>
  <tr>
  <th>Features</th>
  <td>
    <h6>Contiene todos los esenarios codificados en lenguaje Gherking</h6>
  </td>
</tr>
</table>

#### Tasks

##### IniciarSesionLibreriaNacional

Realiza el inicio de sesion al sistema de la pagina de la Libreria Nacional, esta tarea implementa la interfaz Task y sobreescribe su metodo, tambien recibe dos parametros de tipo String y tiene dos metodos de tipo `IniciarSesionLibreriaNacional` que asignaran los valores a las variables creadas.
```java
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
```
#### CerrarSesionLibreriaNacional

Realiza el cierre de sesion al sistema de la pagina de la Libreria Nacional, esta tarea implementa la interfaz Task y sobreescribe su metodo.

```java
public class CerrarSesionLibreriaNacional implements Task{

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(JavaScriptClick.on(InformacionPersonalUserInterface.BTN_CERRAR_SESION));
	}
	
	public static CerrarSesionLibreriaNacional deUsuarioLogueado() {
		return Instrumented.instanceOf(CerrarSesionLibreriaNacional.class).withProperties();
	}
}
```

#### BuscarTemaLibreriaNacional

Realiza la busqueda de un tema en el sistema de la pagina de la Libreria Nacional, esta tarea implementa la interfaz Task y sobreescribe su metodo, tambien recibe un parametro de tipo String.

```java
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
```

### Interactions

#### Esperar

Realiza la espera implicita, esta tarea implementa la interfaz Interaction y sobreescribe su metodo, tambien recibe un parametro de tipo int.

```java
public class Esperar implements Interaction {

    private int seconds;

    public Esperar(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        DriverRemoteBrowser.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public static Performable estosSegundos(int seconds)
    {
        return Instrumented.instanceOf(Esperar.class).withProperties(seconds);
    }
}
```

### Models

#### Usuario

Esta clase crea un objeto de tipo usuario que contiene dos variables de tipo string, el constructor y los getter y setter correspondiente a cada variable.

```java
public class Usuario {

	private String email, contrasena;

	public Usuario() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
}
```

### Uis

#### InformacionPersonalUserInterface

Esta clase contiene todos los mapeos de los elementos de la pagina de la informacion personal del usuario en la pagina Libreria Nacional.

```java
public class InformacionPersonalUserInterface {
	
	public static final Target BTN_CERRAR_SESION = Target.the("").locatedBy("//span[contains(text(),'Cerrar')]//parent::a");
	public static final Target TXT_BUSCAR_TEMA = Target.the("").locatedBy("//header//input[contains(@placeholder,'Buscar')]");
	public static final Target BTN_BUSCAR_TEMA = Target.the("").locatedBy("//header//button[@class='border-0 bs-none outline-0 d-flex justify-content-between align-items-center bg-red-dark']");
	public static final Target LIST_RESULTADOS_TEMA = Target.the("").locatedBy("//div[@class='row mx-md-0 content-books']//div[@class='col-12 px-0 mb-2']//a");
}
```

#### IniciarSesionUserInterface

Esta clase contiene todos los mapeos de los elementos de la pagina del proceso de inicio de sesion del usuario en la pagina Libreria Nacional.

```java
public class IniciarSesionUserInterface {
	public static final Target BTN_MI_CUENTA = Target.the("").locatedBy("//div[@class='col-md-6 d-flex justify-content-end']//button[contains(text(),'Mi cuenta')]");
	public static final Target BTN_INICIAR_SESION = Target.the("").locatedBy("//div[@class='col-md-6 d-flex justify-content-end']//a[contains(text(),'Iniciar')]");
	public static final Target BTN_INGRESAR_EMAIL_CONTRASENA = Target.the("").locatedBy("//a[@href='/usuario/iniciar-sesion']//div[contains(text(),'Ingresar con Mail y contrase')]");
	public static final Target TXT_USUARIO = Target.the("").locatedBy("//input[@name='usuario']");
	public static final Target TXT_CONTRASENA = Target.the("").locatedBy("//input[@name='contrasena']");
	public static final Target BTN_INGRESA_SESION = Target.the("").locatedBy("//button");
	public static final Target TXT_VALIDACION = Target.the("").locatedBy("//div[contains(text(),'{0}')]");
}
```

### drivers

#### DriverRemoteBrowser

Esta clase contiene todos los lanzadores de los navegadores a utilizar para la automatizacion, se inicializa una variable WebDriver quien es utilizada en los metodos para levantar cada navegador y asignarle la url

```java
public class DriverRemoteBrowser {
	
	public static WebDriver driver;

	public static DriverRemoteBrowser chromeHisBrowserWeb() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-infobars");

		driver = new ChromeDriver(options);
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
		return driver;
	}
}
```
### Questions

#### ValidarExistenciaElemento

Realiza la validacion de que exista uno o varios elementos dentro de la pagina, esta tarea implementa la interfaz Question y sobreescribe su metodo, tambien recibe un parametro de tipo Target y retorna un valor `Boolean`.

```java
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
```

### Runners

#### BuscarTemaLibreriaNacionalRunner

Ejecuta llama los pasos asignados en el feature `BuscarTemaLibreriaNacional.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/BuscarTemaLibreriaNacional.feature",
glue = "stepsDefinitions",
snippets = SnippetType.CAMELCASE)
public class BuscarTemaLibreriaNacionalRunner {
}
```

#### CerrarSesionLibreriaNacionalRunner

Ejecuta llama los pasos asignados en el feature `CerrarSesionLibreriaNacional.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/CerrarSesionLibreriaNacional.feature",
glue = "stepsDefinitions",
snippets = SnippetType.CAMELCASE)
public class CerrarSesionLibreriaNacionalRunner {
}
```

#### IniciarSesionLibreriaNacionalRunner

Ejecuta llama los pasos asignados en el feature `IniciarSesionLibreriaNacional.feature` y los busca los metodos correspondientes en el paquete de `stepDefinitios` para realizar la ejecucion. Esta clase corre mediante el `@RunWith` de la clase `CucumberWithSerenity.class` y mediante el `@CucumberOptions` llama al feature correspondiente, el paquete que contiene los `Steps Definitions` y el `CamelCase`.

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/IniciarSesionLibreriaNacional.feature",
glue = "stepsDefinitions",
snippets = SnippetType.CAMELCASE)
public class IniciarSesionLibreriaNacionalRunner {
}
```

### StepsDefinitions

Los steps definitions contienen todos los metodos llamados mediante el `Runner` al `Feature`. Los metodos ejecutan las `tasks`,`interactions` y `questions` mediante un `actor`.

#### BuscarTemaLibreriaNacionalStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Buscar Tema, este llama a la tarea `BuscarTemaLibreriaNacional` y le envia un String con el valor del tema y ejecuta la questions `ValidarExistenciaElemento`.

```java
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
```

### CerrarSesionLibreriaNacionalStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Buscar Tema, este llama a la tarea `CerrarSesionLibreriaNacional` y le envia un String con el valor del tema y ejecuta la questions `WebElementQuestion` de Screenplay.

```java
public class CerrarSesionLibreriaNacionalStepDefinitions {

	@Before
    public void before() throws IOException {
        OnStage.setTheStage(new OnlineCast());
    }
	
	@Cuando("^cierre la sesion en la pagina$")
	public void cierreLaSesionEnLaPagina() {
	    OnStage.theActorInTheSpotlight().attemptsTo(CerrarSesionLibreriaNacional.deUsuarioLogueado());
	}
	
	@Entonces("^no podre ver el mensaje (.*) en la pagina$")
	public void noPodreVerElMensajeBienvenidoATuCuentaEnLaPagina(String mensajeEnPantalla) {
		OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(IniciarSesionUserInterface.TXT_VALIDACION.of(mensajeEnPantalla)), WebElementStateMatchers.isNotVisible()));
	}
}
```

#### IniciarSesionLibreriaNacionalStepDefinitions

Contiene todos los pasos a pasos de la ejecucion de Buscar Tema, este llama a la tarea `IniciarSesionLibreriaNacional` y le envia dos varaibles tipo String con el valor del usuario y contraseña y ejecuta la questions `ValidarExistenciaElemento`.

```java
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
```

### Features

#### BuscarTemaLibreriaNacional.feature

Contiene los escenarios exitosos y alternos de Buscar Tema y los datos a utilizar en cada escenario digitado en lenguaje Gherking. A su vez realizada el llamado del escenario de inicio de sesion sin el paso de `Entonces`.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com
Característica: Cerrar sesion en la pagina de la Libreria Nacional

  Antecedentes: 
    Dado que me encuentro en la pagina de la libreria nacional
    Cuando realizo el logueo en la pagina con las credenciales
      | email                     | contrasena      |
      | edgar.lemus2096@gmail.com | 123456789prueba |

  Esquema del escenario: <Escenario>
    Cuando busque el tema <Tema> en la pagina
    Entonces podre ver que obtuvo <Respuesta> en resultados de la busqueda

    Ejemplos: 
      | Escenario                                                     | Tema      | Respuesta |
      | Buscar tema en la pagina de la Libreria Nacional Exitoso      | Filosofia | true      |
      | Buscar tema en la pagina de la Libreria Nacional No Existente | jbdsifhbd | false     |
```

#### CerrarSesionLibreriaNacional.feature

Contiene los escenarios exitosos y alternos de Cerrar Sesion y los datos a utilizar en cada escenario digitado en lenguaje Gherking. A su vez realizada el llamado del escenario de inicio de sesion sin el paso de `Entonces`.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Cerrar sesion en la pagina de la Libreria Nacional

  Antecedentes: 
    Dado que me encuentro en la pagina de la libreria nacional
    Cuando realizo el logueo en la pagina con las credenciales
      | email                     | contrasena      |
      | edgar.lemus2096@gmail.com | 123456789prueba |

  Esquema del escenario: <Escenario>
    Cuando cierre la sesion en la pagina
    Entonces no podre ver el mensaje <Mensaje> en la pagina

    Ejemplos: 
      | Escenario                                                  | Mensaje                |
      | Cerrar sesion en la pagina de la Libreria Nacional Exitoso | Bienvenido a tu cuenta |
```

#### IniciarSesionLibreriaNacional.feature

Contiene los escenarios exitosos y alternos de Inicio Sesion y los datos a utilizar en cada escenario digitado en lenguaje Gherking.

```cucumber
#language: es
#Author: edgar_duvan_l_r@hotmail.com
Característica: Iniciar sesion en la pagina de la Libreria Nacional

  Esquema del escenario: <Escenario>
    Dado que me encuentro en la pagina de la libreria nacional
    Cuando realizo el logueo en la pagina con las credenciales
      | email   | contrasena   |
      | <Email> | <Contrasena> |
    Entonces podre obtener <Validacion> al ver el mensaje <Mensaje> en la pagina

    Ejemplos: 
      | Escenario                                                        | Email                     | Contrasena      | Mensaje                | Validacion |
      | Inicio de sesion en la pagina de la Libreria Nacional Exitoso                          | edgar.lemus2096@gmail.com | 123456789prueba | Bienvenido a tu cuenta | true       |
      | Inicio de sesion en la pagina de la Libreria Nacional No Exitoso Usuario Incorrecto    | edgar.lmus2096@gmail.com  | 123456789prueba | Bienvenido a tu cuenta | false      |
      | Inicio de sesion en la pagina de la Libreria Nacional No Exitoso Contrasena Incorrecta | edgar.lemus2096@gmail.com | 1256789prueba   | Bienvenido a tu cuenta | false      |
```

## Ejecucion

Al momento de ejecutar el proyecto y obtener el reporte debemos ubicarnos en la carpeta del proyecto y abrir el `CMD` para ejecutar el siguiente comando

```yml
    gradle clean test aggregate
```

Este comando ejecutara todos los escenarios implementados en el proyecto

```cmd
    7 actionable tasks: 7 executed
```

Al finalizar debemos ingresar y abrir el archivo `index.html` que se encuentra en la siguiente ruta

```yml
  <ProyectoName>\target\site\serenity\index.html
```
