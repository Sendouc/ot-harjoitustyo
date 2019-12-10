package runningdiaryapp.domain;

import java.sql.Date;

/**
 * Yksittäistä lenkkiä kuvaava luokka
 */

public class Run implements Comparable<Run> {
    private String id;
    private Date date;
    private int length;

    public Run(String id, Date date, int length) {
        this.id = id;
        this.date = date;
        this.length = length;
    }

    public Run(int length) {
        this.date = new java.sql.Date(System.currentTimeMillis());
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getLength() {
        return length;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Run anotherRun) {
        long result = anotherRun.date.getTime() - this.date.getTime();

        if (result > 0L)
            return 1;
        if (result < 0L)
            return -1;

        return 0;
    }
}