package pl.kw;

public class Diagram implements java.io.Serializable {
    private final double[][] points;
    private double max, min;
    public DiagramType defaultDiagramType;

    enum DiagramType {
        BOXES,
        LINE,
        POINTS
    }

    public Diagram(double[][] points, DiagramType defaultDiagramType) {
        this.points = points;
        this.defaultDiagramType = defaultDiagramType;
        max = points[0][1];
        min = max;
        for(int i = 1; i < points.length; i++) {
            if(points[i][1] > max){ max = points[i][1];}
            else if (points[i][1] < min){ min = points[i][1];}
        }
    }

    public Diagram(double[][] points) {
        this.points = points;
        this.defaultDiagramType = DiagramType.LINE;
        max = points[0][1];
        min = max;
        for(int i = 1; i < points.length; i++) {
            if(points[i][1] > max){ max = points[i][1];}
            else if (points[i][1] < min){ min = points[i][1];}
        }
    }

    public double[][] getPoints() {
        return points;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
