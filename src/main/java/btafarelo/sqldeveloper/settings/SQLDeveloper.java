package btafarelo.sqldeveloper.settings;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class SQLDeveloper {

	private File sqlDevDir;
	
	private File[] versions;
	
	public SQLDeveloper() {
		sqlDevDir = new File(System.getProperty("user.home") + "/.sqldeveloper");
	}
	
	public List<InstallationVO> listInstallations() {
		List<InstallationVO> result = new ArrayList<InstallationVO>();
		
		versions = sqlDevDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.matches("system[0-9\\.]+");
			}
		});
		
		
		InstallationVO installation = null;
		
		for(File f : versions) {
			installation = new InstallationVO();
			
			installation.setVersion(f.getName());
			
			installation.setConnection(getFile("o.jdeveloper.db.connection[0-9\\.]+", "connections.xml"));
			
			installation.setProductPreference(getFile("o.sqldeveloper[0-9\\.]+", "product-preferences.xml"));
			
			result.add(installation);
		}
		
		return result;
	}

	private File getFile(final String string, final String string2) {
		return versions[0].listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.matches(string); 
			}
		})[0].listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.equals(string2);
			}
		})[0];
	}
}
