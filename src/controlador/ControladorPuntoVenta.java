/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.util.Locale;
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

    public ControladorPuntoVenta() {
     //Crear objeto vista
        this.vista = new Vista_PuntoVenta();
        this.modelo = new PuntoVenta();
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
