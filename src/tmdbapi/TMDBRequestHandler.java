package tmdbapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TMDBRequestHandler {

	/**
	 * 
	 * @param objClass
	 * @param request
	 * @return
	 */
	public static Object request(Class<?> objClass, Request request) {
		String result = sendRequest(request);
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
		try {
			if(request.getQuery() == "") {
				object = mapper.readValue(result, objClass);
			}
			else {
				
			}
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

