package multicast;

import java.net.*;
import java.io.*;

public class SMulticast {

    public static void main(String[] args) {
        InetAddress gpo = null;
        try {
            MulticastSocket s = new MulticastSocket(9876);
            s.setReuseAddress(true);
            s.setTimeToLive(1);
            String msj = "Hola";

            byte[] b = msj.getBytes();
            gpo = InetAddress.getByName("228.1.1.1");
            s.joinGroup(gpo);//Agrega mi direccion IP de multicast y mi direccion fisica
            for (;;) {
                DatagramPacket p = new DatagramPacket(b, b.length, gpo, 9999);
                s.send(p);
                System.out.println("Enviando Mensaje " + msj + " con un TTL= " + s.getTimeToLive());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
