package com.winter.app.accounts;

import java.sql.Date;

public class AccountDTO {

	private String accountNum;
	private String userName;
	private Long productNum;
	private Long accounBalance;
	private Date accountDate;
	
	
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getProductNum() {
		return productNum;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}
	public Long getAccounBalance() {
		return accounBalance;
	}
	public void setAccounBalance(Long accounBalance) {
		this.accounBalance = accounBalance;
	}
	public Date getAccountDate() {
		return accountDate;
	}
	public void setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

}
