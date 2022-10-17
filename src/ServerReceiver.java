import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class ServerReceiver extends Thread {
	//Ŭ���̾�Ʈ�� ��û ������ �޾Ƽ� ������ Ŭ���̾�Ʈ ������ ���� ������ ����
	
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
	
	void sendToAll(String msg) {	// ��� Ŭ���̾�Ʈ�� �޼��� ����
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
			sendToAll("a"+name+"���� �����̽��ϴ�.");
		
			clients.put(name, out);
			System.out.println("���� ���� ������ ����" + clients.size() + "�Դϴ�");
		
			while(in != null) {
				sendToAll(in.readUTF());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			sendToAll("a"+name+"���� �����̽��ϴ�.");
			clients.remove(name);
			System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"���� ����");
			System.out.println("���� ���� ������ ����  " + clients.size() +" �Դϴ�.");
		}
	}
	
	
	

}
