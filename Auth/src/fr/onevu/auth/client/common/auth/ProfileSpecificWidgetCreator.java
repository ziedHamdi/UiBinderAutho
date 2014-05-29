package fr.onevu.auth.client.common.auth;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.Widget;

import fr.onevu.auth.shared.auth.model.ProfileWidgetRules;
import fr.onevu.auth.shared.auth.model.RuleList;
import fr.onevu.gwt.uibinder.client.factory.ContextSpecificWidgetCreator;

public class ProfileSpecificWidgetCreator implements ContextSpecificWidgetCreator {
	protected static ProfileWidgetRules profileWidgetRules;

	@Override
	public <T> void init(Class<T> clazz, String uiBinder, String fieldName, T field) {
		if (profileWidgetRules == null) {
			System.err
					.println("Potential bug : no rules are still set. You should call ProfileSpecificWidgetCreator.setProfileWidgetRules() before starting to create widgets");
		} else {
			if (!(field instanceof Widget)) {
				throw new AssertionError("attemping to init a non widget field " + field);
			}

			handleWidget((Widget) field, uiBinder, fieldName);
		}
	}

	protected <T extends Widget> void handleWidget(T widget, String uiBinder, String field) {
		RuleList ruleList = profileWidgetRules.getRuleList(uiBinder, field);
		if (ruleList != null) {
			Element element = widget.getElement();
			if (!ruleList.isEnabled())
				element.setAttribute("disabled", "true");
			if (!ruleList.isVisible())
				element.getStyle().setVisibility(Visibility.HIDDEN);

			String styles = ruleList.getStyles();
			if (styles != null && styles.length() > 0) {
				if ('~' == styles.charAt(0))
					widget.setStyleName(styles.substring(1));
				else
					widget.addStyleName(styles);
			}
		}
	}

	public static void setProfileWidgetRules(ProfileWidgetRules profileWidgetRules) {
		ProfileSpecificWidgetCreator.profileWidgetRules = profileWidgetRules;
	}

}