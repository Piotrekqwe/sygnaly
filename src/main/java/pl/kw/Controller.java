package pl.kw;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.plot.FunctionPlot;
import com.panayotis.gnuplot.plot.Plot;
import com.panayotis.gnuplot.style.FillStyle;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.GNUPlotTerminal;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Math.*;


public class Controller implements Initializable {
    public static int ACC = 1000;
    public static String SAVE_FILE_PATH = "diagrams.bin";

    //public static ArrayList<Diagram> diagrams = new ArrayList<Diagram>();
    public static ArrayList<Diagram> displayGroup = new ArrayList<Diagram>();
    private Diagram currentDiagram = null;
    private Diagram first = null;
    private Diagram second = null;

    //tab1
    public ToggleGroup function;
    public RadioButton btn1;
    public RadioButton btn2;
    public RadioButton btn3;
    public RadioButton btn4;
    public RadioButton btn5;
    public RadioButton btn6;
    public RadioButton btn7;
    public RadioButton btn8;
    public RadioButton btn9;
    public RadioButton btn10;
    public RadioButton btn11;
    public Text title1;
    public Text title2;
    public Text title3;
    public Text title4;
    public Text title5;
    public TextField field1;
    public TextField field2;
    public TextField field3;
    public TextField field4;
    public TextField field5;

    //tab2
    public ListView listOfDiagrams;
    public TextField histogramSize;
    public TextField newNameField;
    public Text meanDisplay;
    public Text absoluteMeanDisplay;
    public Text averagePowerDisplay;
    public Text varianceDisplay;
    public Text effectiveValueDisplay;
    public Text firstDiagramName;
    public Text secondDiagramName;

