package nl.rabo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import nl.rabo.app.model.Payment;

public class PaymentValidatorService{
	
	@Autowired
	private XmlParser xmlParser;
	
	@Autowired
	private PaymentValidator paymentValidator;
	
	
	public List<ValidationError> processFile(String file) {
		List<ValidationError> errors = new ArrayList<>();
		List<Payment> listOfPayments = new ArrayList<>();
		listOfPayments = xmlParser.parseXml(null); 
		if (listOfPayments.isEmpty()) {
			errors.add(new ValidationError(0, "Uploaded file is empty"));
		} else {
			paymentValidator.validate(listOfPayments, errors);
		}
		if(errors.isEmpty()) {
			// save the payemnts
		}
		return errors;
	}

}
