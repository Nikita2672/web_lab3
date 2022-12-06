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

@Entity(name = "s336592_currency")
@Table(name = "s336592_currency", schema = "public")
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CurrencyIdGenerator")
    @SequenceGenerator(name = "CurrencyIdGenerator", sequenceName = "CURRENCY_ID", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column
    private Date date;

    @Column
    private float open;

    @Column
    private float high;

    @Column
    private float low;

    @Column
    private float close;

    public Currency() {

    }

    public Currency(Date date, float open, float high, float low, float close) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getOpen() {
        return open;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getClose() {
        return close;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public void setClose(float close) {
        this.close = close;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        Currency currency = (Currency) o;
        return getId() == currency.getId() && Float.compare(currency.getOpen(), getOpen()) == 0 && Float.compare(currency.getHigh(), getHigh()) == 0 && Float.compare(currency.getLow(), getLow()) == 0 && Float.compare(currency.getClose(), getClose()) == 0 && Objects.equals(getDate(), currency.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getOpen(), getHigh(), getLow(), getClose());
    }

    @Override
    public String toString() {
        return "Currency{"
                + ",date=" + date
                + ",open=" + open
                + ",high=" + high
                + ",low=" + low
                + ",close=" + close
                + '}';
    }
}
