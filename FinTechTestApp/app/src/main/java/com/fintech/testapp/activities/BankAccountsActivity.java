package com.fintech.testapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.testapp.R;
import com.fintech.testapp.data.local.model.BankAccountDetailsModel;
import com.fintech.testapp.data.local.model.BankAccountModel;
import com.fintech.testapp.util.CardTypeEnum;
import com.fintech.testapp.util.CurrencyTypeEnum;
import com.fintech.testapp.util.TransactionTypeEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankAccountsActivity extends BaseActivity {

	private static final String TAG = BankAccountsActivity.class.getSimpleName();

	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;

	private List<BankAccountModel> bankAccountList;
	private View.OnClickListener onItemClickListener;
	private BankAccountsAdapter adapter;

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_bank_accounts;
	}

	public static Intent getNewIntent(Context context) {
		return new Intent(context, BankAccountsActivity.class);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);

		onItemClickListener = v -> {
			RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
			int position = viewHolder.getAdapterPosition();

			BankAccountModel model = bankAccountList.get(position);
			startActivity(BankAccountActivity.getNewIntent(this, model));
		};

		//init data
		this.bankAccountList = mRepository.getLocalDataStore().getBankAccounts();
		if (this.bankAccountList == null || this.bankAccountList.size() == 0)
			initDummyData();

		//setup recycler view
		adapter = new BankAccountsAdapter(this, new ArrayList<>());
		LinearLayoutManager llm = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(llm);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(adapter);
		adapter.setItemClickListener(onItemClickListener);
		adapter.updateBankAccountsListItems(this.bankAccountList);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void initDummyData() {
		this.bankAccountList = new ArrayList<>();
		List<BankAccountDetailsModel> detailList1 = new ArrayList<>();
		detailList1.add(new BankAccountDetailsModel(0, "INFOCOM > CHISINAU MD", TransactionTypeEnum.PAYMENT, new Date().toString(), (float) 100.2));
		detailList1.add(new BankAccountDetailsModel(1, "Some Person", TransactionTypeEnum.P2P, new Date().toString(), (float) 150.2));

		List<BankAccountDetailsModel> detailList2 = new ArrayList<>();
		detailList2.add(new BankAccountDetailsModel(2, "", TransactionTypeEnum.WITHDRAW, new Date().toString(), (float) 100.2));
		detailList2.add(new BankAccountDetailsModel(3, "Some online shop", TransactionTypeEnum.PAYMENT, new Date().toString(), (float) 300.33));
		detailList2.add(new BankAccountDetailsModel(4, "", TransactionTypeEnum.SUPPLY, new Date().toString(), (float) 500.0));

		List<BankAccountDetailsModel> detailList3 = new ArrayList<>();
		detailList3.addAll(detailList1);
		detailList3.addAll(detailList2);

		bankAccountList.add(new BankAccountModel(1, CardTypeEnum.VISA, "0000 0000 0000 0002", (float) 423.0, CurrencyTypeEnum.EUR, detailList2));
		bankAccountList.add(new BankAccountModel(0, CardTypeEnum.MASTER_CARD, "0000 0000 0000 0001", (float) 0.26, CurrencyTypeEnum.MDL, detailList1));
		bankAccountList.add(new BankAccountModel(2, CardTypeEnum.MASTER_CARD, "0000 0000 0000 0003", (float) 0.32, CurrencyTypeEnum.USD, detailList3));

		Collections.sort(bankAccountList, (o1, o2) -> o1.getId() - o2.getId());

		mRepository.getLocalDataStore().saveBankAccount(bankAccountList);
	}
}
