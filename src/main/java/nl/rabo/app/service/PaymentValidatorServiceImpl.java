package nl.rabo.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;

import nl.rabo.app.model.Payment;
import nl.rabo.app.service.parser.XMLParser;
import nl.rabo.app.service.validator.PaymentValidator;
import nl.rabo.app.service.validator.PaymentsValidationError;

public class PaymentValidatorServiceImpl implements PaymentValidatorService {

	@Autowired
	private XMLParser xmlParser;
	
	@Autowired
	private PaymentValidator<List<Payment>> paymentValidator;
	@Override
	public List<PaymentsValidationError> processFile(String file) {
		List<PaymentsValidationError> errors = new ArrayList<>();
		List<Payment> listOfPayments = new ArrayList<>();
		try {
			listOfPayments = xmlParser.parseXml(""); 
			if (listOfPayments.isEmpty()) {
				errors.add(new PaymentsValidationError("Uploaded file is empty"));
			} else {
				paymentValidator.validate(listOfPayments, errors);
			}
			if(errors.isEmpty()) {
				// save the payemnts
			}
				
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return errors;
	}

}
