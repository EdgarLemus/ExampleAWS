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
