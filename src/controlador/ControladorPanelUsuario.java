/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import vista.PanelUsuarios;

/**
 *
 * @author leoyochi
 */
public class ControladorPanelUsuario {

    //Atributos
    private PanelUsuarios vista;
    private Usuario modelo;

    //Constructor
    public ControladorPanelUsuario() {
        //Crear objetos modelo y vista
        this.vista = new PanelUsuarios();
        this.modelo = new Usuario();

        //Llamar al metodo manejadorEventos
        manejadorEventos();

        //Llamar al metodo llenarTablaUsuarios
        LlenarTablaUsuario();

    }

    //Metodos set y get
    public PanelUsuarios getVista() {
        return vista;
    }

    public void setVista(PanelUsuarios vista) {
        this.vista = vista;
    }

    public Usuario getModelo() {
        return modelo;
    }

    public void setModelo(Usuario modelo) {
        this.modelo = modelo;
    }

    //Metodo para manejar los eventos 
    public void manejadorEventos() {
        //evento para el boton registrar
        this.vista.btnRegistrar.addActionListener(e -> registrar());
        //evento para el boton editar
        this.vista.btnEditar.addActionListener(e -> editar());
        //evento para el boton eliminar
        this.vista.btnEliminar.addActionListener(e -> eliminar());
        //evento para el boton para buscar
        this.vista.btnBuscar.addActionListener(e -> buscarId());
        //evento para el boton para Nuevo
        this.vista.btnNuevo.addActionListener(e -> nuevo());
        //evento para el boton para salir
        this.vista.btnSalir.addActionListener(e -> salir());



    }

