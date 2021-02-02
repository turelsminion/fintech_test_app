package com.fintech.testapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.fintech.testapp.activities.LockActivity;
import com.fintech.testapp.data.local.LocalDataStore;

public class AppRepository {

	private static final String ACTIVITY = "ACTIVITY";

	private Context context;
	private LocalDataStore localDataStore;
	private SharedPreferences sharedPreferences;

	public AppRepository(Context context, LocalDataStore localDataStore, SharedPreferences sharedPreferences) {
		this.context = context;
		this.localDataStore = localDataStore;
		this.sharedPreferences = sharedPreferences;
	}

	public LocalDataStore getLocalDataStore() {
		return localDataStore;
	}

	private void clearPreferences() {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.apply();
	}

	public void storeClassCanonicalName(String className) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(ACTIVITY, className);
		editor.apply();
	}

	public String getClassCanonicalName() {
		return sharedPreferences.getString(ACTIVITY, LockActivity.class.getCanonicalName());
	}
}
