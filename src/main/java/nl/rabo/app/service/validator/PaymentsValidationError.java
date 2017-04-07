package nl.rabo.app.service.validator;

public class PaymentsValidationError {
	private String errorMessage;
	private FieldName fieldName;
	
	public PaymentsValidationError(FieldName fieldName, String errorMessage) {
		super();
		this.fieldName = fieldName;
		this.errorMessage = errorMessage;
	}

	public PaymentsValidationError(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public FieldName getFieldName() {
		return fieldName;
	}

	public void setFieldName(FieldName fieldName) {
		this.fieldName = fieldName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
