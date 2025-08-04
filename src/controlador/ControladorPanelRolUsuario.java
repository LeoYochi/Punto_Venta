/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.RolUsuario;
import modelo.Usuario;
import vista.PanelRolUsuario;

/**
 *
 * @author leoyochi
 */
public class ControladorPanelRolUsuario {

    //Atributos
    private PanelRolUsuario vista;
    private RolUsuario modelo;

    //Constructor
    public ControladorPanelRolUsuario() {
        //Crear objetos modelo y vista
        this.vista = new PanelRolUsuario();
        this.modelo = new RolUsuario();

        //Llamar al metodo manejadorEventos
        manejadorEventos();

        //Llamar al metodo llenarTablaRolUsuarios
        LlenarTablaRolUsuario();
    }
    //Metodos set y get

    public PanelRolUsuario getVista() {
        return vista;
    }

    public void setVista(PanelRolUsuario vista) {
        this.vista = vista;
    }

    public RolUsuario getModelo() {
        return modelo;
    }

    public void setModelo(RolUsuario modelo) {
        this.modelo = modelo;
    }

    //Metodo para manejar los eventos 
    public void manejadorEventos() {
        //evento para el boton registrar
        this.vista.btnRegistrarRol.addActionListener(e -> registrarRol());
        //evento para el boton editar
        this.vista.btnEditarRol.addActionListener(e -> editarRol());
        //evento para el boton eliminar
        this.vista.btnEliminarRol.addActionListener(e -> eliminarRol());
        //evento para el boton para buscar
        this.vista.btnBuscarRol.addActionListener(e -> buscarIdRol());
        //evento para el boton para Nuevo
        this.vista.btnNuevoRol.addActionListener(e -> nuevoRol());
        //evento para el boton para salir
        this.vista.btnSalirRol.addActionListener(e -> salirRol());

    }

    //Metodo para registar 
    public void registrarRol() {

        //JOptionPane.showMessageDialog(this.vista, "Registrar usuario");
        //Validar Cajas de texto
        if (validarCajasTextoRol()) {

            //Obtrener los datos de la vista y agregarlos al modelo 
            this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtIDRol.getText()));
            this.modelo.setTipoRolUsuario(this.vista.txtTipoRol.getText());
            this.modelo.setDescripcionRolUsuario(this.vista.txtDescripción.getText());

            if (this.modelo.insertar()) {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario se guardaron correctamente");
                //Limpiar cajas texto
                limpiarCajasTextoRol();
                //Llamar al metodo llenarTablaRolUsuarios
                LlenarTablaRolUsuario();

            } else {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario NO se guardaron....");
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
        }

    }

    public void editarRol() {
        //validar cajas de texto
        if (validarCajasTextoRol()) {

            //Obtrener los datos de la vista y agregarlos al modelo 
            this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtIDRol.getText()));
            this.modelo.setTipoRolUsuario(this.vista.txtTipoRol.getText());
            this.modelo.setDescripcionRolUsuario(this.vista.txtDescripción.getText());

            if (this.modelo.modificar(this.modelo.getIdRolUsuario())) {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario se guardaron correctamente");
                //Limpiar cajas texto
                limpiarCajasTextoRol();

                //Llamar al metodo llenarTablaRolUsuarios
                LlenarTablaRolUsuario();

            } else {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario NO se guardaron....");
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
        }

    }

    public boolean validarCajasTextoRol() {
        try {
            if (this.vista.txtTipoRol.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el Tipo Rol usuario");
                this.vista.txtTipoRol.requestFocus();
                return false;
            }
            if (this.vista.txtDescripción.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar una descripcion");
                this.vista.txtDescripción.requestFocus();
                return false;
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.vista, "error" + e.getMessage());
        }
        return false;

    }

    public void limpiarCajasTextoRol() {
        this.vista.txtIDRol.setText("");
        this.vista.txtTipoRol.setText("");
        this.vista.txtDescripción.setText("");

    }

    public void eliminarRol() {
        //validar cajas de texto
        if (!this.vista.txtIDRol.getText().trim().isEmpty()) {

            //Obtener los datos de la vista y agregarlos al modelo 
            this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtIDRol.getText()));

            if (this.modelo.eliminar(this.modelo.getIdRolUsuario())) {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario se guardaron correctamente");
                //Limpiar cajas texto
                limpiarCajasTextoRol();

                //Llamar al metodo llenarTablaRolUsuarios
                LlenarTablaRolUsuario();

            } else {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Rol usuario NO se guardaron....");
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
            this.vista.txtIDRol.requestFocus();
        }

    }

    public void buscarIdRol() {

        this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtIDRol.getText()));

        if (this.modelo.buscarPorId(this.modelo.getIdRolUsuario())) {
            //Ontener los datos de la vista y agregarlos al modelo
            this.vista.txtTipoRol.setText(this.modelo.getTipoRolUsuario());
            this.vista.txtDescripción.setText(this.modelo.getDescripcionRolUsuario());

            //Llamar al metodo llenarTablaRolUsuarios
            LlenarTablaRolUsuario();

        } else {
            JOptionPane.showMessageDialog(this.vista, "Los datos no se encontraron");
        }

    }

    public void LlenarTablaRolUsuario() {
        this.vista.tablaRolUsuario.setModel(obtenerDatosRolUsuarios());

    }

    public DefaultTableModel obtenerDatosRolUsuarios() {
        String encabezado2Tabla[] = {"ID", "Tipo", "Descripción"};
        DefaultTableModel modelTabla = new DefaultTableModel(encabezado2Tabla, 0);

        Object[] fila = new Object[modelTabla.getColumnCount()];

        //Agregar lod datos del objeto  rol usuario del arrayList al modeloTabla
        for (RolUsuario rolUsuario : this.modelo.buscar()) {

            fila[0] = rolUsuario.getIdRolUsuario();
            fila[1] = rolUsuario.getTipoRolUsuario();
            fila[2] = rolUsuario.getDescripcionRolUsuario();

            modelTabla.addRow(fila);
        }

        return modelTabla;
    }

    private void nuevoRol() {
        //lamar el metodo limpiar cajas de texto
        limpiarCajasTextoRol();
        this.vista.txtIDRol.requestFocus();
    }

    private void salirRol() {
        //ocultar panel Rol usuario
        this.vista.setVisible(false);
    }

}
