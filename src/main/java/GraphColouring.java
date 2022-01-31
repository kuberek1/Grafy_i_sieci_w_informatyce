
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

class GraphColouring
{
    DrawingGraphs drawingGraphs = new DrawingGraphs();
    private int vertexes;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency List

    GraphColouring(int v)
    {
        vertexes = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(JTable matrix) {
        adj = drawingGraphs.Lista(matrix, adj);
    }

    ArrayList<String> greedyColoring()
    {
        int result[] = new int[vertexes];
        Arrays.fill(result, -1);
        result[0]  = 0;
        boolean available[] = new boolean[vertexes];
        Arrays.fill(available, true);
        for (int u = 1; u < vertexes; u++)
        {
            Iterator<Integer> it = adj[u].iterator() ;
            while (it.hasNext())
            {
                int i = it.next();
                if (result[i] != -1)
                    available[result[i]] = false;
            }
            int cr;
            for (cr = 0; cr < vertexes; cr++){
                if (available[cr])
                    break;
            }
            result[u] = cr;
            Arrays.fill(available, true);
        }
        ArrayList<String> colouring_result = new ArrayList<String>();
        for (int u = 0; u < vertexes; u++) {
            System.out.println("Vertex " + u + " --->  Color "
                    + result[u]);
        colouring_result.add("Vertex " + u + " --->  Color " + result[u] +"\n");
        }
        return colouring_result;
    }


}
