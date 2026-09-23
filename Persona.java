
public class Persona {
    private int identificacion;
    private String nombre;
    private String apellido;
    private String sexo;

    // Constructor
    public Persona(int identificacion, String nombre, String apellido, String sexo){
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
    }

    public int getIdentificacion(){
        return identificacion;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return String.valueOf(apellido);
    }

    public String getSexo(){
        return sexo;
    }
}