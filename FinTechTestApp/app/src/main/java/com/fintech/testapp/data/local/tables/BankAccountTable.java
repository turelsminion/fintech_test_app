package com.fintech.testapp.data.local.tables;

import com.fintech.testapp.data.local.model.BankAccountDetailsModel;
import com.fintech.testapp.data.local.model.BankAccountModel;
import com.fintech.testapp.util.CardTypeEnum;
import com.fintech.testapp.util.CurrencyTypeEnum;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BankAccountTable extends RealmObject {

	@PrimaryKey
	private int id;

	private String cardType;
	private String cardNumber;
	private float balance;
	private String currencyType;
	private RealmList<BankAccountDetailsTable> detailsList;

	public BankAccountTable() {

	}

	public BankAccountTable(BankAccountModel model) {
		this.id = model.getId();
		this.cardType = model.getCardType().toString();
		this.cardNumber = model.getCardNumber();
		this.balance = model.getBalance();
		this.currencyType = model.getCurrencyType().toString();
		this.detailsList = new RealmList<>();
		for (BankAccountDetailsModel badm : model.getDetailsList())
			this.detailsList.add(new BankAccountDetailsTable(badm));
	}

	public BankAccountTable(int id, CardTypeEnum cardType, String cardNumber, float balance, CurrencyTypeEnum currencyType, RealmList<BankAccountDetailsTable> detailsList) {
		this.id = id;
		this.cardType = cardType.toString();
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.currencyType = currencyType.toString();
		this.detailsList = detailsList;
	}

	public RealmList<BankAccountDetailsTable> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(RealmList<BankAccountDetailsTable> detailsList) {
		this.detailsList = detailsList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CardTypeEnum getCardType() {
		return this.cardType != null ? CardTypeEnum.valueOf(cardType) : null;
	}

	public void setCardType(CardTypeEnum cardType) {
		this.cardType = cardType.toString();
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCurrencyType(CurrencyTypeEnum currencyType) {
		this.currencyType = currencyType.toString();
	}

	public CurrencyTypeEnum getCurrencyType() {
		return this.currencyType != null ? CurrencyTypeEnum.valueOf(currencyType) : null;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankAccountTable{" +
				"id=" + id +
				", cardType='" + cardType + '\'' +
				", cardNumber='" + cardNumber + '\'' +
				", balance=" + balance +
				", currencyType='" + currencyType + '\'' +
				", detailsList=" + detailsList +
				'}';
	}
}
