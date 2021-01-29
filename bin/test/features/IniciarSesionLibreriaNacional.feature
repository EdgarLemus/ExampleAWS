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
      | Escenario                                                                              | Email                     | Contrasena      | Mensaje                | Validacion |
      | Inicio de sesion en la pagina de la Libreria Nacional Exitoso                          | edgar.lemus2096@gmail.com | 123456789prueba | Bienvenido a tu cuenta | true       |
      | Inicio de sesion en la pagina de la Libreria Nacional No Exitoso Usuario Incorrecto    | edgar.lmus2096@gmail.com  | 123456789prueba | Bienvenido a tu cuenta | false      |
      | Inicio de sesion en la pagina de la Libreria Nacional No Exitoso Contrasena Incorrecta | edgar.lemus2096@gmail.com | 1256789prueba   | Bienvenido a tu cuenta | false      |
