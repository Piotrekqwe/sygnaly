package pl.kw;

import java.util.Calendar;

public class RadarSimulation {
    private double[][] sentSignal;
    private double[][] reflectedSignal;
    private double distance;

    public double[][] getSentSignal() {
        return sentSignal;
    }

    public double[][] getReflectedSignal() {
        return reflectedSignal;
    }

    public double getDistance() {
        return distance;
    }

    public RadarSimulation(double[][] signal, int bufferSize, double probingFrequency, double realDistance, double signalSpeed, double objectSpeed) {
        reflectedSignal = new double[bufferSize][2];
        sentSignal = new double[bufferSize][2];
        double delay = -realDistance / signalSpeed * 2000;
        double probingTime = 1000 / probingFrequency;
        double delayStep = delay / (signal[signal.length - 1][0] / signal.length);
        double step = probingTime / (signal[signal.length - 1][0] / signal.length);
        double iter;
        int num;
        double objectSpeedMultiplier = 1 + objectSpeed / signalSpeed;

        double maxValue = signal[0][1];
        double minValue = maxValue;
        for(int i = 1; i < signal.length; i++) {
            if(signal[i][1] > maxValue){ maxValue = signal[i][1];}
            else if (signal[i][1] < minValue){ minValue = signal[i][1];}
        }

        double[][] noise = Calculator.szumGaussowski((maxValue - minValue) / 20, 0, 0, bufferSize);

        while(delayStep < 0){
            delayStep += signal.length;
        }

        for(int i = 0; i < bufferSize; i++){
            reflectedSignal[i][0] = probingTime * i;
            iter = (delayStep + step * i * objectSpeedMultiplier);
            num = (int) iter;
            reflectedSignal[i][1] = signal[num % signal.length][1] * (iter - num) + signal[(num + 1) % signal.length][1] * (num + 1 - iter) + noise[i][1];

            sentSignal[i][0] = probingTime * i;
            iter = (step * i);
            num = (int) iter;
            sentSignal[i][1] = signal[num % signal.length][1] * (iter - num) + signal[(num + 1) % signal.length][1] * (num + 1 - iter);
        }

        double[][] correlatedSignal = Calculator.correlation(sentSignal, reflectedSignal);

        int max = correlatedSignal.length / 2;
        for(int i = max; i > 0; i--){
            if(correlatedSignal[max][1] < correlatedSignal[i][1]) max = i;
        }

        distance = signalSpeed * (correlatedSignal.length / 2 - max) * (signal[signal.length - 1][0] / signal.length) / 110;
    }
}
