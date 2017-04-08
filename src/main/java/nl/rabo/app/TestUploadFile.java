package nl.rabo.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import nl.rabo.app.service.PaymentValidatorService;
import nl.rabo.app.service.ValidationError;

public class TestUploadFile {

	public static void main(String[] args) {
		String fileName = "csv/records.csv";
	    ClassLoader classLoader = new TestUploadFile().getClass().getClassLoader();
	    File file = new File(classLoader.getResource(fileName).getFile());
		try {
			List<ValidationError> errors = PaymentValidatorService.processFile(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
