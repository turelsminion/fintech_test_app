package com.fintech.testapp.dagger.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.fintech.testapp.data.AppRepository;
import com.fintech.testapp.data.local.LocalDataStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

	@Provides
	@Singleton
	AppRepository providesAppRepository(Context context, LocalDataStore localDataStore, SharedPreferences sharedPreferences) {
		return new AppRepository(context, localDataStore, sharedPreferences);
	}

	@Provides
	@Singleton
	LocalDataStore providesLocalDataStore() {
		return new LocalDataStore();
	}


}
