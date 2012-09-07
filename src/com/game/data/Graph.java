/**
 * This class is the Heart of the application, it holds the cell nodes. The game of life 
 * consists of live cell & dead cell. live cells are considered as '1' & dead cells are 
 * represented as '0'. The algorithm for running Game of life has following four rules:
 * 
 * 		1.	Any live cell with fewer than two live neighbors dies, as if by loneliness.
 * 		2.	Any live cell with more than three live neighbors dies, as if by overcrowding.
 * 		3.	Any live cell with two or three live neighbors lives, unchanged, to the next generation.
 * 		4.	Any dead cell with exactly three live neighbors comes to life.
 * 
 */
package com.game.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Karthikeyan R
 * 
 */
public class Graph {

	// Represents collection of nodes in a world
	private List<Node> cellWorldList;

	// Represents initial input rows
	private int initialRowNum;

	// Represents initial input columns
	private int initialColNum;

	public Graph(List<Node> cellWorldList, int initialMaxRow, int initialMaxCol) {
		super();
		this.cellWorldList = cellWorldList;
		this.initialRowNum = initialMaxRow;
		this.initialColNum = initialMaxCol;
	}

	// Getter & Setters for class fields
	public List<Node> getCellWorldList() {
		return cellWorldList;
	}

	public void setCellWorldList(List<Node> cellWorld) {
		this.cellWorldList = cellWorld;
	}

	public int getInitialMaxRow() {
		return initialRowNum;
	}

	public void setInitialMaxRow(int initialMaxRow) {
		this.initialRowNum = initialMaxRow;
	}

	public int getInitialMaxCol() {
		return initialColNum;
	}

	public void setInitialMaxCol(int initialMaxCol) {
		this.initialColNum = initialMaxCol;
	}

	/**
	 * Initializes neighbors for nodes of a world.
	 * 
	 * @return : Collection of nodes in universe with updated neighbors status
	 * @throws Exception
	 */
	public List<Node> initializeUniverseSequence() throws Exception {
		cellWorldList = createNodesUpdateNegighborsStatus(cellWorldList);

		if (cellWorldList != null)
			return cellWorldList;
		else
			throw new Exception("Invalid node list");
	}

	/**
	 * 
	 * Algorithm for initializing neighbors for nodes of a world.
	 * 
	 * @param cellNodes
	 *            : Newly created nodes collection in universe.
	 * @return
	 * @throws Exception
	 */
	public List<Node> createNodesUpdateNegighborsStatus(List<Node> cellNodes)
			throws Exception {
		if (cellNodes != null && !cellNodes.isEmpty()) {
			// temporary collection of nodes = input nodes + dynamically created
			// nodes
			List<Node> updatedNodesList = new ArrayList<Node>();

			updatedNodesList.addAll(cellNodes);

			/*
			 * Iterate through nodes & initialize neighbors status. Neighbors
			 * are identified by using row & column index of node using the
			 * combination of -1, +1, 0.
			 */
			try {
				// newly created non-existent nodes
				List<Node> newlyCreatedNodes = new ArrayList<Node>();

				Iterator<Node> nodeIterator = cellNodes.iterator();

				while (nodeIterator.hasNext()) {

					Node nodes = nodeIterator.next();

					if (!nodes.isNodeInitialized()) {
						Boolean nodeNeighbor[] = new Boolean[8];

						ArrayList<Boolean> neighborsList = new ArrayList<Boolean>();

						long maxRow = nodes.getRowIndex();
						long maxCol = nodes.getColIndex();

						for (long row = maxRow - 1; row <= maxRow + 1; row++) {
							for (long col = maxCol - 1; col <= maxCol + 1; col++) {

								if (row == maxRow && col == maxCol)
									continue;

								Node tempNode = searchNode(updatedNodesList,
										row, col);

								if (tempNode != null)
									neighborsList.add(tempNode.isNodeAlive());
								else {
									neighborsList.add(false);

									if (!nodes.isIsnewlyCreated()) {
										tempNode = createNewNode(row, col);
										updatedNodesList.add(tempNode);
										newlyCreatedNodes.add(tempNode);
									}
								}

							}
						}
						nodeNeighbor = neighborsList.toArray(nodeNeighbor);

						nodes.setNodeInitialized(true);
						nodes.setNeighboursStatus(nodeNeighbor);
					}
				}

				// Recursively set neighbors will be called for newly created
				// nodes
				if (!newlyCreatedNodes.isEmpty()) {
					cellWorldList.addAll(newlyCreatedNodes);
					cellWorldList = createNodesUpdateNegighborsStatus(cellNodes);
				}
				return cellWorldList;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw e;
			}
		}
		return null;
	}

	/**
	 * 
	 * Creates & returns a new node with specified row & column index.
	 * 
	 * @param rowID
	 *            : Row index for new node to be created
	 * @param colID
	 *            : Column index for new node to be created
	 * @return: Newly created node with specified row & column id
	 */
	public Node createNewNode(long rowID, long colID) {
		return new Node(rowID, colID, false, true);
	}

	/**
	 * 
	 * Searches node in the list and returns corresponding node if available,
	 * else null will be returned
	 * 
	 * @param cellNodes
	 *            : Collection of nodes to search
	 * @param rowID
	 *            : Row index of required node
	 * @param colID
	 *            : Column index of required node
	 * @return : Returns corresponding node if available in node collection,
	 *         else returns null
	 * @throws Exception
	 */
	public static Node searchNode(List<Node> cellNodes, long rowID, long colID)
			throws Exception {
		if (cellNodes != null && !cellNodes.isEmpty()) {
			for (Node node : cellNodes) {
				if (node.equals(rowID, colID))
					return node;
			}
		} else
			throw new Exception("Invalid node list");
		return null;
	}

	/**
	 * Used to calculate the number of alive cells from neighbors status
	 * 
	 * @param neighborsStatus
	 *            : Boolean status of neighbors for selected node
	 * @return : returns total count of live cell neighbors.
	 * @throws Exception
	 */
	public int getLiveCellCount(Boolean[] neighborsStatus) throws Exception {
		if (neighborsStatus != null) {
			int count = 0;
			for (Boolean boolean1 : neighborsStatus) {
				if (boolean1 != null && boolean1)
					count++;
			}
			return count;
		}
		throw new Exception("Invalid neighbors status collection");
	}

	/**
	 * Updates status of nodes by applying the rules of "game of life"
	 * 
	 * @return : Updated status for node in the world
	 * @throws Exception
	 */
	public List<Node> updateStatus() throws Exception {
		if (cellWorldList != null) {
			for (Node nodes : cellWorldList) {
				if (nodes != null) {
					int count = getLiveCellCount(nodes.getNeighboursStatus());
					if (count == 2)
						continue;
					if (count == 3) {
						if (!nodes.isNodeAlive())
							nodes.setNodeAlive(true);
					} else
						nodes.setNodeAlive(false);
				}
			}
			return cellWorldList;
		}
		return null;
	}
}
