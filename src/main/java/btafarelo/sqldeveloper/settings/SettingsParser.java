package btafarelo.sqldeveloper.settings;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SettingsParser {
	
	private DocumentBuilder builder;
	
	private XPath xpath;
	
	public SettingsParser() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		xpath = xPathfactory.newXPath();		
	}

	public SettingsVO parser(InstallationVO installation) throws Exception {
		SettingsVO result = new SettingsVO();
		
		parseProductPreference(installation.getProductPreference(), result);
		
		parseConnections(installation.getConnection(), result);
		
		return result;
	}
	
	private void parseProductPreference(File file, SettingsVO setting) throws Exception {
		Document doc = builder.parse(file);
		XPathExpression expr = xpath.compile("//value[@n='db.system.id']/@v");
		
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		
		String systemId = nodes.item(0).getNodeValue();
		
		setting.setSystemId(systemId);
	}
	
	private void parseConnections(File file, SettingsVO setting) throws Exception {
		Document doc = builder.parse(file);
		XPathExpression expr = xpath.compile("//StringRefAddr[@addrType='password']/Contents");

		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		
		ConnectionVO connection = null;
		
		for (int i = 0; i < nodes.getLength(); i++) {
			
			connection = new ConnectionVO();
			
			Element e = (Element) nodes.item(i);

			String name = ((Element) e.getParentNode().getParentNode().getParentNode()).getAttribute("name");
			
			expr = xpath.compile("//Reference[@name='" + name + "']/RefAddresses/StringRefAddr[@addrType='customUrl']/Contents");
			
			String customUrl = (String) expr.evaluate(doc, XPathConstants.STRING);

			connection.setName(name);

			connection.setUrl(customUrl);

			connection.setPassword(e.getTextContent());

			setting.getConnections().add(connection);
		}		
	}

}
