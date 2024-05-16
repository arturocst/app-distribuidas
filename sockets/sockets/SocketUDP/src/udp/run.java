
package udp;


public class run {
    
    public static void main (String [] args){
        ClientUDP cliente = new ClientUDP();
        ServerUDP server = new ServerUDP();
        cliente.main(args);
        server.main(args);
    }
    
}
