package com.example.lab3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "s336592_hits")
@Table(name = "s336592_hits", schema = "public")
public class Attempt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HitsIdGenerator")
    @SequenceGenerator(name="HitsIdGenerator", sequenceName="HIT_ID", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column
    private double x;

    @Column
    private double y;

    @Column
    private int r;

    @Column
    private boolean result;

    @Column
    private Date date;

    @Column
    private String workTime;

    public Attempt(double x, double y, int r, boolean result, Date date, String workTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.date = date;
        this.workTime = workTime;
    }

    public Attempt() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attempt)) return false;
        Attempt attempt = (Attempt) o;
        return id == attempt.id && x == attempt.x && Double.compare(attempt.y, y) == 0 && r == attempt.r && result == attempt.result && Objects.equals(date, attempt.date) && Objects.equals(workTime, attempt.workTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, r, result, date, workTime);
    }

    public long getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean isResult() {
        return result;
    }

    public Date getDate() {
        return date;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(int r) {
        this.r = r;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }
}
