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
import java.util.Collections;
import java.util.Scanner;



//Merci a ce site pour l'aide : https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
//ainsi qu'a stackoverflow evidemment, comme toujours... !

public class Test {
	
	private static final String FILENAME = "data.xml";

	public static void main(String[] args) throws ConsommationException, CategoryException
	{
		ArrayList<Component> C = new ArrayList<Component>();
		ArrayList<Building> B = new ArrayList<Building>();
		ArrayList<Extracteur> Extra = new ArrayList<Extracteur>();
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
									double drain, usage;
									if(element.getElementsByTagName("usage").getLength() != 0)
										usage = Double.parseDouble(element.getElementsByTagName("usage").item(0).getTextContent());
									else
										usage = 0;
									if(element.getElementsByTagName("drain").getLength() != 0)
										drain = Double.parseDouble(element.getElementsByTagName("drain").item(0).getTextContent());
									else
										drain = 0;
									if(element.getElementsByTagName("speed").getLength() ==0){
										try{
										C.add(new ClassicFactory(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(), usage, drain));
										}
										catch(Exception e)
										{
											System.out.println(e);
											C.add(new ClassicFactory(element.getElementsByTagName("id").item(0).getTextContent(),element.getElementsByTagName("name").item(0).getTextContent(), usage, 0));
										}
									}
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
												//r.addE((Building)C.get(k));
												r.addE(e);
											}
											else
											{
												if( C.get(k) instanceof ClassicFactory)
												{
													ClassicFactory cf = (ClassicFactory)C.get(k);
													cf.addR((Resource)C.get(m));
													Resource r = (Resource)C.get(m);
													r.addE((Building)C.get(k));
												}
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
					
					//On regarde le nom et la categorie du composant
					Element element = (Element) node;
					String id = element.getElementsByTagName("id").item(0).getTextContent();
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String producers = element.getElementsByTagName("producers").item(0).getTextContent();
					double time = Double.parseDouble(element.getElementsByTagName("time").item(0).getTextContent());
					
					//On recupere les ingredients in de la recette
					Element input = (Element) element.getElementsByTagName("in").item(0);
					int o= 0;
					int q;
					Recette recipe;
					while((!C.get(o).getId().equals(producers))&&(o<C.size()))
						o++;
					if(C.get(o).getId().equals(producers))
					{
						recipe = new Recette(id,name,(Building)C.get(o),time); // on conserve l'handle vers la recette pour pouvoir ajouter les ingrédients in et out par la suite.
					}
					else
					{
						recipe = new Recette(id,name,null,time);// cas où l'on ne trouve pas de batiment qui produise la recette
					}
					R.add(recipe);
				
					NodeList liste_in = input.getElementsByTagName("*");
					
					for(int i=0; i<liste_in.getLength(); i++)
					{
						//On utilise la variable e pour recuperer le nom du tag (qui est le nom de l'item), et la variable input pour recuperer la quantite une fois qu'on connait le nom du tag
						q=0;
						Element e = (Element)liste_in.item(i);
						String id_ingred = e.getNodeName();
						double qte = Double.parseDouble(input.getElementsByTagName(id_ingred).item(0).getTextContent());
						while(!C.get(q).getId().equals(id_ingred))
							q++;
						recipe.add_Component_in(C.get(q),qte);
						
					}
					if(element.getElementsByTagName("out").getLength() == 0)
					{
						q=1;
						while((!C.get(q).getId().equals(id))&&(q<C.size()))
							q++;
						if(q<C.size()){
							if(C.get(q) instanceof Extracteur)
								if(((Extracteur)C.get(q)).getResource().size() >0)
								{
									recipe.add_Component_out(((Extracteur)C.get(q)).getResource().get(0),1.);//n'ayant pas la donnée quantité out de l'élèment produit pour chaque intervalle time on le suppose à 1
									C.get(q).trec.add(recipe);
									
								}
							if(C.get(q) instanceof ClassicFactory)
								if (((ClassicFactory)C.get(q)).getResource().size()>0)
								{
									recipe.add_Component_out(((ClassicFactory)C.get(q)).getResource().get(0),1.);
									C.get(q).trec.add(recipe);
								}
						}
					}
					else
					{
						Element output = (Element) element.getElementsByTagName("out").item(0);
						NodeList liste_out = output.getElementsByTagName("*");
						for(int i=0; i<liste_out.getLength(); i++)
						{
							q=1;
							Element e = (Element) liste_out.item(i);
							String id_ingred = e.getNodeName();
							double qte = Double.parseDouble(output.getElementsByTagName(id_ingred).item(0).getTextContent());
							while(!C.get(q).getId().equals(id_ingred))
							q++;
							recipe.add_Component_out(C.get(q),qte);
							if(C.get(q) instanceof Resource)
								for(int j=0;j<((Resource)C.get(q)).getE().size();j++)
								{
									Resource re = (Resource)C.get(q);
									re.getE().get(j).trec.add(recipe);
								}
							
						}
					}
			}
			}
			Collections.sort(C, Component.ComparatorName);
			Collections.sort(R, Recette.ComparatorName);
			System.out.println("Liste alphabétique des composants qui ne sont pas des batiments:");
			for(int i =0;i<C.size();i++){
				//System.out.println(C.get(i));
				if(C.get(i) instanceof Building){
					B.add((Building)C.get(i));
					if(C.get(i) instanceof Extracteur)
						Extra.add((Extracteur)C.get(i));
				}
				else
					System.out.println("- "+C.get(i).getName()+ " composant n° "+i);
			}
			System.out.println("Liste alphabétique des batiments:");
			for(int i = 0;i<B.size();i++)
				System.out.println("- "+B.get(i).getName()+" batiment n° "+i);
			System.out.println("Liste alphabétique des recettes:");
			for(int i =0;i<R.size();i++)
				System.out.println("- "+R.get(i).getName()+" recette n° "+i);
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez entrer le n° associé au composant rechercher pour plus d'information:");
			String str;
			str = sc.nextLine();
			System.out.println(B.get(Integer.parseInt(str)));
			System.out.println("Veuillez entrer le n° associé au Batiment rechercher pour plus d'information:");
			str = sc.nextLine(); 
			System.out.println(B.get(Integer.parseInt(str)));
			Component ex = B.get(Integer.parseInt(str));
			if(ex.trec.size()>0)
			{
				System.out.println("\nLe bâtiments a pour recette :\n");
				for(Recette r : ex.trec)
					System.out.println(r);
			}
			if(ex instanceof Central)
			{
                
				String cat = ((Central)ex).getCategory();
				System.out.println("\nLa centrale étant de type "+cat+", elle peut utiliser les carburants suivant:\n");
				for(int i;i<C.size();i++){
					if(C.get(i) instanceof Fuel){
						Fuel rf=(Fuel) C.get(i);
						if(rf.getCategory().equals(cat))
							System.out.println(rf2);
					}
					if(C.get(i) instanceof ResourceFuel)
					{
						ResourceFuel rf2=(ResourceFuel)C.get(i);
						if(rf2.getCategory().equals(cat))
							System.out.println(rf3);
					}
				}
				
			}
			System.out.println("Veuillez entrer le n° associé à la recette rechercher pour plus d'informations:");
			str = sc.nextLine();
			System.out.println(C.get(Integer.parseInt(str)));
			System.out.println("entrer le n° associé a l'extracteur rechercher(qui se situe entre 0 et " +(Extra.size()-1)+"):");
			str = sc.nextLine();
			System.out.println("l'"+Extra.get(Integer.parseInt(str)));
			ex = (Component)Extra.get(Integer.parseInt(str));
			if(ex.trec.size()>0)
			{
				System.out.println("\nLe bâtiments a pour recette :\n");
				for(Recette r : ex.trec)
					System.out.println(r);
			}					 
			
			/*ajouter le code qui permet d'afficher toutes les recette associé à cette extractor, donc d'id == à l'extractor ou de ressource produite == ressource associé àl'extractor*/
			sc.close();
		}
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			e.printStackTrace();
		}
		
  }
}

