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
import java.util.Timer;
import java.util.TimerTask;


class Response {
	
	public boolean choke;
	private TimerTask timeout;
	
	public void init() {
		Timer timer = new Timer();
		timeout = new TimeOutTask();
		timer.schedule(timeout, 2000L);
	}
	
	public void invoke() {
		Timer timer = new Timer();
		timeout.cancel();
		timeout = new TimeOutTask();
		timer.schedule(timeout, 2000L);
	}
}

class TimeOutTask extends TimerTask {
	
	void destroy() {
			
	}
	public void run() {
		// TODO Auto-generated method stub
		destroy();
	}
	
}

public abstract class peerExecute implements Runnable{
	peerProcess currentPeer;
	int downloadSpeed = 0;
	boolean choked = true;
	boolean alreadySentInterested = false;
	boolean receivedUnchoked = false;
	boolean end = false;
	BitSet bitfield;
	
	peerExecute(peerProcess currentPeer, int goalID, Socket socket) throws IOException, InterruptedException {
		this.currentPeer = currentPeer;
		this.goalID = goalID;
		peerInfo = currentPeer.peerInfos.get(goalID);
		this.socket = socket;
		inStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
	}
	
		return false;
	}
	
	//--
	public void Timer(boolean receivedUnchoked) {
			return;
		}
		
		TimerTask task = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
	}
	
}