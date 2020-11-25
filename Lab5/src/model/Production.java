package model;

import java.util.List;

public class Production {
    private String start;
    private List<List<String>> rules;

    Production(String start, List<List<String>> rules) {
        this.start = start;
        this.rules = rules;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<List<String>> getRules() {
        return rules;
    }

    public void setRules(List<List<String>> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return start + " -> " + rules;
    }
}
