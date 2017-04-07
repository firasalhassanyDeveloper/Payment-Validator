package nl.rabo.app.service.parser;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVParser;
import nl.rabo.app.model.Payment;
import nl.rabo.app.model.PaymentsList;


@Component
public class XMLParser {
	private final Logger logger = LoggerFactory.getLogger(XMLParser.class);
	
	public List<Payment> parseXml(String fileName) throws JAXBException {
			File file = new File(CSVParser.class.getClass().getResource("/records.xml").getFile());
			JAXBContext jaxbContext = JAXBContext.newInstance(PaymentsList.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			PaymentsList paymets = (PaymentsList) jaxbUnmarshaller.unmarshal(file);
			return paymets.getPaymentsList();
		}
}
