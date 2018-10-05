package ca.ualberta.cs.qiaoyan1_feelsbook;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test()
    {
        feelingHistory newfeel = new feelingHistory("test");
        String str = newfeel.getMessage();

        String str2 = Util.test();
    }

    @Test
    public void testGetCount()
    {
        HashMap<String, Integer> counts = new HashMap<>();
        String feeling[] = {"LOVE", "JOY", "SURPRISE", "ANGER", "SADNESS", "FEAR"};

        for(String str:feeling)
        {
            counts.put(str, 0);
        }

        ArrayList<feelingHistory> history = new ArrayList<>();

        feelingHistory feelA = new feelingHistory("JOY");
        history.add(feelA);

        feelingHistory feelB = new feelingHistory("FEAR");
        history.add(feelB);

        feelingHistory feelC = new feelingHistory("SURPRISE");
        history.add(feelC);

        feelingHistory feelD = new feelingHistory("SURPRISE");
        history.add(feelD);

        MainActivity.getCount(history, counts);

        assertTrue("# of JOY should be 1", counts.get("JOY") == 1);
        assertTrue("# of FEAR should be 1", counts.get("FEAR") == 1);
        assertTrue("# of SURPRISE should be 2", counts.get("SURPRISE") == 2);
    }
}