package fr.onevu.auth.client.common.concept.ui;

import com.google.gwt.user.client.ui.Widget;

import fr.onevu.auth.client.common.auth.ProfileSpecificWidgetCreator;
import fr.onevu.auth.shared.auth.model.RuleList;

/**
 * This interface is used in special cases where the Ui has internal fields and
 * constraints that have to be managed specifically.
 * 
 * In those specific cases, it is preferable to extract the logic out from the
 * generic
 * {@link ProfileSpecificWidgetCreator#init(Class, String, String, Object)} to
 * let a WidgetSpecificAuthHandler handle it.
 * 
 * {@link ProfileSpecificWidgetCreator} detects when a widget has a through the
 * {@link RuleList} associated to it. it then transmits it to all of
 * {@link WidgetSpecificAuthHandler} instances that can handle it. Many handlers
 * may accept to handle the same widget
 * 
 * Instead of a chain of responsibility, a list of handlers is hold in a list
 * filled with
 * {@link ProfileSpecificWidgetCreator#add(WidgetSpecificAuthHandler)}
 * 
 * 
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public interface WidgetSpecificAuthHandler {

	/**
	 * Indicates that all the work was done by this handler, but doesn't answer if
	 * the standard processing has to continue (by default it continues)
	 */
	public static int STOP_WITH_HANDLERS = 1;
	/**
	 * Indicates that the work normally made by the standard process was done by
	 * this handler, it therefore prevents the standard processing to execute, but
	 * doesn't prevent other handlers from working on the widget
	 */
	public static int STOP_WITH_STANDARD_HANDLING = 2;

	/**
	 * this method handles the current widget with the information provided in
	 * {@link RuleList}.
	 * 
	 * The widget might be used in different places, so the two first parameters
	 * specify in which context the currently processed widget is.
	 * 
	 * @param widget
	 *          the widget to be manipulated
	 * @param uiBinder
	 *          file name
	 * @param field
	 *          field name
	 * @param ruleList
	 *          how to change widget
	 * @return 0 to continue or {@link #STOP_WITH_HANDLERS} &
	 *         {@link #STOP_WITH_STANDARD_HANDLING} for flags on how to continue
	 */
	<T extends Widget> int handleWidget(T widget, String uiBinder, String field, RuleList ruleList);
}
