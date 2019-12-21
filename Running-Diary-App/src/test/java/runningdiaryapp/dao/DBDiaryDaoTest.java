package runningdiaryapp.dao;

import java.util.List;

import org.h2.tools.DeleteDbFiles;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import runningdiaryapp.domain.Route;
import runningdiaryapp.domain.Run;

public class DBDiaryDaoTest {
    private static DiaryDao diaryDao;

    @BeforeClass
    public static void setUp() throws Exception {
        DeleteDbFiles.execute(System.getProperty("user.dir"), ":mem:", true);
        diaryDao = new DBDiaryDao(":mem:");
        diaryDao.addRoute(new Route("1", "test", 100));
        diaryDao.addRoute(new Route("2", "bbb", 100));
        diaryDao.addRoute(new Route("3", "bb", 200));

        diaryDao.addRun(new Run("1", new java.sql.Date(System.currentTimeMillis()), 100));
        diaryDao.addRun(new Run("2", new java.sql.Date(System.currentTimeMillis()), 200));
    }

    @Test
    public void routeCanBeAdded() throws Exception {
        boolean hasTest = false;

        for (Route route : diaryDao.getRoutes()) {
            if (route.getName().equals("test")) {
                hasTest = true;
                break;
            }
        }

        assertEquals(true, hasTest);
    }

    @Test
    public void runCanBeAdded() throws Exception {
        boolean has100MeterRun = false;

        for (Run run : diaryDao.getRuns()) {
            if (run.getLength() == 100) {
                has100MeterRun = true;
                break;
            }
        }

        assertEquals(true, has100MeterRun);
    }

    @Test
    public void routeCanBeSearchedFor() throws Exception {
        List<Route> routes = diaryDao.getRoutesByName("B");
        assertEquals(2, routes.size());
    }

    @Test
    public void runDistanceCountIsCorrect() throws Exception {
        int distance = diaryDao.getDistanceRan();
        assertEquals(300, distance);
    }
}