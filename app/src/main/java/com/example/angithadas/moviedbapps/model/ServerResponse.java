package com.example.angithadas.moviedbapps.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by angitha.das on 14-11-2016.
 */

public class ServerResponse implements Serializable
{
    private ArrayList<MovieResults> results;

    private MovieDates dates;

    private String page;

    private String total_pages;

    private String total_results;

    public ArrayList<MovieResults> getResults ()
    {
        return results;
    }

    public void setResults (ArrayList<MovieResults> results)
    {
        this.results = results;
    }

    public MovieDates getDates ()
    {
        return dates;
    }

    public void setDates (MovieDates dates)
    {
        this.dates = dates;
    }

    public String getPage ()
    {
        return page;
    }

    public void setPage (String page)
    {
        this.page = page;
    }

    public String getTotal_pages ()
    {
        return total_pages;
    }

    public void setTotal_pages (String total_pages)
    {
        this.total_pages = total_pages;
    }

    public String getTotal_results ()
    {
        return total_results;
    }

    public void setTotal_results (String total_results)
    {
        this.total_results = total_results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", dates = "+dates+", page = "+page+", total_pages = "+total_pages+", total_results = "+total_results+"]";
    }
}