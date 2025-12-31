package com.college.departmentcrud;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Department {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;  // Changed from int to Integer (allows null)
    private String name;
    private String location;
    private Double budget;  // Changed from double to Double (allows null)

    public Department() {}

    public Department(Integer id, String name, String location, Double budget) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.budget = budget;
    }

    // Convenience constructor for database results
    public Department(int id, String name, String location, double budget) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.budget = budget;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }
}