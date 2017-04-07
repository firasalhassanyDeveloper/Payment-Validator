package nl.rabo.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import nl.rabo.app.service.validator.PaymentsValidationError;

@Service
public interface PaymentValidatorService {
	List<PaymentsValidationError> processFile(String file);
}
