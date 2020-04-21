package com.shabaka.shabakautils;

import java.util.List;

public class DjangoRestFrameworkPagedResponse<T> {


    private int count;
    private String next, previous;
    private List<T> results;

    /**
     * class for page response of Django Rest-Framework
     * @param count of total items
     * @param next url page if is null then no next page
     * @param previous url page if is null then no previous page
     * @param results list of results
     */
    public DjangoRestFrameworkPagedResponse(int count, String next, String previous, List<T> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<T> getResults() {
        return results;
    }
}
