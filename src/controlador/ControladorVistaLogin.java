/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import modelo.Login;
import vista.VistaDashBoardAdmin;
import vista.VistaLogin;

/**
 *
 * @author leoyochi
 */
public class ControladorVistaLogin {
    //Atributos
    private VistaLogin vista;
    private Login modelo;
    
    //Constructor
    
    public ControladorVistaLogin() {
        //Crear objeto vista y modelo
        this.vista=new VistaLogin();
        this.modelo=new Login();
        
        //Llamar el metodo manejadorEventos
        manejadorEventos();
       
    }
    
    //Metodo para el manejador de eventos
    public void manejadorEventos() {
        this.vista.btnIniciar.addActionListener(e->iniciarSesion());
    }
    
    //Metodo para iniciar sesion
    public void iniciarSesion(){
        //Entrada de datos
        String user=this.vista.txtUsuario.getText();
        String password=String.valueOf(this.vista.txtpassword.getPassword());
        //String tipoUser="student";
        
        this.modelo.getUsuario().setNombreUsuario(user);
        this.modelo.setPasswordLogin(password);
        //this.modelo.getRolUsuario().setTipoRolUsuario(tipoUser);
        
        //Validar login
        if(this.modelo.validarLogin() && this.modelo.getRolUsuario().getTipoRolUsuario().equals("admin")) {
            //JOptionPane.showMessageDialog(vista, "Usuario y/o Password correctos");
            //Crear objeto de la vista DashBoardAdmin
            ControladorDashBoardAdmin vistaDashBoardAdmin =new ControladorDashBoardAdmin();
            vistaDashBoardAdmin.getVista().setVisible(true);
            vistaDashBoardAdmin.getVista().setLocationRelativeTo(null);
            
            //Ocultar la vista de Login
            this.vista.dispose();
        }else if(this.modelo.validarLogin() && this.modelo.getRolUsuario().getTipoRolUsuario().equals("cajero")){
            //JOptionPane.showMessageDialog(vista, "Usuario y/o Password incorrectos");
 //Crear objeto de la vista DashBoardAdmin
            ControladorDashBoardAdmin vistaDashBoardAdmin =new ControladorDashBoardAdmin();
            vistaDashBoardAdmin.getVista().setVisible(true);
            vistaDashBoardAdmin.getVista().setLocationRelativeTo(null);
            
            //Ocultar la vista de Login
            this.vista.dispose();
        }
        else{
            JOptionPane.showMessageDialog(vista, "Usuario y/o Password incorrectos");
        }
        
        
    }
    
    //Metodo principal main
    public static void main(String[] args) {
        ControladorVistaLogin controlador=new ControladorVistaLogin();
        controlador.vista.setVisible(true);
    }
}
