package br.com.hikanban.utils;

import java.util.Map;

public class HtmlUtils {

	public static String BuildBody(String template, Map<String, String> values) {
		String body = template;
		for (Map.Entry<String, String> entry : values.entrySet()) {
			String value = entry.getValue();
			if (value == null) {
				value = "";
			}
			body = body.replaceAll("\\{\\{" + entry.getKey() + "\\}\\}", value);
		}
		body = body.replaceAll("\\{\\{.+?\\}\\}", " ");
		return body;
	}

}
