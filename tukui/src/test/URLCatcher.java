package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class URLCatcher {
	public static void main(String[] args) throws  SAXException, ParserConfigurationException, IOException {
		InputStreamReader t = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(t);
		 System.out.println("5ケタの路線コードを入力してください");
		 String str = br.readLine();  
		 //11302
		 //30015
		 //http://www.ekidata.jp/api/l/(路線コード).xml
		 
		URL url = new URL("http://www.ekidata.jp/api/l/"+ str + ".xml");
		URLConnection con = url.openConnection();
		InputStream is = con.getInputStream();
	
		Document document= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		//ルート
		Element elementRoot = document.getDocumentElement();
		
		Element elementTitle = (Element) elementRoot.getElementsByTagName("line_name").item(0);
		String title = elementTitle.getFirstChild().getNodeValue();
		System.out.println(title + "\r");
		
		
		NodeList localNodeList =
				 		elementRoot.getElementsByTagName("station");
		for (int i = 0; i < localNodeList.getLength(); i++) {
			Element elementItem = (Element) localNodeList.item(i);
			 Element elementItemName = (Element) elementItem.getElementsByTagName("station_name").item(0);
			 String itemName = elementItemName.getFirstChild().getNodeValue();
			 System.out.println(itemName);
			}
	}
}