/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


/**
 *
 * @author leoyochi
 */
public class PuntoVenta extends ConexionBD implements CRUDinterface {

    //Atributos 
    private String NombreProducto;
    private String cantidad;
    private String Precio;

    private CallableStatement cstmt;
    private ResultSet result;    
     //Constructor
    public PuntoVenta() {
    }
      public PuntoVenta(String NombreProducto, String cantidad,  String Precio) {
        this.NombreProducto = NombreProducto;
        this.cantidad = cantidad;
        this.Precio = Precio;
        
      }
    //Metodos get y set

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }
    
    //Metodo toString

    @Override
    public String toString() {
        return "PuntoVenta{" + "NombreProducto=" + NombreProducto + ", cantidad=" + cantidad + ", Precio=" + Precio + '}';
    }
    @Override
    public ArrayList<PuntoVenta> buscar() {
        ArrayList<PuntoVenta> listaPuntoVenta = new ArrayList<>();

        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_buscar_PuntoVenta();");

                //ejecutar consulta
                this.result = this.cstmt.executeQuery();

                while (this.result.next()) {

                    PuntoVenta puntoVenta = new PuntoVenta();

                    puntoVenta.NombreProducto = this.result.getString(2);
                    puntoVenta.Precio = this.result.getString(3);
                    puntoVenta.cantidad = this.result.getString(4);

                    //agregar el objeto producto a la lista 
                    listaPuntoVenta.add(puntoVenta);

                }

                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();

            } catch (SQLException ex) {
                super.setMensajes(null + ex.getMessage());

            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());

        }

        return listaPuntoVenta;
    }

    @Override
    public boolean insertar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
      
}
