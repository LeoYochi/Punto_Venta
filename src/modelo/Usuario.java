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
public class Usuario extends ConexionBD implements CRUDinterface{
    //Atributos
    private int idUsuario;
    private String nombreUsuario;
    private String apPaternoUsuario;
    private String apMaternoUsuario;
    private String emailUsuario;
    private String telefonoCelular;
    
    private CallableStatement cstmt;
    private ResultSet result;
    
    //Constructor

    public Usuario() {
    
    }

    public Usuario(int idUsuario, String nombreUsuario, String apPaternoUsuario, String apMaternoUsuario, String emailUsuario, String telefonoCelular) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apPaternoUsuario = apPaternoUsuario;
        this.apMaternoUsuario = apMaternoUsuario;
        this.emailUsuario = emailUsuario;
        this.telefonoCelular = telefonoCelular;
    }
    
    //Metodos set y get

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApPaternoUsuario() {
        return apPaternoUsuario;
    }

    public void setApPaternoUsuario(String apPaternoUsuario) {
        this.apPaternoUsuario = apPaternoUsuario;
    }

    public String getApMaternoUsuario() {
        return apMaternoUsuario;
    }

    public void setApMaternoUsuario(String apMaternoUsuario) {
        this.apMaternoUsuario = apMaternoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }
    
    //Metodo toString

    @Override
    public String toString() {
        return "Usuario{ " + "idUsuario=" + idUsuario + ",\n nombreUsuario=" + nombreUsuario + ",\n apPaternoUsuario=" + apPaternoUsuario + ",\n apMaternoUsuario=" + apMaternoUsuario + ",\n emailUsuario=" + emailUsuario + ",\n telefonoCelular=" + telefonoCelular + '}';
    }

    @Override
    public boolean insertar() {
        
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());
                
                //Llamar el procedimiento alamacenado
                this.cstmt=super.getConexion().prepareCall("call BaseDeMySQL.sp_insertar_usuario(?,?,?,?,?,?);");
                this.cstmt.setInt(1, this.idUsuario);
                this.cstmt.setString(2, this.nombreUsuario);
                this.cstmt.setString(3, this.apPaternoUsuario);
                this.cstmt.setString(4, this.apMaternoUsuario);
                this.cstmt.setString(5, this.emailUsuario);
                this.cstmt.setString(6, this.telefonoCelular);
                
                //ejecutar el procedimiento almacenado
                this.cstmt.execute();
                
                
                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();


                
                
                
                return true;
            } catch (SQLException ex) {
                super.setMensajes("Error sql: "+ ex.getMessage());

            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());

        }
        
        
        
        return false;

    }//fin del metodo 

    @Override
    public ArrayList<Usuario> buscar() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        
                
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_buscar_usuarios();");

                //ejecutar consulta
                this.result = this.cstmt.executeQuery();
                
                while (this.result.next()) {
                    
                    Usuario usuario = new Usuario();
                    
                    usuario.idUsuario = this.result.getInt(1);
                    usuario.nombreUsuario = this.result.getString(2);
                    usuario.apPaternoUsuario = this.result.getString(3);
                    usuario.apMaternoUsuario = this.result.getString(4);
                    usuario.emailUsuario = this.result.getString(5);
                    usuario.telefonoCelular = this.result.getString(6);
                    //agregar el objeto usuario a la lista 
                    listaUsuarios.add(usuario);

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

        return listaUsuarios;

        
    }


    @Override
    public boolean buscarPorId(int id) {
        
        this.idUsuario = id;
        
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_buscar_usuario_id(?);");
                this.cstmt.setInt(1, this.idUsuario);

                //ejecutar consulta
                this.result = this.cstmt.executeQuery();
                
                while (this.result.next()) {
                    this.idUsuario = this.result.getInt(1);
                    this.nombreUsuario = this.result.getString(2);
                    this.apPaternoUsuario = this.result.getString(3);
                    this.apMaternoUsuario = this.result.getString(4);
                    this.emailUsuario = this.result.getString(5);
                    this.telefonoCelular = this.result.getString(6);

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

    }//fin del metodo buscarPorId

       

    

    @Override
    public boolean modificar(int id) {
        this.idUsuario=id;
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_actualizar_usuario(?,?,?,?,?,?);");
                this.cstmt.setInt(1, this.idUsuario);
                this.cstmt.setString(2, this.nombreUsuario);
                this.cstmt.setString(3, this.apPaternoUsuario);
                this.cstmt.setString(4, this.apMaternoUsuario);
                this.cstmt.setString(5, this.emailUsuario);
                this.cstmt.setString(6, this.telefonoCelular);

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
                this.idUsuario=id;

        
        if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_eliminar_usuario(?);");
                this.cstmt.setInt(1, this.idUsuario);
             

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
