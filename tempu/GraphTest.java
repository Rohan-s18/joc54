
public class GraphTest {

	public static void main(String[] args) {
		Graph myGraph = new Graph();
		myGraph.addNode("Cleveland");
		myGraph.addNode("Pittsburgh");
		myGraph.addNode("Cincinnati");
		myGraph.addNode("Baltimore");
		myGraph.addNode("Buffalo");
		myGraph.addNode("New York");
		myGraph.addNode("Boston");
		myGraph.addNode("Florida");
		
		myGraph.addRoad("Cleveland", "Pittsburgh", 201);
		myGraph.addRoad("Cleveland", "Baltimore", 756);
		myGraph.addRoad("Cleveland", "Cincinnati", 301);
		myGraph.addRoad("Baltimore", "Florida", 1234);
		myGraph.addRoad("Baltimore", "Pittsburgh", 504);
		myGraph.addRoad("Cleveland", "Buffalo", 251);
		myGraph.addRoad("Buffalo", "Pittsburgh", 318);
		myGraph.addRoad("Buffalo", "Boston", 516);
		myGraph.addRoad("Boston", "New York", 145);
		myGraph.addRoad("New York", "Baltimore", 457);
		myGraph.addRoad("New York", "Buffalo", 343);
		
		myGraph.printGraph();
		
		System.out.println("\n");
		
		myGraph.shortestLength("Baltimore", "Cincinnati");
	}

}
