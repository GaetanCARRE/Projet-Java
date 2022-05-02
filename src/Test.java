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
		ArrayList<Building> B = new ArrayList<Building>();
		ArrayList<Recette> R = new ArrayList<Recette>();
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
							//String mining = element.getElementsByTagName("mining").item(0).getTextContent();
							//String factory = element.getElementsByTagName("factory").item(0).getTextContent();
							if(element.getElementsByTagName("mining").getLength() == 0 && element.getElementsByTagName("factory").getLength() == 0)
								C.add(new Building(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent()));
							if((!(element.getElementsByTagName("mining").getLength() == 0))&&(element.getElementsByTagName("factory").getLength() == 0))
							{
								C.add(new ExtractorSE(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),(int)Double.parseDouble(element.getElementsByTagName("speed").item(0).getTextContent())));															
							}
							if((element.getElementsByTagName("mining").getLength() == 0)&&(!(element.getElementsByTagName("factory").getLength() == 0))){
								if(element.getElementsByTagName("value").getLength()==0)
								{							
									int drain, usage;
									if(element.getElementsByTagName("usage").getLength() != 0)
										usage = Double.parseDouble(element.getElementsByTagName("usage").item(0).getTextContent());
									else
										usage = 0;
									if(element.getElementsByTagName("drain").getLength() != 0)
										drain = Double.parseDouble(element.getElementsByTagName("drain").item(0).getTextContent());
									else
										drain = 0;
									if(element.getElementsByTagName("speed").getLength() ==0)
										C.add(new ClassicFactory(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(), usage, drain));
									if(element.getElementsByTagName("speed").getLength() ==1)
										C.add(new ClassicFactory(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(), usage, drain,Double.parseDouble(element.getElementsByTagName("speed").item(0).getTextContent())));
								}
								else
								{
									if(element.getElementsByTagName("category").getLength() == 1)
										C.add(new CentralRE(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),element.getElementsByTagName("type").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent())));
									else //String id, String name,String t,int v, String c
										C.add(new Central(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),element.getElementsByTagName("type").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent()),element.getElementsByTagName("category").item(1).getTextContent()));
								
								}
							}
							if((!(element.getElementsByTagName("mining").getLength() == 0))&&(!(element.getElementsByTagName("factory").getLength() == 0)))
								C.add(new Extractor(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("usage").item(0).getTextContent()),Integer.parseInt(element.getElementsByTagName("drain").item(0).getTextContent()),Double.parseDouble(element.getElementsByTagName("speed").item(0).getTextContent())));
								
							break;
						case "resource":
							//String fuel = element.getElementsByTagName("fuel").item(0).getTextContent();
							if(element.getElementsByTagName("fuel").getLength() == 0)
							{
								C.add(new Resource(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent()));
							}
							else
							{
								C.add(new ResourceFuel(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),element.getElementsByTagName("category").item(1).getTextContent(),Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent())));
							}
							break;
						case "components":
							if(element.getElementsByTagName("fuel").getLength() == 0)
							{
								C.add(new Component(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent()));
							}
							else
							{
								C.add(new Fuel(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(),Integer.parseInt(element.getElementsByTagName("value").item(0).getTextContent()),element.getElementsByTagName("category").item(1).getTextContent()));
							}
							break;
					}
					
				}
			}
			/*deuxieme for où l'on réalise les liaisons représenter sur notre diagramme de classe*/
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
							NodeList l = element.getElementsByTagName("minedby");
							int m = temp;
							/*int m=0;
							while( (!C.get(m).getId().equals(element.getElementsByTagName("id"))))
								m++;*/
							
							for (int j = 0; j < l.getLength(); j++)
							{
								String minedby = l.item(j).getTextContent();
								
								for( int k = 0;k < C.size();k++)
								{
									if((C.get(k) instanceof Building)&&(C.get(k).getId().equals(minedby)))
									{
										if(C.get(k) instanceof ExtractorSE){
											ExtractorSE eSE = (ExtractorSE)C.get(k);
											eSE.addr((Resource)C.get(m));
											Resource r = (Resource)C.get(m);
											r.addE((Building)C.get(k));
										}
										else
										{
											if( C.get(k) instanceof Extractor)
											{
												Extractor e = (Extractor)C.get(k);
												e.addR((Resource)C.get(m));
												Resource r = (Resource)C.get(m);
												r.addE((Building)C.get(k));
											}
										}
									}
										
								}
								
							}
							break;
						case "components":
							break;
					}
					
				}
			}
			NodeList list2 = doc.getElementsByTagName("recipes");

			for (int temp = 0; temp < list2.getLength(); temp++) {
				Node node = list2.item(temp);
				if(node.getNodeType() == Node.ELEMENT_NODE) 
				{
					/*
					//On regarde le nom et la categorie du composant
					Element element = (Element) node;
					String id = element.getElementsByTagName("id").item(0).getTextContent();
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String producers = element.getElementsByTagName("producers").item(0).getTextContent();
					double time = Double.parseDouble(element.getElementsByTagName("time").item(0).getTextContent());
					
					//On recupere les ingredients in de la recette
					Element input = (Element) element.getElementsByTagName("in").item(0);
					Element output = (Element) element.getElementsByTagName("out").item(0);
					int o= 0;
					int q;
					while(!C.get(o).getId().equals(producers))
						o++;
					Recette recipe = new Recette(id,name,C.get(o),time); // on conserve l'handle vers la recette pour pouvoir ajouter les ingrédients in et out par la suite.
					
					R.add(recipe);
				
					NodeList liste_in = input.getElementsByTagName("*");
					NodeList liste_out = output.getElementsByTagName("*");
					
					for(int i=0; i<liste_in.getLength(); i++)
					{
						//On utilise la variable e pour recuperer le nom du tag (qui est le nom de l'item), et la variable input pour recuperer la quantite une fois qu'on connait le nom du tag
						q=0;
						Element e = (Element)liste_in.item(i);
						String id_ingred = e.getNodeName();
						int qte = Integer.parseInt(input.getElementsByTagName(id_ingred).item(0).getTextContent());
						while(!C.get(q).getId().equals(id_ingred))
							q++;
						recipe.add_Resource_in((Resource)C.get(q),qte);
						
					}
					if(liste_out.getLength() == 0)
					{
						q=0;
						while(!C.get(q).getId().equals(id))
							q++;
						if(C.get(q) instanceof Extractor)
							recipe.add_Resource_out(((Extractor)C.get(q)).getResource(),1);//n'ayant pas la donnée quantité out de l'élèment produit pour chaque intervalle time on le suppose à 1
					}
					else
					{
						for(int i=0; i<liste_out.getLength(); i++)
						{
							q=0;
							Element e = (Element) liste_out.item(i);
							String id_ingred = e.getNodeName();
							int qte = Integer.parseInt(input.getElementsByTagName(id_ingred).item(0).getTextContent());
							while(!C.get(q).getId().equals(id_ingred))
							q++;
							recipe.add_Resource_out((Resource)C.get(q),qte);
						}
					}*/  
			}
			}
			for(int i =0;i<C.size();i++)
				System.out.println(C.get(i));
		}
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
		
  }
}

