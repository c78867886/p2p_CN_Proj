
import java.util.BitSet;

public class Peer {
	
	protected final String peerID;
	protected final String hostname;
	protected final int port;
	protected final int pieceCount;
	protected BitSet pieceStatus;		//bit representation of file piece completion status
		
	Peer(String peerID, String hostname, int port, int pieceCount, boolean hasFile) {
		this.peerID = peerID;
		this.hostname = hostname;
		this.port = port;
		this.pieceCount = pieceCount;
		pieceStatus = new BitSet(pieceCount);
		if (hasFile) {
			pieceStatus.set(0, pieceCount);
		}		
	}
		
	public String getPeerID() {
		return peerID;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public int getPort() {
		return port;
	}
	
	public int getPieceCount() {
		return pieceCount;
	}

	public BitSet getPieceStatus() {
		return pieceStatus;
	}
	
	public byte[] getPieceStatusAsBitfield() {
		//P2P protocol bitfield uses big-endian. Need to switch.
		return switchBitEndian(pieceStatus.toByteArray());
	}
	
	public void setPieceStatus(byte[] bitfield) {
		//P2P protocol bitfield uses big-endian. Need to switch.
		pieceStatus = BitSet.valueOf(switchBitEndian(bitfield));
	}

	public void markPieceComplete(int pieceIndex) {
		pieceStatus.set(pieceIndex);
	}
		
	public boolean hasFile() {
		return pieceStatus.cardinality() == pieceCount;
	}
	
	private byte[] switchBitEndian(byte[] bytes) {
		byte b;
		for (int i = 0; i < bytes.length; i++) {
			b = bytes[i];
			bytes[i] = (byte)((b & 0b00000001) << 7 | (b & 0b00000010) << 5 | (b & 0b00000100) << 3 | (b & 0b00001000) << 1 | (b & 0b00010000) >> 1 | (b & 0b00100000) >> 3 | (b & 0b01000000) >> 5 | (b & 0b10000000) >> 7);
		}
		return bytes;
	}
	
}
