/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author leoyochi
 */
public class Login {
    //Atributos
    private int idlogin;
    private String nombreLogin;
    private String passwordLogin;
    private Date fechaCreacionLOg;
    private boolean estatusLogin;
    
    //Declarar objetos usuario y rolUsuario
    private Usuario usuario;
    private RolUsuario rolUsuario;
    
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
//Variables para el usuuario, password y tipo usuario
String nameUser="Leonardo";
String passwordUser="123";
String typeUser="student";

if ((nameUser.equals(this.usuario.getNombreUsuario())) && (passwordUser.equals(this.passwordLogin)) && (typeUser.equals(this.rolUsuario.getTipoRolUsuario()))) {

return true;

}else{
    
    return false;
}
}
}


    




