package udp;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class ServerUDP {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MarcoServidorUDP();
            }
        });
    }

}

class MarcoServidorUDP extends JFrame implements Runnable {

    private JTextArea areaTexto;
    private Map<String, String> usuarios;

    public MarcoServidorUDP() {
        setBounds(1200, 300, 280, 350);
        usuarios = new HashMap<>();
        usuarios.put("usuario1", "contrasena1");
        usuarios.put("usuario2", "contrasena2");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        areaTexto = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Thread miHilo = new Thread(this);
        miHilo.start();
    }

    @Override
    public void run() {
        try {
            DatagramSocket servidorSocket = new DatagramSocket(9999);
            byte[] recibirDatos = new byte[1024];

            while (true) {
                DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
                servidorSocket.receive(recibirPaquete);

                String mensajeRecibido = new String(recibirPaquete.getData(), 0, recibirPaquete.getLength());
                String[] partesMensaje = mensajeRecibido.split(":");
                String nombreUsuario = partesMensaje[0];
                String contrasena = partesMensaje[1];
                String mensaje = partesMensaje[2];

                if (usuarios.containsKey(nombreUsuario) && usuarios.get(nombreUsuario).equals(contrasena)) {
                    areaTexto.append("\n" + mensaje);
                } else {
                    areaTexto.append("\nIntento de acceso no autorizado desde computadora 1 error:" + recibirPaquete.getAddress());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
