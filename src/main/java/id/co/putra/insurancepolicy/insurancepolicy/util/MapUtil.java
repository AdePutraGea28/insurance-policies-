package id.co.putra.insurancepolicy.insurancepolicy.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapUtil {

	private MapUtil() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Feed me with mapping data method (re-use method)
	 */

	public static <T> String objectToString(T objects) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(objects);
	}

}
