package floodFill;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;

public class Node {

	private static int row = 1;
	private static int column = 1;
	public static Node[][] allNodes = new Node[12][12];
	JButton button;
	public ArrayList<Node> connectedNodes = new ArrayList<Node>();
	boolean isVisited = false;

	// Constructor : We match a button to a node each time we make a new node.
	public Node(JButton button) {
		this.button = button;
		allNodes[row][column] = this;
		if (column < 10) {
			column++;
		} else {
			column = 1;
			row++;
		}

	}

	// Each Node has an ArrayList containing the nodes witch are connected to
	// it. And with the help of this method we add the connected nodes to this
	// ArrayList.
	// This first one is for adding NUMBER of nodes to "connectedNodes".
	public void addConnectedNode(ArrayList<Node> connectedNodes) {
		if (connectedNodes != null) {
			this.connectedNodes.addAll(connectedNodes);
		}
	}

	// This second one is for adding ONE node to "connectedNodes".
	public void addConnectedNode(Node node) {
		connectedNodes.add(node);
	}

	// Getters and setters :

	public void setVisited(boolean bool) {
		this.isVisited = bool;
	}

	public Color getColor() {
		return button.getBackground();
	}

	public void setColor(Color color) {
		this.button.setBackground(color);
	}

	public ArrayList<Node> getConnectedNodes() {
		return connectedNodes;
	}

}
