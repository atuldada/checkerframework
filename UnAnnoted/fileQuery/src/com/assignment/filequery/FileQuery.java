package com.assignment.filequery;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * FileQuery: Takes input from the user and performs query.
 * 
 * @author Atul Dada
 *
 */
public class FileQuery implements Constants{

	ArrayList<String[]> usrArray = new ArrayList<String[]>();
	ArrayList<String[]> locationArray = new ArrayList<String[]>();
	ArrayList<String[]> professionArray = new ArrayList<String[]>();

	/**
	 * Print tree for location
	 */
	
	void printTree() {
        int n = locationArray.size();
	    TreeModel root = new TreeModel("", n);

		for (int i = 0; i < n; i++) {
			ArrayList<TreeModel> children = root.children;
			for (int j = locationArray.get(i).length - 1; j > 0; j--) {
				int flag = 0;
				for (TreeModel t : children) {
					if (Objects.equals(t.LABEL,(locationArray.get(i)[j].replaceAll("\\s+", "")))) {
						flag = 1;
					}
				}
				if (flag == 0)
					children.add(new TreeModel((locationArray.get(i)[j].replaceAll("\\s+", "")), n));
				for (TreeModel t : children) {
					if (Objects.equals((t.LABEL),(locationArray.get(i)[j].replaceAll("\\s+", "")))) {
						children = t.children;
						break;
					}
				}
			}

		}
		TreeModel.print(root);
	}

	/**
	 * Called by @solveUserQuery() Prints the user name and gender matching
	 * particular location or profession
	 * 
	 * @param userInput
	 *            Input given by user as string for to search users in File
	 * @param choice
	 *            Selection made by user for Profession Search/LocationSearch
	 */
	private void solveQuery( String userInput, int choice) {
		ArrayList<String[]> db = new ArrayList<String[]>();
		if (choice == 1)
			db = locationArray;
		else if (choice == 2) {
			db = professionArray;
		}
		ArrayList<Integer> ids = new ArrayList<>();
		for (int i = 0; i < db.size(); i++) {

			for (int j = 1; j < db.get(i).length; j++) {
				if (db.get(i)[j].replaceAll("\\s+", "").equals(userInput)) {
					ids.add(Integer.valueOf(db.get(i)[0].replaceAll("\\s+", "")));
				}
			}
		}
		for (int j = 0; j < ids.size(); j++)
			for (int i = 0; i < usrArray.size(); i++) {
				if (usrArray.get(i)[choice + 1].replaceAll("\\s+", "").equals(ids.get(j).toString()))
					System.out.println(usrArray.get(i)[0] + "  " + usrArray.get(i)[1]);
			}
	}

	/**
	 * Take input query from the user
	 */
	void solveUserQuery() {
		//
		System.out.println(USER_INPUT_MSG);
		Scanner sc = new Scanner(System.in);
		int input = 0;
		try {
			input = sc.nextInt();

			if (input == 1) {
				System.out.println(ENTER_LOC);
				String locationInput = sc.nextLine();
				locationInput = sc.nextLine();
				solveQuery(locationInput, input);
			}

			if (input == 2) {
				System.out.println(ENTER_PROF);
				String professionInput = sc.nextLine();
				professionInput = sc.nextLine();
				solveQuery(professionInput, input);
			}
			if (input == 3)
				System.out.println(TERMINATING);
		} catch (Exception e) {
			System.out.println(Constants.WRONG_CHOICE);
		} finally {
			sc.close();
		}
	}

	/**
	 * checks table for correct possible values
	 * 
	 * @return true if duplicate entries are there else it returns false
	 */
	boolean checkTable() {
		Set<String> locId = new HashSet<String>();
		Set<String> profId = new HashSet<String>();
		Set<String> professionData = new HashSet<String>();
		Set<String> locationData = new HashSet<String>();

		for (int i = 0; i < locationArray.size(); i++) {
			if (locId.contains(locationArray.get(i)[0]))
				return true;
			locId.add(locationArray.get(i)[0]);
			String tmp = "";
			for (int j = 1; j < locationArray.get(i).length; j++)
				tmp += locationArray.get(i)[j];
			if (locationData.contains(tmp.replaceAll("\\s+", "")))
				return true;
			locationData.add(tmp.replaceAll("\\s+", ""));
		}
		for (int i = 0; i < professionArray.size(); i++) {
			if (profId.contains(professionArray.get(i)[0]))
				return true;
			profId.add(professionArray.get(i)[0]);
			String tmp = "";
			for (int j = 0; j < professionArray.get(i).length; j++)
				tmp += professionArray.get(i)[j];
			if (professionData.contains(tmp.replaceAll("\\s+", "")))
				return true;
			professionData.add(tmp.replaceAll("\\s+", ""));
		}

		return false;
	}
	
	/**
	 * adds table to array list
	 * 
	 * @param br
	 *            current buffer reader to take input.
	 * @param table
	 *            (Required)Array list in which table is to be added
	 * @throws IOException 
	 *             if there is error in reading line from buffered reader
	 */
	public void addTable(BufferedReader br,  ArrayList<String[]> table) throws IOException {
		String currentLine;
		currentLine = br.readLine();// getting table attributes
		while ((currentLine = br.readLine()) != null && !currentLine.equals("")) {
			table.add(Arrays.stream(currentLine.split(",")).filter(s -> (s != null && s.length() > 0))
					.toArray(String[]::new));
		}		
	}
}
