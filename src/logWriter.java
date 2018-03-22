import java.io.*;
import java.util.*;
import java.text.*;

public class logWriter {
	BufferedWriter Log;
	int currentPeerId;
	
	// Constructor
	public logWriter(int peerID) throws IOException {
		currentPeerId = peerID;
		String filename = "log_peer_" + peerID + ".log";
		Log = new BufferedWriter(new FileWriter(filename,true));
	}
	
	private static String now() {
		SimpleDateFormat time;
		time = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]: ", Locale.ENGLISH);	
		return time.format(new Date());
	}
	
	public  void connect(int connection_id) throws IOException{
		String message="["+now()+"]: Peer "+currentPeerId+" makes a connection to Peer "+connection_id+".\n";

		System.out.println(message);
		
		Log.write(message);
	}
	
	
}