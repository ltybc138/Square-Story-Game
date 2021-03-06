package com.denis.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.denis.game.Main;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = true;
		config.useCompass = true;
		config.useGLSurfaceView20API18 = true;
		config.useGyroscope = true;
		config.useWakelock = true;
		config.useImmersiveMode = true;
		initialize(new Main(), config);
	}
}
