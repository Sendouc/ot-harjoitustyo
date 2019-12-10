package runningdiaryapp.domain;

import java.sql.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class RunTest {

    @Test
    public void runAddedWithoutDateGetsCorrectDate() {
        Run run = new Run(100);
        Date now = new java.sql.Date(System.currentTimeMillis());

        assertTrue(now.toString().equals(run.getDate().toString()));
    }

}