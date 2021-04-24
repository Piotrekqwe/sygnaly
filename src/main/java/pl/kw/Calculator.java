package pl.kw;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class Calculator {

    public static double[][] szumJednostajny(double range, double czasP, double dlugosc, int acc) {
        double[][] points = new double[acc][2];
        Random r = new Random();

        for(int i = 0; i < acc; i++) {
            points[i][0] = czasP + i * dlugosc / acc;
            points[i][1] = r.nextDouble() * (range * 2) - range;
        }
        return points;
    }

    public static double[][] szumGaussowski(double normalna, double czasP, double dlugosc, int acc) {
        double[][] points = new double[acc][2];
        Random r = new Random();

        for(int i = 0; i < acc; i++) {
            points[i][0] = czasP + i * dlugosc / acc;
            points[i][1] = r.nextGaussian() * normalna;
        }
        return points;
    }

    public static double[][] sinusoida(double amplituda, double czasP, double dlugosc, double okres, int acc) {
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = czasP + i * dlugosc / acc;
            points[i][1] = amplituda * sin(2 * PI / okres * (points[i][0] - czasP));
        }
        return points;
    }

    public static double[][] prostokatny(double max, double min, double czasP, double dlugosc, double okres, double wypelnienie, int acc) {
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = czasP + i * dlugosc / acc;
            if(((points[i][0] - czasP) % okres) / okres < wypelnienie / 100){
                points[i][1] = max;
            } else {
                points[i][1] = min;
            }
        }
        return points;
    }

    public static double[][] trojkatny(double amplituda, double czasP, double dlugosc, double okres, double wygiecie, int acc) {
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = czasP + i * dlugosc / acc;
            if(((points[i][0] - czasP) % okres) / okres < wygiecie / 100){
                points[i][1] = amplituda * (((points[i][0] - czasP) % okres) / okres / wygiecie * 100);
            } else {
                points[i][1] = amplituda - amplituda * (((points[i][0] - czasP) % okres) / okres - wygiecie / 100) * 100 / (100 - wygiecie);
            }
        }
        return points;
    }

    public static double[][] skok(double max, double min, double czasP, double dlugosc, double czasSkoku, int acc) {
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = czasP + i * dlugosc / acc;
            if(points[i][0] > czasSkoku){
                points[i][1] = max;
            } else {
                points[i][1] = min;
            }
        }
        return points;
    }

    public static double[][] impulsJednostkowy(double max, double min, double czasP, double dlugosc, double nrImpulsu, int acc) {
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = czasP + i * dlugosc / (acc - 1);
            if(i == nrImpulsu){
                points[i][1] = max;
            } else {
                points[i][1] = min;
            }
        }
        return points;
    }

    public static double[][] szumImpulsowy(double max, double min, double czasP, double dlugosc, double chance, int acc) {
        double[][] points = new double[acc][2];
        Random r = new Random();

        for(int i = 0; i < acc; i++) {
            points[i][0] = czasP + i * dlugosc / (acc - 1);
            if(r.nextDouble() <= chance / 100){
                points[i][1] = max;
            } else {
                points[i][1] = min;
            }
        }
        return points;
    }


    public static double[][] rescale(ArrayList<Double> xValues, double[][] points) {
        double[][] newPoints = new double[xValues.size()][2];
        for (int i = 0; i < xValues.size(); i++) {
            newPoints[i][0] = xValues.get(i);
        }
        int i = 0, j = 0;
        while(newPoints[i][0] < points[0][0]){
            newPoints[i][1] = 0;
            i++;
        }
        while(i < xValues.size() && j < points.length){
            if(newPoints[i][0] < points[j][0]){
                newPoints[i][1] = ((points[j][0] - newPoints[i][0]) * points[j-1][1] + (newPoints[i][0] - points[j-1][0]) * points[j][1]) / (points[j][0] - points[j-1][0]);
                i++;
            }else{
                newPoints[i][1] = points[j][1];
                i++;
                j++;
            }
        }
        while(i < xValues.size()){
            newPoints[i][1] = 0;
            i++;
        }
        return newPoints;
    }
    public static ArrayList<Double> generateXValues(double[][] first, double[][] second){
        ArrayList<Double> xValues = new ArrayList<>();

        for (double[] d : first){
            xValues.add(d[0]);
        }
        for (double[] d : second){
            xValues.add(d[0]);
        }

        xValues.sort(Double::compareTo);
        ArrayList<Double> result = new ArrayList<>();
        result.add(xValues.get(0));
        int i = 1;
        while(i < xValues.size()){
            if(!xValues.get(i).equals(xValues.get(i - 1))) {
                result.add(xValues.get(i));
            }
            i++;
        }


        return result;
    }

    public static double[][] add(double[][] first, double[][] second){
        ArrayList<Double> xValues = generateXValues(first, second);
        double[][] p1 = rescale(xValues, first);
        double[][] p2 = rescale(xValues, second);
        double[][] result = new double[p1.length][2];
        for(int i = 0; i < result.length; i++) {
            result[i][0] = p1[i][0];
            result[i][1] = p1[i][1] + p2[i][1];
        }
        return result;
    }
    public static double[][] subtract(double[][] first, double[][] second){
        ArrayList<Double> xValues = generateXValues(first, second);
        double[][] p1 = rescale(xValues, first);
        double[][] p2 = rescale(xValues, second);
        double[][] result = new double[p1.length][2];
        for(int i = 0; i < result.length; i++) {
            result[i][0] = p1[i][0];
            result[i][1] = p1[i][1] - p2[i][1];
        }
        return result;
    }
    public static double[][] multiply(double[][] first, double[][] second){
        ArrayList<Double> xValues = generateXValues(first, second);
        double[][] p1 = rescale(xValues, first);
        double[][] p2 = rescale(xValues, second);
        double[][] result = new double[p1.length][2];
        for(int i = 0; i < result.length; i++) {
            result[i][0] = p1[i][0];
            result[i][1] = p1[i][1] * p2[i][1];
        }
        return result;
    }
    public static double[][] divide(double[][] first, double[][] second){
        ArrayList<Double> xValues = generateXValues(first, second);
        double[][] p1 = rescale(xValues, first);
        double[][] p2 = rescale(xValues, second);
        double[][] result = new double[p1.length][2];
        for(int i = 0; i < result.length; i++) {
            if(p2[i][1] == 0){ p2[i][1] = 0.01;}
            result[i][0] = p1[i][0];
            result[i][1] = p1[i][1] / p2[i][1];
        }
        return result;
    }

    public static double[][] sampling(double[][] signal, int numberOfSamples){
        int n = numberOfSamples - 1;
        double[][] result = new double[n + 1][2];
        double min = signal[0][0];
        double max = signal[signal.length - 1][0];
        int iter = 0;
        for (int i = 0; i < n; i++){
            double time = (max * i + min * (n - i)) / n;
            while(signal[iter][0] < time){
                iter++;
            }
            result[i][0] = signal[iter][0];
            result[i][1] = signal[iter][1];
        }
        result[n][0] = signal[signal.length - 1][0];
        result[n][1] = signal[signal.length - 1][1];
        return result;
    }
    public static double[][] zeroOrder(double[][] signal){
        double[][] result = new double[signal.length * 2 - 1][2];
        result[0][0] = signal[0][0];
        result[0][1] = signal[0][1];
        for(int i = 1; i < signal.length; i++){
            result[i * 2 - 1][0] = signal[i][0];
            result[i * 2 - 1][1] = signal[i - 1][1];
            result[i * 2][0] = signal[i][0];
            result[i * 2][1] = signal[i][1];
        }

        return result;
    }
    public static double[][] zeroOrderNormalized(double[][] signal){
        double[][] result = new double[signal.length * 2][2];
        result[0][0] = signal[0][0];
        result[0][1] = signal[0][1];
        for(int i = 1; i < signal.length; i++){
            double avg = (signal[i - 1][0] + signal[i][0]) / 2;
            result[i * 2 - 1][0] = avg;
            result[i * 2][0] = avg;
            result[i * 2 - 1][1] = signal[i - 1][1];
            result[i * 2][1] = signal[i][1];
        }
        result[result.length - 1][0] = signal[signal.length - 1][0];
        result[result.length - 1][1] = signal[signal.length - 1][1];

        return result;
    }
    private static double sinc(double x){
        if(x == 0) return 1;
        double y = Math.PI * x;
        return Math.sin(y) / y;
    }
    public static double[][] sincInterpolation(double[][] signal, int acc){
        double[][] result = new double[acc][2];
        double Ts = (signal[signal.length - 1][0] - signal[0][0]) / (signal.length - 1);
        double Tr = (signal[signal.length - 1][0] - signal[0][0]) / (acc - 1);
        for (int i = 0; i < acc; i++){
            result[i][0] = i * Tr;
            result[i][1] = 0;
            for (int j = 0; j < signal.length; j++){
                result[i][1] += signal[j][1] * sinc((result[i][0] / Ts) - j);
            }
        }
        return result;
    }

    public static double[][] truncatedQuantization(double[][] signal, int acc){
        double[][] result = new double[signal.length][2];
        double[] levels = new double[acc];
        double min = signal[0][1];
        double max = signal[0][1];
        for (double[] x : signal){
            if(x[1] > max) max = x[1];
            if(x[1] < min) min = x[1];
        }
        double dif = (max - min) / (acc - 1);
        for(int i = 0; i < acc; i++) {
            levels[i] = min + i * dif;
        }

        for (int i = 0; i < signal.length; i++){
            int j = acc - 1;
            while (j > 0 && levels[j] > signal[i][1]){
                j--;
            }
            result[i][1] = levels[j];
            result[i][0] = signal[i][0];
        }

        return result;
    }
    public static double[][] roundingQuantization(double[][] signal, int acc){
        double[][] result = new double[signal.length][2];
        double[] levels = new double[acc];
        double min = signal[0][1];
        double max = signal[0][1];
        for (double[] x : signal){
            if(x[1] > max) max = x[1];
            if(x[1] < min) min = x[1];
        }
        double diff = (max - min) / (acc - 1);
        for(int i = 0; i < acc; i++) {
            levels[i] = min + i * diff - diff / 2;
        }

        for (int i = 0; i < signal.length; i++){
            int j = acc - 1;
            while (j > 0 && levels[j] > signal[i][1]){
                j--;
            }
            result[i][1] = levels[j] + diff / 2;
            result[i][0] = signal[i][0];
        }

        return result;
    }
}
