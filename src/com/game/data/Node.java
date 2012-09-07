/**
 * Class represents the cells in the world with some common properties for each cells. 
 * All nodes are implemented with the combined property of Graph data structure & Array data structure.
 */
package com.game.data;

/**
 * @author Karthikeyan R
 * 
 */
public class Node {

	// Row index of node in cell world
	private long rowIndex;

	// Column index of node in cell world
	private long colIndex;

	// Node ID used internally for encoding nodes in the world
	protected String nodeID;

	// Life status of node
	private boolean isNodeAlive;

	// Represents whether node is dynamically created during allocation
	private boolean isnewlyCreated;

	// Whether neighbors status of node is initialized
	private boolean isNodeInitialized;

	// neighbors status
	private Boolean[] neighboursStatus;

	// Function to compare two nodes w.r.t nodeID
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Node) {
			if (this.nodeID.equals(((Node) obj).nodeID))
				return true;
		}
		return false;
	}

	/**
	 * Function to compare two nodes w.r.t node's row & column index
	 * 
	 * @param rowID
	 * @param colID
	 * @return
	 */
	public boolean equals(long rowID, long colID) {
		if (this.nodeID.equals(rowID + "|" + colID))
			return true;

		return false;
	}

	public Node(long rowID, long colID, boolean isNodeAlive,
			boolean isnewlyCreated) {
		super();
		this.rowIndex = rowID;
		this.colIndex = colID;
		this.nodeID = rowID + "|" + colID;
		this.isNodeAlive = isNodeAlive;
		this.isnewlyCreated = isnewlyCreated;
	}

	// Getter & Setter function for accessing fields
	public long getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(long rowIndex) {
		this.rowIndex = rowIndex;
	}

	public long getColIndex() {
		return colIndex;
	}

	public void setColIndex(long colIndex) {
		this.colIndex = colIndex;
	}

	public boolean isNodeAlive() {
		return isNodeAlive;
	}

	public void setNodeAlive(boolean isNodeAlive) {
		this.isNodeAlive = isNodeAlive;
	}

	public boolean isIsnewlyCreated() {
		return isnewlyCreated;
	}

	public void setIsnewlyCreated(boolean isnewlyCreated) {
		this.isnewlyCreated = isnewlyCreated;
	}

	public boolean isNodeInitialized() {
		return isNodeInitialized;
	}

	public void setNodeInitialized(boolean isNodeInitialized) {
		this.isNodeInitialized = isNodeInitialized;
	}

	public Boolean[] getNeighboursStatus() {
		return neighboursStatus;
	}

	public void setNeighboursStatus(Boolean[] neighboursStatus) {
		this.neighboursStatus = neighboursStatus;
	}

}
