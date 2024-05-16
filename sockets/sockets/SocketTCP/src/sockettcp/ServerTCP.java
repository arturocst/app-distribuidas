
package sockettcp;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
 
public class ServerTCP {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MarcoServidor mimarco = new MarcoServidor();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
}

class MarcoServidor extends JFrame implements Runnable {

    public MarcoServidor() {

        setBounds(1200, 300, 280, 350);

        JPanel milamina = new JPanel();

        milamina.setLayout(new BorderLayout());

        areatexto = new JTextArea();

        milamina.add(areatexto, BorderLayout.CENTER);

        add(milamina);

        setVisible(true);

        Thread miHilo = new Thread(this);

        miHilo.start();

    }

    private JTextArea areatexto;

    @Override
    public void run() {

        try {
            //System.out.print("escucha");

            ServerSocket servidor = new ServerSocket(9999);

            while (true) {

                Socket miSocket = servidor.accept();
                DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());
                String mensaje_Texto = flujo_entrada.readUTF();
                areatexto.append("\n" + mensaje_Texto);

            }

        } catch (IOException ex) {
            //Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
}
