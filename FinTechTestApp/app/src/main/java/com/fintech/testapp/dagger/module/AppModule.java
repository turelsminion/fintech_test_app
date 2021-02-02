package com.fintech.testapp.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.fintech.testapp.MainApplication;
import com.fintech.testapp.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

	private MainApplication mApplication;

	public AppModule(MainApplication mApplication) {
		this.mApplication = mApplication;
	}

	@Provides
	@Singleton
	MainApplication provideApplication() {
		return mApplication;
	}

	@Provides
	@Singleton
	SharedPreferences provideSharedPrefs(MainApplication app) {
		return app.getSharedPreferences(app.getString(R.string.shared_prefs), Context.MODE_PRIVATE);
	}

	@Provides
	@NonNull
	@Singleton
	Context provideContext() {
		return mApplication;
	}

}
