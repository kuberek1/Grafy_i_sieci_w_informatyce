
import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;

public class DepthFirstSearch{
    DrawingGraphs drawingGraphs = new DrawingGraphs();


        private LinkedList<Integer> adjLists[];
        private boolean visited[];

        DepthFirstSearch(int vertices){
            adjLists = new LinkedList[vertices];
            visited = new boolean[vertices];

            for (int i = 0; i < vertices; i++)
                adjLists[i] = new LinkedList<Integer>();
        }

        void addEdge(JTable matrix) {
            adjLists= drawingGraphs.Lista(matrix, adjLists);
        }

        String dfs_result= "";

        void DFS(int vertex){

            visited[vertex] = true;
            System.out.print(vertex + " ");
            dfs_result=dfs_result+ vertex+ " ";

            Iterator<Integer> ite = adjLists[vertex].listIterator();
            while (ite.hasNext()) {
                int adj = ite.next();
                if (!visited[adj])
                    DFS(adj);
            }

        }

}
