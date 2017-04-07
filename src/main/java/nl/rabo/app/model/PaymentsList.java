package nl.rabo.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "records")
public class PaymentsList {
	@XmlElement(name = "record")
	List<Payment> paymentsList = new ArrayList<>();

	public List<Payment> getPaymentsList() {
		return paymentsList;
	}

}
