package fr.onevu.auth.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import fr.onevu.auth.client.common.auth.ProfileSpecificWidgetCreator;
import fr.onevu.auth.client.common.auth.autobean.ProfileWidgetJsonSerializer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Auth implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel profilePanel = RootPanel.get("profile");
		if (profilePanel == null)
			return;

		String rulesJson = profilePanel.getElement().getInnerHTML().trim();
		if (rulesJson == null || 0 == rulesJson.length())
			return;

		ProfileWidgetJsonSerializer jsonSerializer = new ProfileWidgetJsonSerializer();
		ProfileSpecificWidgetCreator.setProfileWidgetRules(jsonSerializer.deserializeFromJson(rulesJson));
		// AuthTest authTest = new AuthTest();
		// RootPanel.get("widget").add(authTest);
	}
}
