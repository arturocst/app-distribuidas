import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.*;
import java.net.*;

public class ClientTCP {

    public static void main(String[] args) {
        MarcoCliente mimarco = new MarcoCliente();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoCliente extends JFrame {

    public MarcoCliente() {
        setBounds(600, 300, 280, 350);
        LaminaMarcoCliente milamina = new LaminaMarcoCliente();
        add(milamina);
        setVisible(true);
    }
}

class LaminaMarcoCliente extends JPanel {

    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private JTextField campoMensaje;
    private JButton botonEnviar;
    private Socket miSocket;
    private DataOutputStream flujoSalida;

    public LaminaMarcoCliente() {
        JLabel etiquetaUsuario = new JLabel("Usuario:");
        add(etiquetaUsuario);
        campoUsuario = new JTextField(20);
        add(campoUsuario);

        JLabel etiquetaContraseña = new JLabel("Contraseña:");
        add(etiquetaContraseña);
        campoContraseña = new JPasswordField(20);
        add(campoContraseña);

        JLabel etiquetaMensaje = new JLabel("Mensaje:");
        add(etiquetaMensaje);
        campoMensaje = new JTextField(20);
        add(campoMensaje);

        botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarYEnviar();
            }
        });
        add(botonEnviar);
    }

    private void autenticarYEnviar() {
        String nombreUsuario = campoUsuario.getText();
        String contraseña = new String(campoContraseña.getPassword());
        String mensaje = campoMensaje.getText();

        try {
            miSocket = new Socket("192.168.231.70", 9999);
            flujoSalida = new DataOutputStream(miSocket.getOutputStream());
            flujoSalida.writeUTF(nombreUsuario);
            flujoSalida.writeUTF(contraseña);

            // Enviamos el mensaje solo si la autenticación es exitosa
            flujoSalida.writeUTF(mensaje);

            flujoSalida.close();
            miSocket.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            // Mostrar mensaje de error al usuario
            JOptionPane.showMessageDialog(this, "Error al autenticar. Por favor, verifique su nombre de usuario y contraseña.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }
}
