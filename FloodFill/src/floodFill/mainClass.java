package floodFill;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MultiHashtable;
import com.sun.org.apache.xpath.internal.operations.Mult;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

public class mainClass {

	private static JFrame frame;
	static Color referenceColor;
	static public JButton[][] t = new JButton[10][10];
	static private ArrayList<Color> color = new ArrayList<>();
	final static private ArrayList<JButton> btn = new ArrayList<>();
	private static Hash pictureConnectedNodes = new Hash();

	public static void main(String[] args) {

		// Make a 10*10 colored frame :
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainClass window = new mainClass();
					window.frame.setVisible(true);

					createColor();

					frame.getContentPane().setLayout(new GridLayout(11, 10));

					// Making 100 colored buttons and adding actionListener for
					// each of them:(Line: 42_95)
					for (int r = 0; r < 10; r++) {
						for (int c = 0; c < 10; c++) {
							t[r][c] = new JButton();
							Collections.shuffle(color);
							t[r][c].setBackground(color.get(0));
							frame.getContentPane().add(t[r][c]);
							t[r][c].addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {

								}

								@Override
								public void mousePressed(MouseEvent e) {

								}

								@Override
								public void mouseExited(MouseEvent e) {

								}

								@Override
								public void mouseEntered(MouseEvent e) {

								}

								@Override
								public void mouseClicked(MouseEvent e) {

									// Changing the color of, every buttons or
									// nodes witch has a same color with the
									// selected button, with the
									// "referenceColor".
									// ("referenceColor is one of the 8 colors
									// that we have in the
									// bottom of the frame.)
									ArrayList<Node> tmp;
									JButton jb = (JButton) e.getSource();
									Color jbColor = jb.getBackground();
									if (referenceColor != null && jbColor != referenceColor) {
										// Get the all nodes witch their color
										// is "jbColor" and put them to the
										// "tmp".
										tmp = pictureConnectedNodes.get(jbColor);

										// Changing the color of all nodes in
										// the "tmp", to "referenceColor".(in
										// this for)
										for (int i = 0; i < tmp.size(); i++) {
											tmp.get(i).setColor(referenceColor);
										}
										pictureConnectedNodes.add(tmp);
										pictureConnectedNodes.remove(jbColor);
									}
								}
							});
						}
					}

					// Making 8 buttons for reference, adding them to the frame
					// and add actionListener for them:
					for (int i = 0; i < 8; i++) {
						btn.add(new JButton("" + i + ""));
						btn.get(i).setBackground(color.get(i));
						btn.get(i).setForeground(Color.black);
						frame.getContentPane().add(btn.get(i));
						btn.get(i).addMouseListener(new MouseListener() {

							@Override
							public void mouseReleased(MouseEvent e) {

							}

							@Override
							public void mousePressed(MouseEvent e) {

							}

							@Override
							public void mouseExited(MouseEvent e) {

							}

							@Override
							public void mouseEntered(MouseEvent e) {

							}

							@Override
							public void mouseClicked(MouseEvent e) {

								JButton jb = (JButton) e.getSource();
								referenceColor = jb.getBackground();
							}
						});
					}

					// In this for we model each button of those 100 button to a
					// Node:
					for (int i = 1; i < 11; i++) {
						for (int j = 1; j < 11; j++) {
							new Node(t[i - 1][j - 1]);
						}
					}

					// In this for we find every connected nodes in the frame,
					// with the help of "makeConnectedGraph" and add them to our
					// hash (pictureConnectedNodes).
					ArrayList<Node> tmp;
					for (int i = 1; i < 11; i++) {
						for (int j = 1; j < 11; j++) {
							tmp = makeConnectedGraph(Node.allNodes[i][j], i, j);
							if (tmp != null) {
								pictureConnectedNodes.add(tmp);
							}
						}
					}

				} catch (IndexOutOfBoundsException e) {
					System.out.println("indexOutOfBound");
					e.printStackTrace();
				}

				catch (Exception e) {
					e.printStackTrace();
				}

			}

			private void createColor() {
				color.add(Color.red);
				color.add(Color.blue);
				color.add(Color.lightGray);
				color.add(Color.green);
				color.add(Color.darkGray);
				color.add(Color.WHITE);
				color.add(Color.MAGENTA);
				color.add(Color.ORANGE);
			}
		});

	}

	/**
	 * Create the application.
	 */
	public mainClass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	}

	// A recursive method to finding all connected nodes
	// of the frame. (Actually we do DFS for each button or node of the
	// frame.)
	public static ArrayList<Node> makeConnectedGraph(Node node, int row, int column) {
		if (node.isVisited) {
			return null;
		}

		node.setVisited(true);
		node.addConnectedNode(node);

		Node left = Node.allNodes[row][column - 1];
		if (left != null && left.getColor().equals(node.getColor()) && left.isVisited != true) {
			node.addConnectedNode(makeConnectedGraph(left, row, column - 1));
		}

		Node right = Node.allNodes[row][column + 1];
		if (right != null && right.getColor().equals(node.getColor()) && right.isVisited != true) {
			node.addConnectedNode(makeConnectedGraph(right, row, column + 1));
		}

		Node up = Node.allNodes[row - 1][column];
		if (up != null && up.getColor().equals(node.getColor()) && up.isVisited != true) {
			node.addConnectedNode(makeConnectedGraph(up, row - 1, column));
		}

		Node down = Node.allNodes[row + 1][column];
		if (down != null && down.getColor().equals(node.getColor()) && down.isVisited != true) {
			node.addConnectedNode(makeConnectedGraph(down, row + 1, column));
		}

		return node.getConnectedNodes();

	}
}
