package fr.onevu.auth.shared.auth.model;

import java.util.Map;

public interface ProfileWidgetRules {

	RuleList getRuleList(String uiBinder, String field);

	String getProfile();

	void setUiBinderRules(Map<String, UiBinderRuleList> uiBinderRules);

	Map<String, UiBinderRuleList> getUiBinderRules();

	void setProfile(String profile);

}