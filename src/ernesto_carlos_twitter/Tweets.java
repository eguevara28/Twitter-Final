/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoredsocial;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author ernes
 */
public class Tweets {
    
    public static Tweets tweets[]=new Tweets[300];
    String username, tweet;
    
    public Tweets(){
    } 
    
    public Tweets(String username, String tweet){
        this.username=username;
        this.tweet=tweet;
    }

    public Tweets[] getTweets() {
        return tweets;
    }
    
    public boolean publicar(String username, String tweet){
        username=Users.getUsuariologgeado();
        for (int i = tweets.length-1; i >= 0; i--) {
            if(tweets[i]==null){
                tweets[i]=new Tweets(username, tweet);
                return true;
            }
        }
        return false;
    }
    
    public String printtweets() {
    String twit= username + ": " + tweet + " (" + formatoFecha()+ ")";
    return twit;
    }
        
    public static String[] obtenerTweetsFiltrados(String[] seguidos, String usuarioLoggeado) { 
        String[] tweetsFiltrados = new String[300];
        int indice = 0;

        for (Tweets t : tweets) {
            if (t != null && (t.username.equals(usuarioLoggeado) || contiene(seguidos, t.username)) && Users.buscarUsuario(t.username).estado ) {
                tweetsFiltrados[indice++] = t.printtweets();
            }
        }

        return tweetsFiltrados;
    }

    private static boolean contiene(String[] seguidos, String username) {
        for (String seguido : seguidos) {
            if (seguido != null && seguido.equals(username)) {
                return true;
            }
        }
        return false;
    }
    
    public static String[] obtenerHashtag(String hashtagbuscado) {
    int contador = 0;
    String[] tweetsHashtag = new String[100];
    for (Tweets t : tweets) {
        if (t != null && t.printtweets().contains("#" + hashtagbuscado)) {
            Users u = Users.buscarUsuario(t.username);
            if (u != null && u.estado) {
                tweetsHashtag[contador++] = t.printtweets();
            }
        }
    }
    return tweetsHashtag;
    }

    public static String[] ObtenerMencionesFiltradas(){
        int contador = 0;
        String usuario = Users.getUsuariologgeado();

        String[] tweetMencion = new String[300];
        for(Tweets t : tweets){
            if(t != null && t.tweet.contains("@"+ usuario)){
                Users u = Users.buscarUsuario(t.username);
                if(u != null && u.estado){
                    tweetMencion[contador++]= t.printtweets();
                }
            }
        }
        return tweetMencion;
    }
    
    public static String[] obtenerTweetsUsuario(){
        int contador=0;
        String usuario=Users.getUsuariovisita();
        String[] tweetsusuario=new String[300];
        for(Tweets t: tweets){
            if(t!=null && t.username.equals(usuario)){
                tweetsusuario[contador]=t.printtweets();
                contador++;
            }
        }
        return tweetsusuario;
    }
    
    public static String[] obtenerTweetsMiPerfil(){
        int contador=0;
        String usuario=Users.getUsuariologgeado();
        String[] tweetsusuario=new String[300];
        for(Tweets t: tweets){
            if(t!=null && t.username.equals(usuario)){
                tweetsusuario[contador]=t.printtweets();
                contador++;
            }
        }
        return tweetsusuario;
    }
    
    public static String formatoFecha() {
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd MMMM yyyy");
        String fechaFormateada = formato.format(fechaActual);
        return fechaFormateada;
    }
    
}

       

