package udp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import java.net.*;

public class ClientUDP {

    public static void main(String[] args) {
        MarcoCliente mimarco = new MarcoCliente();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class MarcoCliente extends JFrame {

    public MarcoCliente() {
        setBounds(600, 300, 280, 400);
        LaminaMarcoCliente milamina = new LaminaMarcoCliente();
        add(milamina);
        setVisible(true);
    }
}

class LaminaMarcoCliente extends JPanel {

    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JTextField campoMensaje;

    public LaminaMarcoCliente() {
        JLabel labelUsuario = new JLabel("Usuario:");
        add(labelUsuario);
        campoUsuario = new JTextField(20);
        add(campoUsuario);

        JLabel labelContrasena = new JLabel("Contraseña:");
        add(labelContrasena);
        campoContrasena = new JPasswordField(20);
        add(campoContrasena);

        JLabel labelMensaje = new JLabel("Mensaje:");
        add(labelMensaje);
        campoMensaje = new JTextField(20);
        add(campoMensaje);

        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(new EnviarDatos());
        add(botonEnviar);
    }

    private class EnviarDatos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                // Obtener los datos del campo de texto
                String usuario = campoUsuario.getText();
                String contrasena = new String(campoContrasena.getPassword());
                String mensaje = campoMensaje.getText();

                // Convertir los datos a bytes
                String datos = usuario + ":" + contrasena + ":" + mensaje;
                byte[] datosBytes = datos.getBytes();

                // Obtener la dirección IP y el puerto del servidor
                InetAddress direccionServidor = InetAddress.getByName("192.168.56.1"); // Cambiar por la dirección del servidor
                int puertoServidor = 9999; // Cambiar por el puerto del servidor

                // Crear un socket UDP
                DatagramSocket socketUDP = new DatagramSocket();

                // Crear un paquete UDP con los datos, la dirección y el puerto del servidor
                DatagramPacket paquete = new DatagramPacket(datosBytes, datosBytes.length, direccionServidor, puertoServidor);

                // Enviar el paquete
                socketUDP.send(paquete);

                // Cerrar el socket
                socketUDP.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
