package com.assignment.filequery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.checkerframework.checker.nullness.qual.Nullable;

/**s
 * FileInput Reads file and passes the data to {@link FileQuery} to perform user
 * query
 * 
 * @author Atul Dadas
 */
public class FileInput implements Constants {

	public static void main(String[] args) {

		FileReader fr = null;
		BufferedReader br = null;
		@Nullable  String currentLine;
		FileQuery file = new FileQuery();

		int noOfTables = 0;
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			while (noOfTables != MAX_NO_OF_TABLES) {
				currentLine = br.readLine();// getting table name

				if (LOCATIONS.equals(currentLine)) {
					file.addTable(br, file.locationArray);

				} else if (PROFESSIONS.equals(currentLine)) {
					file.addTable(br, file.professionArray);

				} else if (INDIVIDUALS.equals(currentLine)) {
					file.addTable(br, file.usrArray);

				} else {
					continue;
				}
				noOfTables++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing connections
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		// check table for validity if correct print tree and solve query
		if (file.checkTable() == true) {
			System.out.println(WRONG_TABLE_FORMAT);
			System.exit(0);
		} else {
			System.out.println(PRINT_TREE);
			file.printTree();
			file.solveUserQuery();
		}
	}
}
