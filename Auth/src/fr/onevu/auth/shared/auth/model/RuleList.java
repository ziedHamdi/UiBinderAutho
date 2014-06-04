package fr.onevu.auth.shared.auth.model;

import java.util.Map;

/**
 * The rule list for a widget, the name rule list is showing there might be a
 * list of rules to apply, for example: show as disabled and highlighted through
 * css.
 * 
 * In the future, some additional declarative actions might be added to this
 * interface (onclick, onHover, etc...)
 * 
 * Some uiBinders like tabs, don't declare their content in the xml but rather
 * programatically. This also can be specifically handled through the method
 * {@link #getItemsProperties()}
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public interface RuleList {

	boolean isVisible();

	boolean isEnabled();

	/**
	 * returns styles of the element. If the style is preceded by ~ this means,
	 * override the present style, otherwise the styles are appended to the
	 * existing ones
	 * 
	 * @return
	 */
	String getStyles();

	/**
	 * this is an abstract concept that permit to each widget to express its
	 * constraints specifically to its needs.
	 * 
	 * The use of this method should be avoided unless necessary: the
	 * documentation of its use must be complete in the widget that handles it,
	 * since there can be anything inside the strings. A non verbose use is
	 * recommended : by assuming defaults, like fields are enabled by default, you
	 * should only specify the fields that are not in their default state
	 * 
	 * @return typically a list of enum elements to be recovered by name
	 */
	Map<String, String> getItemsProperties();

}
