package nl.rabo.app.service;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nl.rabo.app.model.Payment;

public class PaymentValidatorTest {
	
	@Before
	public void executedBeforeEach() {
		PaymentValidator paymentValidator = new PaymentValidator();
		List<Payment> paymentsList = new ArrayList<>();
		List<ValidationError> errors = new ArrayList<>();
	}
	@Test
	public void verifyThatPaymentWithNullAsEndBelanceIsNotValid() {
		PaymentValidator paymentValidator = new PaymentValidator();
		Payment payment = new Payment();
		payment.setReference(123);
		List<Payment> paymentsList = new ArrayList<>();
		paymentsList.add(payment);
		List<ValidationError> errors = new ArrayList<>();
		paymentValidator.validate(paymentsList,errors);
		assertThat(errors, hasSize(1));
	}
	
	@Test
	public void verifyIntegerEndBelanceMatchStardAndWhenMutationIsZero() {
		Payment payment = new Payment();
		payment.setEndBalance(new BigDecimal(9.1));
		payment.setStartBalance(new BigDecimal(9.1));
		payment.setMutation(new BigDecimal(0.0));
		List<Payment> paymentsList = new ArrayList<>();
		paymentsList.add(payment);
		List<ValidationError> errors = new ArrayList<>();
		PaymentValidator paymentValidator = new PaymentValidator();
		paymentValidator.validate(paymentsList,errors);
		assertThat(errors, hasSize(0));
	}
	

	@Test
	public void verifyIntegerEndBelanceIsZeroWhenMutationEqualToStartBalance() {
		Payment payment = new Payment();
		payment.setEndBalance(new BigDecimal(0));
		payment.setStartBalance(new BigDecimal(9.1));
		payment.setMutation(new BigDecimal(-9.1));
		List<Payment> paymentsList = new ArrayList<>();
		paymentsList.add(payment);
		List<ValidationError> errors = new ArrayList<>();
		PaymentValidator paymentValidator = new PaymentValidator();
		paymentValidator.validate(paymentsList,errors);
		assertThat(errors, hasSize(0));
	}
	
	
	@Test
	public void verifyMinusEndBalance() {
		Payment payment = new Payment();
		payment.setEndBalance(new BigDecimal(-18.2));
		payment.setStartBalance(new BigDecimal(- 9.1));
		payment.setMutation(new BigDecimal(-9.1));
		List<Payment> paymentsList = new ArrayList<>();
		paymentsList.add(payment);
		List<ValidationError> errors = new ArrayList<>();
		PaymentValidator paymentValidator = new PaymentValidator();
		paymentValidator.validate(paymentsList,errors);
		assertThat(errors, hasSize(0));
	}
	
	@Test
	public void verifyMinusEndBalanceIsInvalid() {
		Payment payment = new Payment();
		payment.setEndBalance(new BigDecimal(-18.1));
		payment.setStartBalance(new BigDecimal(- 9.1));
		payment.setMutation(new BigDecimal(-9.1));
		List<Payment> paymentsList = new ArrayList<>();
		paymentsList.add(payment);
		List<ValidationError> errors = new ArrayList<>();
		PaymentValidator paymentValidator = new PaymentValidator();
		paymentValidator.validate(paymentsList,errors);
		assertThat(errors, hasSize(1));
	}
	
	@Test
	public void InvalidListContainTwoSamePayemnt() {
		List<Payment> paymentList = new ArrayList<>();
		Payment payment1 = new Payment();
		payment1.setReference(1);
		Payment payment3 = new Payment();
		payment1.setReference(1);
		paymentList.add(payment1); 
		paymentList.add(payment3);
		List<ValidationError> errors = new ArrayList<>();
		PaymentValidator paymentValidator = new PaymentValidator();
		paymentValidator.validate(paymentList,errors);
		assertThat(errors, hasSize(2));
		
	}
	
	@Test
	public void ListContainTwoDoublicatedPayemnts() {
		List<Payment> paymentList = new ArrayList<>();
		Payment payment1 = new Payment();
		payment1.setReference(1);
		Payment payment2 = new Payment();
		payment1.setReference(2);
		Payment payment3 = new Payment();
		payment1.setReference(1);
		Payment payment4 = new Payment();
		payment1.setReference(2);
		paymentList.add(payment1); 
		paymentList.add(payment2);
		paymentList.add(payment3);
		paymentList.add(payment4);
		List<ValidationError> errors = new ArrayList<>();

		PaymentValidator paymentValidator = new PaymentValidator();
		paymentValidator.validate(paymentList,errors);
		assertThat(errors, hasSize(6));

	}
	

}
