import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.mutNode;

public class DrawingGraphs {

    public void rysujGrafSasiedztwa(Integer vertexes, JTable MacierzSasiedztwa, JLabel graph) {

        List<MutableNode> nodes = new ArrayList<>();
        int q = 0;
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {

                if (nodes.isEmpty()) {
                    for (int l = 0; l < Integer.valueOf(String.valueOf(MacierzSasiedztwa.getValueAt(i, j))); l++) {
                        nodes.add(mutNode(String.valueOf(i)).addLink(mutNode(String.valueOf(j))));
                    }
                } else {

                    for (MutableNode n : nodes) {

                        if (n.equals(mutNode(String.valueOf(j)).addLink(mutNode(String.valueOf(i))))) {
                            q = 1;
                        }
                    }
                    if (q == 0) {
                        for (int k = 0; k < Integer.valueOf(String.valueOf(MacierzSasiedztwa.getValueAt(i, j))); k++) {
                            nodes.add(mutNode(String.valueOf(i)).addLink(mutNode(String.valueOf(j))));
                        }
                    } else {
                        q = 0;
                    }
                }
            }
        }
        System.out.println(nodes);
        MutableGraph g = mutGraph("Graf").add(nodes);
        graph.setText("");
        ImageIcon graphs = new ImageIcon(Graphviz.fromGraph(g).height(500).render(Format.PNG).toImage());
        graph.setIcon(graphs);
        try {
            Graphviz.fromGraph(g).height(500).render(Format.PNG).toFile(new File("example/ex1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void rysujGrafIncydencji(Integer vertexes, JTable MacierzIncydencji, JLabel graph) {

        String[] k = new String[2];
        String[] l = new String[2];
        System.out.print("\n \n ");

        int edges = MacierzIncydencji.getColumnCount();
        List<MutableNode> nodes = new ArrayList<>();

        int z = 0;
        for (int j = 0; j < edges; j++) {
            System.out.print("\n");
            for (int i = 0; i < vertexes; i++) {
                System.out.print("[" + j + "," + i + "]:" + MacierzIncydencji.getModel().getValueAt(i, j) + " \n");

                if (Integer.valueOf(String.valueOf(MacierzIncydencji.getModel().getValueAt(i, j))) == 1) {
                    int p = i;
                    int r = j;
                    k[z] = "" + p;
                    l[z] = "" + r;
                    System.out.print("Znaleziono jedynke: [" + l[z] + "][" + k[z] + "]\n");

                    z++;
                    if (z == 2) {

                        System.out.print(" Pierwsza para: [" + k[0] + "][" + l[0] + "]\n");
                        System.out.print(" Druga para:[" + k[1] + "][" + l[1] + "]\n");

                        nodes.add(mutNode(String.valueOf(Integer.valueOf(k[0]))).addLink(mutNode(String.valueOf(Integer.valueOf(k[1])))));
                        z = 0;
                    }

                }
            }

        }

        MutableGraph g = mutGraph("Graf").add(nodes);
        graph.setText("");
        ImageIcon graphs = new ImageIcon(Graphviz.fromGraph(g).height(500).render(Format.PNG).toImage());
        graph.setIcon(graphs);
        try {
            Graphviz.fromGraph(g).height(500).render(Format.PNG).toFile(new File("example/ex1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void IncydencjiNaSasiedztwa(JTable A, JTable B ) {

        Matrix matrix = new Matrix();
        matrix.SizeOfAdjacencyMatrix(A.getRowCount(), B);

        List<MutableNode> nodes = new ArrayList<>();
        MutableNode node;
        int z;
        int g = 0;
        for (int i = 0; i < A.getColumnCount(); i++) {
            node = null;
            z = 0;
            for (int j = 0; j < A.getRowCount(); j++) {
                if (Integer.parseInt(String.valueOf(A.getValueAt(j, i))) == 1) {
                    if (z == 0) {
                        g = j;
                        node = mutNode(String.valueOf(j + 1));
                        z++;
                    } else {
                        nodes.add(node.addLink(mutNode(String.valueOf(j + 1))));
                        if (!nodes.isEmpty()) {
                            for (MutableNode b : nodes) {
                                if (b.equals(mutNode(String.valueOf(g + 1)).addLink(mutNode(String.valueOf(j + 1))))) {
                                    z = 1;
                                }
                            }
                            if (z != 1) {
                                nodes.add(mutNode(String.valueOf(g + 1)).addLink(mutNode(String.valueOf(j + 1))));
                                z = 0;
                            }
                        }
                    }
                }
            }
        }
    }




    public LinkedList<Integer>[] Lista(JTable Macierz, LinkedList<Integer> AdjList[] ) {

        List<MutableNode> nodes = new ArrayList<>();
        int q = 0;
        int vertexes = Macierz.getColumnCount();
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {

                if (nodes.isEmpty()) {
                    for (int l = 0; l < Integer.valueOf(String.valueOf(Macierz.getValueAt(i, j))); l++) {

                        nodes.add(mutNode(String.valueOf(i)).addLink(mutNode(String.valueOf(j))));
                        AdjList[i].add(j);
                        AdjList[j].add(i);
                    }
                } else {

                    for (MutableNode n : nodes) {

                        if (n.equals(mutNode(String.valueOf(j)).addLink(mutNode(String.valueOf(i))))) {
                            q = 1;
                        }
                    }
                    if (q == 0) {
                        for (int k = 0; k < Integer.valueOf(String.valueOf(Macierz.getValueAt(i, j))); k++) {
                            nodes.add(mutNode(String.valueOf(i)).addLink(mutNode(String.valueOf(j))));
                            AdjList[i].add(j);
                            AdjList[j].add(i);
                        }
                    } else {
                        q = 0;
                    }
                }
            }
        }

        return AdjList;
    }

}