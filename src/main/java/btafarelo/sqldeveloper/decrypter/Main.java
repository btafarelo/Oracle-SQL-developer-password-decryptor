package btafarelo.sqldeveloper.decrypter;

import java.util.List;

import btafarelo.sqldeveloper.settings.ConnectionVO;
import btafarelo.sqldeveloper.settings.InstallationVO;
import btafarelo.sqldeveloper.settings.SQLDeveloper;
import btafarelo.sqldeveloper.settings.SettingsParser;
import btafarelo.sqldeveloper.settings.SettingsVO;

public class Main {

	public static void main(String[] args) throws Exception {
		List<InstallationVO> installations = new SQLDeveloper().listInstallations();
		
		for(InstallationVO i : installations) {
			System.out.println(i.getVersion());
		
			SettingsVO settings = new SettingsParser().parser(i);
			
			System.out.println("\t" + settings.getSystemId());
			
			for(ConnectionVO c : settings.getConnections()) {
				System.out.println("\t\t" + c.getName());
				System.out.println("\t\t" + c.getUrl());
				System.out.println("\t\t" + c.getPassword());
				System.out.println("\t\t" + Decrypt_V4.decrypt_v4(c.getPassword().getBytes(), settings.getSystemId().getBytes()));
				System.out.println();
			}
		}
	}
}
