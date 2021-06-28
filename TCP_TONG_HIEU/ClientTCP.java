package TCP_TONG_HIEU;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);
        DataOutputStream send_data = new DataOutputStream(client.getOutputStream());
        DataInputStream receive_data = new DataInputStream(client.getInputStream());
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        send_data.writeInt(a);
        send_data.writeInt(b);
        System.out.println("result : " + receive_data.readInt());
        send_data.close();
        receive_data.close();
    }   
}
