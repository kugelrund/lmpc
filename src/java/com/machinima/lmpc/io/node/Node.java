/* $Id$ */

package com.machinima.lmpc.io.node;

public class Node {
	public Node next;
	String comment;
	int type;
	int hint;
	public Node down;

	public Node add_next(Node node)
	{
		Node n;
		for (n = this ; n.next!=null ; n = n.next) ;
		n.next = node;
		return this;
	}

	public Node add_down(Node node)
	{
		down = node;
		return this;
	}

	public String toString() { return null; }

}


