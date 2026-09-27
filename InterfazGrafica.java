import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Image;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//SQL
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InterfazGrafica {
    // Atributos de la clase
    private Persona personaACensar;
    private JFrame ventanaPrincipal;
    private JTextField identificacionTextField;
    private JTextField nombreTextField;
    private JTextField apellidoTextField;
    private JComboBox<String> sexoComboBox;
    private JButton botonGuardar;
    private JTable tablaDePersonasCensadas;
    private DefaultTableModel modeloDeLaTabla;
    private Censo unCenso;

    // Constructor de la clase
    public InterfazGrafica() {
        unCenso = new Censo(); 
    }

    /*
    * Método para inicializar la interfaz gráfica
    */ 
    public void iniciarPrograma(){
        crearVentanaPrincipal();
        crearLogo();
        crearEtiquetasYCamposDeTexto();
        crearBotones();
        crearTabla();
        crearEventos();

        ventanaPrincipal.setVisible(true);  // Hace visible la ventana principal
    }

    /*
    * Creamos la ventana principal.
    */ 
    public void crearVentanaPrincipal(){
        ventanaPrincipal = new JFrame("Bienvenido a censo de personas");
        ventanaPrincipal.setSize(1280, 720); // Define el tamaño de la ventana
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define la acción de cierre de la ventana
        ventanaPrincipal.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        ventanaPrincipal.setResizable(false); // Evita que la ventana sea redimensionable

        Color colorFondoVentanaPrincipal = new Color(45, 45, 45); // Define el color de fondo de la ventana
        ventanaPrincipal.getContentPane().setBackground(colorFondoVentanaPrincipal); // Aplica el color de fondo a la ventana

        // Desactivamos el layout automático para poder posicionar los elementos manualmente.
        ventanaPrincipal.setLayout(null);
    }
 
    /*
    * Creamos el logo.
    */ 
    public void crearLogo(){
        ImageIcon darxLogo = new ImageIcon("C:\\Users\\vroch\\OneDrive\\Desktop\\Classroom\\2026-2\\Test Development\\logo.png");
        Image darxLogoRedimensionado = darxLogo.getImage().getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING);
        ImageIcon darxLogoRedimensionadoIcon = new ImageIcon(darxLogoRedimensionado);

        JLabel darxCompanyLabel = new JLabel(darxLogoRedimensionadoIcon);
        darxCompanyLabel.setBounds(10, 565, 150, 150);
        ventanaPrincipal.add(darxCompanyLabel);
    }

    /*
    * Creamos nuestras etiquetas y campos para ingresar texto.
    */ 
    public void crearEtiquetasYCamposDeTexto(){
        JLabel identificacionLabel = new JLabel("Identificación:");
        identificacionLabel.setBounds(50, 50, 100, 30); // Posiciona el label en la ventana
        identificacionLabel.setForeground(Color.WHITE);
        identificacionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ventanaPrincipal.add(identificacionLabel);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(50, 90, 100, 30); // Posiciona el label en la ventana
        nombreLabel.setForeground(Color.WHITE); // Cambia el color del texto del label a blanco
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Cambia la fuente del texto del label  
        ventanaPrincipal.add(nombreLabel); // Agrega el label a la ventana.

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(50, 130, 100, 30);
        apellidoLabel.setForeground(Color.WHITE);
        apellidoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ventanaPrincipal.add(apellidoLabel);

        JLabel sexoLabel = new JLabel("Sexo:");
        sexoLabel.setBounds(50, 170, 100, 30);
        sexoLabel.setForeground(Color.WHITE);
        sexoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ventanaPrincipal.add(sexoLabel);

        // Campos de texto.
        identificacionTextField = new JTextField();
        identificacionTextField.setBounds(160, 50, 180, 25); // Posiciona el text field en la ventana
        ventanaPrincipal.add(identificacionTextField); // Agrega el text field a la ventana

        nombreTextField = new JTextField();
        nombreTextField.setBounds(160, 90, 180, 25); // Posiciona el text field en la ventana
        ventanaPrincipal.add(nombreTextField); // Agrega el text field a la ventana

        apellidoTextField = new JTextField();
        apellidoTextField.setBounds(160, 130, 180, 25);
        ventanaPrincipal.add(apellidoTextField);

        // Usamos un comboBox para seleccionar el genero.
        String[] soloHayDosGeneros = {"Masculino", "Femenino", "Helicoptero Apache"};
        sexoComboBox = new JComboBox<>(soloHayDosGeneros);
        sexoComboBox.setBounds(160, 170, 180, 25);
        ventanaPrincipal.add(sexoComboBox);
        
    }

    /*
    * Creamos nuestros botones.
    */ 
    public void crearBotones(){
        botonGuardar = new JButton("Guardar");
        botonGuardar.setBounds(160, 230,120, 40);
        botonGuardar.setBackground(new Color (70,130,180));
        botonGuardar.setForeground(Color.WHITE);
        botonGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        botonGuardar.setFocusPainted(false); // Evita que el botón tenga un borde al ser seleccionado
        ventanaPrincipal.add(botonGuardar);        
    }

    /*
    * Creamos la tabla para mostrar la informacion.
    */     
    public void crearTabla(){
        String[] columnas = {"Identificacion", "Nombre", "Apellido", "Sexo"}; // Encabezados de las columnas
        modeloDeLaTabla = new DefaultTableModel(columnas, 0); // Creamos un modelo para la tabla

        tablaDePersonasCensadas = new JTable(modeloDeLaTabla); // Creamos la tabla asignando el modelo anterior.

        JScrollPane scrollTabla = new JScrollPane(tablaDePersonasCensadas); // Creamos un scroll para la tabla
        tablaDePersonasCensadas.getTableHeader().setReorderingAllowed(false); // Evitamos que el usuario pueda reordenar las columnas.
        scrollTabla.setBounds(400, 50, 800, 550); // Posicionamos la tabla.
        ventanaPrincipal.add(scrollTabla); // Finalmente la agregamos.        
    }
    
    /*
    * Creamos nuestros eventos para nuestros botones.
    */
    public void crearEventos(){
        botonGuardar.addActionListener(new ActionListener()
        {
            @Override

            public void actionPerformed(ActionEvent e) {
                // Obtenemos los datos ingresados por el usuario en los text fields.
                try {

                    String identificacionString = identificacionTextField.getText();
                    String nombre = nombreTextField.getText();
                    String apellido = apellidoTextField.getText();
                    String sexo = sexoComboBox.getSelectedItem().toString();

                    // Validamos que los campos no esten vacios.
                    if (identificacionString.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || sexo.isEmpty()){
                        JOptionPane.showMessageDialog(ventanaPrincipal, "No pueden haber campos vacios, intente de nuevo.","Error en los datos ingresados.", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int identificacion = Integer.parseInt(identificacionString);

                    personaACensar = new Persona(identificacion, nombre, apellido, sexo);
                    unCenso.censarPersona(personaACensar);

                    // Guardamos los datos en la base de datos.
                    unCenso.censarEnLaDB(personaACensar);

                    

                    // Agregamos los datos de las personas a la tabla.
                    Object[] nuevaFila = {identificacion, nombre, apellido, sexo};
                    modeloDeLaTabla.addRow(nuevaFila);

                    JOptionPane.showMessageDialog(ventanaPrincipal, "Persona censada exitosamente.", "Hecho", JOptionPane.INFORMATION_MESSAGE);

                    identificacionTextField.setText("");
                    nombreTextField.setText("");
                    apellidoTextField.setText("");
                    sexoComboBox.setSelectedIndex(0);

                } catch (NumberFormatException ex) 
                {
                    //Error si el numero no es valido.
                    JOptionPane.showMessageDialog(ventanaPrincipal, "Probablemente el numero de indentificacion ingresado no es correcto", "Error de datos", JOptionPane.ERROR_MESSAGE);
                }
            }

        });        
    }
    
}