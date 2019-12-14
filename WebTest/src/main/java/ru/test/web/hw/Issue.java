package ru.test.web.hw;

public class Issue {
    private final String summary;
    private final String description;

    public Issue(String summary, String description) {
        this.summary = summary;
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }
}
