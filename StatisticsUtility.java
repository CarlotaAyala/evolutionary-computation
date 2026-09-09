package ec.task1.domain;


public class StatisticsUtility {

    public static double min(double[] values) {
        double m = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] < m) {
                m = values[i];
            }
        }
        return m;
    }

    public static double mean(double[] values) {
        double sum = 0.0;
        for (double v : values) {
            sum += v;
        }
        return sum / values.length;
    }

    public static double std(double[] values) {
        double mean = mean(values);
        double sumSq = 0.0;
        for (double v : values) {
            double diff = v - mean;
            sumSq += diff * diff;
        }
        return Math.sqrt(sumSq / values.length);
    }
}
