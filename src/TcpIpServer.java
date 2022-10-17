import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpIpServer {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;	// ���������� ����
		
		try {
		serverSocket = new ServerSocket(7777);		// 6000���� �̻� ������
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {	// ������ 24�ð� ���ư�����.
			
			try {
				System.out.println("�����û�� ��ٸ��ϴ�.");
				Socket socket = serverSocket.accept();	// ������ ��û������ �Ѱܼ� ���� Ŭ���̾�Ʈ�� ���ߴ� ���Ͽ� ���
				System.out.println(socket.getInetAddress() + "�� ���� ���� ��û�� ����");	// ���� ��û�� ����� ������ Ȯ��
				
				OutputStream out = socket.getOutputStream();	// ��Ʈ������ �����͸� ������ 
				DataOutputStream dos = new DataOutputStream(out);
				
				dos.writeUTF("[Notice] ���� ���� Message from Server");		// ��� �����ϸ� write�� ������ ���ڰ� ���� ���ڵ�(��ȣ)�۾��� �ϱ����� writeUTF ���
				System.out.println("�����͸� �����߽��ϴ�.");
				dos.close(); // ���������� �׻� close�� ������
				
	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	}// main end{}

}
