package fr.onevu.auth.shared.auth.model;

/**
 * simple pojo implementation of {@link RuleList}
 * 
 * @author Zied Hamdi - http://1vu.fr
 * 
 */
public class RuleListImpl implements RuleList{

	protected boolean visible, enabled;
	protected String styles;

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
	
}
