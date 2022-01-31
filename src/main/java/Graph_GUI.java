import guru.nidi.graphviz.model.MutableNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Graph_GUI extends JFrame {
    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel TopMiddlePanel;
    private JPanel TopRightPanel;
    private JPanel TopLeftPanel;
    private JPanel MiddlePanel;
    private JTextPane podajIlośćWierzchołkówTextPane;
    private JTextField WierzcholekIlosc;
    private JTextField KrawedzIlosc;
    private JTextPane WierzcholekPanel;
    private JTable MacierzSasiedztwa;
    private JButton rysujGrafSasiedztwaButton;
    private JTable MacierzIncydencji;
    private JButton rysujGrafIncydencjiButton;
    private JTextPane KrawedziePanel;
    private JButton przeszukajGrafButton;
    private JLabel Image;
    private JButton kolorujGrafButton;
    private JTextArea DFS_result;
    private JTextArea Coloring_result;
    private JLabel Coloring_result_info;
    private JTextArea Critical_edge_result;
    private JLabel DFS_result_info;
    private JLabel Critical_edge_result_info;
    private JTextArea Colouring_result;
    private JLabel Colouring_result_info;

    public int vertexes = 1, edges = 1;

    Matrix matrix = new Matrix();
    DrawingGraphs drawingGraphs = new DrawingGraphs();


    public Graph_GUI() {
        JFrame frame = new JFrame();

        frame.add(MainPanel, BorderLayout.CENTER);
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Graphs");
        frame.pack();
        frame.setVisible(true);


        WierzcholekIlosc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vertexes();
            }
        });
        KrawedzIlosc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edges();
            }
        });
        rysujGrafSasiedztwaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingGraphs.rysujGrafSasiedztwa(vertexes, MacierzSasiedztwa, Image);
            }
        });
        rysujGrafIncydencjiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingGraphs.rysujGrafIncydencji(vertexes, MacierzIncydencji, Image);

                drawingGraphs.IncydencjiNaSasiedztwa(MacierzIncydencji,MacierzSasiedztwa);
            }
        });
        przeszukajGrafButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    przeszukajGrafButton(DFS_result);

            }
        });
        kolorujGrafButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                kolorujGrafButton(Colouring_result);
            }
        });

    }


    public int vertexes() {
            vertexes = Integer.valueOf(WierzcholekIlosc.getText());
        if (vertexes > 0) {
            matrix.SizeOfAdjacencyMatrix(vertexes, MacierzSasiedztwa);
        }
        return vertexes;
    }


    public void edges() {
            edges = Integer.valueOf(KrawedzIlosc.getText());
            vertexes = Integer.valueOf(WierzcholekIlosc.getText());

        if (edges > 0 || vertexes > 0) {
            matrix.SizeOfIncidenceMatrix(vertexes, edges, MacierzIncydencji);
        }
    }


    public void przeszukajGrafButton(JTextArea label1){
        label1.setText("");
        System.out.print("\n Przeszukiwanie: \n");
        DepthFirstSearch dfs = new DepthFirstSearch(vertexes);
        dfs.addEdge(MacierzSasiedztwa);
        dfs.DFS(0);

        System.out.println(dfs.dfs_result);
        label1.setText(dfs.dfs_result);

        System.out.print("\n\n Krawędź krytyczna: \n");
        CriticalEdge critical = new CriticalEdge(vertexes);
        critical.addEdge(MacierzSasiedztwa, Critical_edge_result);
        critical.first(Critical_edge_result);

    }
    public void kolorujGrafButton(JTextArea label2) {
        label2.setText("");
        System.out.print("\n Kolorowanie: \n");
        GraphColouring colour = new GraphColouring(vertexes);
        colour.addEdge(MacierzSasiedztwa);

        for (String text : colour.greedyColoring()) {

            System.out.print(text);
            label2.setText(label2.getText()+text+"\n");

        }

    }
}







