package ca.ualberta.cs.qiaoyan1_feelsbook;

import java.util.Date;

public abstract class feelingHistory {

    protected String message;
    protected Date date;

    public feelingHistory(String message)
    {
        this.message = message;
        this.date = new Date();
    }


    public String getMessage()
    {
        return this.message;
    }

    public Date getDate()
    {
        return this.date;
    }

    // public abstract boolean isImportant();

    public String toString(){

        return this.date.toString() + " | " + this.message;
    }
}
