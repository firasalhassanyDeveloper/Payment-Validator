package nl.rabo.app.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.io.File;

import java.util.List;

import javax.xml.bind.UnmarshalException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import nl.rabo.app.model.Payment;

@RunWith(MockitoJUnitRunner.class)
public class XmlParserTest {

	@InjectMocks
	private XmlParser xmlParser;

	@Test
	public void parseRecordFromValidXml() {
		File file = new File(XmlParserTest.class.getClass().getResource("/xml/10_records.xml").getFile());
		List<Payment> paymentList = xmlParser.parseXml(file);
		assertThat(paymentList, hasSize(10));
	}

	@Test
	public void parseFileWithOnlyOneRecordValidXml() {
		File file = new File(XmlParserTest.class.getClass().getResource("/xml/1_record.xml").getFile());
		List<Payment> paymentList = xmlParser.parseXml(file);
		assertThat(paymentList, hasSize(1));
	}

	@Test
	public void parseFileWithEmptyValidXml() {
		File file = new File(XmlParserTest.class.getClass().getResource("/xml/0_records.xml").getFile());
		List<Payment> paymentList = xmlParser.parseXml(file);
		assertThat(paymentList, hasSize(0));
	}
	@Test(expected= UnmarshalException.class)
	public void parseInValidXml() {
		File file = new File(XmlParserTest.class.getClass().getResource("/xml/invalid_records.xml").getFile());
		 xmlParser.parseXml(file);
    
	}
}