    //Metodo para registar 
    public void registrar() {
        //JOptionPane.showMessageDialog(this.vista, "Registrar usuario");

        //Validar Cajas de texto
        if (validarCajasTexto()) {

            //Obtrener los datos de la vista y agregarlos al modelo 
            this.modelo.setIdUsuario(Integer.parseInt(this.vista.txtIDUsuario.getText()));
            this.modelo.setNombreUsuario(this.vista.txtNombre.getText());
            this.modelo.setApPaternoUsuario(this.vista.txtApPaterno.getText());
            this.modelo.setApMaternoUsuario(this.vista.txtApMaterno.getText());
            this.modelo.setEmailUsuario(this.vista.txtEmail.getText());
            this.modelo.setTelefonoCelular(this.vista.txtTelefono.getText());

            if (this.modelo.insertar()) {
                JOptionPane.showMessageDialog(this.vista, "Los datos del usuario se guardaron correctamente");
                //Limpiar cajas texto
                limpiarCajasTexto();
                //Llamar al metodo llenarTablaUsuarios
                LlenarTablaUsuario();

            } else {
                JOptionPane.showMessageDialog(this.vista, "Los datos del usuario NO se guardaron....");
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
        }
    }

    public void editar() {
        //validar cajas de texto
        if (validarCajasTexto()) {

            //Obtrener los datos de la vista y agregarlos al modelo 
            this.modelo.setIdUsuario(Integer.parseInt(this.vista.txtIDUsuario.getText()));
            this.modelo.setNombreUsuario(this.vista.txtNombre.getText());
            this.modelo.setApPaternoUsuario(this.vista.txtApPaterno.getText());
            this.modelo.setApMaternoUsuario(this.vista.txtApMaterno.getText());
            this.modelo.setEmailUsuario(this.vista.txtEmail.getText());
            this.modelo.setTelefonoCelular(this.vista.txtTelefono.getText());

            if (this.modelo.modificar(this.modelo.getIdUsuario())) {
                JOptionPane.showMessageDialog(this.vista, "Los datos del usuario se guardaron correctamente");
                //Limpiar cajas texto
                limpiarCajasTexto();

                //Llamar al metodo llenarTablaUsuarios
                LlenarTablaUsuario();

            } else {
                JOptionPane.showMessageDialog(this.vista, "Los datos del usuario NO se guardaron....");
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
        }

    }
    //Metodo para validar las cajas de texto

    public boolean validarCajasTexto() {
        try {
            if (this.vista.txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el nombre del usuario");
                this.vista.txtNombre.requestFocus();
                return false;
            }
            if (this.vista.txtApPaterno.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el Ap Paterno");
                this.vista.txtApPaterno.requestFocus();
                return false;

            }
            if (this.vista.txtApMaterno.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el Ap Materno");
                this.vista.txtApMaterno.requestFocus();
                return false;

            }
            if (this.vista.txtEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el email");
                this.vista.txtEmail.requestFocus();
                return false;

            }
            if (this.vista.txtTelefono.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el Telefono");
                this.vista.txtTelefono.requestFocus();
                return false;

            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.vista, "error" + e.getMessage());
        }
        return false;
    }//fin del metodo validarCajasTexto

    //Metodo para limpiar las cajas de texto
    public void limpiarCajasTexto() {
        this.vista.txtIDUsuario.setText("");
        this.vista.txtNombre.setText("");
        this.vista.txtApPaterno.setText("");
        this.vista.txtApMaterno.setText("");
        this.vista.txtEmail.setText("");
        this.vista.txtTelefono.setText("");

    }

    public void eliminar() {
        //validar cajas de texto
        if (!this.vista.txtIDUsuario.getText().trim().isEmpty()) {

            //Obtrener los datos de la vista y agregarlos al modelo 
            this.modelo.setIdUsuario(Integer.parseInt(this.vista.txtIDUsuario.getText()));

            if (this.modelo.eliminar(this.modelo.getIdUsuario())) {
                JOptionPane.showMessageDialog(this.vista, "Los datos del usuario se guardaron correctamente");
                //Limpiar cajas texto
                limpiarCajasTexto();

                //Llamar al metodo llenarTablaUsuarios
                LlenarTablaUsuario();

            } else {
                JOptionPane.showMessageDialog(this.vista, "Los datos del usuario NO se guardaron....");
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
            this.vista.txtIDUsuario.requestFocus();
        }

    }

    public void buscarId() {

        this.modelo.setIdUsuario(Integer.parseInt(this.vista.txtIDUsuario.getText()));

        if (this.modelo.buscarPorId(this.modelo.getIdUsuario())) {
            //Ontener los datos de la vista y agregarlos al modelo
            this.vista.txtNombre.setText(this.modelo.getNombreUsuario());
            this.vista.txtApMaterno.setText(this.modelo.getApMaternoUsuario());
            this.vista.txtApPaterno.setText(this.modelo.getApPaternoUsuario());
            this.vista.txtEmail.setText(this.modelo.getEmailUsuario());
            this.vista.txtTelefono.setText(this.modelo.getTelefonoCelular());

            //Llamar al metodo llenarTablaUsuarios
            LlenarTablaUsuario();

        } else {
            JOptionPane.showMessageDialog(this.vista, "Los datos no se encontraron");
        }

    }

    //Metodo para llenar la tabla de usuarios
    public void LlenarTablaUsuario() {
        this.vista.tablaUsuarios.setModel(obtenerDatosUsuarios());

    }

    public DefaultTableModel obtenerDatosUsuarios() {
        String encabezadoTabla[] = {"Id", "Nombre", "Apellido Paterno", "Apellido Materno ", "Email", "Telefono"};
        DefaultTableModel modeloTabla = new DefaultTableModel(encabezadoTabla, 0);

        Object[] fila = new Object[modeloTabla.getColumnCount()];

        //Agregar lod datos del objeto  usuario del arrayList al modeloTabla
        for (Usuario usuario : this.modelo.buscar()) {

            fila[0] = usuario.getIdUsuario();
            fila[1] = usuario.getNombreUsuario();
            fila[2] = usuario.getApPaternoUsuario();
            fila[3] = usuario.getApMaternoUsuario();
            fila[4] = usuario.getEmailUsuario();
            fila[5] = usuario.getTelefonoCelular();

            modeloTabla.addRow(fila);
        }

        return modeloTabla;
    }

    private void nuevo() {
 //lamar el metodo limpiar cajas de texto
        limpiarCajasTexto();
        this.vista.txtIDUsuario.requestFocus();
    }

    private void salir() {
        //ocultar panel usuario
        this.vista.setVisible(false);
    }
    
}
