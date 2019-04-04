# GLPITicket

EN_US
An Android mobile app for opening new tickets in GLPI.

PT_BR
Aplicativo android para abrir chamados no GLPI.

## Requirements | Requisitos

EN_US
Android device with at least the Kitkat version
GLPI hosted a web server with:
  - API active;
  - Login with Credentials on API active;
  - External API access granted.

PT_BR
Dispositivo Android, no minimo com a versão Kitkat;
GLPI hospedado em um servidor web com:
  - API ativa;
  - Login com credenciais ativo na API;
  - Acesso externo na API concedido.

## Instructions | Instruções  

EN_US
Configuring the Host of GLPI API on App:
  - Clone the project for your PC
  - Open with Anroid Studio
  - In the DAO package, open files "LoginDAO.java" and "NewTicketDAO.java"
  - Search for String "host"(under the OKHTTPClient instance)
  - Replace "GLPI_URL_HERE" with your URL, but do not remove "/apirest.php/"
  - Compile the project and enjoy!

PT_BR
Configurando o Host da API do GLPI no App:
  - Clone o projeto para o seu PC;
  - Abra com o Android Studio;
  - No pacote DAO, abra os arquivos "LoginDAO.java" e também o "NewTicketDAO.java";
  - Procure pela String "host"(Está logo após a instancia do OKHTTPClient);
  - Substitua "GLPI_URL_HERE" pela sua URL, mas não remova "/apirest.php/";
  - Compile o projeto e aproveite!


## Author | Autor

* **Guilherme Bueno** - [gbuenoo](https://github.com/gbuenoo)
