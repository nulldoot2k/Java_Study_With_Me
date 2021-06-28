package Java_student_hoc_bong;

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
        while (true) {
            System.out.println("=================Menu====================");
            System.out.println("1 Show all list student");
            System.out.println("2 Display the full list of students with scholarships");
            System.out.println("3 Enter info for student");
            System.out.println("4 Exists!!!");
            System.out.print("Choose: ");

            int choose = Integer.parseInt(scanner.nextLine());
            String result = "";
            if (choose == 1) {
                result = choose + " Display list student";
                client(result);
            }
            if (choose == 2) {
                result = choose + " Display list student have scholarships";
                client(result);
            }
            if (choose == 3) {
                System.out.print("Choose student: ");
                int stt = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter the mark want edit for student: ");
                float mark = Float.parseFloat(scanner.nextLine());
                result = choose + " markStudent" + stt + " " + mark;
                client(result);
            }
            if (choose == 4) {
                System.out.println("Bye!!!");
                break;
            }
        }
    }

    public static void client(String result) throws IOException, SocketException {
        DatagramSocket client = new DatagramSocket();
        InetAddress address = InetAddress.getLocalHost();
        byte[] send_data = result.getBytes();
        byte[] receive_data = new byte[1024];
        DatagramPacket send_packet = new DatagramPacket(send_data, send_data.length, address, 9999);
        DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
        client.send(send_packet);
        client.receive(receive_packet);
        System.out.println("Receive===>");
        String result_data = new String(receive_packet.getData(), 0, receive_packet.getLength());
        System.out.println("\n" + result_data);
        client.close();
    }
}
