package fr.onevu.auth.server.auth.model;

import java.util.HashMap;
import java.util.Map;

import fr.onevu.auth.shared.auth.model.RuleList;

/**
 * simple pojo implementation of {@link RuleList}
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public class RuleListImpl implements RuleList {

	protected boolean visible, enabled;
	protected String styles;
	protected Map<String, String> itemsProperties = new HashMap<String, String>();

	public RuleListImpl(boolean visible, boolean enabled, String styles) {
		this.visible = visible;
		this.enabled = enabled;
		this.styles = styles;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
	}

	public Map<String, String> getItemsProperties() {
		return itemsProperties;
	}

	public void setItemsProperties(Map<String, String> itemsProperties) {
		this.itemsProperties = itemsProperties;
	}

	public String getItemProperty(String key) {
		return itemsProperties.get(key);
	}

	public String putItemProperty(String key, String prop) {
		return itemsProperties.put(key, prop);
	}

}
