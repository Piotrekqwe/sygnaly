package pl.kw;

public class DftDiagram extends Diagram{
    private double length;

    public DftDiagram(double[][] points, DiagramType defaultDiagramType, String name, double length) {
        super(points, defaultDiagramType, name);
        this.length = length;
    }

    public double getLength() {
        return length;
    }
}
