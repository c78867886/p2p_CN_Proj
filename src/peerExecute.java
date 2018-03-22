import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;



public abstract class peerExecute implements Runnable{
	final peerProcess currentPeer;
	final int goalID;
	final PeerInfo peerInfo;
	final Socket socket;
	final DataInputStream inStream;
	final DataOutputStream outStream;
	int downloadSpeed = 0;
	boolean choked = true;
	boolean alreadySentInterested = false;
	boolean end = false;
	
	peerExecute(peerProcess currentPeer, int goalID, Socket socket) throws IOException, InterruptedException {
		this.currentPeer = currentPeer;
		this.goalID = goalID;
		peerInfo = currentPeer.peerInfos.get(goalID);
		this.socket = socket;
		inStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
	}
}