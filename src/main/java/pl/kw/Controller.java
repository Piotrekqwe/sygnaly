package pl.kw;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.FillStyle;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This is the main controller class responsible for direct contact with javafx gui
 */
public class Controller implements Initializable {
    public static int ACC = 300;

    public static ArrayList<Diagram> diagrams = new ArrayList<Diagram>();
    private Diagram currentDiagram = null;

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
    public Button generateDiagramBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numbersOnlyTextField(field1);
        numbersOnlyTextField(field2);
        numbersOnlyTextField(field3);
        numbersOnlyTextField(field4);
        numbersOnlyTextField(field5);
        checkBtn();
    }

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

    public void generateDiagram() {

        Toggle selectedToggle = function.getSelectedToggle();

        if (btn1.equals(selectedToggle)) {
            double[][] points = Calculator.szumJednostajny(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.BOXES);

        }
        else if (btn2.equals(selectedToggle)) {
            double[][] points = Calculator.szumGaussowski(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.BOXES);

        }
        else if (btn3.equals(selectedToggle)) {
            double[][] points = Calculator.sinusoida(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE);

        }
        else if (btn4.equals(selectedToggle)) {
            double[][] points = Calculator.sinusoida(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), ACC);
            for (double[] point : points) {
                if (point[1] < 0) {
                    point[1] = 0;
                }
            }
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE);

        }
        else if (btn5.equals(selectedToggle)) {
            double[][] points = Calculator.sinusoida(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), ACC);
            for (double[] point : points) {
                if (point[1] < 0) {
                    point[1] *= -1;
                }
            }
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE);

        }
        else if (btn6.equals(selectedToggle)) {
            double[][] points = Calculator.prostokatny(Double.parseDouble(field1.getText()), 0,
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Double.parseDouble(field5.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE);

        }
        else if (btn7.equals(selectedToggle)) {
            double[][] points = Calculator.prostokatny(Double.parseDouble(field1.getText()), -Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Double.parseDouble(field5.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE);

        }
        else if (btn8.equals(selectedToggle)) {
            double[][] points = Calculator.trojkatny(Double.parseDouble(field1.getText()),
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Double.parseDouble(field5.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE);

        }
        else if (btn9.equals(selectedToggle)) {
            double[][] points = Calculator.skok(Double.parseDouble(field1.getText()), 0,
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), ACC);
            currentDiagram = new Diagram(points, Diagram.DiagramType.LINE);

        }
        else if (btn10.equals(selectedToggle)) {
            double[][] points = Calculator.impulsJednostkowy(Double.parseDouble(field1.getText()), 0,
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Integer.parseInt(field5.getText()));
            currentDiagram = new Diagram(points, Diagram.DiagramType.POINTS);

        }
        else if (btn11.equals(selectedToggle)) {
            double[][] points = Calculator.szumImpulsowy(Double.parseDouble(field1.getText()), 0,
                    Double.parseDouble(field2.getText()), Double.parseDouble(field3.getText()),
                    Double.parseDouble(field4.getText()), Integer.parseInt(field5.getText()));
            currentDiagram = new Diagram(points, Diagram.DiagramType.POINTS);


        }
    }

    public void showDiagram(ActionEvent actionEvent) {
        if(currentDiagram != null){
            if (currentDiagram.defaultDiagramType == Diagram.DiagramType.BOXES){
                JavaPlot p = new JavaPlot();

                PlotStyle myPlotStyle = new PlotStyle();
                myPlotStyle.setStyle(Style.BOXES);
                FillStyle fillStyle = new FillStyle();
                fillStyle.setStyle(FillStyle.Fill.SOLID);
                myPlotStyle.setFill(fillStyle);
                myPlotStyle.setLineWidth(0);
                DataSetPlot DataSetPlot = new DataSetPlot(currentDiagram.getPoints());
                DataSetPlot.setPlotStyle(myPlotStyle);

                p.addPlot(DataSetPlot);
                p.plot();

            }
            else if (currentDiagram.defaultDiagramType == Diagram.DiagramType.LINE){
                JavaPlot p = new JavaPlot();

                PlotStyle myPlotStyle = new PlotStyle();
                myPlotStyle.setStyle(Style.LINES);
                myPlotStyle.setLineWidth(0);
                DataSetPlot DataSetPlot = new DataSetPlot(currentDiagram.getPoints());
                DataSetPlot.setPlotStyle(myPlotStyle);

                p.addPlot(DataSetPlot);
                p.plot();

            }
            else if (currentDiagram.defaultDiagramType == Diagram.DiagramType.POINTS){
                JavaPlot p = new JavaPlot();

                PlotStyle myPlotStyle = new PlotStyle();
                myPlotStyle.setStyle(Style.POINTS);
                myPlotStyle.setPointType(7);
                DataSetPlot DataSetPlot = new DataSetPlot(currentDiagram.getPoints());
                DataSetPlot.setPlotStyle(myPlotStyle);

                p.addPlot(DataSetPlot);
                p.plot();

            }
        }
    }

}
