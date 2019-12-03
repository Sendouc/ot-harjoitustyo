package runningdiaryapp.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.h2.tools.DeleteDbFiles;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import runningdiaryapp.domain.Route;
import runningdiaryapp.ui.TUI;

public class DBDiaryDaoTest {
    private DiaryDao diaryDao;

    @Before
    public void setUp() throws Exception {
        DeleteDbFiles.execute(System.getProperty("user.dir"), ":mem:", true);
        diaryDao = new DBDiaryDao(":mem:");
        diaryDao.addRoute(new Route("1", "test", 100));
        diaryDao.addRoute(new Route("2", "bbb", 100));
        diaryDao.addRoute(new Route("3", "bb", 200));
    }

    @Test
    public void routeCanBeAdded() throws Exception {
        boolean hasTest = false;

        for (Route route : diaryDao.getRoutes()) {
            if (route.getName().equals("test"))
                hasTest = true;
        }

        assertEquals(true, hasTest);
    }

    @Test
    public void routeCanBeSearchedFor() throws Exception {
        List<Route> routes = diaryDao.getRoutesByName("B");
        assertEquals(2, routes.size());
    }
}