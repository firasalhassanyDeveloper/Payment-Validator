package nl.rabo.app.service.validator;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public interface PaymentValidator<T> {
	List<PaymentsValidationError> validate (final T toBeValidated, List<PaymentsValidationError> errors);
}
