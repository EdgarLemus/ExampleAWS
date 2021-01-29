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
