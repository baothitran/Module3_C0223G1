package model;

import java.time.LocalDate;

public class CustomerType {
    private int id;
    private String type;
    private LocalDate deleteAt;

    public CustomerType() {
    }

    public CustomerType(int id, String type, LocalDate deleteAt) {
        this.id = id;
        this.type = type;
        this.deleteAt = deleteAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDate deleteAt) {
        this.deleteAt = deleteAt;
    }
}
