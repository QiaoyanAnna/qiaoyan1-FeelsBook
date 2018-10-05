/**
 * Class Name: ImportantFeeling
 *
 * Author: Qiaoyan Zhang
 *
 * Date: October 5, 2018
 *
 */
package ca.ualberta.cs.qiaoyan1_feelsbook;

import java.text.SimpleDateFormat;

public class ImportantFeeling extends feelingHistory{
    public ImportantFeeling(String message)
    {
        super(message);
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateString = sdf.format(this.date);

        return dateString + " | " + this.message + " | " + this.comment;
    }
}
