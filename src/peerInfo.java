import java.util.BitSet;

class PeerInfo {
		final String ID;
		final String host;
		final int port;
		boolean hasFile;
		BitSet bitfield;
		boolean choked;
		boolean interested;
		//PeerHandler handler;
		
		PeerInfo(String id, String host, int port, boolean hasFile) {
			ID = id;
			this.host = host;
			this.port = port;
			this.hasFile = hasFile;
			choked = true;
			interested = false;
			//handler = null;
		}
	}