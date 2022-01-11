import java.util.*;

public class Graph {
	private int[][] matrix;
	private int numVertices;
	private int maxNum;
	private boolean[] finalized;
	private int[] pathCost;
	private String[] nodes;
	
	private class Path implements Comparable<Path>{
		private int toIndex;
		private int costEstimate;
		
		private Path(int toIndex, int costEstimate) {
			this.toIndex = toIndex;
			this.costEstimate = costEstimate;
		}
		
		public String toString() {
			return nodes[toIndex] + String.valueOf(costEstimate);
		}

		@Override
		public int compareTo(Path arg0) {
			return this.costEstimate - arg0.costEstimate;
		}
		
	}
	
	public Graph(int maxNum) {
		this.maxNum = maxNum;
		finalized = new boolean[maxNum];
		pathCost = new int[maxNum];
		matrix = new int[maxNum][maxNum];
		nodes = new String[maxNum];
		for(int i = 0; i < maxNum; i++) {
			for(int j = 0; j < maxNum; j++) {
				matrix[i][j] = Integer.MAX_VALUE;
			}
		}
	}
	
	public Graph() {
		maxNum = 2;
		finalized = new boolean[maxNum];
		pathCost = new int[maxNum];
		matrix = new int[maxNum][maxNum];
		nodes = new String[maxNum];
		for(int i = 0; i < maxNum; i++) {
			for(int j = 0; j < maxNum; j++) {
				matrix[i][j] = Integer.MAX_VALUE;
			}
		}
	}
	
	public void printGraph() {
		for(int i = 0; i < numVertices; i++) {
			String str = nodes[i] + ": " + "\t";
			for(int j = 0; j < numVertices; j++) {
				if(j != i && matrix[i][j] != Integer.MAX_VALUE)
					str += nodes[j] + ", ";
			}
			str = str.substring(0,str.length()-2);
			System.out.println(str);
		}
	}
	
	public boolean addNode(String str) {
		if(indexOf(str) != -1)
			return false;
		nodes[numVertices] = str;
		numVertices++;
		refresh();
		return true;
	}
	
	public boolean addRoad(String from, String to, int cost) {
		int fromIndex = indexOf(from);
		int toIndex = indexOf(to);
		if(fromIndex == -1 || toIndex == -1 || matrix[fromIndex][toIndex] != Integer.MAX_VALUE)
			return false;
		matrix[fromIndex][toIndex] = cost;
		matrix[toIndex][fromIndex] = cost;
		return true;
	}
	
	public void shortestLength(String from, String to) {
		int[] parentArray = new int[numVertices];
		int fromIndex = indexOf(from);
		int toIndex = indexOf(to);
		if(fromIndex == -1 || toIndex == -1)
			return;
		
		for(int i = 0; i < numVertices; i++) {
			finalized[i] = false;
			pathCost[i] = Integer.MAX_VALUE;
		}
		PriorityQueue<Path> pq = new PriorityQueue<Path>();
		int numFinalized = 0;
		pathCost[fromIndex] = 0;
		pq.add(new Path(fromIndex, 0));
		parentArray[fromIndex] = -1;
		
		while(numFinalized < numVertices) {
			if(pq.isEmpty())
				break;
			Path temp = pq.poll();
			int tempIndex = temp.toIndex;
			if(finalized[tempIndex])
				continue;
			finalized[tempIndex] = true;
			numFinalized++;
			
			for(int j = 0; j < numVertices; j++) {
				if(matrix[tempIndex][j] != Integer.MAX_VALUE) {
					if(!finalized[j]) {
						int costEstimate = pathCost[tempIndex] + matrix[tempIndex][j];
						if(costEstimate < pathCost[j]) {
							pathCost[j] = costEstimate;
							parentArray[j] = tempIndex;
						}
						pq.add(new Path(j, pathCost[j]));
					}
				}
			}
				
		}
		
		//System.out.println(to + String.valueOf(pathCost[toIndex]));
		int curr = toIndex;
		Stack<String> pathNodes = new Stack<String>();
		while(curr != -1) {
			pathNodes.add(nodes[curr]);
			curr = parentArray[curr];
		}
		String str = "";
		while(!pathNodes.isEmpty()) {
			str += pathNodes.pop();
			str += " -> ";
		}
		str = str.substring(0, str.length() - 4);
		System.out.println(str);
	}
	
	private int indexOf(String str) {
		for(int i = 0; i < numVertices; i++){
			if(nodes[i].toLowerCase().equals(str.toLowerCase()))
				return i;
		}
		return -1;
	}
	
	private void refresh() {
		if(numVertices != maxNum - 1)
			return;
		boolean[] oldFinalized = finalized;
		int[] oldPathCost = pathCost;
		int[][] oldMatrix = matrix;
		String[] oldNodes = nodes;
		maxNum *= 2;
		finalized = new boolean[maxNum];
		pathCost = new int[maxNum];
		matrix = new int[maxNum][maxNum];
		nodes = new String[maxNum];
		for(int i = 0; i < numVertices; i++) {
			finalized[i] = oldFinalized[i];
			pathCost[i] = oldPathCost[i];
			nodes[i] = oldNodes[i];
		}
		for(int j = 0; j < maxNum; j++) {
			for(int k = 0; k < maxNum; k++) {
				if(j < oldMatrix.length && k < oldMatrix.length && oldMatrix[j][k] != Integer.MAX_VALUE)
					matrix[j][k] = oldMatrix[j][k];
				else
					matrix[j][k] = Integer.MAX_VALUE;
			}
		}
	}
	
}
