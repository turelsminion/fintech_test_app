package com.fintech.testapp.data.local;

import com.fintech.testapp.MainApplication;
import com.fintech.testapp.data.local.model.BankAccountDetailsModel;
import com.fintech.testapp.data.local.model.BankAccountModel;
import com.fintech.testapp.data.local.tables.BankAccountDetailsTable;
import com.fintech.testapp.data.local.tables.BankAccountTable;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class LocalDataStore {

	private static final String TAG = "LocalDataStore";
	private Realm realm;

	public LocalDataStore() {
		MainApplication.getAppComponent().inject(this);
		realm = Realm.getDefaultInstance();
	}

	public void saveBankAccount(List<BankAccountModel> bankAccountModelList) {
		if (realm == null)
			realm = Realm.getDefaultInstance();

		List<BankAccountTable> list = new ArrayList<>();
		for (BankAccountModel bam : bankAccountModelList) {
			BankAccountTable bankAccountTable = new BankAccountTable(bam);
			list.add(bankAccountTable);
		}
		realm.executeTransaction(realm -> realm.insertOrUpdate(list));
	}

	public void clearAllDataBase() {
		if (realm == null)
			realm = Realm.getDefaultInstance();
		realm.beginTransaction();
		realm.deleteAll();
		realm.commitTransaction();
	}

	public List<BankAccountModel> getBankAccounts() {
		if (realm == null)
			realm = Realm.getDefaultInstance();
		RealmResults<BankAccountTable> realmResults = realm.where(BankAccountTable.class).findAll();
		List<BankAccountModel> list = new ArrayList<>();
		for (BankAccountTable bat : realmResults)
			list.add(new BankAccountModel(bat));
		return list;
	}

}
