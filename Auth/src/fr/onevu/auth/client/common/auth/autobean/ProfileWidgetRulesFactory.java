package fr.onevu.auth.client.common.auth.autobean;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanFactory.Category;

import fr.onevu.auth.shared.auth.model.ProfileWidgetRules;
import fr.onevu.auth.shared.auth.model.RuleList;

/**
 * add the utility methods to {@link ProfileWidgetRules}
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
@Category(ProfileWidgetRulesCategory.class)
public interface ProfileWidgetRulesFactory extends AutoBeanFactory {
	AutoBean<ProfileWidgetRules> profile();

	AutoBean<RuleList> ruleList();
}
