package fr.onevu.auth.server.auth;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;

import fr.onevu.vume.server.profile.ProfileManager;

public class Profile {

	public static String getProfileWidgetRules(HttpServletRequest request) {
		try {
			// FIXME circular dependency: ProfileManager should be an interface and should be injected
			return ProfileManager.getProfileRules(request);
		} catch (JsonProcessingException ex) {
			ex.printStackTrace();
			return "";
		}
	}
}
