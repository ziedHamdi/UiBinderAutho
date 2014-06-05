package fr.onevu.auth.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.RootPanel;

import fr.onevu.auth.client.common.auth.ProfileSpecificWidgetCreator;
import fr.onevu.auth.client.common.auth.autobean.ProfileWidgetJsonSerializer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Auth implements EntryPoint {
	public static final String PROFILE_JSP = "modules/profile.jsp";
	public static final String PROFILE_DIV = "_profile_";

	public static void doGet(String url, final Callback<String, String> callback) {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

		try {
			builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					callback.onFailure(exception.getMessage());
				}

				public void onResponseReceived(Request request, Response response) {
					callback.onSuccess(response.getText());
				}
			});
		} catch (RequestException ex) {
			throw new RuntimeException("failed to load url : " + url, ex);
		}
	}

	public static void reloadProfile(final Callback<String, String> callback) {
		final Callback<String, String> callbackProxy = new Callback<String, String>() {

			@Override
			public void onSuccess(String result) {
				setProfileRulesFromJson(result);
				callback.onSuccess(result);
			}

			@Override
			public void onFailure(String reason) {
				callback.onFailure(reason);
			}
		};
		doGet(PROFILE_JSP, callbackProxy);
	}

	protected static void setProfileRulesFromJson(String result) {
		ProfileWidgetJsonSerializer jsonSerializer = new ProfileWidgetJsonSerializer();
		ProfileSpecificWidgetCreator.setProfileWidgetRules(jsonSerializer.deserializeFromJson(result.trim()));
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get(PROFILE_DIV);
		if (rootPanel == null) {
			System.err.println("You must include a div named '" + PROFILE_DIV + "' containing the json rules. This is recommended for performance concerns");
			return;
		}
		String profileJson = rootPanel.getElement().getInnerHTML();
		if (profileJson.trim().length() > 0) {
			setProfileRulesFromJson(profileJson);
		}
	}

}
