/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author leoyochi
 */
public class Login extends ConexionBD{
    //Atributos
    private int idlogin;
    private String nombreLogin;
    private String passwordLogin;
    private Date fechaCreacionLOg;
    private boolean estatusLogin;
    
    //Declarar objetos usuario y rolUsuario
    private Usuario usuario;
    private RolUsuario rolUsuario;
    
     private CallableStatement cstmt;
    private ResultSet result;
    
    //Constructor
    
    public Login() {
        //Crear objetos usuario y rolUsuario
        this.usuario=new Usuario();
        this.rolUsuario=new RolUsuario();
        
    }

    public Login(int idlogin, String nombreLogin, String passwordLogin) {
        this.idlogin = idlogin;
        this.nombreLogin = nombreLogin;
        this.passwordLogin = passwordLogin;
        
                //Crear objetos usuario y rolUsuario
        this.usuario=new Usuario();
        this.rolUsuario=new RolUsuario();
        
    }
//Metodo set  get 

        public int getIdlogin() {
            return idlogin;
        }

        public void setIdlogin(int idlogin) {
            this.idlogin = idlogin;
        }

        public String getNombreLogin() {
            return nombreLogin;
        }

        public void setNombreLogin(String nombreLogin) {
            this.nombreLogin = nombreLogin;
        }

        public String getPasswordLogin() {
            return passwordLogin;
        }

        public void setPasswordLogin(String passwordLogin) {
            this.passwordLogin = passwordLogin;
        }

        public Date getFechaCreacionLOg() {
            return fechaCreacionLOg;
        }

        public void setFechaCreacionLOg(Date fechaCreacionLOg) {
            this.fechaCreacionLOg = fechaCreacionLOg;
        }

        public boolean isEstatusLogin() {
            return estatusLogin;
        }

        public void setEstatusLogin(boolean estatusLogin) {
            this.estatusLogin = estatusLogin;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        public RolUsuario getRolUsuario() {
            return rolUsuario;
        }

        public void setRolUsuario(RolUsuario rolUsuario) {
            this.rolUsuario = rolUsuario;
        }
//Metodo toString

        @Override
        public String toString() {
            return "Login{" + "idlogin=" + idlogin + ", nombreLogin=" + nombreLogin + ", passwordLogin=" + passwordLogin + ", fechaCreacionLOg=" + fechaCreacionLOg + ", estatusLogin=" + estatusLogin + ", usuario=" + usuario.getNombreUsuario() + ", rolUsuario=" + rolUsuario.getTipoRolUsuario() + '}';
        }
        
//Metodo para validar el inicio de sesion
public boolean validarLogin(){
    if (super.openConexionBD()) {
            try {
                //JOptionPane.showMessageDialog(null, super.getMensajes());

                //Llamar el procedimiento alamacenado
                this.cstmt = super.getConexion().prepareCall("call BaseDeMySQL.sp_validar_login(?,?);");
                this.cstmt.setString(1, this.getUsuario().getNombreUsuario());
                this.cstmt.setString(2, this.getPasswordLogin());
                //ejecutar consulta
                this.result = this.cstmt.executeQuery();
                
                boolean existeUsuario = false;

                while (this.result.next()) {
                    existeUsuario = true;
                    //agregar los datos de la consulta a los atributos del rol usuario
                    this.getRolUsuario().setTipoRolUsuario(this.result.getString("TipoRolUsuario"));
                    

                }

                //this.cstmt.execute();

                //Cerrar conexion a la BD
                this.cstmt.close();
                super.getConexion().close();
                if(existeUsuario){
                    super.setMensajes("Si existe el usuario");
                    return true;
                }else{
                    super.setMensajes("NO Existe el usuario");
                }
            } catch (SQLException ex) {
                super.setMensajes(null + ex.getMessage());

            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());

        }

        return false;
}
}


    




