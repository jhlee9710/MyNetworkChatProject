import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpIpClient {
	
	public static void main(String args[]){
		
		System.out.println("서버에 연결합니다.");
		try {
			Socket socket = new Socket("192.168.0.76",7777);
			InputStream in = socket.getInputStream();	// 스트림으로 데이터를 받을때
			DataInputStream dis = new DataInputStream(in); 
			
			System.out.println("서버로부터 받는 메세지" + dis.readUTF()); //서버가 아웃스트림으로 보낸 데이터를 받아옴
			System.out.println("연결 종료");
			dis.close(); // 마지막에 서버 닫기
			socket.close(); // 서버와 부터 연결이 끊김
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
