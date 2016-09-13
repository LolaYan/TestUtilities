import java.io.File;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class xmlGenerator {

	public static void main(String[] args) throws ParseException {
		//generatorBullseyeResultsFile1();
		for (int index = 1534; index <= 1612; index++) {
			String fileName = "C:\\Users\\User\\Results\\lotto\\"+Integer.toString(index);
			File myFile = new File(fileName);

			if (!myFile.exists()) {
				myFile.mkdirs();
			}
		}
		
	}

	private static void generatorBullseyeResultsFile2() {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			doc.appendChild(getBullseyeResults(doc, "2300", "2016-02-15",
					"18:02", "617279", "2", "345200", "Waltham",
					"Christchurch", "St Martins New World Supermark"));

			//
			String fileName = "C:\\Users\\User\\Results\\bullseye";
			File myFile = new File(fileName);
			if (!myFile.exists()) {
				myFile.mkdirs();
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(
					"C:\\Users\\User\\Results\\bullseye\\DRAWBUL2_02300.xml"));

			// Output to console for testing
			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);

			transformer.transform(source, result);

			System.out.println("File saved!");
			System.out.println("\nXML DOM Created Successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void generatorBullseyeResultsFile1() {
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder icBuilder;
		try {
			icBuilder = icFactory.newDocumentBuilder();
			Document doc = icBuilder.newDocument();
			// Element mainRootElement =
			// doc.createElementNS("http://crunchify.com/CrunchifyCreateXMLDOM",
			// "Companies");
			// doc.appendChild(mainRootElement);

			// append child elements to root element
			doc.appendChild(getBullseyeResults(doc, "2300", "2016-02-15",
					"18:02", "617279", "2", "345200", "Waltham",
					"Christchurch", "St Martins New World Supermark"));

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			String fileName = "C:\\Users\\User\\Results\\bullseye";
			File myFile = new File(fileName);

			if (!myFile.exists()) {
				myFile.mkdirs();
			}
			StreamResult result = new StreamResult(new File(
					"C:\\Users\\User\\Results\\bullseye\\DRAWBUL1_02300.xml"));

			// Output to console for testing
			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);

			transformer.transform(source, result);

			System.out.println("File saved!");
			System.out.println("\nXML DOM Created Successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Node getBullseyeResults(Document doc, String DrawNumber,
			String DrawDate, String DrawTime, String WinningNumbers,
			String Division, String WinnerLocationId, String WinnerSuburb,
			String WinnerCity, String WinnerRetailer) {
		Element bullseyeResults = doc.createElement("BullseyeResults");
		// company.setAttribute("id", id);
		bullseyeResults.appendChild(getBullseyeResultsElements(doc,
				bullseyeResults, "DrawNumber", DrawNumber));
		bullseyeResults.appendChild(getBullseyeResultsElements(doc,
				bullseyeResults, "DrawDate", DrawDate));
		bullseyeResults.appendChild(getBullseyeResultsElements(doc,
				bullseyeResults, "DrawTime", DrawTime));

		Element nodeWinningNumbers = doc.createElement("WinningNumbers");
		bullseyeResults.appendChild(nodeWinningNumbers);
		nodeWinningNumbers.appendChild(getBullseyeResultsElements(doc,
				nodeWinningNumbers, "Number", WinningNumbers));

		Element nodeWinnerLocations = doc.createElement("WinnerLocations");
		bullseyeResults.appendChild(nodeWinnerLocations);
		nodeWinnerLocations.appendChild(getBullseyeResultsElements(doc,
				nodeWinningNumbers, "Division", Division));
		nodeWinnerLocations.appendChild(getBullseyeResultsElements(doc,
				nodeWinningNumbers, "WinnerLocationId", WinnerLocationId));
		nodeWinnerLocations.appendChild(getBullseyeResultsElements(doc,
				nodeWinningNumbers, "WinnerSuburb", WinnerSuburb));
		nodeWinnerLocations.appendChild(getBullseyeResultsElements(doc,
				nodeWinningNumbers, "WinnerCity", WinnerCity));
		nodeWinnerLocations.appendChild(getBullseyeResultsElements(doc,
				nodeWinningNumbers, "WinnerRetailer", WinnerRetailer));

		return bullseyeResults;
	}
	
	private static Node getBullseyeResultsElements(Document doc,
			Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

}
