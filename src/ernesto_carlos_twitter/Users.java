/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoredsocial;

import javax.swing.JOptionPane;

/**
 *
 * @author ernes
 */
public class Users {
    public static Users usuarios[] = new Users[100];
    private static String usuariologgeado=null;
    private static String generologgeado=null;
    private static String nombrevisita=null;
    private static String usuariovisita=null;
    private static String generovisita=null;
    private static int seguidoresvisita=0;
    private static int edadvisita=0;
    private static int seguidosvisita=0;
    String nombre, username, password, genero;
    int edad;
    boolean estado;
    String[] seguidores=new String[300];
    String[] seguidos=new String[300]; 

    public Users() {
    }

    public Users(String nombre, String username, String password, int edad, String[] seguidores, String[] seguidos, String genero, boolean estado) {
        this.nombre=nombre;
        this.username = username;
        this.password = password;
        this.seguidores=seguidores;
        this.seguidos=seguidos;
        this.edad=edad;
        this.genero=genero;
        this.estado=estado;
    }
    
    
    
    public void setGenero(String username){
        Users u=buscarUsuario(username);
        generologgeado=u.genero;
    }
    
    public void setEdad(String username){
        Users u=buscarUsuario(username);
        edadvisita=u.edad;
    }
    
    public static int getEdadVisita(){
        return edadvisita;
    }
    
    public static void resetGeneroLoggeado(){
        generologgeado=null;
    }
    
    public String getGenero(String username){
        return generologgeado;
    }
    
    public String [] buscarArregloSeguidos(String username){
        Users u=buscarUsuario(username);
        String[] seguidos=u.seguidos;
        return seguidos;
    }
    
    public void setNombreVisita(String username){
        Users u=buscarUsuario(username);
        nombrevisita=u.nombre;
    }
    
    public void setGeneroVisita(String username){
        Users u=buscarUsuario(username);
        generovisita=u.genero;
    }
    
    public static String getGeneroVisita(){
        return generovisita;
    }
    
    public void resetGeneroVisita(){
        generovisita=null;
    }
    
    public void setUsernameVisita(String username){
        Users u=buscarUsuario(username);
        usuariovisita=u.username;
    }
    
    public void setSeguidores(String username){
        Users u=buscarUsuario(username);
        int cantidad=0;
        for (int i = 0; i < seguidores.length; i++) {
            Users verificar=buscarUsuario(u.seguidores[i]);
            if(u.seguidores[i]!=null && verificar.estado){
                cantidad++;
            }
        }
        seguidoresvisita=cantidad;
    }
    
    public void setSeguidos(String username){
        Users u=buscarUsuario(username);
        int cantidad=0;
        for (int i = 0; i < seguidos.length; i++) {
            Users verificar=buscarUsuario(u.seguidos[i]);
            if(u.seguidos[i]!=null && verificar.estado){
                cantidad++;
            }
        }
        seguidosvisita=cantidad;
    }
    
    public void resetNombre(){
        nombrevisita=null;
    }
    
    public void resetUsuario(){
        usuariovisita=null;
    }
    
    public void resetSeguidosVisitas(){
        seguidosvisita=0;
    }
    
    public void resetSeguidoresVisitas(){
        seguidoresvisita=0 ;
    }

    public static String getNombrevisita() {
        return nombrevisita;
    }

    public static String getUsuariovisita() {
        return usuariovisita;
    }

    public static int getSeguidoresvisita() {
        return seguidoresvisita;
    }

    public static int getSeguidosvisita() {
        return seguidosvisita;
    }
    
    public static void loggear(String username) {
        usuariologgeado = username;
    }

    public static String getUsuariologgeado() {
        return usuariologgeado;
    }
    
    public static void desloggear() {
        usuariologgeado = null;
    }
    
    public static String[] getSeguidosDeUsuarioLogueado() {
        Users u = buscarUsuario(usuariologgeado);
        if (u != null) {
            return u.seguidos;
        }
        return null;
    }

