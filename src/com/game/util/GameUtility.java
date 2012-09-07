/**
 * Common utility classes used across game of life & can be reused in future while extending application.
 * 
 */
package com.game.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.game.data.Graph;
import com.game.data.Node;

/**
 * @author Karthikeyan R
 * 
 */
public class GameUtility {

	/**
	 * Fetch the entire contents of a text file, and return it in a String. This
	 * style of implementation does not throw Exceptions to the caller.
	 * 
	 * @param aFile
	 *            is a file which already exists and can be read.
	 * @throws Exception
	 */
	static public String getContents(String filePath) throws Exception {
		if (filePath != null & !filePath.trim().equals("")) {
			File inputFile = new File(filePath);

			if (inputFile.exists()) {
				StringBuilder contents = new StringBuilder();

				try {
					// use buffering, reading one line at a time
					BufferedReader input = new BufferedReader(new FileReader(
							inputFile));
					try {
						String line = null;
						// Reads input line by line and appends it.
						while ((line = input.readLine()) != null) {
							contents.append(line);
							contents.append("\n");
						}
					} finally {
						input.close();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				return contents.toString();
			}
			throw new Exception("Invalid file Path");
		}
		return null;

	}

	/**
	 * Filters dead nodes in irrelevant rows/columns and limits output to input
	 * pattern, along with alive newly created nodes
	 * 
	 * @param cellWorld
	 *            : Collection of nodes in cell world
	 * @param maxInputRow
	 *            : Maximum row provided during initial input
	 * @param maxInputCol
	 *            : Maximum column provided during initial input
	 * @return : Returns filtered node depending on specified row & column
	 */
	public static List<Node> filterNode(List<Node> cellWorld,
			final int maxInputRow, final int maxInputCol) {
		if (cellWorld != null && !cellWorld.isEmpty()) {
			List<Node> filteredNodes = new ArrayList<Node>();

			for (Node nodes : cellWorld) {
				long nodeRow = nodes.getRowIndex();
				long nodeCol = nodes.getColIndex();
				if ((nodeRow >= 0 && nodeRow < maxInputRow)
						&& (nodeCol >= 0 && nodeCol < maxInputCol))
					filteredNodes.add(nodes);
				else if (nodes.isNodeAlive())
					filteredNodes.add(nodes);
			}

			return filteredNodes;
		}
		return null;

	}

	/**
	 * Prints output data to console
	 * 
	 * @param cellWorld
	 *            : Collection of nodes in cell world
	 * @param maxInputRow
	 *            : Maximum initial input row in cell world
	 * @param maxInputCol
	 *            : Maximum initial input column in cell world
	 * @throws Exception
	 */
	public static void printData(List<Node> cellWorld, final int maxInputRow,
			final int maxInputCol) throws Exception {

		if (cellWorld != null && !cellWorld.isEmpty()) {
			cellWorld = filterNode(cellWorld, maxInputRow, maxInputCol);

			ArrayList<Long> columnList = new ArrayList<Long>();
			ArrayList<Long> rowList = new ArrayList<Long>();

			for (Node nodeElement : cellWorld) {
				columnList.add(nodeElement.getColIndex());
				rowList.add(nodeElement.getRowIndex());
			}

			Collections.sort(columnList);
			Collections.sort(rowList);

			long maxColumnValue = Math.abs(Collections.max(columnList))
					+ Math.abs(Collections.min(columnList)) + 1;
			long maxRowValue = Math.abs(Collections.max(rowList))
					+ Math.abs(Collections.min(rowList)) + 1;

			for (long tempRowIndex = 0, rowItr = Collections.min(rowList); tempRowIndex < maxRowValue; tempRowIndex++, rowItr++) {
				for (long tempColIndex = 0, colItr = Collections
						.min(columnList); tempColIndex < maxColumnValue; tempColIndex++, colItr++) {
					Node tempNode = Graph.searchNode(cellWorld, rowItr, colItr);
					if (tempNode == null)
						System.out.print(0);
					else
						System.out.print(tempNode.isNodeAlive() ? 1 : 0);

					System.out.print("\t");
				}
				System.out.println("\n");
			}
		} else {
			throw new Exception("Invalid input");
		}
	}

	/**
	 * Utility which will try input string to parse to integer
	 * 
	 * @param value
	 * @return : Indicator for specifying whether value can be converted into
	 *         integer
	 */
	public static boolean tryParseInt(String value) {
		if (value != null && !value.trim().equals("")) {
			try {
				Integer.parseInt(value);
				return true;
			} catch (NumberFormatException numberFormatException) {
				return false;
			} catch (Exception exception) {
				return false;
			}
		}
		return false;
	}
}
