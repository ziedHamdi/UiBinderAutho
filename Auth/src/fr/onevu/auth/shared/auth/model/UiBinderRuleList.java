package fr.onevu.auth.shared.auth.model;

import java.util.Map;

public interface UiBinderRuleList {
	Map<String, RuleList> getFieldsRuleListMap();

	void setFieldsRuleListMap(Map<String, RuleList> ruleList);

	RuleList getFieldRuleList(String field);

	void putFieldRuleList(String field, RuleList ruleList);

}
