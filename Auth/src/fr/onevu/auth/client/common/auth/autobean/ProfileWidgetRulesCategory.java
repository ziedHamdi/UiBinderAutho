package fr.onevu.auth.client.common.auth.autobean;

import com.google.web.bindery.autobean.shared.AutoBean;

import fr.onevu.auth.shared.auth.model.ProfileWidgetRules;
import fr.onevu.auth.shared.auth.model.RuleList;
import fr.onevu.auth.shared.auth.model.UiBinderRuleList;

public class ProfileWidgetRulesCategory {
	public static RuleList getRuleList(AutoBean<ProfileWidgetRules> instance, String uiBinder, String field) {
		UiBinderRuleList rulesPerField = instance.as().getUiBinderRules().get(uiBinder);
		if (rulesPerField == null)
			return null;

		return rulesPerField.getFieldRuleList(field);
	}

	public static RuleList getFieldRuleList(AutoBean<UiBinderRuleList> instance, String field) {
		return instance.as().getFieldsRuleListMap().get(field);
	}

	public static RuleList putFieldRuleList(AutoBean<UiBinderRuleList> instance, String field, RuleList ruleList) {
		return instance.as().getFieldsRuleListMap().put(field, ruleList);
	}
}
