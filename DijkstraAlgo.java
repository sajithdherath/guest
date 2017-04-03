/**
* Created by Rehan Samarasekera  on 4/2/2017. [2015065]  [w1583026]
 */
import java.awt.Color;
import java.util.*;

public class DijkstraAlgo {

    NodeClass startPoint;
    NodeClass endPoint;
    NodeClass[][] gridArea;
    static double hVDistance;
    static double dDistance;
    
  
    ArrayList<NodeClass> distance(boolean[][] matrix, int Ai, int Aj, int Bi, int Bj) {

        int sizeOfLenght = matrix.length;

        startPoint = new NodeClass(Ai, Aj);
        endPoint = new NodeClass(Bi, Bj);
        // The grid that is used to store nodes
        gridArea = new NodeClass[sizeOfLenght][sizeOfLenght];

        // Creating nodes and finding blocked cells in matrix and mapping accordingly to our grid
        for (int i = 0; i < sizeOfLenght; ++i) {
            for (int j = 0; j < sizeOfLenght; ++j) {
                gridArea[i][j] = new NodeClass(i, j);
                if (matrix[i][j] == false) {
                    gridArea[i][j].blocked = true;
                }
            }
        }

        // setting start distance to 0. 
        // All other nodes will have infinity distance at the beginning
        startPoint.distance =0;

        // a comparator object to deal with Priority Queue
        Comparator<NodeClass> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;
            }
            return -1;
        };

        // Queue to store visiting nodes
        Queue<NodeClass> queueB = new PriorityQueue(sizeOfLenght , adjacencyComparator);

        queueB.add(startPoint);

        while (queueB.size() > 0) {
            NodeClass current = queueB.remove();
            NodeClass t;

            // Top
            if (current.len_X - 1 >= 0) {

                // Top Top
                t = gridArea[current.len_X - 1][current.len_Y];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }

                // Top Left
                if (current.len_Y - 1 > 0) {
                    t = gridArea[current.len_X - 1][current.len_Y - 1];
                    if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                        t.distance = current.distance + dDistance;
                        t.parent = current;
                        queueB.add(t);
                    }
                }

                // Top Right
                if (current.len_Y+ 1 < sizeOfLenght) {
                    t = gridArea[current.len_X - 1][current.len_Y+ 1];
                    if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                        t.distance = current.distance + dDistance;
                        t.parent = current;
                        queueB.add(t);
                    }
                }
            }

            // Left
            if (current.len_Y- 1 > 0) {
                t = gridArea[current.len_X][current.len_Y - 1];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
            }

            // Right
            if (current.len_Y + 1 < sizeOfLenght) {
                t = gridArea[current.len_X][current.len_Y + 1];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
            }
            // Down
            if (current.len_X + 1 < sizeOfLenght) {

                // Down Down
                t = gridArea[current.len_X + 1][current.len_Y];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }

                // Down Left
                if (current.len_Y - 1 >= 0) {
                    t = gridArea[current.len_X + 1][current.len_Y - 1];
                    if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                        t.distance = current.distance + dDistance;
                        t.parent = current;
                        queueB.add(t);
                    }
                }

                // Down Right
                if (current.len_Y+ 1 < sizeOfLenght) {
                    t = gridArea[current.len_X + 1][current.len_Y + 1];
                    if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                        t.distance = current.distance + dDistance;
                        t.parent = current;
                        queueB.add(t);
                    }
                }
            }
            current.visited = true;
        }

        ArrayList<NodeClass> path = new ArrayList<>();

        // Checking if a path exists
        if (!(gridArea[endPoint.len_X][endPoint.len_Y].distance == Integer.MAX_VALUE)) {
            //Trace back the path
            NodeClass current = gridArea[endPoint.len_X][endPoint.len_Y];

            while (current.parent != null) {
                path.add(current.parent);
                current = current.parent;
            }
            StdDraw.setPenColor(Color.RED);

            for (NodeClass node : path) {
            StdDraw.circle(node.len_Y,sizeOfLenght - node.len_X - 1, .25);
            
        }
        } else System.out.println("Hey user, there are no any possible path");

        return path;
    }


}