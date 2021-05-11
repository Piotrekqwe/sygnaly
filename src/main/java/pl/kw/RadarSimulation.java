package pl.kw;

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
        //TODO: do something with objectSpeed XD

        while(delayStep < 0){
            delayStep += signal.length;
        }

        for(int i = 0; i < bufferSize; i++){
            reflectedSignal[i][0] = probingTime * i;
            iter = (delayStep + step * i);
            num = (int) iter;
            reflectedSignal[i][1] = signal[num % signal.length][1] * (iter - num) + signal[(num + 1) % signal.length][1] * (num + 1 - iter);
            //TODO: add noise

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

        //TODO: distance is about 50~55 times bigger than it should
        distance = signalSpeed * (correlatedSignal.length / 2 - max) * (signal[signal.length - 1][0] / signal.length) / 2;
    }
}
