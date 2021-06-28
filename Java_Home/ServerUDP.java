package Java_Home;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerUDP {

    public static void main(String[] args) throws IOException, SocketException {
        DatagramSocket server = new DatagramSocket(9999);
        while (true) {
            File file = new File("home.dat");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            byte[] receive_data = new byte[1024];
            DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
            server.receive(receive_packet);
            String data = new String(receive_packet.getData(), 0, receive_packet.getLength());
            String[] a = data.split(" ");
            String b = reader.readLine();
            String resule = "";
            if (a[0].equals("1")) {
                resule = "Show All Info House \n";
                while (b != null) {
                    resule += b + "\n";
                    b = reader.readLine();
                }
            } else if (a[0].equals("2")) {
                if (a[2].equals("1")) {
                    System.out.println("Search by house number");
                    while (b != null) {
                        String[] s = b.split(" ");
                        if (s[0].equals(a[3])) {
                            System.out.println("Duplicate house number");
                            resule += b + "\n";
                        }
                        b = reader.readLine();
                    }
                }
                if (a[2].equals("2")) {
                    System.out.println("Search by status");
                    while (b != null) {
                        String[] s = b.split(" ");
                        if (s[2].equals(a[3])) {
                            resule += b + "\n";
                        }
                        if (!s[2].equals(a[3])) {
                            resule = "No result";
                        }
                        b = reader.readLine();
                    }
                }
            } else if (a[0].equals("3")) {
                String addHome = "";
                while (b != null) {
                    addHome += b + "\n";
                    b = reader.readLine();
                }
                addHome += a[2] + " " + a[3] + " false";
                createFile(addHome);
                resule = "Add successfully";
            } else if (a[0].equals("4")) {
                String muaNha = "";
                while (b != null) {
                    String[] s = b.split(" ");
                    if (s[0].equals(a[2])) {
                        if (s[2].equals("true")) {
                            resule = "House bought";
                        } else {
                            muaNha += s[0] + " " + s[1] + " " + "true" + "\n";
                            resule = "Bye successfully";
                        }
                    } else {
                        muaNha += b + "\n";
                    }
                    b = reader.readLine();
                }
                createFile(muaNha);
            } else if (a[0].equals("5")) {
                break;
            }
            reader.close();
            byte[] send_data = resule.getBytes();
            DatagramPacket send_packet = new DatagramPacket(send_data, send_data.length, receive_packet.getAddress(),
                    receive_packet.getPort());
            server.send(send_packet);
            reader.close();
            // server.close();
        }
    }

    public static void createFile(String s) throws IOException {
        File file = new File("home.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(s.getBytes());
        fileOutputStream.close();
    }
}
