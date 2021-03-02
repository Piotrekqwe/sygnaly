package pl.kw;

import com.panayotis.gnuplot.JavaPlot;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This is the main controller class responsible for direct contact with javafx gui
 */
public class Controller implements Initializable {


    public Button btn1, btn2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    /**
     * is called on key press
     */
    public void keyPress(KeyEvent key) {
    }
    /**
     * is called on key release
     */
    public void keyRelease(KeyEvent key) {
    }

    /**
     * reset 1st distance counter
     */
    public void fun1(ActionEvent actionEvent) {
        JavaPlot p = new JavaPlot();
        p.addPlot("sin(x)");
        p.plot();
    }
    /**
     * reset 2nd distance counter
     */
    public void fun2(ActionEvent actionEvent) {
    }


}
