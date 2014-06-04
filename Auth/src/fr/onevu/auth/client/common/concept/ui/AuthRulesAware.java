package fr.onevu.auth.client.common.concept.ui;

import com.google.gwt.uibinder.client.UiBinder;

import fr.onevu.auth.shared.auth.model.RuleList;

/**
 * Widgets can implement this interface to get notified directly that certain
 * {@link RuleList} apply to them. If a widget present in a UiBinder xml
 * implements this interface (or the Composite widget it self), its
 * {@link #handleWidget(String, String, RuleList)} method will get called as
 * soon as there are {@link RuleList} defined for it
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public interface AuthRulesAware {

	/**
	 * gets called just before returning from the
	 * {@link UiBinder#createAndBindUi(Object)} method
	 * 
	 * @param uiBinder
	 *          the xml file representing/containing the widget
	 * @param field
	 *          the field is empty if a widget is the root of the UiBinder,
	 *          elsewhere the field name is passed
	 * @param ruleList
	 *          the rules applied to the widget implementing this interface.
	 *          Please notice that if there are no rules, this method will not get
	 *          called with a null rule. This is intentionally thought for
	 *          performance. Since this api is called only at widget creation, we
	 *          can rely on the "apply defaults if nothing specified" rule
	 * @return true ifaoif you still want the default behavior to be applied on
	 *         your widget after this method was called
	 */
	boolean handleWidget(String uiBinder, String field, RuleList ruleList);
}
