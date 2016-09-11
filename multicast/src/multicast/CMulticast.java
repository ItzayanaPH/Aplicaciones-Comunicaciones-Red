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
            //Lo que tiene  el Servidor
            cl.setTimeToLive(1);
            String msjC = "Hola Cliente";
            byte[] b = msjC.getBytes();
            //
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

                DatagramPacket pC = new DatagramPacket(b,b.length,gpo,9999);
                cl.send(pC);
                System.out.println("Enviando mensaje desde el cliente: "+ msjC + "con un TTL = "+ cl.getTimeToLive());
                try{
                  Thread.sleep(3000);
                } catch (InterruptedException ie){
                  ie.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
