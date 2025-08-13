/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.PuntoVenta;
import vista.Vista_PuntoVenta;


/**
 *
 * @author leoyochi
 */
public class ControladorPuntoVenta {
     //Atributos
    private Vista_PuntoVenta vista;
    private PuntoVenta modelo;
    private double subtotal = 0.0;
    private final double IVA_PORCENTAJE = 0.16; // 16% de IVA

    public ControladorPuntoVenta() {
     //Crear objeto vista
        this.vista = new Vista_PuntoVenta();
        this.modelo = new PuntoVenta();
        
        //Llamar al metodo manejadorEventos
        manejadorEventos();
        //Llamar al metodo LlenarTablaPuntoVenta
        LlenarTablaPuntoVenta();
}
    //Metodo set y get

    public Vista_PuntoVenta getVista() {
        return vista;
    }

    public void setVista(Vista_PuntoVenta vista) {
        this.vista = vista;
    }
    
    
     //Metodo para manejar los eventos 
    public void manejadorEventos() {
       
      
        //evento para el boton para buscar
        this.vista.btnAgregar.addActionListener(e -> agregarProducto());
        //evento para el boton para Nuevo
        this.vista.btnNuevo2.addActionListener(e -> nuevoProductoVenta());
        //evento para generar ticket
        this.vista.btnGenerarVenta.addActionListener(e -> generarTicket());
        //evento para descartar producto
        this.vista.btnDescartar.addActionListener(e -> descartarProducto()); 


    
    }
    

// Método para descartar producto de la tabla (sin afectar BD)
private void descartarProducto() {
    try {
        String idTexto = vista.txtid1.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor ingrese el ID del producto a descartar.");
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) vista.tablaPuntoVenta.getModel();
        boolean encontrado = false;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String nombreProducto = modeloTabla.getValueAt(i, 0).toString();
            
            // Si en vez del nombre usas el ID en la tabla, cámbialo por el índice correcto
            if (nombreProducto.equalsIgnoreCase(idTexto)) {
                modeloTabla.removeRow(i);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            recalcularTotales();
            JOptionPane.showMessageDialog(vista, "Producto descartado de la tabla.");
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontró un producto con ese ID en la tabla.");
        }

        vista.txtid1.setText("");
        vista.txtid1.requestFocus();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al descartar producto: " + ex.getMessage());
    }
}

