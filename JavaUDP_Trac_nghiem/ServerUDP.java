package JavaUDP_Trac_nghiem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerUDP {
    private static ArrayList<String> b = new ArrayList<>();
    private static ArrayList<String> c = new ArrayList<>();

    public static void main(String[] args) throws IOException, SocketException, UnknownHostException {
        DatagramSocket server = new DatagramSocket(9999);

        byte[] receive_data = new byte[1024];
        DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
        server.receive(receive_packet);
        String data = new String(receive_packet.getData(), 0, receive_packet.getLength());

        String[] a = data.split(" ");
        String menu = "";
        String question = "";
        String option = "";
        String result = "";

        menu = a[0];
        question = a[1];
        option = a[2];
        
        if (menu.equals("read")) {
            result = readData(question);
        } else if (menu.equals("check")) {
            result = check(question, option);
        }
        byte[] sent_data = new byte[1024];
        DatagramPacket send_packet = new DatagramPacket(sent_data, sent_data.length, receive_packet.getAddress(), receive_packet.getPort());
        server.send(send_packet);

    }

    public void readFile() {
        try {
            File file = new File("tracnghiem.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str = reader.readLine();
            while(str != null)
            {
                String[] a = str.split(" ");
                b.add(a[1]);
                c.add(a[2]);
                str = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readData(String question) {
        String data = "";
        data = b.get(Integer.parseInt(question));
        return data;
    }

    public static String check(String question, String option) {
        if (option.trim().equals(c.get(Integer.parseInt(question)))) {
            return "Result True, Good luck";
        }
        return "Result false, you bad";
    }
}
