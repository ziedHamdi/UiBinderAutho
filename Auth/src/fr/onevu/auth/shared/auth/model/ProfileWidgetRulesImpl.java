package fr.onevu.auth.shared.auth.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Specifies how a field should show in a specific profile
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public class ProfileWidgetRulesImpl implements ProfileWidgetRules {
	/**
	 * this field is here only for information, represents the rule handled by
	 * this instance
	 */
	protected String profile;
	protected Map<String, UiBinderRuleList> uiBinderRules = new HashMap<String, UiBinderRuleList>();

	public ProfileWidgetRulesImpl(String profile) {
		this.profile = profile;
	}


	/* (non-Javadoc)
	 * @see fr.onevu.auth.shared.auth.model.ProfileWidgetRules#getRuleList(java.lang.String, java.lang.String)
	 */
	@Override
	public RuleList getRuleList(String uiBinder, String field) {
		UiBinderRuleList rulesPerField = uiBinderRules.get(uiBinder);
		if (rulesPerField == null)
			return null;

		return rulesPerField.getFieldRuleList(field);
	}

	public void putRuleList(String uiBinder, String field, RuleList ruleList) {
		UiBinderRuleList rulesPerField = uiBinderRules.get(uiBinder);
		if (rulesPerField == null) {
			rulesPerField = new UiBinderRuleListImpl();
			uiBinderRules.put(uiBinder, rulesPerField);
		}

		rulesPerField.putFieldRuleList(field, ruleList);
	}

	/* (non-Javadoc)
	 * @see fr.onevu.auth.shared.auth.model.ProfileWidgetRules#getProfile()
	 */
	@Override
	public String getProfile() {
		return profile;
	}

	@Override
	public Map<String, UiBinderRuleList> getUiBinderRules() {
		return uiBinderRules;
	}

	/* (non-Javadoc)
	 * @see fr.onevu.auth.shared.auth.model.ProfileWidgetRules#setUiBinderRules(java.util.Map)
	 */
	@Override
	public void setUiBinderRules(Map<String, UiBinderRuleList> uiBinderRules) {
		this.uiBinderRules = uiBinderRules;
	}

	@Override
	public void setProfile(String profile) {
		this.profile = profile;
	}
}
