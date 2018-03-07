package com.assignment.filequery;

import java.util.ArrayList;
import java.util.Objects;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * TreeModel: Class to store nodes in tree format
 * 
 * @author Atul Dada
 *
 */
public class TreeModel {
    @Nullable final String LABEL;
	final int N;
	public final ArrayList<TreeModel> children;

	/**
	 * Constructor
	 * 
	 * @param LABEL
	 *            (Required) name of the node
	 * @param n
	 *            (Required) Max no of children possible
	 */
	public TreeModel(String LABEL,  int n) {
		if (n < 0)
			n = 0; // no child is to be added
		this.LABEL = LABEL;
		this.N = n;
		children = new ArrayList<>(n);
	}

	/**
	 * Method is Called by @addChild()
	 * 
	 * @param node
	 *            (Required) node of TreeModel is added. else String is to be passed
	 *            in order to invoke from overloaded method
	 * @return true if node is added successfully else return false if already
	 *         present
	 */
	private boolean addChild( TreeModel node) {
		
		if (children.size() < N) {
			return children.add(node);
		}

		return false;
	}

	/**
	 * {@inheritDoc @add} child node is added to current object
	 * 
	 * @param label
	 *            (Required) add child node with given string
	 * @return true if successfully added false if already present
	 * @throws Exception if new node can not 
	 */
	public boolean addChild(String label)  {
		TreeModel node=new TreeModel(label, N);
		return addChild(node);
	}

	/**
	 * Get all children of given node
	 * 
	 * @return Array list of all children
	 */

	public ArrayList<TreeModel> getChildren() {
		return new ArrayList<>(children);
	}

	/**
	 * Get child at particular index
	 * 
	 * @param index
	 *            (optional) pass index to get child
	 * @return child of the node else null if index is greater than no of children
	 */

	@Nullable
	public TreeModel getChild(@NonNegative int index) {
		if (index < children.size()) {
			return children.get(index);
		}

		return null;
	}

	/**
	 * Get child at particular index
	 * 
	 * @param index
	 *            (Required) pass index to get child
	 * @return child of the node else null if index is greater than no of children
	 */

	@Nullable
	public TreeModel getChild(String s) {
		for (int i = 0; i < children.size(); i++) {
			if (Objects.equals(children.get(i).LABEL,(s)))
				return children.get(i);
		}

		return null;
	}

	/**
	 * Print tree
	 * 
	 * @param root
	 *            instance of the starting point of tree
	 */

	public static void print(TreeModel root) {
		printUtil(root, 0);
	}

	private static void printUtil(TreeModel node, int depth) {

		for (int i = 0; i < depth; ++i) {
			System.out.print("   ");
		}
		System.out.print("|_");
		System.out.println(node.LABEL);

		for (TreeModel child : node.getChildren()) {
			printUtil(child, depth + 1);
		}
	}
}