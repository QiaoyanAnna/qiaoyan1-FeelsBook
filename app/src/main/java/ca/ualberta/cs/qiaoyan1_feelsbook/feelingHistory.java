package ca.ualberta.cs.qiaoyan1_feelsbook;

import java.util.Date;

public class feelingHistory {

    protected String message;
    protected Date date;
    protected String comment;

    public feelingHistory(String message)
    {
        this.message = message;
        this.date = new Date();
        this.comment = "No Comment";
    }

    public String getComment()
    {
        return comment;
    }

    public String getMessage()
    {
        return this.message;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setMessage(String newMessage)
    {
        this.message = newMessage;
    }

    public void setComment(String newComment)
    {
        this.comment = newComment;
    }

    public void setDate(Date newDate)
    {
        this.date = newDate;
    }
    // public abstract boolean isImportant();



}
