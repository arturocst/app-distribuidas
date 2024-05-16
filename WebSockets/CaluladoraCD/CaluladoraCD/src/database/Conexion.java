package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instancia;
    private Connection conexion;

    private Conexion() {
        try {
            // Parámetros de conexión
            String url = "jdbc:mysql://localhost:3306/operaciones";
            String usuario = "root";
            String contraseña = "1234";

            // Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection conectar() {
        return conexion;
    }

    public void desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
