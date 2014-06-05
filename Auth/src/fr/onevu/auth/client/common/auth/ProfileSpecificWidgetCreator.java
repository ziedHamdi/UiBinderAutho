package fr.onevu.auth.client.common.auth;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

import fr.onevu.auth.client.common.concept.ui.AuthRulesAware;
import fr.onevu.auth.client.common.concept.ui.WidgetSpecificAuthHandler;
import fr.onevu.auth.shared.auth.model.ProfileWidgetRules;
import fr.onevu.auth.shared.auth.model.RuleList;
import fr.onevu.gwt.uibinder.client.factory.ContextSpecificWidgetCreator;

/**
 * handles a widget in three phases
 * <ol>
 * <li>if the current widget implements {@link AuthRulesAware}, it will be given
 * the handle to process itself alone through the call to
 * {@link AuthRulesAware#handleWidget(String, String, RuleList)}. If the widget
 * knows it handled all {@link RuleList} params, it should return false to
 * interrupt this method and skip the next phases</li>
 * <li>all {@value #widgetSpecificAuthHandlers} are executed, each must check if
 * the current widget is his target, and each may skip other handler by
 * returning an interruption code
 * {@link WidgetSpecificAuthHandler#STOP_WITH_HANDLERS} or/and exit the method
 * by returning a {@value WidgetSpecificAuthHandler#STOP_WITH_STANDARD_HANDLING}
 * </li>
 * <li>finally if both phases before don't interrupt the method, a standard
 * processing of the {@link RuleList} is done</li>
 * </ol>
 * 
 * @see AuthRulesAware
 * @see WidgetSpecificAuthHandler
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public class ProfileSpecificWidgetCreator implements ContextSpecificWidgetCreator {
	protected static ProfileWidgetRules profileWidgetRules;
	protected static List<WidgetSpecificAuthHandler> widgetSpecificAuthHandlers = new ArrayList<WidgetSpecificAuthHandler>();

	@Override
	public <T> void init(Class<T> clazz, String uiBinder, String fieldName, T field) {
		if (profileWidgetRules == null) {
			System.err
					.println("Potential bug : no rules are still set. You should call ProfileSpecificWidgetCreator.setProfileWidgetRules() before starting to create widgets");
		} else {
			if (field instanceof Widget)
				handleWidget((Widget) field, uiBinder, fieldName);
		}
	}

	/**
	 * handles a widget in three phases
	 * <ol>
	 * <li>if the current widget implements {@link AuthRulesAware}, it will be
	 * given the handle to process itself alone through the call to
	 * {@link AuthRulesAware#handleWidget(String, String, RuleList)}. If the
	 * widget knows it handled all {@link RuleList} params, it should return false
	 * to interrupt this method and skip the next phases</li>
	 * <li>all {@value #widgetSpecificAuthHandlers} are executed, each must check
	 * if the current widget is his target, and each may skip other handler by
	 * returning an interruption code
	 * {@link WidgetSpecificAuthHandler#STOP_WITH_HANDLERS} or/and exit the method
	 * by returning a
	 * {@value WidgetSpecificAuthHandler#STOP_WITH_STANDARD_HANDLING}</li>
	 * <li>finally if both phases before don't interrupt the method, a standard
	 * processing of the {@link RuleList} is done</li>
	 * </ol>
	 * 
	 * @param widget
	 * @param uiBinder
	 * @param field
	 * 
	 * @see AuthRulesAware
	 * @see WidgetSpecificAuthHandler
	 */
	protected <T extends Widget> void handleWidget(T widget, String uiBinder, String field) {
		RuleList ruleList = profileWidgetRules.getRuleList(uiBinder, field);
		if (ruleList == null)
			return;

		if (widget instanceof AuthRulesAware) {
			if (!((AuthRulesAware) widget).handleWidget(uiBinder, field, ruleList))
				return;
		}

		if (ruleList.getItemsProperties() != null) {
			boolean processDefaultBehavior = handleWidgetSpecifically(widget, uiBinder, field, ruleList);
			if (!processDefaultBehavior)
				return;
		}

		Element element = widget.getElement();
		if (!ruleList.isEnabled())
			element.setAttribute("disabled", "true");
		if (!ruleList.isVisible())
			element.getStyle().setVisibility(Visibility.HIDDEN);

		String styles = ruleList.getStyles();
		if (styles != null && styles.length() > 0) {
			if ('~' == styles.charAt(0))
				widget.setStyleName(styles.substring(1));
			else
				widget.addStyleName(styles);
		}
	}

	/**
	 * This method uses the list of {@value #widgetSpecificAuthHandlers} to check
	 * if any of them handles the current widget. You may register special
	 * handlers for targeted widgets through
	 * {@link #add(WidgetSpecificAuthHandler)}
	 * 
	 * @param widget
	 * @param uiBinder
	 * @param field
	 * @param ruleList
	 * @return if any handler returns
	 *         WidgetSpecificAuthHandler.STOP_WITH_STANDARD_HANDLING this method
	 *         will return false to forbid to the rot method to continue
	 *         processing
	 */
	protected <T extends Widget> boolean handleWidgetSpecifically(T widget, String uiBinder, String field, RuleList ruleList) {
		boolean toReturn = true;
		for (WidgetSpecificAuthHandler handler : widgetSpecificAuthHandlers) {
			int handlingResult = handler.handleWidget(widget, uiBinder, field, ruleList);
			if ((handlingResult & WidgetSpecificAuthHandler.STOP_WITH_STANDARD_HANDLING) == WidgetSpecificAuthHandler.STOP_WITH_STANDARD_HANDLING)
				toReturn = false;
			if ((handlingResult & WidgetSpecificAuthHandler.STOP_WITH_HANDLERS) == WidgetSpecificAuthHandler.STOP_WITH_HANDLERS)
				break;
		}
		return toReturn;
	}

	public static void setProfileWidgetRules(ProfileWidgetRules profileWidgetRules) {
		ProfileSpecificWidgetCreator.profileWidgetRules = profileWidgetRules;
	}

	/**
	 * NEXT handlers should be able to subscribe for a given uiBinder or a given
	 * field in a uiBinder, a package name or a name pattern. However a regular
	 * expression would be too much processing for the startup
	 * 
	 * @param e
	 * @return
	 */
	public boolean add(WidgetSpecificAuthHandler e) {
		return widgetSpecificAuthHandlers.add(e);
	}

	public WidgetSpecificAuthHandler get(int index) {
		return widgetSpecificAuthHandlers.get(index);
	}

	public WidgetSpecificAuthHandler set(int index, WidgetSpecificAuthHandler element) {
		return widgetSpecificAuthHandlers.set(index, element);
	}

	public void add(int index, WidgetSpecificAuthHandler element) {
		widgetSpecificAuthHandlers.add(index, element);
	}

}