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
public class RolUsuario extends ConexionBD implements  CRUDinterface{
    //Atributos
    private int idRolUsuario;
    private String tipoRolUsuario;
    private String descripcionRolUsuario;
    
    private CallableStatement cstmt;
    private ResultSet result;

    //Constructor
    
    public RolUsuario() {
        }

    public RolUsuario(int idRolUsuario, String tipoRolUsuario, String descripcionRolUsuario) {
        this.idRolUsuario = idRolUsuario;
        this.tipoRolUsuario = tipoRolUsuario;
        this.descripcionRolUsuario = descripcionRolUsuario;
    }
    

    public int getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(int idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public String getTipoRolUsuario() {
        return tipoRolUsuario;
    }

    public void setTipoRolUsuario(String tipoRolUsuario) {
        this.tipoRolUsuario = tipoRolUsuario;
    }

    public String getDescripcionRolUsuario() {
        return descripcionRolUsuario;
    }

    public void setDescripcionRolUsuario(String descripcionRolUsuario) {
        this.descripcionRolUsuario = descripcionRolUsuario;
    }
    //Metodo toString
    

    @Override
    public String toString() {
        return "RolUsuario{" + "idRolUsuario=" + idRolUsuario + ", tipoRolUsuario=" + tipoRolUsuario + ", descripcionRolUsuario=" + descripcionRolUsuario + '}';
    }

    @Override
    public boolean insertar() {
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_insertar_rolUsuario(?,?,?);");
                this.cstmt.setInt(1, this.idRolUsuario);
                this.cstmt.setString(2, this.tipoRolUsuario);
                this.cstmt.setString(3, this.descripcionRolUsuario);
                

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
    public ArrayList<RolUsuario> buscar() {
        ArrayList<RolUsuario> listaRolUsuarios = new ArrayList<>();

        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_buscar_rolUsuario();");

                //ejecutar consulta
                this.result = this.cstmt.executeQuery();

                while (this.result.next()) {

                    RolUsuario rolUsuario = new RolUsuario();

                    rolUsuario.idRolUsuario = this.result.getInt(1);
                    rolUsuario.tipoRolUsuario = this.result.getString(2);
                    rolUsuario.descripcionRolUsuario = this.result.getString(3);

                    //agregar el objeto usuario a la lista 
                    listaRolUsuarios.add(rolUsuario);

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

        return listaRolUsuarios;

    }

    @Override
    public boolean buscarPorId(int id) {
        
          this.idRolUsuario = id;
        
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_buscar_rolUsuario_id(?);");
                this.cstmt.setInt(1, this.idRolUsuario);

                //ejecutar consulta
                this.result = this.cstmt.executeQuery();
                
                while (this.result.next()) {
                    this.idRolUsuario = this.result.getInt(1);
                    this.tipoRolUsuario = this.result.getString(2);
                    this.descripcionRolUsuario = this.result.getString(3);
                    
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
        
         this.idRolUsuario=id;
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_actualizar_rolUsuario(?,?,?);");
                this.cstmt.setInt(1, this.idRolUsuario);
                this.cstmt.setString(2, this.tipoRolUsuario);
                this.cstmt.setString(3, this.descripcionRolUsuario);
                

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
        
            this.idRolUsuario=id;

        
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_eliminar_rolUsuario(?);");
                this.cstmt.setInt(1, this.idRolUsuario);
             

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
