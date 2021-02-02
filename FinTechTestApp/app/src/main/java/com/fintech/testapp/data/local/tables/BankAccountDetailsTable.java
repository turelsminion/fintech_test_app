package com.fintech.testapp.data.local.tables;

import com.fintech.testapp.data.local.model.BankAccountDetailsModel;
import com.fintech.testapp.util.TransactionTypeEnum;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BankAccountDetailsTable extends RealmObject {

	@PrimaryKey
	private int id;

	private String beneficiary;
	private String transactionType;
	private String transactionDate;
	private float transactionCount;

	public BankAccountDetailsTable() {

	}

	public BankAccountDetailsTable(BankAccountDetailsModel model) {
		this.id = model.getId();
		this.beneficiary = model.getBeneficiary();
		this.transactionType = model.getTransactionType().toString();
		this.transactionDate = model.getTransactionDate();
		this.transactionCount = model.getTransactionCount();
	}

	public BankAccountDetailsTable(int id, String beneficiary, TransactionTypeEnum transactionType, String transactionDate, float transactionCount) {
		this.id = id;
		this.beneficiary = beneficiary;
		this.transactionType = transactionType.toString();
		this.transactionDate = transactionDate;
		this.transactionCount = transactionCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public TransactionTypeEnum getTransactionType() {
		return transactionType != null ? TransactionTypeEnum.valueOf(transactionType) : null;
	}

	public void setTransactionType(TransactionTypeEnum transactionType) {
		this.transactionType = transactionType.toString();
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public float getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(float transactionCount) {
		this.transactionCount = transactionCount;
	}

	@Override
	public String toString() {
		return "BankAccountDetailsTable{" +
				"id=" + id +
				", beneficiary='" + beneficiary + '\'' +
				", transactionType='" + transactionType + '\'' +
				", transactionDate=" + transactionDate +
				", transactionCount=" + transactionCount +
				'}';
	}
}
