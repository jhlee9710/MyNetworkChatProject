import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpIpServer {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;	// 서버소켓을 만듦
		
		try {
		serverSocket = new ServerSocket(7777);		// 6000번대 이상 권장사용
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {	// 서버는 24시간 돌아가야함.
			
			try {
				System.out.println("연결요청을 기다립니다.");
				Socket socket = serverSocket.accept();	// 서버의 요청정보를 넘겨서 실제 클라이언트에 속했던 소켓에 통신
				System.out.println(socket.getInetAddress() + "로 부터 연결 요청이 들어옴");	// 연결 요청한 상대의 아이피 확인
				
				OutputStream out = socket.getOutputStream();	// 스트림으로 데이터를 보낼때 
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[Notice] 접속 성공 Message from Server");		// 영어를 제외하면 write로 보낼시 글자가 깨짐 인코딩(보호)작업을 하기위해 writeUTF 사용
				System.out.println("데이터를 전송했습니다.");
				dos.close(); // 마지막에는 항상 close가 들어가야함
				
	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	}// main end{}

}
