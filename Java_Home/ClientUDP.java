package Java_Home;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) throws IOException, SocketException, UnknownHostException {
        Scanner scanner = new Scanner(System.in);
        String result = "";
        while (true) {
            System.out.println("=================Menu====================");
            System.out.println("1.Show all home");
            System.out.println("2.Search");
            System.out.println("3.Add home");
            System.out.println("4.Bye home");
            System.out.println("5.Exists!");
            System.out.print("Choose: ");
            int choose = Integer.parseInt(scanner.nextLine());
            if (choose == 1) {
                result = choose + " Show_all_home";
                client(result);
            }
            if (choose == 2) {
                result = choose + " Search";
                System.out.println("1.Search by house number");
                System.out.println("2.search by status");
                System.out.print("Choose: ");
                int search = Integer.parseInt(scanner.nextLine());
                if (search == 1) {
                    System.out.print("Enter the house number you want to search for: ");
                    int sonha = Integer.parseInt(scanner.nextLine());
                    result = choose + " Search " + search + " " + sonha;
                }
                if (search == 2) {
                    System.out.print("Enter current status: ");
                    String status = scanner.nextLine();
                    result = choose + " search " + search + " " + status;
                }
                client(result);
            }
            if (choose == 3) {
                System.out.print("Enter the house number: ");
                int sonha = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter house price: ");
                int gianha = Integer.parseInt(scanner.nextLine());
                result = choose + " add_home " + sonha + " " + gianha;
                client(result);
            }
            if (choose == 4) {
                System.out.print("Choose house number: ");
                int sonha = Integer.parseInt(scanner.nextLine());
                result = choose + " Bye_home " + sonha;
                client(result);
            }
            if (choose == 5) {
                System.out.println("Ok Bye!!!=======================> Lol");
                break;
            }
        }
        scanner.close();
    }

    public static void client(String result) throws IOException, UnknownHostException {
        DatagramSocket client = new DatagramSocket();
        InetAddress address = InetAddress.getLocalHost();
        byte[] send_data = result.getBytes();
        byte[] receive_data = new byte[1024];
        DatagramPacket send_packet = new DatagramPacket(send_data, send_data.length, address, 9999);
        DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
        client.send(send_packet);
        client.receive(receive_packet);
        String data = new String(receive_packet.getData(), 0, receive_packet.getLength());
        System.out.println("\n" + data);
        client.close();
    }
}
