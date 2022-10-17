import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{
	Socket socket;
	DataOutputStream out;
	String name;
	
	public Sender(Socket socket) {
		super();
		this.socket = socket;
		
		try {
			out = new DataOutputStream(socket.getOutputStream());
			name = "[" + socket.getInetAddress() + ":" + socket.getPort() + "]";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		
		while(out != null) {
			try {
				out.writeUTF(name + sc.nextLine());
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	

}
