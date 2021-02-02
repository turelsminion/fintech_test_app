package com.fintech.testapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fintech.testapp.MainApplication;
import com.fintech.testapp.data.AppRepository;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

	protected String TAG = "BaseActivity";

	@Inject
	AppRepository mRepository;

	protected abstract int getLayoutResource();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResource());
		MainApplication.getAppComponent().inject(this);
	}

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(newBase);
	}
}
