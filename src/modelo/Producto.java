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
 * @author kevin
 */
public class Producto extends ConexionBD implements CRUDinterface {

    //Atributos 
    private int idProducto;
    private String NombreProducto;
    private String cantidad;
    private String Categotria;
    private String Precio;

    private CallableStatement cstmt;
    private ResultSet result;

    //Constructor
    public Producto() {
    }

    public Producto(int idProducto, String NombreProducto, String cantidad, String Categotria, String Precio) {
        this.idProducto = idProducto;
        this.NombreProducto = NombreProducto;
        this.cantidad = cantidad;
        this.Categotria = Categotria;
        this.Precio = Precio;
    }

    //Metodos get y set
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

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

    public String getCategotria() {
        return Categotria;
    }

    public void setCategotria(String Categotria) {
        this.Categotria = Categotria;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    //metodo String
    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", NombreProducto=" + NombreProducto + ", cantidad=" + cantidad + ", Categotria=" + Categotria + ", Precio=" + Precio + '}';
    }

    //Metodos CRUD
    @Override
    public boolean insertar() {
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_insertar_PuntoVenta(?,?,?,?,?);");
                this.cstmt.setInt(1, this.idProducto);
                this.cstmt.setString(2, this.NombreProducto);
                this.cstmt.setString(3, this.Precio);
                this.cstmt.setString(4, this.cantidad);
                this.cstmt.setString(5, this.Categotria);

                //ejecutar el procedimiento almacenado
                this.cstmt.execute();

                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();

                return true;
            } catch (SQLException ex) {
                super.setMensajes("Error sql: " + ex.getMessage());

            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());

        }

        return false;

    }

    @Override
    public ArrayList<Producto> buscar() {
        ArrayList<Producto> listaProducto = new ArrayList<>();

        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_buscar_PuntoVenta();");

                //ejecutar consulta
                this.result = this.cstmt.executeQuery();

                while (this.result.next()) {

                    Producto producto = new Producto();

                    producto.idProducto = this.result.getInt(1);
                    producto.NombreProducto = this.result.getString(2);
                    producto.Precio = this.result.getString(3);
                    producto.cantidad = this.result.getString(4);
                    producto.Categotria = this.result.getString(5);

                    //agregar el objeto producto a la lista 
                    listaProducto.add(producto);

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

        return listaProducto;

    }

    @Override
    public boolean buscarPorId(int id) {

        this.idProducto = id;

        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_buscarId_PuntoVenta(?);");
                this.cstmt.setInt(1, this.idProducto);

                //ejecutar consulta
                this.result = this.cstmt.executeQuery();

                while (this.result.next()) {
                    this.idProducto = this.result.getInt(1);
                    this.NombreProducto = this.result.getString(2);
                    this.Precio = this.result.getString(3);
                    this.cantidad = this.result.getString(4);
                    this.Categotria = this.result.getString(5);

                }

                this.cstmt.execute();

                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();

                return true;

            } catch (SQLException ex) {
                super.setMensajes(null + ex.getMessage());

            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());

        }

        return false;
    }

    @Override
    public boolean modificar(int id) {

        this.idProducto = id;
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_actualizar_PuntoVenta(?, ?, ?, ?, ?);");
                this.cstmt.setInt(1, this.idProducto);
                this.cstmt.setString(2, this.NombreProducto);
                this.cstmt.setString(3, this.Precio);
                this.cstmt.setString(4, this.cantidad);
                this.cstmt.setString(5, this.Categotria);

                //ejecutar el procedimiento almacenado
                this.cstmt.execute();

                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();

                return true;
            } catch (SQLException ex) {
                super.setMensajes("Error sql: " + ex.getMessage());

            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());

        }

        return false;

    }

    @Override
    public boolean eliminar(int id) {

        this.idProducto = id;

        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_eliminar_PuntoVenta(?);");
                this.cstmt.setInt(1, this.idProducto);

                //ejecutar el procedimiento almacenado
                this.cstmt.execute();

                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();

                return true;
            } catch (SQLException ex) {
                super.setMensajes("Error sql: " + ex.getMessage());

            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());

        }

        return false;
    }

}
