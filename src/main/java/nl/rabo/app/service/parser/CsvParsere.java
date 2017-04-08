package nl.rabo.app.service.parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import nl.rabo.app.model.Payment;
@Component
public class CsvParsere {
	
	   public List<Payment> parseXml() throws FileNotFoundException, IOException  {
		   List<Payment> listOfPayments = new ArrayList<>();
		 	File file = new File(CsvParsere.class.getClass().getResource("/records.csv").getFile());
			try(ICsvBeanReader beanReader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE)){
	            	beanReader.getHeader(true);
	            final CellProcessor[] processors = getProcessors();
	            final String[] fieldMapping = new String[] { "reference", "accountNumber", "description", "startBalance", "mutation", "endBalance"};
	            Payment payment;
	            while ((payment = beanReader.read(Payment.class, fieldMapping, processors)) != null){
	            	listOfPayments.add(payment);
	            }
			};
			return listOfPayments;
    };
	 
	 private static CellProcessor[] getProcessors() {
	          final CellProcessor[] processors = new CellProcessor[] {
	                new NotNull(new ParseInt()), // reference
	                new NotNull(), // account number
	                new NotNull(), // discreption
	                new NotNull(new ParseBigDecimal()), // start balance
	                new NotNull(new ParseBigDecimal()), // mutation
	                new NotNull(new ParseBigDecimal()), // end balance
	        };
	        return processors;
	    }
	    
}
