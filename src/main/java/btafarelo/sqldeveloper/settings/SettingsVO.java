package btafarelo.sqldeveloper.settings;

import java.util.ArrayList;
import java.util.List;

public class SettingsVO {

	private String systemId;
	
	private List<ConnectionVO> connections;

	public SettingsVO() {
		connections = new ArrayList<ConnectionVO>();
	}
	
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public List<ConnectionVO> getConnections() {
		return connections;
	}

	public void setConnections(List<ConnectionVO> connections) {
		this.connections = connections;
	}
}
