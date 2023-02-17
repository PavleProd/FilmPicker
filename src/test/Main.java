package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tmdbapi.TMDBService;
import tmdbobjects.*;

class Wrapper<T> {
	public int num;
	public T[] res;
}

public class Main {
	public static void main(String[] args) {
		//Movie movie = TMDBService.getMovie(64690);
		//System.out.println(movie);
		String req = "{\r\n"
				+ "	\"num\" : 2,\r\n"
				+ "	\"res\" : [\r\n"
				+ "		{\r\n"
				+ "			\"id\" : 1,\r\n"
				+ "			\"name\" : \"Pavle\"\r\n"
				+ "		},\r\n"
				+ "		{\r\n"
				+ "			\"id\" : 2,\r\n"
				+ "			\"name\" : \"Tasija\"\r\n"
				+ "		}\r\n"
				+ "	]\r\n"
				+ "}";
		
		ObjectMapper mapper = new ObjectMapper();
		
		Wrapper<Person> wrapper = null;
		try {
			wrapper = mapper.readValue(req, Wrapper.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(wrapper);
	}
	
}
