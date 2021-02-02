package com.fintech.testapp.data.local.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fintech.testapp.data.local.tables.BankAccountDetailsTable;
import com.fintech.testapp.util.TransactionTypeEnum;

public class BankAccountDetailsModel implements Parcelable {

	private int id;
	private String beneficiary;
	private String transactionType;
	private String transactionDate;
	private float transactionCount;

	public BankAccountDetailsModel() {
	}

	public BankAccountDetailsModel(BankAccountDetailsTable table) {
		this.id = table.getId();
		this.beneficiary = table.getBeneficiary();
		this.transactionType = table.getTransactionType().toString();
		this.transactionDate = table.getTransactionDate();
		this.transactionCount = table.getTransactionCount();
	}

	public BankAccountDetailsModel(int id, String beneficiary, TransactionTypeEnum transactionType, String transactionDate, float transactionCount) {
		this.id = id;
		this.beneficiary = beneficiary;
		this.transactionType = transactionType.toString();
		this.transactionDate = transactionDate;
		this.transactionCount = transactionCount;
	}

	protected BankAccountDetailsModel(Parcel in) {
		id = in.readInt();
		beneficiary = in.readString();
		transactionType = in.readString();
		transactionDate = in.readString();
		transactionCount = in.readFloat();
	}

	public static final Creator<BankAccountDetailsModel> CREATOR = new Creator<BankAccountDetailsModel>() {
		@Override
		public BankAccountDetailsModel createFromParcel(Parcel in) {
			return new BankAccountDetailsModel(in);
		}

		@Override
		public BankAccountDetailsModel[] newArray(int size) {
			return new BankAccountDetailsModel[size];
		}
	};

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
		return "BankAccountDetailsModel{" +
				"id=" + id +
				", beneficiary='" + beneficiary + '\'' +
				", transactionType='" + transactionType + '\'' +
				", transactionDate=" + transactionDate +
				", transactionCount=" + transactionCount +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(beneficiary);
		dest.writeString(transactionType);
		dest.writeString(transactionDate);
		dest.writeFloat(transactionCount);
	}
}
