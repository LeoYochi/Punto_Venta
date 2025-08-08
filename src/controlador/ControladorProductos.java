/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import vista.Panel_Productos;

/**
 *
 * @author kevin
 */
public class ControladorProductos {

    //Atributos
    private Producto modelo;
    private Panel_Productos vista;

    //Constructor
    public ControladorProductos() {
        //Crear objetos modelo y vista
        this.vista = new Panel_Productos();
        this.modelo = new Producto();
        //Llamar al metodo manejadorEventos
        manejadorEventos();

        //Llamar al metodo llenarTablaProductos
        LlenarTablaProducto();
    }

    //Metodos set y get
    public Producto getModelo() {
        return modelo;
    }

    public void setModelo(Producto modelo) {
        this.modelo = modelo;
    }

    public Panel_Productos getVista() {
        return vista;
    }

    public void setVista(Panel_Productos vista) {
        this.vista = vista;
    }

    //Metodo para manejar los eventos 
    public void manejadorEventos() {
        //evento para el boton registrar
        this.vista.btnRegistar.addActionListener(e -> registrarProducto());
        //evento para el boton editar
        this.vista.btnEditar.addActionListener(e -> editarProducto());
        //evento para el boton eliminar
        this.vista.btnEliminar.addActionListener(e -> eliminarProducto());
        //evento para el boton para buscar
        this.vista.btnBuscar.addActionListener(e -> buscarIdProducto());
        //evento para el boton para Nuevo
        this.vista.btnNuevo.addActionListener(e -> nuevoProducto());
        //evento para el boton para salir
        this.vista.btnSalir.addActionListener(e -> salirProducto());

    }

    private void registrarProducto() {

        //JOptionPane.showMessageDialog(this.vista, "Registrar usuario");
        //Validar Cajas de texto
        if (validarCajasTextoProductos()) {

            //Obtrener los datos de la vista y agregarlos al modelo 
            this.modelo.setIdProducto(Integer.parseInt(this.vista.txtId_Producto1.getText()));
            this.modelo.setNombreProducto(this.vista.txtNombre_Producto1.getText());
            this.modelo.setPrecio(this.vista.txtPrecio.getText());
            this.modelo.setCantidad(this.vista.txtCantidad.getText());
            this.modelo.setCategotria(this.vista.txtCategoria.getText());

            if (this.modelo.insertar()) {
            
                JOptionPane.showMessageDialog(this.vista, "Los datos del Producto se guardaron correctamente");
                //Limpiar cajas texto
                limpiarCajasTextoProducto();
                //Llamar al metodo llenarTablaProductos
                LlenarTablaProducto();

            } else {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Producto NO se guardaron....");
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
        }

    }

    private void editarProducto() {
        //validar cajas de texto
        if (validarCajasTextoProductos()) {

            //Obtrener los datos de la vista y agregarlos al modelo 
            this.modelo.setIdProducto(Integer.parseInt(this.vista.txtId_Producto1.getText()));
            this.modelo.setNombreProducto(this.vista.txtNombre_Producto1.getText());
            this.modelo.setPrecio(this.vista.txtPrecio.getText());
            this.modelo.setCantidad(this.vista.txtCantidad.getText());
            this.modelo.setCategotria(this.vista.txtCategoria.getText());

            if (this.modelo.modificar(this.modelo.getIdProducto())) {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Producto se guardaron correctamente");
                //Limpiar cajas texto
                limpiarCajasTextoProducto();

                //Llamar al metodo llenarTablaRolUsuarios
                LlenarTablaProducto();

            } else {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Producto NO se guardaron....");
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
        }
    }

    private void eliminarProducto() {
        //validar cajas de texto
        if (!this.vista.txtId_Producto1.getText().trim().isEmpty()) {
            //Obtrener los datos de la vista y agregarlos al modelo 
            this.modelo.setIdProducto(Integer.parseInt(this.vista.txtId_Producto1.getText()));

            if (this.modelo.eliminar(this.modelo.getIdProducto())) {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Producto se guardaron correctamente");
                //Limpiar cajas texto
                limpiarCajasTextoProducto();

                //Llamar al metodo llenarTablaRolUsuarios
                LlenarTablaProducto();

            } else {
                JOptionPane.showMessageDialog(this.vista, "Los datos del Producto NO se guardaron....");
            }
        } else {
            JOptionPane.showMessageDialog(this.vista, "Se deben capturar todos los datos");
            this.vista.txtId_Producto1.requestFocus();
        }

    }

    private void buscarIdProducto() {
        this.modelo.setIdProducto(Integer.parseInt(this.vista.txtId_Producto1.getText()));

        if (this.modelo.buscarPorId(this.modelo.getIdProducto())) {
            //Ontener los datos de la vista y agregarlos al modelo
            //Obtrener los datos de la vista y agregarlos al modelo 
            this.vista.txtNombre_Producto1.setText(this.modelo.getNombreProducto());
            this.vista.txtPrecio.setText(this.modelo.getPrecio());
            this.vista.txtCantidad.setText(this.modelo.getCantidad());
            this.vista.txtCategoria.setText(this.modelo.getCategotria());

            //Llamar al metodo llenarTablaRolUsuarios
            LlenarTablaProducto();

        } else {
            JOptionPane.showMessageDialog(this.vista, "Los datos no se encontraron");
        }

    }

    private void nuevoProducto() {
        //lamar el metodo limpiar cajas de texto
        limpiarCajasTextoProducto();
        this.vista.txtId_Producto1.requestFocus();
    }

    private void salirProducto() {
        //ocultar panel Rol usuario
        this.vista.setVisible(false);
    }

    public boolean validarCajasTextoProductos() {
        try {
            if (this.vista.txtNombre_Producto1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el Nombre del producto");
                this.vista.txtNombre_Producto1.requestFocus();
                return false;
            }
            if (this.vista.txtCategoria.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar una categoria al producto");
                this.vista.txtCategoria.requestFocus();
                return false;
            }
            if (this.vista.txtCantidad.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar la cantidad del producto");
                this.vista.txtCantidad.requestFocus();
                return false;
            }
            if (this.vista.txtPrecio.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this.vista, "Debes de ingresar el precio del producto");
                this.vista.txtPrecio.requestFocus();
                return false;
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this.vista, "error" + e.getMessage());
        }
        return false;

    }

    public void limpiarCajasTextoProducto() {
        this.vista.txtId_Producto1.setText("");
        this.vista.txtNombre_Producto1.setText("");
        this.vista.txtPrecio.setText("");
        this.vista.txtCantidad.setText("");
        this.vista.txtCategoria.setText("");

    }

    public void LlenarTablaProducto() {
        this.vista.TablaProducto.setModel(obtenerDatosProductos());

    }

    public DefaultTableModel obtenerDatosProductos() {
        String encabezado2Tabla[] = {"ID", "Nombre", "Precio", "Cantidad", "Categoria"};
        DefaultTableModel modelTabla = new DefaultTableModel(encabezado2Tabla, 0);

        Object[] fila = new Object[modelTabla.getColumnCount()];

        //Agregar lod datos del objeto  rol usuario del arrayList al modeloTabla
        for (Producto producto : this.modelo.buscar()) {

            fila[0] = producto.getIdProducto();
            fila[1] = producto.getNombreProducto();
            fila[2] = producto.getPrecio();
            fila[3] = producto.getCantidad();
            fila[4] = producto.getCategotria();

            modelTabla.addRow(fila);
        }

        return modelTabla;
    }
}
