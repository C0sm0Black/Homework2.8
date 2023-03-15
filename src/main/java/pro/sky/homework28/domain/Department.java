package pro.sky.homework28.domain;

import java.util.HashMap;
import java.util.Map;

public class Department {

    private int id;
    private String name;

    public Department(int id, String name) {

        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Map<Integer, Department> DEPARTMENT_BY_ID;

    static {

        DEPARTMENT_BY_ID = new HashMap<>();
        DEPARTMENT_BY_ID.put(1, new Department(1, "Отдел 1"));
        DEPARTMENT_BY_ID.put(2, new Department(2, "Отдел 2"));
        DEPARTMENT_BY_ID.put(3, new Department(3, "Отдел 3"));

    }

}
