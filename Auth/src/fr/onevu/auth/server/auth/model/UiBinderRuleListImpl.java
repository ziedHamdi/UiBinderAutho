package fr.onevu.auth.server.auth.model;

import java.util.HashMap;
import java.util.Map;

import fr.onevu.auth.shared.auth.model.RuleList;
import fr.onevu.auth.shared.auth.model.UiBinderRuleList;

public class UiBinderRuleListImpl implements UiBinderRuleList {
	protected Map<String, RuleList> fieldsRuleListMap = new HashMap<String, RuleList>();
	@Override
	public Map<String, RuleList> getFieldsRuleListMap() {
		return fieldsRuleListMap;
	}

	@Override
	public void setFieldsRuleListMap(Map<String, RuleList> ruleListMap) {
		fieldsRuleListMap = ruleListMap;
	}

	@Override
	public RuleList getFieldRuleList(String field) {
		return fieldsRuleListMap.get(field);
	}

	@Override
	public void putFieldRuleList(String field, RuleList ruleListMap) {
		fieldsRuleListMap.put(field, ruleListMap);
	}

}
