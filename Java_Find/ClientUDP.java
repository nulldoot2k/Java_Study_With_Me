package Java_Find;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) throws SocketException,
    UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        Scanner scanner = new Scanner(System.in);
        System.out.println("================Menu================");
        System.out.println("1: Show info");
        System.out.println("2: Search by name");
        int choose = Integer.parseInt(scanner.nextLine());
        String result = "";
        switch(choose) {
            case 1: {
                result = choose + " " ;
                break;
            }
            case 2: {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                // result = choose + " " + name;
                result = choose + " " + name;
                break;
            }
        }
        byte[] send_data = result.getBytes();
        byte[] receive_data = new byte[1024];

        DatagramPacket datagramSend_Packet = new DatagramPacket(send_data, send_data.length, address, 9999);
        DatagramPacket datagramReceive_Packet = new DatagramPacket(receive_data, receive_data.length);

        client.send(datagramSend_Packet);
        client.receive(datagramReceive_Packet);

        System.out.println("Result: \n" + new String(datagramReceive_Packet.getData()));
        
        client.close();
        scanner.close();
    }
}