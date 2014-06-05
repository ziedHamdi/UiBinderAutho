package fr.onevu.auth.server.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;

import fr.onevu.auth.server.auth.model.ProfileWidgetRulesImpl;
import fr.onevu.auth.server.auth.model.RuleListImpl;

public class Profile {
	protected static ProfileWidgetRulesImpl profileWidgetRulesImpl = new ProfileWidgetRulesImpl("testProfile");
	static {
	}

	public static String getProfileWidgetRules(HttpServletRequest request) {
		RuleListImpl ruleListImpl = new RuleListImpl(true, true, "~btn simple");
		profileWidgetRulesImpl.putRuleList("fr.onevu.auth.client.ui.UiBinderProfileTest", "button", ruleListImpl);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(profileWidgetRulesImpl).trim();
		} catch (JsonProcessingException ex) {
			return ex.getMessage();
		}
	}
}
