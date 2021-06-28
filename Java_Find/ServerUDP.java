package Java_Find;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerUDP {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket server = new DatagramSocket(9999);
        byte[] receive_data = new byte[1024];
        DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
        server.receive(receive_packet);
        String s_receive = new String(receive_packet.getData(), 0, receive_packet.getLength());
        String[] s = s_receive.split(" ");
        BufferedReader reader = new BufferedReader(new FileReader("E:\\Git_java\\Java_Find\\customer.dat"));

        String line = reader.readLine();
        String result = "";
        if (s[0].equals("1")) {
            try {
                while (line != null) {
                    result += line + "\n";
                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (s[0].equals("2")) {
            try {
                System.out.println("Search " + line);
                while (line != null) {
                    String[] info = line.split(" ");
                    System.out.println("Read file: " + info[0]);
                    for (String a : info) {
                        // s[1] la input tu client
                        if (s[1].equalsIgnoreCase(a)) { // dat == Dat == DAt == DAT vi no ignore tat ca cac case
                            result = s[1];
                            break;
                        }
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        if (result.isEmpty()) {
            result = "Not found name!";
        }

        System.out.println("Result: \n" + result);
        byte[] sent_pack = result.getBytes();
        DatagramPacket send_packet = new DatagramPacket(sent_pack, sent_pack.length, 
        receive_packet.getAddress(), receive_packet.getPort());
        server.send(send_packet);
        server.close();
    }
}