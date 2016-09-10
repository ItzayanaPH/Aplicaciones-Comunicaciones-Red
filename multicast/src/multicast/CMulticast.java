package multicast;

import java.net.*;
import java.io.*;

public class CMulticast {

    public static void main(String[] args) {
        InetAddress gpo = null;
        try {
            MulticastSocket cl = new MulticastSocket(9999);
            System.out.println("Cliente escuchando puerto "+cl.getLocalPort());
            cl.setReuseAddress(true);
            try{
                gpo = InetAddress.getByName("228.1.1.1");
                
            }catch(UnknownHostException u){
                System.err.println("Dirección no Valida");
            }
            cl.joinGroup(gpo);
            System.out.println("Unido al grupo");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[10],10);
                cl.receive(p);
                String msj = new String(p.getData());
                System.out.println("Datagrama recibido ... "+msj);
                System.out.println("Servidor descubierto: "+p.getAddress()+" puerto: "+p.getPort());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
