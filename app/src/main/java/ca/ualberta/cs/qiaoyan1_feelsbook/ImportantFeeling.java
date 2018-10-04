package ca.ualberta.cs.qiaoyan1_feelsbook;

public class ImportantFeeling extends feelingHistory{
    public ImportantFeeling(String message)
    {
        super(message);
    }

    public boolean isImportant()
    {
        return true;
    }
}
