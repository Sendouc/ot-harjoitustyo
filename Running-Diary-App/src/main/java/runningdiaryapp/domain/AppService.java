package runningdiaryapp.domain;

import java.util.List;

import runningdiaryapp.dao.DBDiaryDao;
import runningdiaryapp.dao.DiaryDao;

public class AppService {
    private DiaryDao diaryDao;

    public AppService() throws Exception {
        diaryDao = new DBDiaryDao("./test.db");
    }

    /**
     * Uuden lenkkireitin lisääminen
     *
     * @param name   reitin nimi
     * @param length lenkin pituus metreissä
     */

    public boolean createRoute(String name, int length) throws Exception {
        Route route = new Route(name, length);
        diaryDao.addRoute(route);
        return true;
    }

    public List<Route> getRoutes() throws Exception {
        return diaryDao.getRoutes();
    }

    public void closeDbConnection() throws Exception {
        diaryDao.closeConn();
    }
}