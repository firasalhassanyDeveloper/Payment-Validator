package nl.rabo.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import nl.rabo.app.model.Payment;

public class PaymentValidatorService {

	private static PaymentValidator paymentValidator = new PaymentValidator();

	public static List<ValidationError> processFile(File file) throws FileNotFoundException, IOException {
		String fileExtention = FilenameUtils.getExtension(file.getAbsolutePath());
		List<ValidationError> errors = new ArrayList<>();
		List<Payment> listOfPayments = new ArrayList<>();

		if (fileExtention.equals("xml")) {
			listOfPayments = XmlParser.parseXml(file);
		} else if(fileExtention.equals("csv")) {
			listOfPayments = CsvParser.parseCsv(file);

		}
		if (listOfPayments.isEmpty()) {
			errors.add(new ValidationError(0, "Uploaded file is empty"));
		} else {
			paymentValidator.validate(listOfPayments, errors);
		}
		if (errors.isEmpty()) {
			// save the payemnts
		}
		return errors;
	}

}
