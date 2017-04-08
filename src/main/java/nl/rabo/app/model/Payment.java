package nl.rabo.app.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Payment {
	
	private Integer reference;

	private String accountNumber;
	
	private String description;
	
	private BigDecimal startBalance;
	
	private BigDecimal mutation;
	
	private BigDecimal endBalance;
	
	@XmlAttribute
	public Integer getReference() {
		return reference;
	}
	public void setReference(Integer reference) {
		this.reference = reference;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}
	public BigDecimal getMutation() {
		return mutation;
	}
	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}
	public BigDecimal getEndBalance() {
		return endBalance;
	}
	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}
	
	@Override
    public String toString() {
        return "Payment [Reference=" + reference + ", Account number="
                + accountNumber + ", Description=" + description + ", Start Balance ="
                + startBalance + ", Muation=" + mutation + " , End Balance =" + endBalance + "]";
    }
	
	
}
