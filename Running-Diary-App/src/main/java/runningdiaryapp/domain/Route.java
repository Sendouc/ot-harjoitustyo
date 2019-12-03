package runningdiaryapp.domain;

/**
 * Lenkkireittiä kuvaava luokka
 */

public class Route implements Comparable<Route> {
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Route anotherRoute) {
        return anotherRoute.length - this.length;
    }
}