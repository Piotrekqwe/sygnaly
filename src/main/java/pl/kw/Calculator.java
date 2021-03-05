package pl.kw;

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
            points[i][1] = sin(2 * PI / okres * (points[i][0]));
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
}
