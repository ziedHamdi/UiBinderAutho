package fr.onevu.auth.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import fr.onevu.auth.client.ui.UiBinderProfileTest;

public class AuthTest implements EntryPoint {

	@Override
	public void onModuleLoad() {
		UiBinderProfileTest authTest = new UiBinderProfileTest();
		RootPanel.get("widget").add(authTest);
	}

}
