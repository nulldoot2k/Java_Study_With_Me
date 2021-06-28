package JavaUDP_Trac_nghiem;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
    DatagramSocket client = null;
    int port = 9999;

    public void Connect() {
        try {
            client = new DatagramSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String data) {
        if (client != null) {
            byte[] send_data = new byte[1024];
            try {
                InetAddress address = InetAddress.getLocalHost();
                send_data = data.getBytes();
                DatagramPacket send_packet = new DatagramPacket(send_data, send_data.length, address, port);
                client.send(send_packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void recieve() {
        if (client != null) {
            byte[] receive_data = new byte[1024];
            try {
                DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
                client.receive(receive_packet);
                System.out.println("\n" + new String(receive_packet.getData()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (client != null) {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ClientUDP client = new ClientUDP();
        client.Connect();
        for (int i = 0; i < 3; i++) {
            client.send("read" + " " + i + "" + " ");
            client.recieve();
            System.out.println("Moi chon dap an A B C: ");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine().trim();
            if (s != null && !s.equals("T")) {
                client.send("check" + " " + i + "" + " " + s);
                client.recieve();
            }
            if (s.equals("T")) {
                System.exit(0);
            }
        }

    }
}