    public static Users buscarUsuario(String username) {
        for (Users u : usuarios) {
            if (u != null && u.username.equals(username)) {
                return u;
            }
        }
        return null;
    }
    
    public boolean añadirseguidor(String usuario, String usuarioaseguir) {
    Users u = buscarUsuario(usuario);
    Users u2 = buscarUsuario(usuarioaseguir);
    

        for (int i = 0; i < u2.seguidores.length; i++) {
            if (u2.seguidores[i] != null && u2.seguidores[i].equals(usuario)) {
                return false;
            }else{
                if(u2.seguidores[i] == null){
                    u2.seguidores[i] = usuario;
                    break;
                }
            }
        }

        for (int i = 0; i < u.seguidos.length; i++) {
            if (u.seguidos[i] != null && u.seguidos[i].equals(usuarioaseguir)) {
                return false;
            }else{
                u.seguidos[i]=usuarioaseguir;
                break;
            }
        }

        return true;
    }

    
    public boolean dejarDeSeguir(String usuario, String usuarioADejarDeSeguir) {
    Users u = buscarUsuario(usuario);
    Users u2 = buscarUsuario(usuarioADejarDeSeguir);
    
    if (u != null && u2 != null) {
        for (int i = 0; i < u.seguidos.length; i++) {
            if (u.seguidos[i] != null && u.seguidos[i].equals(usuarioADejarDeSeguir)) {
                u.seguidos[i] = null;
                for (int j = 0; j < u2.seguidores.length; j++) {
                    if (u2.seguidores[j] != null && u2.seguidores[j].equals(usuario)) {
                        u2.seguidores[j] = null;
                        return true;
                    }
                }
            }
        }
    }   
    return false;
}
    
    public boolean verificarSiSigue(String usuariologgeado, String usuarioperfil){
        Users u=buscarUsuario(usuariologgeado);
        for (int i = 0; i < seguidos.length; i++) {
            if(u.seguidos[i]!=null && u.seguidos[i].equals(usuarioperfil)){
                return true;
            }
        }
        return false;
    }
    
    public static Users[] buscadordeusuarios(String usuarioabuscar) {
        Users[] resultados = new Users[100];
        String usuariolog=Users.getUsuariologgeado();
        int indice = 0;
        
        for (Users u : usuarios) {
            if (u != null && !u.username.equals(usuariolog) && u.username.contains(usuarioabuscar) && u.estado==true) {
                resultados[indice] = u;
                indice++;
            }
        }        
        return resultados;
    }

    public boolean CrearCuenta(String nombre, String username, String password,int edad, String[] seguidores, String[] seguidos, String genero, boolean estado){
        if (buscarUsuario(username) == null) {
            for (int i = 0; i < usuarios.length; i++) {
                if (usuarios[i] == null) {
                    usuarios[i] = new Users(nombre, username, password, edad, seguidores, seguidos, genero, estado);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean Login(String username, String password){
        Users u= buscarUsuario(username);
        if(u != null && u.password.equals(password)){
            return true;
        }
        return false;
    }
    
public void desactivarCuenta(){
    String Options[]= {"SI","NO"};
    int opcion = JOptionPane.showOptionDialog(null, "¿Deseas desactivar la cuenta?", "Seleccione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,Options, null);
    
    if(opcion == 0){ 
        String usuario = Users.getUsuariologgeado();
        Users u = Users.buscarUsuario(usuario);
        
        if(u != null){
            u.estado = false;
            Inicio i = new Inicio();
            i.setVisible(true);
        }
    } else { 
        JOptionPane.getRootFrame().dispose();
    }
}
    
    public boolean reactivarCuenta(String username){
        Users u=buscarUsuario(username);
        if(u.estado==false){
        u.estado=true;
        return true;
        }
        return false;
        }
}
    

