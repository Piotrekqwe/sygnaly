package pl.kw;

import org.apache.commons.math3.complex.Complex;

public class ComplexCalculator {
    private ComplexCalculator() {
    }

    public static Complex[] signalToComplexArray(double[][] signal) {
        Complex[] result = new Complex[signal.length];
        for (int i = 0; i < signal.length; i++) {
            result[i] = new Complex(signal[i][1], 0);
        }
        return result;
    }
    public static Complex[] constructComplexSignal(double[][] real, double[][] imaginary) {
        int length = real.length;
        if(imaginary.length < length) length = imaginary.length;;
        Complex[] result = new Complex[real.length];
        for (int i = 0; i < length; i++) {
            result[i] = new Complex(real[i][1], imaginary[i][1]);
        }
        return result;
    }
    public static double[][] complexToRealSignal(Complex[] arr, double length) {
        double[][] result = new double[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            result[i][1] = arr[i].getReal();
            result[i][0] = length * i / arr.length;
        }
        return result;
    }
    public static double[][] complexToImaginarySignal(Complex[] arr, double length) {
        double[][] result = new double[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            result[i][1] = arr[i].getImaginary();
            result[i][0] = length * i / arr.length;
        }
        return result;
    }
    public static double[][] complexToArgumentSignal(Complex[] arr, double length) {
        double[][] result = new double[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            result[i][1] = arr[i].getArgument();
            result[i][0] = length * i / arr.length;
        }
        return result;
    }
    public static double[][] complexToModuleSignal(Complex[] arr, double length) {
        double[][] result = new double[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            result[i][1] = arr[i].abs();
            result[i][0] = length * i / arr.length;
        }
        return result;
    }

    //TODO: rename this
    private static Complex recursiveFft2f(Complex[] x, int m) {
        if (x.length == 1) {
            return x[0];
        }

        Complex[] even = new Complex[(x.length + 1) / 2];
        Complex[] odd = new Complex[x.length / 2];

        for (int i = 0; i < x.length; i++) {
            if (i % 2 == 0) {
                even[i / 2] = x[i];
            } else {
                odd[i / 2] = x[i];
            }
        }

        double angle = m * 2.0 * Math.PI / x.length;
        Complex w = new Complex(Math.cos(angle), Math.sin(angle));

        Complex a = recursiveFft2f(even, m);
        Complex b = w.multiply(recursiveFft2f(odd, m));


        return a.add(b);
    }

    public static Complex[] fft(Complex[] signal, int acc) {
        Complex[] result = new Complex[acc];
        for (int i = 0; i < acc; i++) {
            result[i] = recursiveFft2f(signal, -i);
        }
        return result;
    }
    public static Complex[] iFft(Complex[] signal, int acc) {
        Complex[] result = new Complex[acc];
        for (int i = 0; i < acc; i++) {
            result[i] = recursiveFft2f(signal, i).divide(acc);
        }
        return result;
    }
}
