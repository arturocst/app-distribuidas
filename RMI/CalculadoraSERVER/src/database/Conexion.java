/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Santiago
 */
public class Conexion {
    
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB = "operaciones";
    private final String USER = "root";
    private final String PASSWORD = "1234";

    public Connection cadena;
    public static Conexion instancia;

    private Conexion() {

        this.cadena = null;

    }

    
    //METODO CONECTAR A BASE DE DATOS
    public Connection conectar() {

        try {

            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL + DB, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

        return this.cadena;

    }

    
    //METODO DESCONECTAR A BASE DE DATOS
    public void desconectar(){
    
        try {
            this.cadena.close();
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e.getMessage());
        }
    
    }
    
    
    //METODO GET INSTANCIA PARA PODER CONECTARME DE FORMA GLOBAL
    public static synchronized Conexion getInstancia(){
    
        if(instancia==null){
        instancia = new Conexion();
        }
    
        return instancia;
    }
    
}