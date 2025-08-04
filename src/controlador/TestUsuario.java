/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Usuario;

/**
 *
 * @author leoyochi
 */
public class TestUsuario {
    public static void main (String[] args ) {    
        //Crear el objeto usuario
        
        Usuario usuario=new Usuario(1, "Leonardo", "Pasten", "Martinez", "23300637@uttt.ed.mx", "5528611711");
        
        System.out.println(usuario);
        
        Usuario usuario2=new Usuario();
        
        usuario2.setIdUsuario(2);
        usuario2.setNombreUsuario("Carol");
        usuario2.setApPaternoUsuario("Trejo");
        usuario2.setApMaternoUsuario("Rodrigez");
        usuario2.setEmailUsuario("nesnesdswii64@gmail.com");
        usuario2.setTelefonoCelular("77310516761");
        
        System.out.println(usuario2);
        
        System.out.println("");

        Usuario usuario3=new Usuario();
        
        usuario3.setIdUsuario(3);
        usuario3.setNombreUsuario("Kevin");
        usuario3.setApPaternoUsuario("Gonzalez");
        usuario3.setApMaternoUsuario("Perez");
        usuario3.setEmailUsuario("dcdcasbb32@gmail.com");
        usuario3.setTelefonoCelular("77210312712");
        
        System.out.println("Id \t Nombre \t Apellido Paterno \t Apellido Materno \t Email \t Telefono \n");
        System.out.println("--------------------------------------------------------------------------\n");
        System.out.println(usuario3.getIdUsuario() + "\t" + usuario3.getNombreUsuario() + "\t" + usuario.getApPaternoUsuario() + "\t" + usuario3.getApMaternoUsuario() + "\t" + usuario3.getEmailUsuario() + "\t" + usuario3.getTelefonoCelular() + "\n");
        System.out.println("--------------------------------------------------------------------------\n");


        
    }
}