import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class ServerReceiver extends Thread {
	//클라이언트의 요청 정보를 받아서 나머지 클라이언트 쪽으로 값을 보내는 역할
	
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	HashMap clients;
	
	public ServerReceiver(Socket socket) {
		super();
		this.socket = socket;
		
		clients = new HashMap();
		Collections.synchronizedMap(clients);
		
		
		try {
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	void sendToAll(String msg) {	// 모든 클라이언트에 메세지 전달
		Iterator it = clients.keySet().iterator();
		
		while(it.hasNext()) {
				DataOutputStream out = (DataOutputStream)clients.get(it.next());
				try {
					out.writeUTF(msg);
				}catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
	
	

	@Override
	public void run() {
		String name = "";
		try {
			name = in.readUTF();
			sendToAll("a"+name+"님이 들어오셨습니다.");
		
			clients.put(name, out);
			System.out.println("현재 서버 접속자 수는" + clients.size() + "입니다");
		
			while(in != null) {
				sendToAll(in.readUTF());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			sendToAll("a"+name+"님이 나가셨습니다.");
			clients.remove(name);
			System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"에서 접속");
			System.out.println("현재 서버 접속자 수는  " + clients.size() +" 입니다.");
		}
	}
	
	
	

}
