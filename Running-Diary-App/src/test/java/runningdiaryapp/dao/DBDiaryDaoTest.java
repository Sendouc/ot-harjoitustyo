package runningdiaryapp.dao;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

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
        diaryDao = new DBDiaryDao(":mem:");
    }

    @Test
    public void routeCanBeAdded() throws Exception {
        diaryDao.addRoute(new Route("1", "test", 100));
        assertEquals("test", diaryDao.getRoutes().get(0).getName());
    }
}