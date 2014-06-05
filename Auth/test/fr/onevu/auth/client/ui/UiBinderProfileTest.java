package fr.onevu.auth.client.ui;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import fr.onevu.auth.client.Auth;
import fr.onevu.gwt.uibinder.client.factory.ContextSpecificWidgetCreator;

public class UiBinderProfileTest extends Composite implements HasText {

	private static AuthTestUiBinder uiBinder = GWT.create(AuthTestUiBinder.class);

	interface AuthTestUiBinder extends UiBinder<Widget, UiBinderProfileTest> {
	}

	public UiBinderProfileTest() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	protected ContextSpecificWidgetCreator specificWidgetCreator = GWT.create(ContextSpecificWidgetCreator.class);

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

	@UiField
	Button button;
	@UiField
	Label json;

	public UiBinderProfileTest(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Auth.reloadProfile(initCallback);
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

	protected void onProfileLoadFailure() {
		Window.alert("failed to read profile info");
	}

	protected void onProfileLoad(String result) {
		// apply the newly loaded rule on an existing instance
		specificWidgetCreator.init(Button.class, "fr.onevu.auth.client.ui.UiBinderProfileTest", "button", button);
		json.setText(result);
	}
}
