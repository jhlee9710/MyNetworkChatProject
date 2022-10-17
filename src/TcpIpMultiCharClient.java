import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpIpMultiCharClient {

	public static void main(String[] args) {	
		Scanner sc = new Scanner(System.in);
		System.out.println("닉네임 입력 : ");
		String nickName = sc.next();
		
		try {
			Socket socket = new Socket("192.168.0.29",7777);
			System.out.println("서버에 연결됨");
			Thread sender = new Thread(new ClientSender(socket,nickName));
			Thread receiver = new Thread(new ClientReceiver(socket));
			
			sender.start();
			receiver.start();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
