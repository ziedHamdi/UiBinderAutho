package fr.onevu.auth.shared.auth.model;

import java.util.Map;

/**
 * Binds a profile to its Rules. the rules are organized by uiBinder file name
 * and field name. Knowing that each UiBinder has many fields
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public interface ProfileWidgetRules {

	/**
	 * Utility method to get a specific widget rule
	 * 
	 * @param uiBinder
	 * @param field
	 * @return
	 */
	RuleList getRuleList(String uiBinder, String field);

	/**
	 * metadata information about the current profile
	 * 
	 * @return
	 */
	String getProfile();

	/**
	 * Raw method to set the rules
	 * 
	 * @param uiBinderRules
	 */
	void setUiBinderRules(Map<String, UiBinderRuleList> uiBinderRules);

	/**
	 * get back all the previously set rules
	 * 
	 * @return
	 */
	Map<String, UiBinderRuleList> getUiBinderRules();

	void setProfile(String profile);

}