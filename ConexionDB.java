import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/censo_db"; // Cambia "nombre_de_tu_base_de_datos" por el nombre real de tu base de datos
    private static final String USUARIO = "postgres"; // Cambia "tu_usuario" por tu nombre de usuario de PostgreSQL
    private static final String CONTRASENA = "1206"; // Cambia "tu_contraseña" por tu contraseña de PostgreSQL


    public static Connection obtenerConexion(){
        Connection conexion = null; // Inicializamos la variable de conexión como null
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA); // Intentamos establecer la conexión con la base de datos utilizando los parámetros definidos anteriormente
        } catch (SQLException e) {
            System.out.println("Error de conexion a la base de datos: " + e.getMessage());
        }

        return conexion;
    }
    
}
