package pl.kw;

import java.util.ArrayList;
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
            points[i][1] = amplituda * sin(2 * PI / okres * (points[i][0]));
        }
        return points;
    }

    public static double[][] prostokatny(double max, double min, double czasP, double dlugosc, double okres, double wypelnienie, int acc) {
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = czasP + i * dlugosc / acc;
            if((points[i][0] % okres) / okres < wypelnienie / 100){
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
            if((points[i][0] % okres) / okres < wygiecie / 100){
                points[i][1] = amplituda * ((points[i][0] % okres) / okres / wygiecie * 100);
            } else {
                points[i][1] = amplituda - amplituda * ((points[i][0] % okres) / okres - wygiecie / 100) * 100 / (100 - wygiecie);
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
            points[i][0] = czasP + i * dlugosc / acc;
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
            points[i][0] = czasP + i * dlugosc / acc;
            if(r.nextDouble() <= chance / 100){
                points[i][1] = max;
            } else {
                points[i][1] = min;
            }
        }
        return points;
    }


    private static double[][] rescale(ArrayList<Double> xValues, double[][] points) {
        double[][] newPoints = new double[xValues.size()][2];
        for (int i = 0; i < xValues.size(); i++) {
            newPoints[i][0] = xValues.get(i);
        }
        int i = 0, j = 0;
        while(i < xValues.size() && newPoints[i][0] < points[0][0]){
            newPoints[i][1] = 0;
            i++;
        }
        while(i < xValues.size() && newPoints[i][0] <= points[points.length - 1][0]){
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
    private static ArrayList<Double> generateXValues(double[][] first, double[][] second){
        ArrayList<Double> xValues = new ArrayList<Double>();
        int i = 0, j = 0;
        while(i < first.length && j < second.length){
            if(first[i][0] < second[j][0]){
                xValues.add(first[i][0]);
                i++;
            }else if(first[i][0] > second[j][0]){
                xValues.add(second[i][0]);
                j++;
            }else{
                xValues.add(first[i][0]);
                i++;
                j++;
            }
        }
        while(i < first.length){
            xValues.add(first[i][0]);
            i++;
        }
        while(j < second.length){
            xValues.add(second[i][0]);
            j++;
        }
        return xValues;
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
}
