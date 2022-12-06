package com.example.lab3;

public class AreaCheck {

    private AreaCheck() {
    }

    public static boolean checkHit(String x, String y, String r) {
        double xValue = Double.parseDouble(x);
        double yValue = Double.parseDouble(y);
        float rValue = Float.parseFloat(r);
        if (xValue > 0 && yValue >= 0) {
            return false;
        } else if (xValue <= 0 && yValue >= 0) {
            return (Math.pow(xValue, 2) + Math.pow(yValue, 2) <= Math.pow(rValue, 2));
        } else if (xValue <= 0 && yValue < 0) {
            return yValue > (-xValue - rValue);
        } else {
            return ((xValue <= (rValue / 2)) && (yValue >= -rValue));
        }
    }
}
