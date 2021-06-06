package pl.kw;

public class DftDiagram extends Diagram{
    private final double length;
    private final double period;

    public DftDiagram(double[][] points, DiagramType defaultDiagramType, String name, double length, double period) {
        super(points, defaultDiagramType, name);
        this.length = length;
        this.period = period;
    }

    public double getLength() {
        return length;
    }

    public double getPeriod() {
        return period;
    }
}
