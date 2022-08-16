Parte de backend realizado en Java Spring Boot para el proyecto de Hellfest 2022 en Angular.     
En la carpeta hellfestdb se encuentra la base de datos para pruebas.     
En la carpeta postman se encuentra una coleccion de peticiones a la API para importar en Postman.       
     
---------------------------------------------------------------     
     
Ejemplos de uso de la API:     
     
♦  En la tabla de Tickets  ♦     
     
• Get All Tickets:     
GET: localhost:8080/api/v1/tickets/find     
     
• Get Ticket by ID:     
GET: localhost:8080/api/v1/tickets/find/id/GHBF627199731     
     
• Get Ticket By Email:     
GET: localhost:8080/api/v1/tickets/find/fccasdasd@arg.gov     
     
• Get Ticket By Name:     
GET: localhost:8080/api/v1/tickets/find/John/Smith     
     
• Add Ticket:     
POST: localhost:8080/api/v1/tickets/add     
Con un nuevo usuario en el body del post:     
{     
    "tipoDeTicket": "3-day pass",     
    "cantidadDeTickets": 8,     
    "nombre": "John",     
    "apellido": "Smith",     
    "direccionDeMail": "johnsmith@country.gov",     
    "telefono": "+54 (011) 123-6677",     
    "metodoDePago": "EFECTIVO"     
}     
     
• Update Ticket By ID:      
PUT: localhost:8080/api/v1/tickets/find/id/LWJI916387441     
Con lainformacion del ticket actualizado en el body:     
{     
    "tipoDeTicket": "1-day pass",     
    "cantidadDeTickets": 9,     
    "nombre": "John",     
    "apellido": "Smith",     
    "direccionDeMail": "js@arg.gov",     
    "telefono": "+54 (011) 123-6677",     
    "metodoDePago": "TARJETA"     
}     
	 
• Delete Ticket By ID:     
DELETE: localhost:8080/api/v1/tickets/find/id/GHBF627199731     
     
     
     
♦ En la tabla de Usuarios ♦        
     
• Get All Users:     
GET: localhost:8080/api/v1/users     
     
• Get User By ID:     
GET: localhost:8080/api/v1/users/id/595168233124183017     
     
• Get User By Email:     
GET: localhost:8080/api/v1/users/mail_updateado@test.com     
     
• Login With Credentials:     
POST: localhost:8080/api/v1/login     
Con un body que contenga las credenciales por ejemplo:     
{     
    "password": "ASD123",     
    "direccion_de_mail": "fcc092@gmail.com",     
    "rol": "Personal"     
}     
     
• Register New User:     
POST: localhost:8080/api/v1/register     
Con un body que contenga la informacion del registro:     
{     
    "direccion_de_mail": "test@test.com",     
    "password": "contrasenia",     
    "nombre": "Testman",     
    "apellido": "Toasty",     
    "rol": "Personal"     
}     
     
• Update User By ID:     
PUT: localhost:8080/api/v1/users/id/595168233124183017     
Con el usuario actualizado en el body:     
{     
    "direccion_de_mail": "new_mail@test.com",     
    "password": "my_new_pass",     
    "nombre": "Testman_updated",     
    "apellido": "Toasty_updated",     
    "rol": "Comprador"     
}     
     
• Delete User By ID:     
DELETE: localhost:8080/api/v1/users/id/595168233124183017     
     
---------------------------------------------------------------          
     
fcascan - Ago2022     