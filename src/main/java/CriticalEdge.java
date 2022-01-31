import javax.swing.*;
import java.util.*;
import java.util.LinkedList;

class CriticalEdge {
    private int vertexes;
    DrawingGraphs drawingGraphs = new DrawingGraphs();
    private LinkedList<Integer> adj[];
    int time = 0;
    static final int NIL = -1;

    CriticalEdge(int v) {
        vertexes = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(JTable matrix, JTextArea critical) {
        adj= drawingGraphs.Lista(matrix, adj);
        critical.setText("");
    }

    void first(JTextArea critical) {

        boolean visited[] = new boolean[vertexes];
        int disc[] = new int[vertexes];
        int low[] = new int[vertexes];
        int parent[] = new int[vertexes];

        for (int i = 0; i < vertexes; i++) {
            parent[i] = -1;
            visited[i] = false;
        }

        for (int i = 0; i < vertexes; i++)
            if (visited[i] == false) {
                second(i, visited, disc, low, parent, critical);
            }
    }

    void second(int u, boolean visited[], int disc[], int low[], int parent[], JTextArea critical) {

        visited[u] = true;
        disc[u] = low[u] = ++time;

        Iterator<Integer> i = adj[u].iterator();
        while (i.hasNext()) {
            int v = i.next();

            if (!visited[v]) {
                parent[v] = u;

                second(v, visited, disc, low, parent, critical);

                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    System.out.println(u + " " + v);
                    critical.setText(critical.getText() + u + " " + v + "\n");
                }
            }
            else if (v != parent[u])
                low[u] = Math.min(low[u], disc[v]);
        }
    }



}