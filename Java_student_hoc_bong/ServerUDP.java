package Java_student_hoc_bong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerUDP {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, SocketException, UnknownHostException {
        DatagramSocket server = new DatagramSocket(9999);
        File file = new File("student.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        while (true) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            byte[] receive_data = new byte[1024];
            DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
            server.receive(receive_packet);
            String data = new String(receive_packet.getData(), 0, receive_packet.getLength());

            String[] s = data.split(" ");
            String result = "";
            String data_reader = reader.readLine();
            if (s[0].equals("1")) {
                result = "List students\n";
                while (data_reader != null) {
                    result += data_reader + "\n";
                    data_reader = reader.readLine();
                }
            }
            if (s[0].equals("2")) {
                result = "Student have scholarships ";
                while (data_reader != null) {
                    String[] a = data_reader.split(" ");
                    if (Float.parseFloat(a[4]) >= 8) {
                        result += data_reader + "\n";
                        System.out.println("Student pro ===> " + result);
                    } else {
                        result = "Not result";
                    }
                    data_reader = reader.readLine();
                }
            }
            if (s[0].equals("3")) {
                System.out.println("Received");
                String type = "";
                while (data_reader != null) {
                    String[] b = data_reader.split(" ");
                    if (b[0].equals(s[2])) {
                        type += b[0] + " " + b[1] + " " + b[2] + " " + b[3] + " " + s[3] + "\n";
                        System.out.println("Data ===> so 3: ==> " +type);
                    } else {
                        type += data_reader + "\n";
                    }
                    data_reader = reader.readLine();
                }
                createFile(type);
                System.out.println("Data in file ===> " + type);
                result = "The mark has been updated from the server";
            }
            if (s[0].equals("4")) {
                result = "Ok Bye!!!";
                break;
            }
            reader.close();
            byte[] send_data = result.getBytes();
            DatagramPacket send_packet = new DatagramPacket(send_data, send_data.length, receive_packet.getAddress(),
                    receive_packet.getPort());
            server.send(send_packet);
        }
    }

    public static void createFile(String s) throws IOException {
        File file = new File("student.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(s.getBytes());
        fileOutputStream.close();
    }
}
