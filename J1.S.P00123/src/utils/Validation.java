package utils;

import constant.Message;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validation {

    private Validation() {
    }

    // ================= DOUBLE =================
    public static double getDouble(String input, double min, double max) throws Exception {
        try {
            double value = Double.parseDouble(input.trim());

            if (value < min || value > max) {
                throw new Exception(String.format(Message.REAL_NUMBER_RANGE_ERROR, min, max));
            }

            return value;
        } catch (NumberFormatException e) {
            throw new Exception(Message.ERROR_INVALID_GENERAL);
        }
    }

    // ================= INTEGER =================
    public static int getInt(String input, int min, int max) throws Exception {
        try {
            int value = Integer.parseInt(input.trim());

            if (value < min || value > max) {
                throw new Exception(String.format(Message.INTERGER_NUMBER_RANGE_ERROR, min, max));
            }

            return value;
        } catch (NumberFormatException e) {
            throw new Exception(Message.ERROR_INVALID_GENERAL);
        }
    }

    // ================= STRING =================
    public static String getString(String input, final String REGEX) throws Exception {
        if (input == null) {
            throw new Exception(Message.ERROR_INVALID_GENERAL);
        }

        String value = input.trim();

        if (!value.matches(REGEX)) {
            throw new Exception(Message.ERROR_INVALID_GENERAL);
        }

        return value;
    }

    // ================= DATE =================
    public static Date getDate(String input, final String FORMAT) throws Exception {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        sdf.setLenient(false);

        try {
            return sdf.parse(input.trim());
        } catch (ParseException e) {
            throw new Exception(Message.ERROR_INVALID_DATE_FORMAT);
        }
    }

    // ================= PLAN TIME =================
    public static void checkPlanTime(double from, double to) throws Exception {
        if (from >= to) {
            throw new Exception("planFrom must be less than planTo");
        }
    }

    // ================= STEP 0.5 =================
    public static void checkStep(double time) throws Exception {
        // tránh lỗi double → nhân 10
        if ((time * 10) % 5 != 0) {
            throw new Exception("Time must be in step of 0.5");
        }
    }

    // ================= WORKING TIME =================
    public static void checkWorkingTime(double time) throws Exception {
        if (time < 8.0 || time > 17.5) {
            throw new Exception("Time must be between 8.0 -> 17.5");
        }
    }
}