// Método para recalcular subtotal, IVA y total
private void recalcularTotales() {
    DefaultTableModel modeloTabla = (DefaultTableModel) vista.tablaPuntoVenta.getModel();
    subtotal = 0.0;

    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
        String precioStr = modeloTabla.getValueAt(i, 1).toString().replace("$", "").replace(",", "").trim();
        String cantidadStr = modeloTabla.getValueAt(i, 2).toString();

        double precio = Double.parseDouble(precioStr);
        int cantidad = Integer.parseInt(cantidadStr);

        subtotal += precio * cantidad;
    }

    double iva = subtotal * IVA_PORCENTAJE;
    double total = subtotal + iva;

    vista.txtSubtotal.setText(String.format(Locale.US, "%.2f", subtotal));
    vista.txtIva.setText(String.format(Locale.US, "%.2f", iva));
    vista.txtTotal1.setText(String.format(Locale.US, "%.2f", total));
}
    
   private void agregarProducto() {
    try {
        String idProducto = vista.txtid1.getText().trim();
        if (idProducto.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el ID del producto");
            return;
        }

        PuntoVenta producto = modelo.buscarPorId(idProducto);
        if (producto == null) {
            JOptionPane.showMessageDialog(vista, "Producto no encontrado");
            return;
        }

        // Añadir fila a la tabla (mantener historial)
        DefaultTableModel modeloTabla = (DefaultTableModel) vista.tablaPuntoVenta.getModel();
        modeloTabla.addRow(new Object[] {
            producto.getNombreProducto(),
            producto.getPrecio(),
            producto.getCantidad()
        });

        // Validar precio no nulo
        String precioStr = producto.getPrecio();
        if (precioStr == null || precioStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "El producto no tiene precio definido en la BD.");
            return;
        }

        // Quitar $ y comas si existen y convertir
        precioStr = precioStr.replace("$", "").replace(",", "").trim();
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(vista, "Formato de precio inválido: " + producto.getPrecio());
            return;
        }

        int cantidad = 1;
        try {
            cantidad = Integer.parseInt(producto.getCantidad());
        } catch (NumberFormatException nfe) {
            // si no es numérica, asumimos 1
            cantidad = 1;
        }

        // Actualizar subtotal / iva / total
        subtotal += precio * cantidad;
        double iva = subtotal * IVA_PORCENTAJE;
        double total = subtotal + iva;

        vista.txtSubtotal.setText(String.format(Locale.US, "%.2f", subtotal));
        vista.txtIva.setText(String.format(Locale.US, "%.2f", iva));
        vista.txtTotal1.setText(String.format(Locale.US, "%.2f", total));

        // Preparar para siguiente inserción
        vista.txtid1.setText("");
        vista.txtid1.requestFocus();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, "Error al agregar producto: " + ex.getMessage());
    }
}
private void generarTicket() {
    DefaultTableModel modeloTabla = (DefaultTableModel) vista.tablaPuntoVenta.getModel();

    if (modeloTabla.getRowCount() == 0) {
        JOptionPane.showMessageDialog(vista, "No hay productos en la venta.");
        return;
    }

    StringBuilder ticket = new StringBuilder();
    ticket.append("***** PUNTO DE VENTA *****\n");
    ticket.append("Fecha: ").append(java.time.LocalDateTime.now()).append("\n");
    ticket.append("------------------------------\n");
    ticket.append(String.format("%-15s %-8s %-5s\n", "Producto", "Precio", "Cant."));
    ticket.append("------------------------------\n");

    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
        String nombre = modeloTabla.getValueAt(i, 0).toString();
        String precio = modeloTabla.getValueAt(i, 1).toString();
        String cantidad = modeloTabla.getValueAt(i, 2).toString();

        ticket.append(String.format("%-15s %-8s %-5s\n", nombre, precio, cantidad));
    }

    ticket.append("------------------------------\n");
    ticket.append("Subtotal: ").append(vista.txtSubtotal.getText()).append("\n");
    ticket.append("IVA:      ").append(vista.txtIva.getText()).append("\n");
    ticket.append("TOTAL:    ").append(vista.txtTotal1.getText()).append("\n");
    ticket.append("------------------------------\n");
    ticket.append("¡Gracias por su compra!\n");

    JOptionPane.showMessageDialog(vista, ticket.toString(), "Ticket de Venta", JOptionPane.INFORMATION_MESSAGE);
}


     private void nuevoProductoVenta() {
        //lamar el metodo limpiar cajas de texto
        limpiarCajasTextoPuntoVenta();
        this.vista.txtid1.requestFocus();
    }
     
    public void limpiarCajasTextoPuntoVenta() {
        this.vista.txtid1.setText("");
    }
    
    //Metodo main
    public static void main (String[] args) {
        //Crear objeto controlador
        ControladorPuntoVenta controladorVenta=new ControladorPuntoVenta();
        controladorVenta.vista.setVisible(true);
        controladorVenta.vista.setLocationRelativeTo(null);
    }
     public void LlenarTablaPuntoVenta() {
        this.vista.tablaPuntoVenta.setModel(obtenerDatosVenta());

    }

     public DefaultTableModel obtenerDatosVenta() {
        String[] encabezados = {"Nombre", "Precio", "Cantidad"};
        DefaultTableModel modeloTabla = new DefaultTableModel(encabezados, 0);

        // Verificamos que modelo no sea null (aunque ya lo inicializamos)
        if (this.modelo != null) {
            for (PuntoVenta puntoVenta : this.modelo.buscar()) {
                Object[] fila = new Object[3]; // 3 columnas: Nombre, Precio, Cantidad
                
                fila[0] = puntoVenta.getNombreProducto();
                fila[1] = puntoVenta.getPrecio();
                fila[2] = puntoVenta.getCantidad();

                modeloTabla.addRow(fila);
            }
        } else {
            System.err.println("Error: El modelo no ha sido inicializado");
        }

        return modeloTabla;
    }
}
