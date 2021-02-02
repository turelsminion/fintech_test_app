package com.fintech.testapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fintech.testapp.R;
import com.fintech.testapp.data.local.model.BankAccountDetailsModel;
import com.fintech.testapp.data.local.model.BankAccountModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankAccountActivity extends BaseActivity {

	private final static String MODEL = "MODEL";
	public final static String DATE_PATTERN = "yyyy-MM-dd'T'hh:mm:ss.SSS+SSSS";

	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;

	private BankAccountModel model;
	private BankAccountAdapter adapter;

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_bank_account;
	}

	public static Intent getNewIntent(Context context, BankAccountModel model) {
		Intent intent = new Intent(context, BankAccountActivity.class);
		Bundle args = new Bundle();
		args.putParcelable(MODEL, model);
		intent.putExtras(args);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);
		model = (BankAccountModel) getIntent().getExtras().getParcelable(MODEL);

		adapter = new BankAccountAdapter(this, model.getDetailsList(), model.getCardNumber());
		LinearLayoutManager llm = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(llm);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(adapter);
	}

	@OnClick(R.id.back)
	public void onBackClick() {
		this.finish();
	}
}
