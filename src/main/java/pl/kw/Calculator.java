package pl.kw;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.*;


public class Calculator {
    private Calculator() {}

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
        double y = PI * x;
        return sin(y) / y;
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

    public static double[][] entanglement(double[][] signal1, double[][] signal2) {
        double[][] result = new double[signal1.length + signal2.length - 1][2];
        double start = signal1[0][0];
        double end = signal1[signal1.length - 1][0];
        if (signal1[0][0] > signal2[0][0]) {
            start = signal2[0][0];
        }
        if (signal1[signal1.length - 1][0] < signal2[signal2.length - 1][0]) {
            end = signal2[signal2.length - 1][0];
        }
        for (int i = 0; i < result.length; i++) {
            result[i][0] = ((result.length - i) * start + i * end) / result.length;
            result[i][1] = 0;
            int j = 0;
            int k = i;
            if (i > signal2.length - 1) {
                int temp = i - (signal2.length - 1);
                j += temp;
                k -= temp;
            }
            while (k >= 0 && j < signal1.length) {
                result[i][1] += signal1[j][1] * signal2[k][1];
                j++;
                k--;
            }
        }
        return result;
    }
    public static double[][] correlation(double[][] signal1, double[][] signal2){
        double[][] result = new double[signal1.length + signal2.length - 1][2];
        double start = signal1[0][0];
        double end = signal1[signal1.length - 1][0];
        //if(signal1[0][0] > signal2[0][0]){
        //    start = signal2[0][0];
        //}
        //if(signal1[signal1.length - 1][0] < signal2[signal2.length - 1][0]){
        //    end = signal2[signal2.length - 1][0];
        //}

        for (int i = 0; i < result.length; i++) {
            result[i][0] = ((result.length - i) * start + i * end) / result.length;
            result[i][1] = 0;
            int j = 0;
            int k = 0;
            if (i > signal2.length - 1) {
                j += i - (signal2.length - 1);
            }
            else if(i < signal2.length - 1){
                k += (signal2.length - 1) - i;
            }
            while (k < signal2.length && j < signal1.length) {
                result[i][1] += signal1[j][1] * signal2[k][1];
                j++;
                k++;
            }
        }

        return result;
    }

    public static double[] getHTable(double k, int m, boolean high, boolean blackmanWindow){
        double[] hTable = new double[m];
        for(int i = 0; i < m; i++){
            double temp = PI * (i - (m - 1) / 2);
            hTable[i] = sin((2 * temp / k)) / temp;
        }
        hTable[(m-1) / 2] = 2 / k;
        if(high) for(int i = 1; i < m; i+=2) hTable[i] = -hTable[i];
        if(blackmanWindow) {
            for (int i = 1; i < m; i += 2) {
                double temp = 2 * PI * i / m;
                hTable[i] *= 0.42 - 0.5 * cos(temp) + 0.08 * cos(2 * temp);
            }
        }
        return hTable;
    }
    public static double[][] showH(double k, int m, boolean highPass, boolean blackmanWindow){
        double[] h = getHTable(k, m, highPass, blackmanWindow);
        double[][] hTable = new double[h.length][2];
        for(int i = 0; i < h.length; i++){
            hTable[i][0] = i;
            hTable[i][1] = h[i];
        }
        return hTable;
    }
    public static double[][] filter(double[][] signal, double k, int m, boolean highPass, boolean blackmanWindow){
        double[] h = getHTable(k, m, highPass, blackmanWindow);
        double[][] hTable = new double[h.length][2];
        for(int i = 0; i < h.length; i++){
            hTable[i][0] = 0;
            hTable[i][1] = h[i];
        }
        return entanglement(signal, hTable);

    }

