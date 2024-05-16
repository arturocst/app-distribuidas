package sockettcp;

import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String args[]) throws Exception {
        // Crear un socket UDP para enviar mensajes al intermediario
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 8888;

        // Leer el mensaje desde la entrada estándar
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el mensaje: ");
        String message = scanner.nextLine();

        // Convertir el mensaje a bytes y crear el paquete de datagrama
        byte[] sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

        // Enviar el paquete al intermediario
        clientSocket.send(sendPacket);
        System.out.println("Mensaje enviado al intermediario.");

        // Recibir la respuesta del servidor TCP en Python
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        // Convertir la respuesta a String y mostrarla
        String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Respuesta del servidor TCP: " + serverResponse);

        // Cerrar el socket del cliente
        clientSocket.close();
    }
}
