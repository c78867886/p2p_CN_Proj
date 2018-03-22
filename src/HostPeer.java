

import java.util.BitSet;
import java.util.Random;





public class HostPeer extends Peer {

	
	
	
	
	
	
	HostPeer(String peerID, String hostname, int port, int pieceCount, boolean hasFile) {
		super(peerID, hostname, port, pieceCount, hasFile);
		
		
		
		
	}
	
	
	
	
	
	
	//Returns true if this host peer is interested in the neighbor peer.
	public boolean isInterested(NeighborPeer neighborPeer) {
		BitSet bitSet = (BitSet)neighborPeer.getPieceStatus().clone();
		bitSet.andNot(pieceStatus);
		return !bitSet.isEmpty();
	}

	
	
	
	
	
	
	
	//Returns a piece index for this host peer to request from the neighbor peer.
	//Returns -1 if host peer has no interest in the neighbor peer.
	public int findNextPieceIndex(NeighborPeer neighborPeer) {
		BitSet bitSet = (BitSet)neighborPeer.getPieceStatus().clone();
		bitSet.andNot(pieceStatus);
		if (bitSet.isEmpty()) {
			return -1;
		}

		//BitSet toString() returns a string like: {1, 2, 4, 5, 9, 10, 11, 12}
		String pieceIndexString = bitSet.toString();
		String[] pieceIndexStringArray = pieceIndexString.substring(1, pieceIndexString.length() - 1).split(", ");
		//Randomly select a piece index for host peer to request. This could avoid requesting the same piece from multiple neighbor peers.
		int arrayIndex = new Random().nextInt(pieceIndexStringArray.length);
		return Integer.parseInt(pieceIndexStringArray[arrayIndex]);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Temporarily put here.

	
	public static enum MessageType {
		HANDSHAKE("P2PFILESHARINGPROJ"),
		CHOKE("0"),
		UNCHOKE("1"),
		INTERESTED("2"),
		NOT_INTERESTED("3"),
		HAVE("4"),
		BITFIELD("5"),
		REQUEST("6"),
		PIECE("7");
		
		private final String string;
		
		MessageType(String string) {
			this.string = string;
		}
		
		public String getText() {
			return string;
		}


		
		
		
		
	}
	
	
	public void peerListener() {

		MessageType messageType = MessageType.CHOKE;
		
		HostPeer hostPeer = new HostPeer("temp", "localhost", 12345, 200, false);
		NeighborPeer neighborPeer = new NeighborPeer("temp", "localhost", 12345, 200, false);

		
		int pieceIndex = 111;
		
		
		
		
		//only for logic
		switch(messageType) {
		case HANDSHAKE://may not put here
			//establish connection, use Peer ID to create neighbor peer			
			//send BITFIELD
			//send CHOKE
			
			break;
		case CHOKE:
			//neighbor peer setChokingHost to true
			neighborPeer.setChokingHost(true);
			
			break;
		case UNCHOKE:
			//neighbor peer setChokingHost to false
			neighborPeer.setChokingHost(false);

			int indexToRequest = hostPeer.findNextPieceIndex(neighborPeer);
			if (indexToRequest == -1) {
				//send NOT_INTERESTED
			} else {
				//send REQUEST
			}

			
			break;
		case INTERESTED:
			//neighbor peer setInterestedInHost to true
			neighborPeer.setInterestedInHost(true);

			break;
		case NOT_INTERESTED:
			//neighbor peer setInterestedInHost to false
			//better use a list to store
			neighborPeer.setInterestedInHost(false);

			break;
		case HAVE:
			//read piece index from pay load and update

			neighborPeer.markPieceComplete(pieceIndex);
			//if host peer previously had no interest and now has interest
			if (!neighborPeer.getPreviousHostInterest() && hostPeer.isInterested(neighborPeer)) {
				neighborPeer.setPreviousHostInterest(true);
				//send INTERESTED
			}
			
			
			break;
		case BITFIELD:
			//get the bitfield pay load
			byte[] bitfield = new byte[10];
			//set neighbor pieceStatus
			neighborPeer.setPieceStatus(bitfield);
			//calculate if interested
			boolean interest = hostPeer.isInterested(neighborPeer);
			neighborPeer.setPreviousHostInterest(interest);
			if (interest) {
				//send INTERESTED
			} else {
				//send NOT_INTERESTED
			}

			
		
			break;
		case REQUEST:
			if (!neighborPeer.isChokedByHost()) {
				//send PIECE
			}
			
			break;
		case PIECE:
			//write data piece to file
			//update host piece status
			hostPeer.markPieceComplete(pieceIndex);
			//send HAVE to all neighbor peers

			//iterate though all neighbor peers			
			if (neighborPeer.getPreviousHostInterest() && !hostPeer.isInterested(neighborPeer)) {
				neighborPeer.setPreviousHostInterest(false);
				//send NOT_INTERESTED
			}

			//send REQUEST if host still has interest in the neighbor
			if (hostPeer.isInterested(neighborPeer)) {
				//send REQUEST
			}
			
			
			break;
			
		default:
			break;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	//about file writer
	
	public class FileHandler {

		String fileName;
		
		//private RandomAccessFile file;
		
		
		FileHandler (String fileName, long fileLength) {
			
			
			
			
			
		}
		
		
		
		
		public byte[] readPiece(int pieceIndex) {
			
			
			
			
			return new byte[5];
			
			
		}
		
		//Returns length of written data; returns -1 if writing is not successful.
		public int writePiece(int pieceIndex, byte[] bytes) {
			
			
			
			return -1;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
