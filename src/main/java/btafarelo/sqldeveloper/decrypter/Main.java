package btafarelo.sqldeveloper.decrypter;

import java.util.List;

import btafarelo.sqldeveloper.settings.ConnectionVO;
import btafarelo.sqldeveloper.settings.InstallationVO;
import btafarelo.sqldeveloper.settings.SQLDeveloper;
import btafarelo.sqldeveloper.settings.SettingsParser;
import btafarelo.sqldeveloper.settings.SettingsVO;

public class Main {

	public static void main(String[] args) throws Exception {
		SQLDeveloper sqldeveloper = null;
		
		if (args.length == 1)
			sqldeveloper = new SQLDeveloper(args[0]);
		else
			sqldeveloper = new SQLDeveloper();
			
		List<InstallationVO> installations = sqldeveloper.listInstallations();
		
		for(InstallationVO i : installations) {
			System.out.println(i.getVersion());
		
			SettingsVO settings = new SettingsParser().parser(i);
			
			System.out.println("SystemId: \t" + settings.getSystemId());

			System.out.println("\t Connections");
			System.out.println("\t ================================================");
			
			for(ConnectionVO c : settings.getConnections()) {
				System.out.println("\t Name:\t" + c.getName());
				System.out.println("\t Url:\t" + c.getUrl());
				System.out.println("\t Password:\t" + c.getPassword());
				System.out.println("\t Password:\t" + new String(Decrypt_V4.decrypt_v4(c.getPassword().getBytes(), settings.getSystemId().getBytes())));
				System.out.println();
			}
		}
	}
}
