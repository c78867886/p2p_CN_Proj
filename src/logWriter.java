import java.io.*;
import java.util.*;
import java.text.*;

public class logWriter {
	BufferedWriter Log;
	int currentPeerId;
	boolean showLogOnConsole = false;
	
	// Constructor init
	public logWriter(int peerID) throws IOException {
		currentPeerId = peerID;
		
		String filename = "log_peer_" + currentPeerId + ".log";
		
		Log = new BufferedWriter(new FileWriter(filename,true));
	}
	
	private static String now() {
		SimpleDateFormat now;
		
		now = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]: ", Locale.ENGLISH);	
		
		return now.format(new Date());
	}
	
	public  void logConnect(int goalPeerId) throws IOException{
		String message="["+now()+"]: Peer ["+currentPeerId+"] makes a connection to Peer ["+goalPeerId+"].\n";

		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
	
	public  void logConnected(int goalPeerId) throws IOException{
		String message;
		
		message = "["+now()+"]: Peer ["+currentPeerId+"] is connected from Peer ["+goalPeerId+"].\n";
		
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
	
	public  void logChangePreferredNeighbors(ArrayList<Integer> preferNeighborList) throws IOException{
		String message;
		
		message = "["+now()+"]: Peer ["+currentPeerId+"] has the preferred neighbors "; 
		
		if(preferNeighborList.isEmpty()) {
			message = message + "of None";
		}else {
			for(int i = 0; i < preferNeighborList.size() - 1; i++)
				message += preferNeighborList.get(i) + ",";
			message += preferNeighborList.get(preferNeighborList.size() - 1);
		}
		message += ".";
		
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
	
	public void logChangeOptimisticNeighbor (int goalPeerId) throws IOException {
		String message;
		
		message="["+now()+"]: Peer ["+currentPeerId+"] has the optimistically unchoked neighbor [" + goalPeerId+"].\n";
		
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
	
	public  void logUnchoking(int goalPeerId) throws IOException{
		String message;
		
		message="["+now()+"]: Peer ["+currentPeerId+"] is unchoked by ["+goalPeerId+"]. \n";
		
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
	
	public  void logChoking(int goalPeerId) throws IOException{
		String message;
		
		message = "["+now()+"]: Peer ["+currentPeerId+"] is unchoked by ["+goalPeerId+"]. \n";
		
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
	
	public  void logRcvHave (int goalPeerId,int piece_index) throws IOException{
		String message;
		
		message = "["+now()+"]: Peer ["+currentPeerId+"] received the have message from ["+goalPeerId+"] for the piece ["+piece_index+"].\n ";
		
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
	
	public  void logRcvInterested(int goalPeerId) throws IOException{
		String message;
		
		message = "["+now()+"]: Peer ["+currentPeerId+"] received the interested message from ["+goalPeerId+"].\n ";
		
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}

	public  void logRcvnotinterested(int goalPeerId) throws IOException{
		String message;
		
		message = "["+now()+"]: Peer ["+currentPeerId+"] received the not interested message from ["+goalPeerId+"]. \n";
		
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
	
	public  void logDownloadPiece(int piece_index,int goalPeerId) throws IOException{
		String message;
		
		message = "["+now()+"]: Peer ["+currentPeerId+"] has downloaded the piece ["+piece_index+"] from ["+goalPeerId+"]. \n";
	    
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
	
	public  void completeofdl() throws IOException{
		String message;
		
		message = "["+now()+"]: Peer ["+currentPeerId+"] has downloaded the complete file.\n";
	    
		if (showLogOnConsole) {
			System.out.println(message);
		}
		
		Log.write(message);
	}
}