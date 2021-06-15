package com.examples.spring.webflux.client;

import org.springframework.data.annotation.Id;

public class Loan {

	@Id
	private Integer id;
	private String loantype;
	private Integer loanamount;
	private String date;
	private float roi;
	private Integer loanduration;
	
	public Loan() {
		
	}
	
	public Loan(Integer id, String loantype, Integer loanamount, String date, float roi, Integer loanduration) {
		super();
		this.id = id;
		this.loantype = loantype;
		this.loanamount = loanamount;
		this.date = date;
		this.roi = roi;
		this.loanduration = loanduration;
	}
	
	
	public Integer getcustomerId() {
		return id;
	}
	public void setcustomerId(Integer id) {
		this.id = id;
	}
	public String getLoanType() {
		return loantype;
	}
	public void setLoanType(String loantype) {
		this.loantype = loantype;
	}
	public Integer getLoanAmount() {
		return loanamount;
	}
	public void setLoanAmount(Integer loanamount) {
		this.loanamount = loanamount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getROI() {
		return roi;
	}
	public void setROI(float roi) {
		this.roi = roi;
	}
	public Integer getLoanDuration() {
		return loanduration;
	}
	public void setLoanDuration(Integer loanduration) {
		this.loanduration = loanduration;
	}
	@Override
	public String toString() {
		return "LoanDetails [customerId="+id+"loanType=" + loantype + ", loanAmount=" + loanamount + ", date=" + date + ", ROI=" + roi
				+ ", loanDuration=" + loanduration + "]";
	}
}