package tmdbobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) 
public abstract class TMDBObject {
	public abstract String toString();
}
