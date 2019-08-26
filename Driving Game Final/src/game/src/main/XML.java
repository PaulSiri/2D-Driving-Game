package game.src.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

//The XML class deals with writing and reading the high scores XML file for this program
public class XML {

	private int highScore; //Highscore value
	
	//This method overwrites the highscore value in the XML file
	//It intakes an integer score and a String name
	public void createhighscores(int highscore, String name) {

		try {
			OutputStream fout = new FileOutputStream("highscores.xml");
			OutputStream bout = new BufferedOutputStream(fout);
			OutputStreamWriter out = new OutputStreamWriter(bout, "8859_1");

			out.write("<?xml version=\"1.0\" ");
			out.write(" encoding=\"UTF-8\"?>\r\n");
			out.write("<Scores>\r\n");
			out.write("<Name> " + name + " </Name>\r\n");
			out.write("<Scoredpts>" + highscore + "</Scoredpts>\r\n");
			out.write("</Scores> \r\n");

			out.flush();
			out.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			{
			}
		}
	}

	//This method reads the XML file and outputs the name and score of the highscore as a String
	public String readxml() throws ParserConfigurationException, SAXException, IOException {

		try {
			String temp = "";
			String tempScore = "";
			String tempName = "";

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File("highscores.xml"));
			doc.getDocumentElement().normalize();

				Node scoreNode = doc.getElementsByTagName("Scoredpts").item(0);
				Node nameNode = doc.getElementsByTagName("Name").item(0);
				Element nameElement = (Element) nameNode;
				Element scoreElement = (Element) scoreNode;
				
				NodeList textNameList = nameElement.getChildNodes();
				NodeList textScoreList = scoreElement.getChildNodes();

					tempName = ((Node)textNameList.item(0)).getNodeValue().trim();
					tempScore = ((Node)textScoreList.item(0)).getNodeValue().trim();
					
				temp = tempName + ": " + tempScore;
				highScore = Integer.parseInt(tempScore);

			return temp;
		} catch (SAXParseException err) {
			return "";
		}

	}
	
	//Getter for the high score
	public int getHighScore() {
		return highScore;
	}
}
