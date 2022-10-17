import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
/*
public class TcpIpMultiCharServer {

	
//	HashMap clients;	// k: ����� ���̵�		v: stream����	
	
	public TcpIpMultiCharServer() {
//		clients = new HashMap();
//		Collections.synchronizedMap(clients);	// ����ȭ ���� ������ �����尡 ������ ����Ŵ
	}
	
	public void start() {	// ���� ����
		ServerSocket serverSocket = null;
		Socket socket = null;	
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("���� ���۵�");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "���� �����߽��ϴ�.");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
	public static void main(String[] args) {
		
		new TcpIpMultiCharServer().start();
		
		
		
	}// main end()

}// class end()
*/
	
	import java.net.*;
	import java.io.*;
	import java.util.*;

	public class TcpIpMultiCharServer {
	   HashMap clients;
	   
	   TcpIpMultiCharServer() {
	      clients = new HashMap();
	      Collections.synchronizedMap(clients);
	   }

	   public void start() {
	      ServerSocket serverSocket = null;
	      Socket socket = null;

	      try {
	         serverSocket = new ServerSocket(7777);
	         System.out.println("������ ���۵Ǿ����ϴ�.");

	         while(true) {
	            socket = serverSocket.accept();
	            System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+"���� �����Ͽ����ϴ�.");
	            ServerReceiver thread = new ServerReceiver(socket);
	            thread.start();
	         }
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	   } // start()

	   void sendToAll(String msg) {
	      Iterator it = clients.keySet().iterator();
	      
	      while(it.hasNext()) {
	         try {
	            DataOutputStream out = (DataOutputStream)clients.get(it.next());
	            out.writeUTF(msg);
	         } catch(IOException e){}
	      } // while
	   } // sendToAll

	   public static void main(String args[]) {
	      new  TcpIpMultiCharServer().start();
	   } 
	   class ServerReceiver extends Thread {
	      Socket socket;
	      DataInputStream in;
	      DataOutputStream out;

	      ServerReceiver(Socket socket) {
	         this.socket = socket;
	         try {
	            in  = new DataInputStream(socket.getInputStream());
	            out = new DataOutputStream(socket.getOutputStream());
	         } catch(IOException e) {}
	      }

	      public void run() {
	         String name = "";
	         try {
	            name = in.readUTF();
	            sendToAll("#"+name+"���� �����̽��ϴ�.");

	            clients.put(name, out);
	            System.out.println("���� ���������� ���� "+ clients.size()+"�Դϴ�.");

	            while(in!=null) {
	               sendToAll(in.readUTF());
	            }
	         } catch(IOException e) {
	            // ignore
	         } finally {
	            sendToAll("#"+name+"���� �����̽��ϴ�.");
	            clients.remove(name);
	            System.out.println("["+socket.getInetAddress() +":"+socket.getPort()+"]"+"���� ������ �����Ͽ����ϴ�.");
	            System.out.println("���� ���������� ���� "+ clients.size()+"�Դϴ�.");
	         } // try
	      } // run
	   } // ReceiverThread
	} // class