package fr.onevu.auth.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import fr.onevu.auth.client.ui.UiBinderProfileTest;

public class AuthTest implements EntryPoint {

	@Override
	public void onModuleLoad() {
		Callback<String, String> initCallback = new Callback<String, String>() {

			@Override
			public void onSuccess(String result) {
				onProfileLoad(result);
			}

			@Override
			public void onFailure(String reason) {
				onProfileLoadFailure();
			}
		};
		Auth.reloadProfile(initCallback);
	}

	protected void onProfileLoadFailure() {
		Window.alert("failed to read profile info");
	}

	protected void onProfileLoad(String result) {
		UiBinderProfileTest authTest = new UiBinderProfileTest();
		authTest.setProfileJson(result);
		RootPanel.get("widget").add(authTest);
	}
}
