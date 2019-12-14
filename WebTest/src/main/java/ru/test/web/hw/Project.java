package ru.test.web.hw;

public class Project {
    private final String name;
    private final String id;
    private final String lead;

    public Project(String name, String id, String lead) {
        this.name = name;
        this.id = id;
        this.lead = lead;
    }

    String getName() {
        return name;
    }

    String getId() {
        return id;
    }

    String getLead() {
        return lead;
    }
}
