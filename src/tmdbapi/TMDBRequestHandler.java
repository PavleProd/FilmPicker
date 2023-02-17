package tmdbapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TMDBRequestHandler {
	public static Object request(Class<?> objClass, String name) {
		String result = sendRequest(name);
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
		try {
			object = mapper.readValue(result, objClass);
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
	private static String sendRequest(String name) {
		StringBuilder request = new StringBuilder("https://api.themoviedb.org/3/");
		request.append(name);
		return TMDBClient.createTMDBRequest(request);
	}
}