    public static double[][] generateExampleProbeSignal(int acc, double length){
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = (double) i * length / acc;
            points[i][1] = 10 * sin(PI / (length / 6) * points[i][0]) +
                    13 * sin(PI / (length / 4) * points[i][0]);
        }
        return points;
    }
    public static double[] pointValue(DftDiagram diagram){
        double[] values = new double[]{0, 0};
        for (double[] point : diagram.getPoints()) {
            values[0] += point[0];
            values[1] += point[1];
        }
        values[0] /= diagram.getPoints().length;
        values[1] /= diagram.getPoints().length;
        return values;
    }
    public static DftDiagram simpleDft(double[][] signal, double period, String name) {
        double[][] points = new double[signal.length][2];
        double angleMultiplier = 2 * PI / period;
        for(int i = 0; i < signal.length; i++){
            double val = signal[i][1];
            double angle = signal[i][0] * angleMultiplier;
            points[i][0] = val * cos(angle);
            points[i][1] = val * sin(angle);
        }


        DftDiagram diagram = new DftDiagram(points, Diagram.DiagramType.LINE, name, signal[signal.length - 1][0] - signal[0][0], period);
        return diagram;
    }
    public static double[][] simpleIDft(DftDiagram diagram) {
        double[][] points = new double[diagram.getPoints().length][2];
        double angleMultiplier = 2 * PI * diagram.getLength() / diagram.getPeriod() / diagram.getPoints().length;
        for (int i = 0; i < diagram.getPoints().length; i++) {
            points[i][0] = i * diagram.getLength() / diagram.getPoints().length;
            double angle = angleMultiplier * i;
            if (sin(angle) > sqrt(2) / 2 || sin(angle) < sqrt(2) / -2) {
                points[i][1] = diagram.getPoints()[i][1] / sin(angle);
            } else {
                points[i][1] = diagram.getPoints()[i][0] / cos(angle);
            }
            //points[i][1] = sqrt(pow(diagram.getPoints()[i][0], 2) + pow(diagram.getPoints()[i][1], 2));
        }
        return points;
    }
    public static double[][][] fullDft(double[][] signal, double maxPeriods, int acc){
        double[][] real = new double[acc][2];
        double[][] imag = new double[acc][2];
        double step = acc * (signal[signal.length - 1][0] - signal[0][0]) / maxPeriods;
        double timeStep = maxPeriods / acc;
        for(int i = 0; i < acc; i++){
            real[i][0] = i * timeStep;
            imag[i][0] = i * timeStep;
            double period = step / i;
            double[] values = pointValue(simpleDft(signal, period, ""));
            real[i][1] = values[1];
            imag[i][1] = values[0];
        }


        return new double[][][]{real, imag};
    }
                                                    //7              6              5               4           3               2               1           0
    public static final double[] db4h = new double[]{0.2303778133, 0.7148465706, 0.6308807679, -0.0279837694, -0.1870348117, 0.0308413818, 0.0328830117, -0.0105974018};
    public static final double[] db4g = new double[]{-0.0105974018, -0.0328830117, 0.0308413818, 0.1870348117, -0.0279837694, -0.6308807679, 0.7148465706, -0.2303778133};
    public static final double[] db4d = new double[]{0.0328830117, 0.7148465706, -0.1870348117, -0.0279837694, 0.6308807679, 0.0308413818, 0.2303778133, -0.0105974018};
    public static final double[] db4r = new double[]{-0.0105974018, -0.2303778133, 0.0308413818, -0.6308807679, -0.0279837694, 0.1870348117, 0.7148465706, -0.0328830117};


    private static double singleWavelet(int index, double[][] signal, double[] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = (index - i);
            if(num < 0) num += signal.length;
            sum += signal[num][1] * arr[i];
        }
        return sum;
    }
    public static double[][][] waveletTransform(double[][] signal){
        double[][] gResult = new double[signal.length / 2][2];
        double[][] hResult = new double[signal.length / 2][2];

        for (int i = 0; i < signal.length; i++) {
            if(i % 2 == 0){
                gResult[i / 2][0] = signal[i][0] / 2;
                gResult[i / 2][1] = singleWavelet(i, signal, db4g);
            }
            else {
                hResult[i / 2][0] = signal[i][0] / 2;
                hResult[i / 2][1] = singleWavelet(i, signal, db4h);
            }
        }

        return new double[][][]{gResult, hResult};
    }

    public static double[][] reverseWaveletTransform(double[][] gSignal, double[][] hSignal){
        double[][] result = new double[gSignal.length + hSignal.length][2];

        for (int i = 0; i < result.length; i++) {
                result[i][0] = gSignal[i / 2][0] * 2;
                result[i][1] = singleWavelet(i / 2, gSignal, db4r) + singleWavelet(i / 2, hSignal, db4d);
        }
        return result;
    }

    public static double[][] exampleSignal1(int acc){
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = (double) 8 * i / acc;
            points[i][1] = 2 * sin(PI * points[i][0] + PI / 2) +
                    5 * sin(4 * PI * points[i][0] + PI / 2);
        }
        return points;
    }
    public static double[][] exampleSignal2(int acc){
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = (double) 8 * i / acc;
            points[i][1] = 2 * sin(PI * points[i][0]) +
                    sin(2 * PI * points[i][0]) +
                    5 * sin(4 * PI * points[i][0]);
        }
        return points;
    }
    public static double[][] exampleSignal3(int acc){
        double[][] points = new double[acc][2];

        for(int i = 0; i < acc; i++) {
            points[i][0] = (double) 8 * i / acc;
            points[i][1] = 5 * sin(PI * points[i][0]) +
                    sin(8 * PI * points[i][0]);
        }
        return points;
    }

}
