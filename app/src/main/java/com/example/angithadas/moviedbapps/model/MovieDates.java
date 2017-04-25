package com.example.angithadas.moviedbapps.model;

/**
 * Created by angitha.das on 14-11-2016.
 */

public class MovieDates
{
    private String minimum;

    private String maximum;

    public String getMinimum ()
    {
        return minimum;
    }

    public void setMinimum (String minimum)
    {
        this.minimum = minimum;
    }

    public String getMaximum ()
    {
        return maximum;
    }

    public void setMaximum (String maximum)
    {
        this.maximum = maximum;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [minimum = "+minimum+", maximum = "+maximum+"]";
    }
}
