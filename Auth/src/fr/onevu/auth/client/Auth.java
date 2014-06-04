package fr.onevu.auth.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import fr.onevu.auth.client.common.auth.ProfileSpecificWidgetCreator;
import fr.onevu.auth.client.common.auth.autobean.ProfileWidgetJsonSerializer;
import fr.onevu.auth.client.ui.AuthTest;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Auth implements EntryPoint {

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
				ProfileWidgetJsonSerializer jsonSerializer = new ProfileWidgetJsonSerializer();
				System.out.println("request answer: " + result);
				ProfileSpecificWidgetCreator.setProfileWidgetRules(jsonSerializer.deserializeFromJson(result.trim()));
				callback.onSuccess(result);
			}

			@Override
			public void onFailure(String reason) {
				callback.onFailure(reason);
			}
		};
		doGet("profile.jsp", callbackProxy);
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// readProfileFromJsp();
		Callback<String, String> initCallback = new Callback<String, String>() {

			@Override
			public void onSuccess(String result) {
				onProfileLoad();
			}

			@Override
			public void onFailure(String reason) {
				onProfileLoadFailure();
			}
		};
		reloadProfile(initCallback);
	}

	protected void onProfileLoadFailure() {
		Window.alert("failed to read profile info");
	}

	protected void onProfileLoad() {
		AuthTest authTest = new AuthTest();
		RootPanel.get("widget").add(authTest);
	}

}
