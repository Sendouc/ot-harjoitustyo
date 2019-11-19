package runningdiaryapp.domain;

/**
 * Lenkkireittiä kuvaava luokka
 */

public class Route {

    private String id;
    private String name;
    private int length;

    public Route(String id, String name, int length) {
        this.id = id;
        this.name = name;
        this.length = length;
    }

    public Route(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public void setId(String id) {
        this.id = id;
    }
}