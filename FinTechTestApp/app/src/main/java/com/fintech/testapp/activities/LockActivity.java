package com.fintech.testapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.fintech.testapp.MainApplication;
import com.fintech.testapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LockActivity extends BaseActivity {

	private final static String TAG = "LockActivity";

	public static Intent getNewIntent(Context context) {
		return new Intent(context, LockActivity.class);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_lock;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ButterKnife.bind(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@OnClick(R.id.unlock)
	public void onTextClick() {
		this.finish();
	}
}
