import java.net.Socket;

import java.io.DataInputStream;
import java.io.IOException;

public class ClientReceiver extends Thread {
	
	Socket socket;
	DataInputStream in;
	
	public ClientReceiver(Socket socket) {
		super();
		this.socket = socket;
		
		try {
			in = new DataInputStream(socket.getInputStream());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(in != null) {
			try {
				System.out.println(in.readUTF());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

}
