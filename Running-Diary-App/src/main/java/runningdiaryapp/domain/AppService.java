package runningdiaryapp.domain;

import java.util.List;
import java.util.Map;

import runningdiaryapp.dao.DBDiaryDao;
import runningdiaryapp.dao.DiaryDao;

/**
 * Sovelluslogiikasta vastaava luokka
 */

public class AppService {
    private DiaryDao diaryDao;

    public AppService() throws Exception {
        Map<String, String> env = System.getenv();
        String dbName = env.getOrDefault("DB_NAME", "runningdiaryapp");
        diaryDao = new DBDiaryDao("./" + dbName + ".db");
    }

    /**
     * Uuden lenkkireitin lisääminen
     *
     * @param name   reitin nimi
     * @param length lenkin pituus metreissä
     * 
     * @return true jos lisääminen onnistui muuten false
     * 
     * @throws Exception
     */

    public boolean createRoute(String name, int length) throws Exception {
        Route route = new Route(name, length);
        diaryDao.addRoute(route);
        return true;
    }

    /**
     * Uuden lenkin lisääminen
     *
     * @param length lenkin pituus metreissä
     * 
     * @return true jos lisääminen onnistui muuten false
     * 
     * @throws Exception
     */
    public boolean createRun(int length) throws Exception {
        Run run = new Run(length);
        diaryDao.addRun(run);
        return true;
    }

    /**
     * Kaikkien reittien hakeminen
     * 
     * @return lista, jossa on kaikki reitit
     * 
     * @throws Exception
     */
    public List<Route> getRoutes() throws Exception {
        return diaryDao.getRoutes();
    }

    /**
     * Kaikkien juoksujen hakeminen
     * 
     * @return lista, jossa on kaikki juoksut
     * 
     * @throws Exception
     */
    public List<Run> getRuns() throws Exception {
        return diaryDao.getRuns();
    }

    /**
     * Reitin etsiminen nimen perusteella
     * 
     * @param name etsittävän lenkin nimi
     * 
     * @return lista, jossa on kaikki reitit joiden nimen osana on annettu nimi
     * 
     * @throws Exception
     */
    public List<Route> getRoutesByName(String name) throws Exception {
        return diaryDao.getRoutesByName(name);
    }

    /**
     * Juostujen metrien määrän hakeminen
     * 
     * @return juostujen metrien määrä
     * 
     * @throws Exception
     */
    public int getDistanceRan() throws Exception {
        return diaryDao.getDistanceRan();
    }

    /**
     * Tietokantayhteyden sulkeminen
     * 
     * @throws Exception
     */

    public void closeDbConnection() throws Exception {
        diaryDao.closeConn();
    }
}