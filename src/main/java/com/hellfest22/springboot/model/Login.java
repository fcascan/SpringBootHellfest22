package com.hellfest22.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Entity
@Table(name = "usuarios")
public class Login {
    @Id
    @Column (name = "id_user", nullable = false, length = 20, unique = true)
    private BigInteger id_user;    //nro de 20 digitos: 8881644958452399037

    @Column(name = "direccion_de_mail")
    private String direccion_de_mail;  //de 6 a 50 caracteres

    @Column(name = "password", nullable = false, length = 50)
    private String password;    //de 6 a 20 caracteres

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;  //de 2 a 50 caracteres

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;    //de 2 a 50 caracteres

    @Column(name = "rol", nullable = false, length = 10)
    private String rol;     //puede ser: Proveedor, Comprador, Personal

    //Constructores:
    public Login() {
    }
    public Login(String nombre, String apellido, String rol, String direccionDeMail, String password) {
        this.id_user = generarUserID(); //TODO falta hacer que si existe el ID genere otro
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.direccion_de_mail = direccionDeMail;
        this.password = password;
    }

    //Getters y Setters:
    public BigInteger getId_user() {return id_user;}
    public void setId_user(BigInteger id_user) {this.id_user = id_user;}
//    public String getPassword() {return "*******";}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getDireccion_de_mail() {return direccion_de_mail;}
    public void setDireccion_de_mail(String direccion_de_mail) {this.direccion_de_mail = direccion_de_mail;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public String getRol() {return rol;}
    public void setRol(String rol) {this.rol = rol;}

    //Metodos propios adicionales:
    //Metodos:
    @Override
    public String toString() {
        return "Usuario{" +
                ", id usuario='" + this.id_user + '\'' +
                ", nombre='" + this.nombre + '\'' +
                ", apellido='" + this.apellido + '\'' +
                ", mail='" + this.direccion_de_mail + '\'' +
                ", rol='" + this.rol + '\'' +
                '}';
    }

    static BigInteger generarUserID() {
        //Funcion que genera IDs aleatorios de 20 digitos para asignar a los usuarios:
        double max = 1000000000;   //Para lograr 20 digitos: 6762836344 981050000;
        double min = 0;
        long a = (long) (Math.random() * (max - min + 1) + min);
        long b = (long) (Math.random() * (max - min + 1) + min);
        String aux = Long.toString(a) + Long.toString(b);
        return new BigInteger(aux);
    }

    public boolean checkCredenciales(String mail, String pass, String role){
        if (mail.equals(this.direccion_de_mail) && pass.equals(this.password) && role.equals(this.rol)){
            return true;
        } else {
            return false;
        }
    }
}
