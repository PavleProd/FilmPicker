package tmdbobjects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author pavle
 * Generic class for getting List of multiple results that matches criteria. 
 * List is provided when we have additional query for request
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBListWrapper<T> {
	private int total_pages;
	private int total_results;
	private int page;
	private List<T> results;
	
	public int getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}
	public int getTotal_results() {
		return total_results;
	}
	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> result) {
		this.results = result;
	}
	
	
}
