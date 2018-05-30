package floodFill;

import java.awt.Color;
import java.util.ArrayList;

public class Hash {

	private ArrayList<ArrayList<Node>> hashTable = new ArrayList<>();

	// Constructor:
	public Hash() {
		for (int i = 0; i < 8; i++) {
			hashTable.add(new ArrayList<Node>());
		}
	}

	// Adding the number of nodes with the same color to the specific index of
	// the hash which is for specific color. For example if color of nodes is
	// red, we add these nodes to red block of the hash.
	public void add(ArrayList<Node> connectedNodes) {
		Color color = connectedNodes.get(0).getColor();

		switch (color.getRGB()) {

		case -65536: // red
			hashTable.get(0).addAll(connectedNodes);
			break;
		case -16776961: // blue
			hashTable.get(1).addAll(connectedNodes);
			break;
		case -4144960: // light gray
			hashTable.get(2).addAll(connectedNodes);
			break;
		case -16711936: // green
			hashTable.get(3).addAll(connectedNodes);
			break;
		case -12566464: // dark gray
			hashTable.get(4).addAll(connectedNodes);
			break;
		case -1: // WHITE
			hashTable.get(5).addAll(connectedNodes);
			break;
		case -65281: // MAGENTA
			hashTable.get(6).addAll(connectedNodes);
			break;
		case -14336: // ORANGE
			hashTable.get(7).addAll(connectedNodes);
			break;

		}

	}

	// This method returns all the nodes which has specific color.
	public ArrayList<Node> get(Color color) {

		ArrayList<Node> tmp = new ArrayList<>();

		switch (color.getRGB()) {
		case -65536: // red
			tmp.addAll(hashTable.get(0));
			break;
		case -16776961: // blue
			tmp.addAll(hashTable.get(1));
			break;
		case -4144960: // light gray
			tmp.addAll(hashTable.get(2));
			break;
		case -16711936: // green
			tmp.addAll(hashTable.get(3));
			break;
		case -12566464: // dark gray
			tmp.addAll(hashTable.get(4));
			break;
		case -1: // WHITE
			tmp.addAll(hashTable.get(5));
			break;
		case -65281: // MAGENTA
			tmp.addAll(hashTable.get(6));
			break;
		case -14336: // ORANGE
			tmp.addAll(hashTable.get(7));
			break;
		}
		return tmp;
	}

	// This method removes all the nodes which has specific color.
	public void remove(Color color) {
		switch (color.getRGB()) {
		case -65536: // red
			hashTable.get(0).clear();
			break;
		case -16776961: // blue
			hashTable.get(1).clear();
			break;
		case -4144960: // light gray
			hashTable.get(2).clear();
			break;
		case -16711936: // green
			hashTable.get(3).clear();
			break;
		case -12566464: // dark gray
			hashTable.get(4).clear();
			break;
		case -1: // WHITE
			hashTable.get(5).clear();
			break;
		case -65281: // MAGENTA
			hashTable.get(6).clear();
			break;
		case -14336: // ORANGE
			hashTable.get(7).clear();
			break;
		}
	}
}
