import java.util.*;



import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/
		Object[] adj_list = new Object[graph.getNbNodes()];
		
		int[] visited = new int[graph.getNbNodes()];
		HashMap<Integer,Integer> parent = new HashMap<Integer,Integer>();
		
		for(int i = 0; i <= graph.getEdges().size() - 1; i++) {
			
			if(graph.getEdges().get(i).weight != 0) {
			
			if(adj_list[graph.getEdges().get(i).nodes[0]] == null) {
				adj_list[graph.getEdges().get(i).nodes[0]] = new LinkedList<Integer>();
				((LinkedList<Integer>) adj_list[graph.getEdges().get(i).nodes[0]]).add(graph.getEdges().get(i).nodes[1]);
			} else {
				((LinkedList<Integer>) adj_list[graph.getEdges().get(i).nodes[0]]).add(graph.getEdges().get(i).nodes[1]);
			}
		}
			
			
			
			
		}
		
		
		
		visited[source] = source;
		
		boolean found_dest = DFS(source,destination,graph,adj_list,parent,visited);
		
		if(found_dest == true) {
			path.add(source);
			int found = source;
			while(found != destination) {
				path.add(parent.get(found));
				found = parent.get(found);
			}
		}
		
		/**
		for(int i = 0; i <= path.size() - 1; i++) {
			System.out.println("answer:" + path.get(i));
		}
		**/
		return path;
	}
	
	private static boolean DFS(Integer source,Integer destination,WGraph graph,Object[] adj_list,HashMap<Integer,Integer> parent, int[] visited){
		
		for(int node:(LinkedList<Integer>) adj_list[source]) {
			if(node == destination) {
			    parent.put(source, node);
			    return true;	
			} else {
				if(visited[node] != node) {
					visited[node] = node;
					boolean found_dest = DFS(node,destination,graph,adj_list,parent,visited);
					if(found_dest == true) {
						parent.put(source, node);
						return true;
					}
				}
			}
		}
		
		
		return false;
		
	}



	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		
		/* YOUR CODE GOES HERE		*/
		
		int bottleneck = -1;
		int[][] weights = new int[graph.getNbNodes()][graph.getNbNodes()];
		int curr_weight = 0;
		
		WGraph residual = new WGraph(graph);
		for(Edge e:graph.getEdges()){
    		residual.addEdge(new Edge(e.nodes[1],e.nodes[0],0));
    		weights[e.nodes[0]][e.nodes[1]] = e.weight;
    		weights[e.nodes[1]][e.nodes[0]] = 0;
    		
    	}
		
		while(true) {
		
		ArrayList<Integer> path = pathDFS(residual.getSource(),residual.getDestination(),residual);
		if(path.size() == 0) {
			break;
		}
		for(int i = 0; i <= path.size() - 1; i++) {
			if(i+1 == path.size()) {
				break;
			}
			curr_weight = weights[path.get(i)][path.get(i+1)];
			if(bottleneck == -1) {
				bottleneck = curr_weight;
			} else {
				if(curr_weight < bottleneck) {
					bottleneck = curr_weight;
				}
			}
			
		}
		
		maxFlow += bottleneck;
		
		for(int i = 0; i <= path.size() - 1; i++) {
			if(i+1 == path.size()) {
				bottleneck = -1;
				break;
			}
			weights[path.get(i)][path.get(i+1)] -= bottleneck;
			residual.setEdge(path.get(i), path.get(i+1),  weights[path.get(i)][path.get(i+1)]);
			weights[path.get(i+1)][path.get(i)] += bottleneck;
			residual.setEdge(path.get(i+1), path.get(i),weights[path.get(i+1)][path.get(i)] );
			
		}
	}
		
		for(Edge e:graph.getEdges()){
    		graph.setEdge(e.nodes[0], e.nodes[1], e.weight - weights[e.nodes[0]][e.nodes[1]]);
    	}

		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	

	 public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
	    System.out.println(fordfulkerson(g));
		
		
	 }
}
