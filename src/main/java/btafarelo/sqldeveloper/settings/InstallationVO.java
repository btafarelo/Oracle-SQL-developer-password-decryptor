package btafarelo.sqldeveloper.settings;

import java.io.File;

public class InstallationVO {

	private String version;
	
	private File connection;
	
	private File productPreference;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public File getConnection() {
		return connection;
	}

	public void setConnection(File connection) {
		this.connection = connection;
	}

	public File getProductPreference() {
		return productPreference;
	}

	public void setProductPreference(File productPreference) {
		this.productPreference = productPreference;
	}
}