    public TextField numberOfSamples;
    public Text meanSquareErrorDisplay;
    public Text signalToNoiseRatioDisplay;
    public Text maxSignalToNoiseRatioDisplay;
    public Text maxErrorDisplay;
    public Text groupCountDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numbersOnlyTextField(field1);
        numbersOnlyTextField(field2);
        numbersOnlyTextField(field3);
        numbersOnlyTextField(field4);
        numbersOnlyTextField(field5);
        numbersOnlyTextField(histogramSize);
        numbersOnlyTextField(numberOfSamples);
        checkBtn();
    }

    //auxiliary functions
    private void numbersOnlyTextField(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    public void checkBtn() {
        Toggle selectedToggle = function.getSelectedToggle();
        if (btn1.equals(selectedToggle)) {
            title1.setText("Amplituda");
            title2.setText("Czas początkowy [ms]");
            title3.setText("Czas trwania sygnału [ms]");
            title1.setVisible(true);
            field1.setVisible(true);
            title2.setVisible(true);
            field2.setVisible(true);
            title3.setVisible(true);
            field3.setVisible(true);
            title4.setVisible(false);
            field4.setVisible(false);
            title5.setVisible(false);
            field5.setVisible(false);
        } else if (btn2.equals(selectedToggle)) {
            title1.setText("Odchylenie standardowe");
            title2.setText("Czas początkowy [ms]");
            title3.setText("Czas trwania sygnału [ms]");
            title1.setVisible(true);
            field1.setVisible(true);
            title2.setVisible(true);
            field2.setVisible(true);
            title3.setVisible(true);
            field3.setVisible(true);
            title4.setVisible(false);
            field4.setVisible(false);
            title5.setVisible(false);
            field5.setVisible(false);
        } else if (btn3.equals(selectedToggle) || btn4.equals(selectedToggle) || btn5.equals(selectedToggle)) {
            title1.setText("Amplituda");
            title2.setText("Czas początkowy [ms]");
            title3.setText("Czas trwania sygnału [ms]");
            title4.setText("Długość okresu [ms]");
            title1.setVisible(true);
            field1.setVisible(true);
            title2.setVisible(true);
            field2.setVisible(true);
            title3.setVisible(true);
            field3.setVisible(true);
            title4.setVisible(true);
            field4.setVisible(true);
            title5.setVisible(false);
            field5.setVisible(false);

        } else if (btn6.equals(selectedToggle) || btn7.equals(selectedToggle)) {
            title1.setText("Amplituda");
            title2.setText("Czas początkowy [ms]");
            title3.setText("Czas trwania sygnału [ms]");
            title4.setText("Długość okresu [ms]");
            title5.setText("Współczynnik wypełnienia [%]");
            title1.setVisible(true);
            field1.setVisible(true);
            title2.setVisible(true);
            field2.setVisible(true);
            title3.setVisible(true);
            field3.setVisible(true);
            title4.setVisible(true);
            field4.setVisible(true);
            title5.setVisible(true);
            field5.setVisible(true);

        } else if (btn8.equals(selectedToggle)) {
            title1.setText("Amplituda");
            title2.setText("Czas początkowy [ms]");
            title3.setText("Czas trwania sygnału [ms]");
            title4.setText("Długość okresu [ms]");
            title5.setText("Wygięcie [%]");
            title1.setVisible(true);
            field1.setVisible(true);
            title2.setVisible(true);
            field2.setVisible(true);
            title3.setVisible(true);
            field3.setVisible(true);
            title4.setVisible(true);
            field4.setVisible(true);
            title5.setVisible(true);
            field5.setVisible(true);

        } else if (btn9.equals(selectedToggle)) {
            title1.setText("Amplituda");
            title2.setText("Czas początkowy [ms]");
            title3.setText("Czas trwania sygnału [ms]");
            title4.setText("Czas skoku [ms]");
            title1.setVisible(true);
            field1.setVisible(true);
            title2.setVisible(true);
            field2.setVisible(true);
            title3.setVisible(true);
            field3.setVisible(true);
            title4.setVisible(true);
            field4.setVisible(true);
            title5.setVisible(false);
            field5.setVisible(false);

        } else if (btn10.equals(selectedToggle)) {
            title1.setText("Amplituda");
            title2.setText("Czas początkowy [ms]");
            title3.setText("Czas trwania sygnału [ms]");
            title4.setText("Numer próbki impulsu");
            title5.setText("Ilość próbek");
            title1.setVisible(true);
            field1.setVisible(true);
            title2.setVisible(true);
            field2.setVisible(true);
            title3.setVisible(true);
            field3.setVisible(true);
            title4.setVisible(true);
            field4.setVisible(true);
            title5.setVisible(true);
            field5.setVisible(true);

        } else if (btn11.equals(selectedToggle)) {
            title1.setText("Amplituda");
            title2.setText("Czas początkowy [ms]");
            title3.setText("Czas trwania sygnału [ms]");
            title4.setText("Prawdopodobieństwo");
            title5.setText("Ilość próbek");
            title1.setVisible(true);
            field1.setVisible(true);
            title2.setVisible(true);
            field2.setVisible(true);
            title3.setVisible(true);
            field3.setVisible(true);
            title4.setVisible(true);
            field4.setVisible(true);
            title5.setVisible(true);
            field5.setVisible(true);

        }
    }

    //gnuplot functions
    private DataSetPlot generateDataSet(Diagram diagram){
        if (diagram != null) {
            if (diagram.defaultDiagramType == Diagram.DiagramType.BOXES) {
                PlotStyle myPlotStyle = new PlotStyle();
                myPlotStyle.setStyle(Style.BOXES);
                FillStyle fillStyle = new FillStyle();
                fillStyle.setStyle(FillStyle.Fill.SOLID);
                myPlotStyle.setFill(fillStyle);
                myPlotStyle.setLineWidth(0);
                DataSetPlot dataSetPlot = new DataSetPlot(diagram.getPoints());
                dataSetPlot.setPlotStyle(myPlotStyle);
                dataSetPlot.setTitle(diagram.name);
                return dataSetPlot;

            } else if (diagram.defaultDiagramType == Diagram.DiagramType.LINE) {
                PlotStyle myPlotStyle = new PlotStyle();
                myPlotStyle.setStyle(Style.LINES);
                myPlotStyle.setLineWidth(0);
                DataSetPlot dataSetPlot = new DataSetPlot(diagram.getPoints());
                dataSetPlot.setPlotStyle(myPlotStyle);
                dataSetPlot.setTitle(diagram.name);
                return dataSetPlot;

            } else if (diagram.defaultDiagramType == Diagram.DiagramType.POINTS) {
                PlotStyle myPlotStyle = new PlotStyle();
                myPlotStyle.setStyle(Style.POINTS);
                myPlotStyle.setPointType(7);
                DataSetPlot dataSetPlot = new DataSetPlot(diagram.getPoints());
                dataSetPlot.setPlotStyle(myPlotStyle);
                dataSetPlot.setTitle(diagram.name);
                return dataSetPlot;
            }
        }
        return null;
    }
    public void displayDiagram(Diagram diagram) {
        JavaPlot p = new JavaPlot();
        p.set("xzeroaxis", "");
        DataSetPlot dataSetPlot = generateDataSet(diagram);
        p.addPlot(dataSetPlot);
        p.plot();
    }
    public void generateHistogram(Diagram diagram, int acc) {
        if (diagram != null && acc > 1) {
            String[] titles = new String[acc];
            double[] values = new double[acc];
            //Arrays.fill(values, 0.0);
            titles[0] = df2.format(diagram.getMin());
            for (int i = 1; i < acc; i++) {
                double edge = diagram.getMin() + (diagram.getMax() - diagram.getMin()) * i / acc;
                titles[i - 1] += "..." + df2.format(edge);
                titles[i] = df2.format(edge);
            }
            titles[acc - 1] += "..." + df2.format(diagram.getMax());

            for (double[] value : diagram.getPoints()) {
                int index = (int) floor((value[1] - diagram.getMin()) * acc / (diagram.getMax() - diagram.getMin()));
                if (index == acc) {
                    index--;
                }
                values[index]++;
            }

            for (int i = 0; i < acc; i++) {
                values[i] /= diagram.getPoints().length;
            }
            StringBuilder data = new StringBuilder("title histogram");
            for (int i = 0; i < acc; i++) {
                data.append("\n").append(titles[i]).append(" ").append(values[i]);
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
                writer.write(data.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Process p = new ProcessBuilder("\"C:\\Program Files\\gnuplot\\bin\\gnuplot\" -p \"script.gp\"").start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //diagram functions
    public void generateDiagram() {

        Toggle selectedToggle = function.getSelectedToggle();

        if (btn1.equals(selectedToggle)) {
            double[][] points = Calculator.szumJednostajny(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.BOXES, "szum o rozkładzie jednostajnym");

        } else if (btn2.equals(selectedToggle)) {
            double[][] points = Calculator.szumGaussowski(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.BOXES, "szum gaussowski");

        } else if (btn3.equals(selectedToggle)) {
            double[][] points = Calculator.sinusoida(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE, "sygnał sinusoidalny");

        } else if (btn4.equals(selectedToggle)) {
            double[][] points = Calculator.sinusoida(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), ACC);
            for (double[] point : points) {
                if (point[1] < 0) {
                    point[1] = 0;
                }
            }
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE, "sygnał sinusoidalny wyprostowany jednopołówkowo");

        } else if (btn5.equals(selectedToggle)) {
            double[][] points = Calculator.sinusoida(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), ACC);
            for (double[] point : points) {
                if (point[1] < 0) {
                    point[1] *= -1;
                }
            }
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE, "sygnał sinusoidalny wyprostowany dwupołówkowo");

        } else if (btn6.equals(selectedToggle)) {
            double[][] points = Calculator.prostokatny(Double.parseDouble(field1.getText()), 0,
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Double.parseDouble(field5.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE, "sygnał prostokątny");

        } else if (btn7.equals(selectedToggle)) {
            double[][] points = Calculator.prostokatny(Double.parseDouble(field1.getText()), -Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Double.parseDouble(field5.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE, "sygnał prostokątny symetryczny");

        } else if (btn8.equals(selectedToggle)) {
            double[][] points = Calculator.trojkatny(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Double.parseDouble(field5.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE, "sygnał trójkątny");

        } else if (btn9.equals(selectedToggle)) {
            double[][] points = Calculator.skok(Double.parseDouble(field1.getText()), 0,
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE, "skok jednostkowy");

        } else if (btn10.equals(selectedToggle)) {
            double[][] points = Calculator.impulsJednostkowy(Double.parseDouble(field1.getText()), 0,
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Integer.parseInt(field5.getText()));
            currentDiagram = new Diagram(points, Diagram.DiagramType.POINTS, "impuls jednostkowy");

        } else if (btn11.equals(selectedToggle)) {
            double[][] points = Calculator.szumImpulsowy(Double.parseDouble(field1.getText()), 0,
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Integer.parseInt(field5.getText()));
            currentDiagram = new Diagram(points, Diagram.DiagramType.POINTS, "szum impulsowy");


        }
    }
    public void showCurrentDiagram() {
        displayDiagram(currentDiagram);
    }
    public void showSelectedDiagram() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            displayDiagram(diagram);
        }
    }

    //histogram functions
    public void showCurrentHistogram() {
        generateHistogram(currentDiagram, 7);
    }
    public void showSelectedHistogram() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            generateHistogram(diagram, Integer.parseInt(histogramSize.getText()));
        }
    }

    //list functions
    public void addListItem() {
        if(currentDiagram != null){
            listOfDiagrams.getItems().add(currentDiagram);
        }
        currentDiagram = null;
    }
    public void deleteSelectedDiagram() {
        listOfDiagrams.getItems().remove(listOfDiagrams.getSelectionModel().getSelectedItem());
    }
    public void saveDiagrams() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_PATH))) {
            ArrayList<Object> diagrams = new ArrayList<Object>(Arrays.asList((Object[]) listOfDiagrams.getItems().toArray()));
            out.writeObject(diagrams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadDiagrams() {
        listOfDiagrams.getItems().removeAll(listOfDiagrams.getItems());
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE_PATH))) {
            ArrayList<Diagram> diagrams = (ArrayList<Diagram>) in.readObject();
            for (Diagram diagram : diagrams) {
                listOfDiagrams.getItems().add(diagram);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void changeName() {
        ((Diagram) listOfDiagrams.getSelectionModel().getSelectedItem()).name = newNameField.getText();
        listOfDiagrams.refresh();
    }

    //additional functions
    private static final DecimalFormat df2 = new DecimalFormat("#.##");
    public void calculateValues() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            double mean = 0;
            for (double[] point : diagram.getPoints()) {
                mean += point[1];
            }
            mean /= diagram.getPoints().length;

            double absoluteMean = 0;
            for (double[] point : diagram.getPoints()) {
                absoluteMean += abs(point[1]);
            }
            absoluteMean /= diagram.getPoints().length;

            double averagePower = 0;
            for (double[] point : diagram.getPoints()) {
                averagePower += pow(point[1], 2);
            }
            averagePower /= diagram.getPoints().length;

            double variance = 0;
            for (double[] point : diagram.getPoints()) {
                variance += pow(point[1] - mean, 2);
            }
            variance /= diagram.getPoints().length;

            double effectiveValue = sqrt(averagePower);

            meanDisplay.setText("Średnia: " + df2.format(mean));
            absoluteMeanDisplay.setText("Średnia bezwzględna: " + df2.format(absoluteMean));
            averagePowerDisplay.setText("Moc średnia: " + df2.format(averagePower));
            varianceDisplay.setText("Wariancja: " + df2.format(variance));
            effectiveValueDisplay.setText("Wartość skuteczna: " + df2.format(effectiveValue));
        }
    }

    //operations
    public void setDiagram1() {
        first = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (first != null) {
            firstDiagramName.setText(first.toString());
        }
    }
    public void setDiagram2() {
        second = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (second != null) {
            secondDiagramName.setText(second.toString());
        }
    }
    public void addAction() {
        if (first != null && second != null) {
            currentDiagram = new Diagram(Calculator.add(first.getPoints(), second.getPoints()), Diagram.DiagramType.LINE, "suma diagramów");
        }
    }
    public void subtractAction() {
        if (first != null && second != null) {
            currentDiagram = new Diagram(Calculator.subtract(first.getPoints(), second.getPoints()), Diagram.DiagramType.LINE, "różnica diagramów");
        }
    }
    public void multiplyAction() {
        if (first != null && second != null) {
            currentDiagram = new Diagram(Calculator.multiply(first.getPoints(), second.getPoints()), Diagram.DiagramType.LINE, "iloczyn diagramów");
        }
    }
    public void divideAction() {
        if (first != null && second != null) {
            currentDiagram = new Diagram(Calculator.divide(first.getPoints(), second.getPoints()), Diagram.DiagramType.LINE, "iloraz diagramów");
        }
    }

    //sampling and reconstruction
    public void doSampling() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            Diagram newDiagram = new Diagram(Calculator.sampling(diagram.getPoints(), Integer.parseInt(numberOfSamples.getText())),
                    Diagram.DiagramType.POINTS, "wynik próbkowania");
            listOfDiagrams.getItems().add(newDiagram);
        }
    }
    public void zeroOrder() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            currentDiagram = new Diagram(Calculator.zeroOrder(diagram.getPoints()), Diagram.DiagramType.LINE, "Zero Order Hold");
            displayDiagram(currentDiagram);
        }
    }
    public void firstOrder() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            currentDiagram = new Diagram(diagram.getPoints(), Diagram.DiagramType.LINE, "First Order Hold");
            displayDiagram(currentDiagram);
        }
    }
    public void zeroOrderNormalized() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            currentDiagram = new Diagram(Calculator.zeroOrderNormalized(diagram.getPoints()), Diagram.DiagramType.LINE, "Zero Order z Zaokrąglaniem");
            displayDiagram(currentDiagram);
        }
    }
    public void sinc(){
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            currentDiagram = new Diagram(Calculator.sincInterpolation(diagram.getPoints(), ACC), Diagram.DiagramType.LINE, "sinc");
            displayDiagram(currentDiagram);
        }
    }
    public void compare() {
        if (first != null && second != null) {
            ArrayList<Double> xValues = Calculator.generateXValues(first.getPoints(), second.getPoints());
            double[][] p1 = Calculator.rescale(xValues, first.getPoints());
            double[][] p2 = Calculator.rescale(xValues, second.getPoints());

            double meanSquareError = 0;
            double maxError = 0;
            double signalToNoiseRatio = 0;
            double maxSignalToNoiseRatio = 0;
            for (int i = 0; i < p1.length; i++) {
                double error = Math.abs(p1[i][1] - p2[i][1]);
                meanSquareError += Math.pow(error, 2);
                if(error > maxError) maxError = error;
                signalToNoiseRatio += Math.pow(p1[i][1], 2);
                if(p1[i][1] > maxSignalToNoiseRatio) maxSignalToNoiseRatio = p1[i][1];
            }
            signalToNoiseRatio = Math.log10(signalToNoiseRatio / meanSquareError);
            meanSquareError /= p1.length;
            maxSignalToNoiseRatio = Math.log10(maxSignalToNoiseRatio / meanSquareError);

            meanSquareErrorDisplay.setText("Błąd średniokwadratowy: " + df2.format(meanSquareError));
            signalToNoiseRatioDisplay.setText("Stosunek sygnał - szum: " + df2.format(maxError));
            maxSignalToNoiseRatioDisplay.setText("Szczytowy stosunek sygnał - szum: " + df2.format(signalToNoiseRatio));
            maxErrorDisplay.setText("Maksymalna różnica: " + df2.format(maxSignalToNoiseRatio));
        }
    }

    //multiple diagram display
    public void updateGroupCount(){
        groupCountDisplay.setText("liczba diagramów: " + displayGroup.size());
    }
    public void addToGroup() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null && !displayGroup.contains(diagram)) {
            displayGroup.add(diagram);
            updateGroupCount();
        }
    }
    public void clearGroup() {
        displayGroup.clear();
        updateGroupCount();
    }
    public void showGroupOfDiagrams() {
        if (displayGroup.size() >= 1){
            JavaPlot p = new JavaPlot();
            p.set("xzeroaxis", "");
            for (Diagram diagram : displayGroup) {
                DataSetPlot dataSetPlot = generateDataSet(diagram);
                p.addPlot(dataSetPlot);
            }
            p.plot();
        }
    }

    //quantization
    public void truncatedQuantization() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            currentDiagram = new Diagram(Calculator.truncatedQuantization(diagram.getPoints(), Integer.parseInt(numberOfSamples.getText())),
                    Diagram.DiagramType.LINE, "wynik próbkowania");
            displayDiagram(currentDiagram);
        }
    }
    public void roundingQuantization() {
        Diagram diagram = (Diagram) listOfDiagrams.getSelectionModel().getSelectedItem();
        if (diagram != null) {
            currentDiagram = new Diagram(Calculator.roundingQuantization(diagram.getPoints(), Integer.parseInt(numberOfSamples.getText())),
                    Diagram.DiagramType.LINE, "wynik próbkowania");
            displayDiagram(currentDiagram);
        }
    }
}
