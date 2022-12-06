package com.example.lab3;

import au.com.bytecode.opencsv.CSVReader;
import com.google.gson.Gson;

import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class DataManager implements Serializable {

    private transient EntityManagerFactory factory;
    private String transmittedXCoordinate;
    private String transmittedYCoordinate;
    private String transmittedRValue;
    private String transmittedYear;
    private String transmittedMonth;
    private boolean fromG;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

    public String getTransmittedYear() {
        return transmittedYear;
    }

    public String getTransmittedMonth() {
        return transmittedMonth;
    }

    public void setTransmittedYear(String transmittedYear) {
        this.transmittedYear = transmittedYear;
    }

    public void setTransmittedMonth(String transmittedMonth) {
        this.transmittedMonth = transmittedMonth;
    }

    public String getTransmittedXCoordinate() {
        return transmittedXCoordinate;
    }

    public String getTransmittedYCoordinate() {
        return transmittedYCoordinate;
    }

    public String getTransmittedRValue() {
        return transmittedRValue;
    }

    public void setTransmittedXCoordinate(String transmittedXCoordinate) {
        this.transmittedXCoordinate = transmittedXCoordinate;
    }

    public void setTransmittedYCoordinate(String transmittedYCoordinate) {
        this.transmittedYCoordinate = transmittedYCoordinate;
    }

    public void setTransmittedRValue(String transmittedRValue) {
        this.transmittedRValue = transmittedRValue;
    }

    public DataManager() {
        factory = Persistence.createEntityManagerFactory("default");
    }

    public List<Attempt> getAttempts() {
        EntityManager managerFactory = factory.createEntityManager();
        String query = "SELECT a from s336592_hits a";
        List<Attempt> result = managerFactory.createQuery(query, Attempt.class).getResultList();
        Collections.reverse(result);
        return result;
    }

    public String getX() {
        return new Gson().toJson(getAttempts().stream().map(Attempt::getX).collect(Collectors.toList()));
    }

    public String getY() {
        return new Gson().toJson(getAttempts().stream().map(Attempt::getY).collect(Collectors.toList()));
    }

    public String getR() {
        return new Gson().toJson(getAttempts().stream().map(Attempt::getR).collect(Collectors.toList()));
    }

    public String getResult() {
        return new Gson().toJson(getAttempts().stream().map(Attempt::isResult).collect(Collectors.toList()));
    }

    public void fromGraph() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        transmittedXCoordinate = params.get("x");
        transmittedYCoordinate = params.get("y");
        transmittedRValue = params.get("r");
        setData(true);
    }

    public List<Currency> loadGraphic() {
        String month = transmittedMonth;
        String year = transmittedYear;
        if (transmittedYear != null && transmittedMonth != null && (!transmittedYear.equals("")) && (!transmittedMonth.equals(""))) {
            String left = LocalDate.parse(month + " 1, " + year, dateTimeFormatter).toString();
            String right = LocalDate.parse(month + " 31, " + year, dateTimeFormatter).toString();
            System.out.println("left: " + left);
            System.out.println("right: " + right);
            EntityManager managerFactory = factory.createEntityManager();
            String query = "SELECT a from s336592_currency a where date >= " + "'" + left + "'" + " and date <= " + "'" + right + "'";
            List<Currency> result = managerFactory.createQuery(query, Currency.class).getResultList();
            Collections.reverse(result);
            return result;
        } else {
            return null;
        }
    }

    public String getClose() {
        List<Currency> currencyList = loadGraphic();
        if (currencyList != null) {
            return new Gson().toJson(loadGraphic().stream().map(Currency::getClose).collect(Collectors.toList()));
        } else {
            return "";
        }
    }

    public String getHigh() {
        List<Currency> currencyList = loadGraphic();
        if (currencyList != null) {
            return new Gson().toJson(loadGraphic().stream().map(Currency::getHigh).collect(Collectors.toList()));
        } else {
            return "";
        }
    }

    public String getLow() {
        List<Currency> currencyList = loadGraphic();
        if (currencyList != null) {
            return new Gson().toJson(loadGraphic().stream().map(Currency::getLow).collect(Collectors.toList()));
        } else {
            return "";
        }
    }

    public String getOpen() {
        List<Currency> currencyList = loadGraphic();
        if (currencyList != null) {
            return new Gson().toJson(loadGraphic().stream().map(Currency::getOpen).collect(Collectors.toList()));
        } else {
            return "";
        }
    }

    public String getDate() {
        List<Currency> currencyList = loadGraphic();
        if (currencyList != null) {
            return new Gson().toJson(loadGraphic().stream().map(Currency::getDate).collect(Collectors.toList()));
        } else {
            return "";
        }
    }

    public void setData(boolean fromGraph) {
        fromG = fromGraph;
        long start = System.nanoTime();
        if (transmittedXCoordinate == null || transmittedXCoordinate.equals("")) transmittedXCoordinate = "0";
        if (transmittedYCoordinate == null || transmittedYCoordinate.equals("")) transmittedYCoordinate = "0";
        if (transmittedRValue == null || transmittedRValue.equals("")) transmittedRValue = "1";
        if (Validator.validate(transmittedXCoordinate, transmittedYCoordinate, transmittedRValue, fromG)) {
            boolean result = AreaCheck.checkHit(transmittedXCoordinate, transmittedYCoordinate, transmittedRValue);
            long finish = System.nanoTime();
            String workTime = ((finish - start) / 1000) + " ms";
            EntityManager entityManager = factory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(new Attempt(Double.parseDouble(transmittedXCoordinate),
                    Double.parseDouble(transmittedYCoordinate), Integer.parseInt(transmittedRValue),
                    result, new Date(), workTime));
            entityManager.getTransaction().commit();
            System.out.println(transmittedXCoordinate);
        }
    }

}
