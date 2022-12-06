package com.example.lab3;

public class Validator {

    private Validator() {

    }

    public static boolean validate(String x, String y, String r, boolean fromG) {
        if (fromG) {
            return validateValue(r, 1, 5, Integer.class);
        }
        boolean resultX = validateValue(x, -3, 5, Integer.class);
        boolean resultY = validateValue(y, -5, 3, Double.class);
        boolean resultR = validateValue(r, 1, 5, Integer.class);
        return resultX && resultY && resultR;
    }

    private static boolean validateValue(String value, int min, int max, Class<?> cls) {
        try {
            if (cls == Integer.class) {
                int variableValue = Integer.parseInt(value);
                return variableValue >= min && variableValue <= max;
            } else {
                double variableValue = Double.parseDouble(value);
                return variableValue >= min && variableValue <= max;
            }
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }
}
