import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Censo {
    private ArrayList<Persona> personasCensadas;

    // Hacemos el constructor público para que pueda ser instanciado desde otras clases.
    public Censo(){
        personasCensadas = new ArrayList<Persona>();
    }

    // Metodo para censar una persona.
    public void censarPersona(Persona personaACensar){
        personasCensadas.add(personaACensar);
    }

    // Metodo para obtener el numero de personas censadas.
    public int obtenerNumeroDePersonasCensadas(){
        return personasCensadas.size();
    }

    public boolean censarEnLaDB(Persona personaACensar){
        // Esta linea es en otros terminos la instruccion que damos a postgres desde java, los signos de admiracion
        // dentro de los parentesis son reemplazados en la linea 213 a 216, pos los datos exactos que queremos guardar.
        String sql = "INSERT INTO persona (identificacion, nombre, apellido, sexo) VALUES (?, ?, ?, ?)";

        try 
        (Connection conexionDB = ConexionDB.obtenerConexion();

        // Envia una plantilla de la instruccion a SQL para que la compile de antemano.
        PreparedStatement solicitud = conexionDB.prepareStatement(sql)){

            solicitud.setInt(1, personaACensar.getIdentificacion());
            solicitud.setString(2, personaACensar.getNombre());
            solicitud.setString(3, personaACensar.getApellido());
            solicitud.setString(4, personaACensar.getSexo());

            solicitud.executeUpdate(); // Orden final, para que se inserte la nueva fila en la base de datos.
            return true;
        } 
        catch (SQLException e) {
            System.out.println("Error al guardar en la base de datos: " + e.getMessage());
            return false;
        }        

    }
}
