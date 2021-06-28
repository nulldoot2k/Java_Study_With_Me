package Java_Tong_hieu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) throws IOException, SocketException {
        DatagramSocket client = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);


        System.out.println("================MENU====================");
        System.out.println("1.Sum two numbers");
        System.out.println("2.Subtraction of two number");
        String result = "";
        int choose = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter number a: ");
        int a = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter number b: ");
        int b = Integer.parseInt(scanner.nextLine());
        
        switch (choose) {
            case 1: {
                result = "sum " + a + " " + b;
                System.out.println(result);
                break;
            }
            case 2: {
                result = "Subtraction " + a + " " + b;
                System.out.println(result);
                break;
            }
            default: {
                break;
            }
        }
        InetAddress address = InetAddress.getLocalHost();
        byte[] send_data = result.getBytes();
        byte[] receive_data = new byte[1024];
        DatagramPacket sent_packet = new DatagramPacket(send_data, send_data.length, address, 9999);
        DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);

        client.send(sent_packet);
        client.receive(receive_packet);
        System.out.println("Result: " + new String(receive_packet.getData()));
        scanner.close();
        client.close();
    }
}
