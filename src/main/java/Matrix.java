import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class Matrix{

    private JTable AdjacencyMatrix;
    private JTable IncidenceMatrix;

    public TableModel SizeOfAdjacencyMatrix(int vertexes, JTable AdjacencyMatrix) {

        this.AdjacencyMatrix = AdjacencyMatrix;
        List<ArrayList<Integer>> SizeOfMatrix = new ArrayList<>();

        if (vertexes > 0) {
            for (int i = 0; i < vertexes; i++) {
                SizeOfMatrix.add(new ArrayList());
                for (int j = 0; j < vertexes; j++) {
                    SizeOfMatrix.get(i).add(0);
                }
            }
        }
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
            }
        }

        createTableAdjacencyMatrix(SizeOfMatrix, vertexes);
        return null;
    }

    public void SizeOfIncidenceMatrix(int vertexes, int edges, JTable IncidenceMatrix) {

        this.IncidenceMatrix = IncidenceMatrix;
        List<ArrayList<Integer>> SizeOfMatrixIncidence = new ArrayList<>();


        for (int i = 0; i < vertexes; i++) {
            SizeOfMatrixIncidence.add(new ArrayList());
            for (int j = 0; j < edges; j++) {
                SizeOfMatrixIncidence.get(i).add(0);
            }
        }


        createTableIncidenceMatrix(SizeOfMatrixIncidence, vertexes, edges);
    }

    public void createTableAdjacencyMatrix(List<ArrayList<Integer>> SizeOfMatrix, int vertexes) {

        Object[][] data = new Object[vertexes][vertexes];

        DefaultTableModel test = (DefaultTableModel) AdjacencyMatrix.getModel();
        test.setRowCount(vertexes);
        test.setColumnCount(vertexes);

        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
                test.setValueAt(0, i, j);

            }
            ;
        }
        AdjacencyMatrix.setModel(test);

    }


    public void createTableIncidenceMatrix(List<ArrayList<Integer>> SizeOfMatrixIncidence, int vertexes, int edges) {

        DefaultTableModel test = (DefaultTableModel) IncidenceMatrix.getModel();
        test.setRowCount(vertexes);
        test.setColumnCount(edges);

        System.out.print("\n");
        System.out.print("\n Vertexes:" + vertexes);
        System.out.print("\n Edges:" + edges);
        System.out.print("\n Size of Matrix:" + SizeOfMatrixIncidence);

        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < edges; j++) {
                test.setValueAt(0, i, j);
            }
        }

        IncidenceMatrix.setModel(test
        );
    }

}