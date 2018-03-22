import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.lang.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.BitSet;

public class peerProcess {
	static final String peerInfoCfgPath = "src/PeerInfo.cfg";
	static final String commonCfgPath = "src/Common.cfg";
	
	HashMap<Integer, PeerInfo> peerInfos;
	ArrayList<Integer> peerIdList;
	ArrayList<Integer> previousPeersID;
	int numPreNeigh;
	int unchokeingInterval;
	int optUnchokeInterval;
	int fileSize;
	int pieceSize;
	int pieceNum;
	String fileName;
	final int peerID;
	BitSet bitfield;
	PeerInfo currentPeer;
	
	public static void main(String[] args) {
		//checkValidPeerId(args);
		
		peerProcess currentPeer;
		
		try {
			//currentPeer = new peerProcess(args[0]);
			currentPeer = new peerProcess("1001");
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
	}
	
	peerProcess(String arg) throws FileNotFoundException, IOException {
		// Read Common.cfg file and initialize some parameters
		BufferedReader commonCfgReader = new BufferedReader(new FileReader(commonCfgPath));
		try {
			String tempLine;
			String[] tempSplit;
			tempLine = commonCfgReader.readLine();
			tempSplit= tempLine.split(" ");
			numPreNeigh = Integer.parseInt(tempSplit[1]);
			
			tempLine = commonCfgReader.readLine();
			tempSplit= tempLine.split(" ");
			unchokeingInterval = Integer.parseInt(tempSplit[1]);
			
			tempLine = commonCfgReader.readLine();
			tempSplit= tempLine.split(" ");
			optUnchokeInterval = Integer.parseInt(tempSplit[1]);
			
			tempLine = commonCfgReader.readLine();
			tempSplit= tempLine.split(" ");
			fileName = tempSplit[1];
			
			tempLine = commonCfgReader.readLine();
			tempSplit= tempLine.split(" ");
			fileSize = Integer.parseInt(tempSplit[1]);
			
			tempLine = commonCfgReader.readLine();
			tempSplit= tempLine.split(" ");
			pieceSize = Integer.parseInt(tempSplit[1]);
			
			pieceNum = (int) Math.ceil((float) fileSize /(float) pieceSize);
			
			System.out.printf("%d, %d, %d, %s, %d, %d, %d\n",numPreNeigh, unchokeingInterval, optUnchokeInterval, fileName, fileSize, pieceSize, pieceNum);
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot find Common.cfg!");
			throw e;
		}
		catch(IOException e) {
			System.out.println("Cannot read Common.cfg!");
			throw e;
		}
		finally {
			commonCfgReader.close();
		}
		
		
		BufferedReader peerInfoCfgReader = new BufferedReader(new FileReader(peerInfoCfgPath));
		try {
			peerID = Integer.parseInt(arg);
			peerInfos = new HashMap<Integer, PeerInfo>();
			peerIdList = new ArrayList<Integer>();
			previousPeersID = new ArrayList<Integer>();
			
			// info for each peer
			String tempLine;
			
			while ((tempLine = peerInfoCfgReader.readLine()) != null) {
				String peerInfo[] = tempLine.split(" ");
				
				PeerInfo newPeer;
				String peerid = peerInfo[0];
				String host = peerInfo[1];
				int port = Integer.parseInt(peerInfo[2]);
				boolean hasCompleteFile;
				
				if(Integer.parseInt(peerInfo[3]) == 1)
					hasCompleteFile = true;
				else
					hasCompleteFile = false;
				
				newPeer = new PeerInfo(peerid, host, port, hasCompleteFile);
				System.out.printf("%s, %s, %d, %b\n",peerid, host, port, hasCompleteFile);
				
				peerInfos.put(Integer.parseInt(peerInfo[0]), newPeer);
				
				if(peerID==Integer.parseInt(peerInfo[0])) currentPeer=new PeerInfo(peerid, host, port, hasCompleteFile);
			}
			
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot find " + peerInfoCfgPath + ".");
			throw e;
		}
		catch(IOException e) {
			System.out.println("Failed to read " + peerInfoCfgPath + ".");
			throw e;
		}
		finally {
			peerInfoCfgReader.close();
		}
		
		int bitsetlength = pieceNum;
		bitfield=new BitSet(bitsetlength);
		
		if(currentPeer.hasFile){
			setBitfield(bitfield, bitsetlength, true);
		}
		else{
			setBitfield(bitfield, bitsetlength, false);
		}
		
		
	}
	
	private static void checkValidPeerId(String[] peerId) {
		if(peerId.length != 1) {
            System.out.println("Invalid peer ID!");
            return;
        }
	}
	
	private static void setBitfield(BitSet bitfield, int bitsetlength, boolean bool) {
		for(int i=0;i<bitsetlength;i++){
			bitfield.set(i, bool);
		}
	}
	
}