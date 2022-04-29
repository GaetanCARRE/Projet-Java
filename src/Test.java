import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



//Merci a ce site pour l'aide : https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
//ainsi qu'a stackoverflow evidemment, comme toujours... !

public class Test {
	
	private static final String FILENAME = "data.xml";

	public static void main(String[] args) throws ConsommationException, CategoryException
  {
    ArrayList<Component> C = new ArrayList<Component>();
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try 
		{
			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(FILENAME));


			//On parcourt tous les composants du fichier
			NodeList list = doc.getElementsByTagName("items");

			//Pour chaque composant...
			for (int temp = 0; temp < list.getLength(); temp++) 
			{
				Node node = list.item(temp);

				if(node.getNodeType() == Node.ELEMENT_NODE) 
				{
					//On regarde le nom et la categorie du composant, et on les affiche
					Element element = (Element) node;
					//Quand le tag lu est unique pour l'objet, on peut faire ainsi
					String category = element.getElementsByTagName("category").item(0).getTextContent();
					switch(category)
					{
						case "buildings":
							String mining = element.getElementsByTagName("mining").item(0).getTextContent();
							String factory = element.getElementsByTagName("factory").item(0).getTextContent();
							if(mining.equals(null) && factory.equals(null))
								C.add(new Building(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent()));
							if((!mining.equals(null))&&(factory.equals(null)))
							{
								if(element.getElementsByTagName("value").item(0).getTextContent().equals(null)){
									C.add(new ExtractorSE(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),(int)Double.parseDouble(element.getElementsByTagName("speed").item(0).getTextContent())));
								}
								else
								{
									if(element.getElementsByTagName("category").item(0).getTextContent().equals(null))
										C.add(new CentralRE(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),element.getElementsByTagName("type").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent())));
									else //String id, String name,String t,int v, String c
										C.add(new Central(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),element.getElementsByTagName("type").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent()),element.getElementsByTagName("category").item(0).getTextContent()));
								
								}
							}
							if((mining.equals(null))&&(!factory.equals(null))){
								if(element.getElementsByTagName("speed").getLength() ==0)
									C.add(new ClassicFactory(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("usage").item(0).getTextContent()),Integer.parseInt(element.getElementsByTagName("drain").item(0).getTextContent())));
								if(element.getElementsByTagName("speed").getLength() ==1)
									C.add(new ClassicFactory(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("usage").item(0).getTextContent()),Integer.parseInt(element.getElementsByTagName("drain").item(0).getTextContent()),Double.parseDouble(element.getElementsByTagName("speed").item(0).getTextContent())));
							}
							if((!mining.equals(null))&&(!factory.equals(null)))
								C.add(new Extractor(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("usage").item(0).getTextContent()),Integer.parseInt(element.getElementsByTagName("drain").item(0).getTextContent()),Double.parseDouble(element.getElementsByTagName("speed").item(0).getTextContent())));
								
							break;
						case "resource":
							String fuel = element.getElementsByTagName("fuel").item(0).getTextContent();
							if(fuel.equals(null))
							{
								C.add(new Resource(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent()));
							}
							else
							{
								C.add(new ResourceFuel(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),element.getElementsByTagName("category").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent())));
							}
							break;
						case "components":
							if(element.getElementsByTagName("fuel").item(0).getTextContent().equals(null))
							{
								C.add(new Component(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent()));
							}
							else
							{
								C.add(new Fuel(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent()),element.getElementsByTagName("category").item(0).getTextContent()));
							}
							break;
					}
					
				}
			}
		}
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
  }
}

