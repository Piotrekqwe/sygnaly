package pl.kw;

public class Diagram {
    private final double[][] points;
    DiagramType defaultDiagramType;

    enum DiagramType {
        BOXES,
        LINE,
        POINTS
    }

    public Diagram(double[][] points, DiagramType defaultDiagramType) {
        this.points = points;
        this.defaultDiagramType = defaultDiagramType;
    }

    public Diagram(double[][] points) {
        this.points = points;
        this.defaultDiagramType = DiagramType.LINE;
    }

    public double[][] getPoints() {
        return points;
    }


}
