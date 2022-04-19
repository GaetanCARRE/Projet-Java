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




//Merci a ce site pour l'aide : https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
//ainsi qu'a stackoverflow evidemment, comme toujours... !

public class MainTest {
	
	private static final String FILENAME = "data.xml";

	public static void main(String[] args)
  {
    ArrayList<Component> C = new ArrayList<Component>()
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
              
              break;
            case "resource":
              if(element.getElementsByTagNames("fuel").item(0).getTextContent().equals(null))
              {
                C.add(new Resource(element.getElementsByTagNames("name").item(0).getTextContent(),element.getElementsByTagNames("fuel").item(0).getTextContent())
              }
              else
              {}
              break;
            case "components":
              if(element.getElementsByTagNames("fuel").item(0).getTextContent().equals(null))
              {
                C.add(new Component(element.getElementsByTagNames("id").item(0).getTextContent());
              }
              break;
            case "
          }
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					System.out.println(category + " -- " + name);
				}
			}
		}
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
  }
}
