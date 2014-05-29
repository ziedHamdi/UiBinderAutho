package fr.onevu.auth.client.common.auth.autobean;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;

import fr.onevu.auth.shared.auth.model.ProfileWidgetRules;

public class ProfileWidgetJsonSerializer {
	protected ProfileWidgetRulesFactory factory = GWT.create(ProfileWidgetRulesFactory.class);

	public ProfileWidgetRules deserializeFromJson(String json) {
		ProfileWidgetRulesFactory factory = GWT.create(ProfileWidgetRulesFactory.class);
		AutoBean<ProfileWidgetRules> bean = AutoBeanCodex.decode(factory, ProfileWidgetRules.class, json);
		return bean.as();
	}
}
