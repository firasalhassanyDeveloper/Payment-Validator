package nl.rabo.app.service;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import nl.rabo.app.model.Payment;
import nl.rabo.app.model.PaymentsList;


@Component
public class XmlParser {
	
	private final Logger LOGGER = LoggerFactory.getLogger(XmlParser.class);
	
	public List<Payment> parseXml(File file) {
		LOGGER.info("Start parsing xml file");
			//File file = new File(XmlParser.class.getClass().getResource("/records.xml").getFile());
			JAXBContext jaxbContext;
			PaymentsList paymets = null;
			try {
				jaxbContext = JAXBContext.newInstance(PaymentsList.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				paymets = (PaymentsList) jaxbUnmarshaller.unmarshal(file);
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LOGGER.info("Number of Payments in CVS file " + paymets.getPaymentsList().size());

			return paymets.getPaymentsList();
		}
}
