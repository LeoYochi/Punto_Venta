/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.Locale;
import vista.VistaDashBoardAdmin;

/**
 *
 * @author leoyochi
 */
public class ControladorDashBoardAdmin {
    //Atributos
    private VistaDashBoardAdmin vista;
    
    //Constructor
    
    public ControladorDashBoardAdmin() {
        //Crear objeto vista
        this.vista=new VistaDashBoardAdmin();
        
        //Llamar metodo manejadorEventos
        manejadorEventos();
    }
    //Metodos set y get

    public VistaDashBoardAdmin getVista() {
        return vista;
    }

    public void setVista(VistaDashBoardAdmin vista) {
        this.vista = vista;
    }
    
    
    
    //Metodo para el manejador de eventos
    public void manejadorEventos() {
        //Evento para el boton de usuarios
        this.vista.btnUsuarios.addActionListener(e->mostrarPanelUsuarios());
        this.vista.btnRolUsuarios.addActionListener(e->mostrarPanelRolUsuarios());
        
    }
    
    //Metodo para mostar el panel de usuarios
    public void mostrarPanelUsuarios() {
        //Crear el objeto del controlador del panel usuarios
        ControladorPanelUsuario controladorPanelUsuario=new ControladorPanelUsuario();
        
        controladorPanelUsuario.getVista().setSize(800,560);
        controladorPanelUsuario.getVista().setLocation(0,0);
        
        //Agregar el panel usuarios al panel contenedor
        this.vista.panelContenedor.removeAll();
        this.vista.panelContenedor.add(controladorPanelUsuario.getVista());
        this.vista.panelContenedor.revalidate();
        this.vista.panelContenedor.repaint();
       
       
        
    }
    
    public void mostrarPanelRolUsuarios() {
         //Crear el objeto del controlador del panel Rol usuarios
        ControladorPanelRolUsuario controladorPanelRolUsuario=new ControladorPanelRolUsuario();
        
        controladorPanelRolUsuario.getVista().setSize(800,560);
        controladorPanelRolUsuario.getVista().setLocation(0,0);
        
        //Agregar el panel Rol usuarios al panel contenedor
        this.vista.panelContenedor.removeAll();
        this.vista.panelContenedor.add(controladorPanelRolUsuario.getVista());
        this.vista.panelContenedor.revalidate();
        this.vista.panelContenedor.repaint();
       
        
    }
    
    //Metodo main
    
    public static void main (String[] args) {
        //Crear objeto controlador
        ControladorDashBoardAdmin controlador=new ControladorDashBoardAdmin();
        controlador.vista.setVisible(true);
        controlador.vista.setLocationRelativeTo(null);
    }
    
}
