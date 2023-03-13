package test;

//Abstract class for common data for all service classes
public abstract class Service {
    protected final static int minVoteCount = 300; // min votes for movie to be listed
    protected final static int resultsPerPage = 20; // number of results per page of request list
}
