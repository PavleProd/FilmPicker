package tmdbapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TMDBRequestHandler {
	// Made static so we can make JavaTypes TODO:skontaj sta je javatype
	private static final ObjectMapper mapper = new ObjectMapper();
	public static ObjectMapper getMapper() {
		return mapper;
	}
	
	/**
	 * Gets a result string from DataBase and then uses ObjectMapper to map all objects into given JavaType
	 * @param objType used to resolve generic class where java doesn't include type of generic class and ObjectMapper needs it
	 * @param request Strings representing parts of request
	 * @return Resulting Object type with all fields filled with information
	 */
	public static Object request(JavaType objType, Request request) {
		String result = sendRequest(request);
		if(result.contains("status_code")) { // TODO: better impementation
			return null;
		}
		Object object = null;
		try {
			object = mapper.readValue(result, objType);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	// Sends request to the database and returns requested result.
	private static String sendRequest(Request request) {
		return TMDBClient.createTMDBRequest(request);
	}
}

