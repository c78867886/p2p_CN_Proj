

public class NeighborPeer extends Peer {

	
	protected int sentToHostCount;
	protected int sentToHostSinceLastChokeAudit;
	protected int receivedFromHostCount;
	protected int receivedFromHostSinceLastChokeAudit;


	protected boolean chokingHost;
	protected boolean interestedInHost;
	protected boolean previousHostInterest;
	
	

	//private final Socket socket;
	
	
	
	
	
	
	
	
	NeighborPeer(String peerID, String hostname, int port, int pieceCount, boolean hasFile) {
		super(peerID, hostname, port, pieceCount, hasFile);
		
		
		chokingHost = true;

		
		
		
	}
	
	
	
	public void setChokingHost(boolean value) {
		chokingHost = value;
	}
	
	public boolean isChokingHost() {
		return chokingHost;
	}

	public void setInterestedInHost(boolean value) {
		interestedInHost = value;
	}
	
	public boolean isInterestedInHost() {
		return interestedInHost;
	}
	
	public void setPreviousHostInterest(boolean value) {
		previousHostInterest = value;
	}
	
	public boolean getPreviousHostInterest() {
		return previousHostInterest;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//should not need
	protected boolean chokedByHost;
	
	public void chokedByHost(boolean value) {
		chokedByHost = value;
	}
	
	public boolean isChokedByHost() {
		return chokedByHost;
	}
	
	
	
	
}
