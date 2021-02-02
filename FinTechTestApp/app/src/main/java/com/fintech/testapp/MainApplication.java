package com.fintech.testapp;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fintech.testapp.activities.LockActivity;
import com.fintech.testapp.dagger.component.AppComponent;
import com.fintech.testapp.dagger.component.DaggerAppComponent;
import com.fintech.testapp.dagger.module.AppModule;
import com.fintech.testapp.dagger.module.DataModule;
import com.fintech.testapp.data.AppRepository;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public final class MainApplication extends Application {

	//Constants
	private static final String TAG = "MainApplication";
	public static MainApplication INSTANCE;

	//State
	private static AppComponent mAppComponent;

	//Activity state
	private int frameCount = 0;
	private boolean fromBackground = false;

	@Inject
	AppRepository mAppRepository;

	public static AppComponent getAppComponent() {
		return mAppComponent;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		INSTANCE = this;

		Realm.init(this);
		RealmConfiguration config = new RealmConfiguration.Builder()
				.deleteRealmIfMigrationNeeded()
				.allowWritesOnUiThread(true)
				.build();
		Realm.setDefaultConfiguration(config);

		mAppComponent = DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.dataModule(new DataModule())
				.build();

		registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

			@Override
			public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

			}

			@Override
			public void onActivityStarted(@NonNull Activity activity) {
				fromBackground = frameCount == 0;
				if(!(activity instanceof LockActivity)){
					if (fromBackground) {
						Intent i = new Intent(activity, LockActivity.class);
						activity.startActivity(i);
					}
				}
				frameCount++;
			}

			@Override
			public void onActivityResumed(@NonNull Activity activity) {

			}

			@Override
			public void onActivityPaused(@NonNull Activity activity) {

			}

			@Override
			public void onActivityStopped(@NonNull Activity activity) {
				frameCount--;
			}

			@Override
			public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

			}

			@Override
			public void onActivityDestroyed(@NonNull Activity activity) {

			}
		});
	}
}
