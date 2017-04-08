package nl.rabo.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CsvParser {
	private final static Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);

	public static List<Payment> parseCsv(File file) throws FileNotFoundException, IOException {
		LOGGER.info("Start parsing CVS file");
		List<Payment> listOfPayments = new ArrayList<>();
		ICsvBeanReader beanReader = null;
		try {
			beanReader = new CsvBeanReader(new FileReader(file), CsvPreference.STANDARD_PREFERENCE);
			beanReader.getHeader(true);
			final CellProcessor[] processors = getProcessors();
			final String[] fieldMapping = new String[] { "reference", "accountNumber", "description", "startBalance",
					"mutation", "endBalance" };
			Payment payment;
			while ((payment = beanReader.read(Payment.class, fieldMapping, processors)) != null) {
				listOfPayments.add(payment);
			}

		} catch (Exception e) {
			LOGGER.error("not able to parse file" + file.getName());
		}
		finally {
            if( beanReader != null ) {
                    beanReader.close();
            }
		}

		LOGGER.info("Number of Payments in CVS file = " + listOfPayments.size());
		return listOfPayments;
	}

	private static CellProcessor[] getProcessors() {
		final CellProcessor[] processors = new CellProcessor[] { new NotNull(new ParseInt()), // reference
				new NotNull(), // account number
				new NotNull(), // description
				new NotNull(new ParseBigDecimal()), // start balance
				new NotNull(new ParseBigDecimal()), // mutation
				new NotNull(new ParseBigDecimal()), // end balance
		};
		return processors;
	}

}
