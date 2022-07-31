package com.hellfest22.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Random;

@Entity
@Table(name = "tickets")
public class Tickets {
    @Id
    @Column (name = "id_compra", nullable = false, length = 50, unique = true)
    private String idCompra;    //ABDM289960417

    @Column(name = "tipo_de_ticket", nullable = false, length = 50)
    private String tipoDeTicket;    //X-day pass

    @Column(name = "cantidad_de_tickets")
    private int cantidadDeTickets;  //nro de 1 a 10

    @Column(name = "monto_total_en_usd", nullable = false, length = 11)
    private int montoTotal;     //nro de 105 a 2890

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;  //de 2 a 50 caracteres

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;    //de 2 a 50 caracteres

    @Column(name = "direccion_de_mail", nullable = false, length = 80)
    private String direccionDeMail;     //de 3 a 80 caracteres

    @Column(name = "metodo_de_pago", nullable = false, length = 50)
    private String metodoDePago;    //Puede valer: EFECTIVO o TARJETA

    @Column(name = "pases_totales", nullable = false, length = 11)
    private int pasesTotales;   //nro de 1 a 40

    @Column(name = "telefono", nullable = false, length = 40)
    private String telefono;   //varchar(50) ejemplo "+81 (536) 262-8335"

    //Constructores:
    public Tickets() {
    }
    public Tickets(String tipoDeTicket, int cantidadDeTickets, String nombre, String apellido, String direccionDeMail, String telefono, String metodoDePago) {
        this.idCompra = this.generarTicketID(); //TODO falta hacer que si existe el ID genere otro
        this.tipoDeTicket = tipoDeTicket;
        this.cantidadDeTickets = cantidadDeTickets;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccionDeMail = direccionDeMail;
        this.telefono = telefono;
        this.metodoDePago = metodoDePago;

        this.pasesTotales = this.contarPases();
        this.montoTotal = this.calcularMonto();
    }

    //Getters y Setters:
    public String getIdCompra() {
        return idCompra;
    }
    public void setIdCompra() {
        this.idCompra = generarTicketID();
    }
    public String getTipoDeTicket() {
        return tipoDeTicket;
    }
    public void setTipoDeTicket(String tipoDeTicket) {
        this.tipoDeTicket = tipoDeTicket;
    }
    public int getCantidadDeTickets() {
        return cantidadDeTickets;
    }
    public void setCantidadDeTickets(int cantidadDeTickets) {
        this.cantidadDeTickets = cantidadDeTickets;
    }
    public int getMontoTotal() {
        return montoTotal;
    }
    public void setMontoTotal() {
        this.montoTotal = calcularMonto();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getDireccionDeMail() {
        return direccionDeMail;
    }
    public void setDireccionDeMail(String direccionDeMail) {
        this.direccionDeMail = direccionDeMail;
    }
    public String getMetodoDePago() {
        return metodoDePago;
    }
    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }
    public int getPasesTotales() {
        return pasesTotales;
    }
    public void setPasesTotales(int pasesTotales) {
        this.pasesTotales = contarPases();
    }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    //Metodos propios adicionales:
    //Metodos:
    @Override
    public String toString() {
        return "Tickets{" +
                "tipoTicket='" + this.tipoDeTicket + '\'' +
                ", nombre='" + this.nombre + '\'' +
                ", apellido='" + this.apellido + '\'' +
                ", mail='" + this.direccionDeMail + '\'' +
                ", telefono='" + this.telefono + '\'' +
                ", metodoPago='" + this.metodoDePago + '\'' +
                ", cantTickets=" + this.cantidadDeTickets +
                ", montoTotal=" + this.montoTotal +
                ", pasesTotales=" + this.pasesTotales +
                '}';
    }

    private String generarTicketID() {
        //Funcion que genera IDs aleatorios de 4 caracteres en mayusculas y 9 numeros para asignar a las identificaciones de los tickets:
        Random random = new Random();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char randChar1 = abc.charAt(random.nextInt(abc.length()));
        char randChar2 = abc.charAt(random.nextInt(abc.length()));
        char randChar3 = abc.charAt(random.nextInt(abc.length()));
        char randChar4 = abc.charAt(random.nextInt(abc.length()));
        char a[] = {randChar1, randChar2, randChar3, randChar4};
        double max = 1000000000;   //Para lograr 20 digitos: 6762836344 981050000;
        double min = 0;
        long b = (long) (Math.random() * (max - min + 1) + min);
        return String.valueOf(a) + Long.toString(b);
    }

    private int contarPases(){
        //Pases = CantidadTickets * TipoPase
        return this.cantidadDeTickets * ((this.tipoDeTicket.equals("1-day pass"))? 1 : (this.tipoDeTicket.equals("3-day pass"))? 3 : 4);
    }

    private int calcularMonto(){
        //Precios: 1-day = $105 ; 3-days = $215 ; 4-days = $289
        return this.cantidadDeTickets * ((this.tipoDeTicket.equals("1-day pass"))? 105 : (this.tipoDeTicket.equals("3-day pass"))? 215 : 289);
    }
}
