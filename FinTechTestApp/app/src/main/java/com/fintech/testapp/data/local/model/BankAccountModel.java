package com.fintech.testapp.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fintech.testapp.data.local.tables.BankAccountDetailsTable;
import com.fintech.testapp.data.local.tables.BankAccountTable;
import com.fintech.testapp.util.CardTypeEnum;
import com.fintech.testapp.util.CurrencyTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BankAccountModel implements Parcelable {

	private int id;
	private String cardType;
	private String cardNumber;
	private float balance;
	private String currencyType;
	private List<BankAccountDetailsModel> detailsList;

	public BankAccountModel() {
	}

	public BankAccountModel(BankAccountTable table) {
		this.id = table.getId();
		this.cardType = table.getCardType().toString();
		this.cardNumber = table.getCardNumber();
		this.balance = table.getBalance();
		this.currencyType = table.getCurrencyType().toString();
		this.detailsList = new ArrayList<>();
		for (BankAccountDetailsTable badm : table.getDetailsList())
			this.detailsList.add(new BankAccountDetailsModel(badm));
	}

	public BankAccountModel(int id, CardTypeEnum cardType, String cardNumber, float balance, CurrencyTypeEnum currencyType, List<BankAccountDetailsModel> detailsList) {
		this.id = id;
		this.cardType = cardType.toString();
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.currencyType = currencyType.toString();
		this.detailsList = detailsList;
	}

	protected BankAccountModel(Parcel in) {
		id = in.readInt();
		cardType = in.readString();
		cardNumber = in.readString();
		balance = in.readFloat();
		currencyType = in.readString();
		detailsList = new ArrayList<>();
		in.readList(detailsList, BankAccountDetailsModel.class.getClassLoader());
	}

	public static final Creator<BankAccountModel> CREATOR = new Creator<BankAccountModel>() {
		@Override
		public BankAccountModel createFromParcel(Parcel in) {
			return new BankAccountModel(in);
		}

		@Override
		public BankAccountModel[] newArray(int size) {
			return new BankAccountModel[size];
		}
	};

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

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public List<BankAccountDetailsModel> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<BankAccountDetailsModel> detailsList) {
		this.detailsList = detailsList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BankAccountModel)) return false;
		BankAccountModel that = (BankAccountModel) o;
		return id == that.id &&
				Float.compare(that.balance, balance) == 0 &&
				Objects.equals(cardType, that.cardType) &&
				Objects.equals(cardNumber, that.cardNumber) &&
				Objects.equals(currencyType, that.currencyType) &&
				Objects.equals(detailsList, that.detailsList);
	}

	@Override
	public String toString() {
		return "BankAccountModel{" +
				"id=" + id +
				", cardType='" + cardType + '\'' +
				", cardNumber='" + cardNumber + '\'' +
				", balance=" + balance +
				", currencyType='" + currencyType + '\'' +
				", detailsList=" + detailsList +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(cardType);
		dest.writeString(cardNumber);
		dest.writeFloat(balance);
		dest.writeString(currencyType);
		dest.writeList(detailsList);
	}
}
