package com.fintech.testapp.dagger.component;

import androidx.annotation.NonNull;

import com.fintech.testapp.MainApplication;
import com.fintech.testapp.activities.BaseActivity;
import com.fintech.testapp.dagger.module.AppModule;
import com.fintech.testapp.dagger.module.DataModule;
import com.fintech.testapp.data.local.LocalDataStore;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

	void inject(@NonNull BaseActivity activity);

	void inject(@NonNull MainApplication application);

	void inject(@NonNull LocalDataStore localDataStore);

}
