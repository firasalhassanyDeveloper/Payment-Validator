package nl.rabo.app.service.validator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.rabo.app.model.Payment;

public class PaymentValidatorImpl implements PaymentValidator<List<Payment>> {

	@Override
	public List<PaymentsValidationError> validate(List<Payment> payments, List<PaymentsValidationError> errors) {
		validateBalance(payments, errors);
		validateUnique(payments, errors);
		return errors;
	}

	private static List<PaymentsValidationError> validateBalance(List<Payment> payments,
			List<PaymentsValidationError> errors) {
		for (Payment payment : payments) {
			if (preValidation(payment)) {
				errors.add(new PaymentsValidationError(FieldName.REFERENC, "Payment missing mandatory balance value."));
			} else if (invalidateEndBalance(payment)) {
				errors.add(new PaymentsValidationError(FieldName.END_BELANCE, "End balance is not correct."));
			}
		}
		return errors;
	}

	private static List<PaymentsValidationError> validateUnique(List<Payment> payments,
			List<PaymentsValidationError> errors) {
		{
			final Set<Payment> setToReturn = new HashSet();
			final Set<Integer> set1 = new HashSet();

			for (Payment yourPayment : payments) {
				if (!set1.add(yourPayment.getReference())) {
					setToReturn.add(yourPayment);
				}
			}
			for (Payment p : setToReturn) {
				errors.add(new PaymentsValidationError("Duplicitaed payment with id = " + p.getReference()));
			}
			return errors;
		}
	}

	private static boolean preValidation(Payment toBeValidated) {
		return null == toBeValidated.getEndBalance() || null == toBeValidated.getMutation()
				|| null == toBeValidated.getStartBalance();

	}

	private static boolean invalidateEndBalance(Payment toBeValidated) {
		BigDecimal startBalance = toBeValidated.getStartBalance();
		BigDecimal mutation = toBeValidated.getMutation();
		BigDecimal newBalance = startBalance.add(mutation);
		return newBalance.compareTo(toBeValidated.getEndBalance()) != 0;
	}

}
