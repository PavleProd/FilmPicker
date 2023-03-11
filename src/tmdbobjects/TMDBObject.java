package tmdbobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author pavle
 * Abstract class for all types that can be generated from API request
 */
@JsonIgnoreProperties(ignoreUnknown = true) 
public abstract class TMDBObject {
	public abstract String toString();
}
