package nl.rabo.app.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import nl.rabo.app.model.Payment;
@RunWith(MockitoJUnitRunner.class)
public class CsvParserTest {
	
	@InjectMocks
	private CsvParser xmlParser;
	
	
	@Test
	public void parseCsvFileWith10Records() throws FileNotFoundException, IOException {
		File file = new File(CsvParserTest.class.getClass().getResource("/csv/records.csv").getFile());
		List<Payment> paymentList = CsvParser.parseCsv(file);
        assertThat(paymentList, hasSize(10));
	}

	@Test
	public void parseCsvFileWith0Records() throws FileNotFoundException, IOException {
		File file = new File(CsvParserTest.class.getClass().getResource("/csv/0_records.csv").getFile());
		List<Payment> paymentList = CsvParser.parseCsv(file);
        assertThat(paymentList, hasSize(0));
	}
	
	@Test
	public void parseInvalidCsv() throws FileNotFoundException, IOException {
		File file = new File(CsvParserTest.class.getClass().getResource("/csv/invalid_record.csv").getFile());
		CsvParser.parseCsv(file);
	}
}
