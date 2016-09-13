import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ModifyXMLFile {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		//generatorKenoResultsFile1();
		//generatorKenoResultsFile2();
		
		
		//generatorLottoResultsFile1();
		generatorLottoResultsFile2();
		generatorLottoResultsFile3();
		generatorLottoResultsFile4();
		generatorLottoResultsFile5();
		generatorLottoResultsFile6();
		
		//generatorPlay3ResultsFile1();
		//generatorPlay3ResultsFile2();
		
		//generatorBullseyeResultsFile1();
		//generatorBullseyeResultsFile2();
		
		
	}

	private static void generatorKenoResultsFile1() throws ParseException {
		try {
			// int index = 2304;
			for (int index = 12393; index <= 12415; index += 4) {
				int nextIndex = index + 4;
				String indexS = Integer.toString(nextIndex);
				String filepath = "C:\\Users\\User\\Results\\keno\\DRAWKNO1_"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\keno\\DRAWKNO1_"
						+ Integer.toString(nextIndex) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				// DrawDate.setTextContent("2016-02-15");

				String dateString = DrawDate.getTextContent();
				String newDate = getDate(dateString);
				DrawDate.setTextContent(newDate);

				Random rand = new Random();
				int randomNum = rand.nextInt((40 - 30) + 1) + 30;
				Node Number = doc.getElementsByTagName("Number").item(0);
				// Number.setTextContent(Integer.toString(randomNum));
				doc.getElementsByTagName("Number").item(0)
						.setTextContent(Integer.toString(randomNum - 3));
				doc.getElementsByTagName("Number").item(1)
						.setTextContent(Integer.toString(randomNum - 11));
				doc.getElementsByTagName("Number").item(2)
						.setTextContent(Integer.toString(randomNum - 6));
				doc.getElementsByTagName("Number").item(3)
						.setTextContent(Integer.toString(randomNum));
				doc.getElementsByTagName("Number").item(4)
						.setTextContent(Integer.toString(randomNum + 4));
				doc.getElementsByTagName("Number").item(5)
						.setTextContent(Integer.toString(randomNum + 14));
				doc.getElementsByTagName("Number").item(6)
						.setTextContent(Integer.toString(randomNum + 12));
				doc.getElementsByTagName("Number").item(7)
						.setTextContent(Integer.toString(randomNum - 2));
				doc.getElementsByTagName("Number").item(8)
						.setTextContent(Integer.toString(randomNum - 10));
				doc.getElementsByTagName("Number").item(9)
						.setTextContent(Integer.toString(randomNum - 8));
				doc.getElementsByTagName("Number").item(10)
						.setTextContent(Integer.toString(randomNum + 1));
				doc.getElementsByTagName("Number").item(11)
						.setTextContent(Integer.toString(randomNum + 3));
				doc.getElementsByTagName("Number").item(12)
						.setTextContent(Integer.toString(randomNum + 20));
				doc.getElementsByTagName("Number").item(13)
						.setTextContent(Integer.toString(randomNum + 22));
				doc.getElementsByTagName("Number").item(14)
						.setTextContent(Integer.toString(randomNum - 23));
				doc.getElementsByTagName("Number").item(15)
						.setTextContent(Integer.toString(randomNum - 21));
				doc.getElementsByTagName("Number").item(16)
						.setTextContent(Integer.toString(randomNum - 26));
				doc.getElementsByTagName("Number").item(17)
						.setTextContent(Integer.toString(randomNum + 7));
				doc.getElementsByTagName("Number").item(18)
						.setTextContent(Integer.toString(randomNum + 2));
				doc.getElementsByTagName("Number").item(19)
						.setTextContent(Integer.toString(randomNum + 24));

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorKenoResultsFile2() throws ParseException {
		try {
			// int index = 1516;
			for (int index = 12390; index <= 12419; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\keno\\DRAWKNO2_"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\keno\\DRAWKNO2_"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);
				//
				Document doc1 = docBuilder
						.parse("C:\\Users\\User\\Results\\keno\\DRAWKNO1_"
								+ Integer.toString(index + 1) + ".XML");

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				doc.getElementsByTagName("DrawDate")
						.item(0)
						.setTextContent(
								doc1.getElementsByTagName("DrawDate").item(0)
										.getTextContent());

				doc.getElementsByTagName("DrawTime")
						.item(0)
						.setTextContent(
								doc1.getElementsByTagName("DrawTime").item(0)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(0)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(0)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(1)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(1)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(2)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(2)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(3)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(3)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(4)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(4)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(5)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(5)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(6)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(6)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(7)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(7)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(8)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(8)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(9)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(9)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(10)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(10)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(11)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(11)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(12)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(12)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(13)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(13)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(14)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(14)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(15)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(15)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(16)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(16)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(17)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(17)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(18)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(18)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(19)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(19)
										.getTextContent());
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorLottoResultsFile5() throws ParseException {
		try {
			// int index = 1516;
			for (int index = 1612; index <= 1712; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\lotto\\DRAWSTK1_"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\lotto\\DRAWSTK1_"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);
				//
				Document doc1 = docBuilder
						.parse("C:\\Users\\User\\Results\\lotto\\DRAWLPB1_"
								+ Integer.toString(index + 1) + ".XML");

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				String newDate = doc1.getElementsByTagName("DrawDate").item(0)
						.getTextContent();
				DrawDate.setTextContent(newDate);
				String newDay = doc1.getElementsByTagName("DrawDay").item(0)
						.getTextContent();
				doc.getElementsByTagName("DrawDay").item(0)
						.setTextContent(newDay);
				doc.getElementsByTagName("Number")
						.item(0)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(0)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(1)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(1)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(2)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(2)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(3)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(3)
										.getTextContent());

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorLottoResultsFile6() throws ParseException {
		try {
			// int index = 1516;
			for (int index = 1612; index <= 1712; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\lotto\\DRAWSTK2_"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\lotto\\DRAWSTK2_"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);
				//
				Document doc1 = docBuilder
						.parse("C:\\Users\\User\\Results\\lotto\\DRAWLPB1_"
								+ Integer.toString(index + 1) + ".XML");

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				String newDate = doc1.getElementsByTagName("DrawDate").item(0)
						.getTextContent();
				DrawDate.setTextContent(newDate);
				String newDay = doc1.getElementsByTagName("DrawDay").item(0)
						.getTextContent();
				doc.getElementsByTagName("DrawDay").item(0)
						.setTextContent(newDay);
				doc.getElementsByTagName("Number")
						.item(0)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(0)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(1)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(1)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(2)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(2)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(3)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(3)
										.getTextContent());

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorLottoResultsFile1() throws ParseException {
		try {
			// int index = 2304;
			for (int index = 1612; index <= 1712; index += 2) {
				int nextIndex = index + 2;
				String indexS = Integer.toString(nextIndex);
				String filepath = "C:\\Users\\User\\Results\\lotto\\DRAWLPB1_"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\lotto\\DRAWLPB1_"
						+ Integer.toString(nextIndex) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				// DrawDate.setTextContent("2016-02-15");

				String dateString = DrawDate.getTextContent();
				String newDate = getNextWeekDate(dateString);
				DrawDate.setTextContent(newDate);

				Random rand = new Random();
				int randomNum = rand.nextInt((25 - 20) + 1) + 20;
				Node Number = doc.getElementsByTagName("Number").item(0);
				// Number.setTextContent(Integer.toString(randomNum));
				doc.getElementsByTagName("Number").item(0)
						.setTextContent(Integer.toString(randomNum - 13));
				doc.getElementsByTagName("Number").item(1)
						.setTextContent(Integer.toString(randomNum - 11));
				doc.getElementsByTagName("Number").item(2)
						.setTextContent(Integer.toString(randomNum - 6));
				doc.getElementsByTagName("Number").item(3)
						.setTextContent(Integer.toString(randomNum));
				doc.getElementsByTagName("Number").item(4)
						.setTextContent(Integer.toString(randomNum + 4));
				doc.getElementsByTagName("Number").item(5)
						.setTextContent(Integer.toString(randomNum + 14));
				doc.getElementsByTagName("Number").item(6)
						.setTextContent(Integer.toString(randomNum + 12));

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorLottoResultsFile2() throws ParseException {
		try {
			// int index = 1516;
			for (int index = 1612; index <= 1712; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\lotto\\DRAWLPB2_"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\lotto\\DRAWLPB2_"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);
				//
				Document doc1 = docBuilder
						.parse("C:\\Users\\User\\Results\\lotto\\DRAWLPB1_"
								+ Integer.toString(index + 1) + ".XML");

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				String newDate = doc1.getElementsByTagName("DrawDate").item(0)
						.getTextContent();
				DrawDate.setTextContent(newDate);
				String newDay = doc1.getElementsByTagName("DrawDay").item(0)
						.getTextContent();
				doc.getElementsByTagName("DrawDay").item(0)
						.setTextContent(newDay);
				doc.getElementsByTagName("Number")
						.item(0)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(0)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(1)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(1)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(2)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(2)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(3)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(3)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(4)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(4)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(5)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(5)
										.getTextContent());
				doc.getElementsByTagName("Number")
						.item(6)
						.setTextContent(
								doc1.getElementsByTagName("Number").item(6)
										.getTextContent());

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorLottoResultsFile3() throws ParseException {
		try {
			// int index = 1516;
			for (int index = 1612; index <= 1712; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\lotto\\DRAWLPB3_"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\lotto\\DRAWLPB3_"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);
				//
				Document doc1 = docBuilder
						.parse("C:\\Users\\User\\Results\\lotto\\DRAWLPB1_"
								+ Integer.toString(index + 1) + ".XML");

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				String newDate = doc1.getElementsByTagName("DrawDate").item(0)
						.getTextContent();
				DrawDate.setTextContent(newDate);
				String newDay = doc1.getElementsByTagName("DrawDay").item(0)
						.getTextContent();
				doc.getElementsByTagName("DrawDay").item(0)
						.setTextContent(newDay);
				// Set up powerball
				// doc.getElementsByTagName("Number").item(0).setTextContent(doc1.getElementsByTagName("Number").item(0).getTextContent());

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorLottoResultsFile4() throws ParseException {
		try {
			// int index = 1516;
			for (int index = 1612; index <= 1712; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\lotto\\DRAWLPB4_"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\lotto\\DRAWLPB4_"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);
				//
				Document doc1 = docBuilder
						.parse("C:\\Users\\User\\Results\\lotto\\DRAWLPB1_"
								+ Integer.toString(index + 1) + ".XML");

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				String newDate = doc1.getElementsByTagName("DrawDate").item(0)
						.getTextContent();
				DrawDate.setTextContent(newDate);
				String newDay = doc1.getElementsByTagName("DrawDay").item(0)
						.getTextContent();
				doc.getElementsByTagName("DrawDay").item(0)
						.setTextContent(newDay);
				// Set up powerball
				// doc.getElementsByTagName("Number").item(0).setTextContent(doc1.getElementsByTagName("Number").item(0).getTextContent());

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorPlay3ResultsFile2() throws ParseException {
		try {
			// int index = 515;
			for (int index = 533; index <= 539; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\play3\\DRAWPK32_00"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\play3\\DRAWPK32_00"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				// DrawDate.setTextContent("2016-02-15");

				String dateString = DrawDate.getTextContent();
				String newDate = getDate(dateString);
				DrawDate.setTextContent(newDate);

				//
				Document doc1 = docBuilder
						.parse("C:\\Users\\User\\Results\\play3\\DRAWPK31_00"
								+ Integer.toString(index + 1) + ".XML");
				String WNumber = doc1.getElementsByTagName("Number").item(0)
						.getTextContent();

				Node Number = doc.getElementsByTagName("Number").item(0);
				Number.setTextContent(WNumber);

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorPlay3ResultsFile1() throws ParseException {
		try {
			// int index = 515;
			for (int index = 533; index <= 539; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\play3\\DRAWPK31_00"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\play3\\DRAWPK31_00"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				// DrawDate.setTextContent("2016-02-15");

				String dateString = DrawDate.getTextContent();
				String newDate = getDate(dateString);
				DrawDate.setTextContent(newDate);

				Random rand = new Random();
				int randomNum = rand.nextInt((999 - 100) + 1) + 100;
				Node Number = doc.getElementsByTagName("Number").item(0);
				Number.setTextContent(Integer.toString(randomNum));

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorBullseyeResultsFile1() throws ParseException {
		try {
			// int index = 2304;
			for (int index = 2331; index <= 2362; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\bullseye\\DRAWBUL1_0"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\bullseye\\DRAWBUL1_0"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				// DrawDate.setTextContent("2016-02-15");

				String dateString = DrawDate.getTextContent();
				String newDate = getDate(dateString);
				DrawDate.setTextContent(newDate);

				Random rand = new Random();
				int randomNum = rand.nextInt((999999 - 100000) + 1) + 100000;
				Node Number = doc.getElementsByTagName("Number").item(0);
				Number.setTextContent(Integer.toString(randomNum));

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static void generatorBullseyeResultsFile2() throws ParseException {
		try {
			// int index = 2304;
			for (int index = 2331; index <= 2362; index++) {
				String indexS = Integer.toString(index + 1);
				String filepath = "C:\\Users\\User\\Results\\bullseye\\DRAWBUL2_0"
						+ Integer.toString(index) + ".XML";
				String newfilepath = "C:\\Users\\User\\Results\\bullseye\\DRAWBUL2_0"
						+ Integer.toString(index + 1) + ".XML";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);

				// Get the root element
				Node company = doc.getFirstChild();

				// Get the staff element , it may not working if tag has spaces,
				// or
				// whatever weird characters in front...it's better to use
				// getElementsByTagName() to get it directly.
				// Node staff = company.getFirstChild();

				// Get the staff element by tag name directly
				Node DrawNumber = doc.getElementsByTagName("DrawNumber")
						.item(0);
				DrawNumber.setTextContent(indexS);

				// Get the staff element by tag name directly
				Node DrawDate = doc.getElementsByTagName("DrawDate").item(0);
				// DrawDate.setTextContent("2016-02-15");

				String dateString = DrawDate.getTextContent();
				String newDate = getDate(dateString);
				DrawDate.setTextContent(newDate);

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(newfilepath));
				transformer.transform(source, result);
				// Output to console for testing
				StreamResult console = new StreamResult(System.out);
				transformer.transform(source, console);

				System.out.println("Done");

			}

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
	}

	private static String getRan() {
		Random rand = new Random();
		int randomNum = rand.nextInt((999999 - 100000) + 1) + 100000;
		return Integer.toString(randomNum);
	}

	static String getDate(String dateString) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// String dateString="2016-02-15";
		Date myDate = dateFormat.parse(dateString);
		Date oneDayBefore = new Date(myDate.getTime() + 1 * (24 * 3600000));
		String result = dateFormat.format(oneDayBefore);
		System.out.println(result);
		return result;
	}

	static String getNextWeekDate(String dateString) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// String dateString="2016-02-15";
		Date myDate = dateFormat.parse(dateString);
		Date oneDayBefore = new Date(myDate.getTime() + 7 * (24 * 3600000));
		String result = dateFormat.format(oneDayBefore);
		System.out.println(result);
		return result;
	}
}
