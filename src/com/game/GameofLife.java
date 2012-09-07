/**
 * Sample main class for initializing sequence in cell world of Game of life 
 */
package com.game;

import java.util.ArrayList;
import java.util.List;

import com.game.data.Graph;
import com.game.data.Node;
import com.game.util.GameUtility;

/**
 * @author Karthikeyan R
 * 
 * 
 *         Assumptions: All Dead cells in the cell world is considered &
 *         represented as '0' instead of "-" All Alive cells in the cell world
 *         is considered & represented as '1' instead of "X"
 * 
 */
public class GameofLife {

	public static void main(String[] args) {

		// args = new String[1];
		// args[0] = "toad" + "Pattern.txt";

		int length = args.length;
		if (length <= 0) {
			System.out.println("Enter pattern file path as input argument");
		} else {
			if (args[0] != null || !args[0].trim().equals("")) {
				GameofLife gameLife = new GameofLife();

				try {
					gameLife.nextGeneration(args[0]);
				} catch (Exception e) {
					System.out
							.println("Exception occured while creating next generation: "
									+ e.getMessage());
				}
			} else
				System.out
						.println("Invalid File Path or not able to access file");
		}
	}

	// Reads file content & creates initial nodes for cell world
	public Graph getInitialArray(String inputPattern) throws Exception {

		if (inputPattern != null) {
			int initialMaxRow = 0;
			int initialMaxCol = 0;

			List<Node> nodeList = new ArrayList<Node>();

			String[] inputRows = inputPattern.split("\n");

			if (inputRows != null) {
				initialMaxRow = inputRows.length;

				for (int rowIndex = 0; rowIndex < initialMaxRow; rowIndex++) {

					String[] cells = inputRows[rowIndex].split("\t");

					initialMaxCol = Math.max(cells.length, initialMaxCol);

					for (int colIndex = 0; colIndex < cells.length; colIndex++) {

						String cellTempValue = cells[colIndex] != null ? cells[colIndex]
								: "";

						if (cellTempValue == null
								|| cellTempValue.trim().equals("")) {
							throw new Exception("Invalid input format");
						}

						if (cellTempValue.equals("1")
								|| cellTempValue.equals("0")) {
							boolean cellValue = cells[colIndex] != null ? cells[colIndex]
									.equals("1") : false;

							Node node = new Node(rowIndex, colIndex, cellValue,
									false);

							nodeList.add(node);
						} else
							throw new Exception(
									"Invalid input for cell representation");

					}
				}

				System.out.println("Input Pattern: ");
				GameUtility.printData(nodeList, initialMaxRow, initialMaxCol);

				return new Graph(nodeList, initialMaxRow, initialMaxCol);
			}

		}
		System.out.println("Invalid input");
		return null;
	}

	// Initiates the cell sequence with content of file
	public boolean nextGeneration(String filePath) throws Exception {

		String fileContent = GameUtility.getContents(filePath);
		if (fileContent != null) {
			// Create Cell nodes & Initialize Graph
			Graph cellWorld = getInitialArray(fileContent);
			if (cellWorld != null) {
				// System.out.println("Cell World created from nodes");

				// Initialize neighbors
				try {
					cellWorld.initializeUniverseSequence();
					// System.out.println("Cell World neightbors initialized");

					// Initialize neighbors
					cellWorld.updateStatus();
					// System.out.println("Cell World next generation updated");

					// Prints output data
					System.out.println("Output Pattern: ");
					GameUtility.printData(cellWorld.getCellWorldList(),
							cellWorld.getInitialMaxRow(),
							cellWorld.getInitialMaxCol());
					return true;
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return false;
	}
}
