/* $Id$ */


package com.machinima.lmpc.io.text;


import com.machinima.lmpc.io.node.*;
import com.machinima.lmpc.io.base.BlockWriter;


public class TextBlockWriter extends BlockWriter {
	StringBuffer ts;
	StringBuffer cs;

	java.io.Writer writer;

	String LineSeparator;

	public TextBlockWriter(java.io.Writer writer)
	{
		super(writer);
		this.writer = writer;
		LineSeparator = java.lang.System.getProperty("line.separator");
	}

	public void WriteBlock(Node b)
	{
		node_write_text(b, 0);
	}

	public void startnewline(int depth)
	{
		ts = new StringBuffer("");
		// there must be a faster way to create depth spaces at once
		for (int i = 0 ; i<depth ; i++) {
			ts.append(" ");
		}
		cs = new StringBuffer("");
	}

	public void node_write_text(Node b, int depth)
	{
		startnewline(depth);
		node_write_text_recursive(depth, b, true);
	}

	public void output_text_and_comment()
	{
		if (cs.length() > 0) {
			ts.append(" // ");
			ts.append(cs);
		}
		ts.append(LineSeparator);
		try {
			writer.write(ts.toString());
			writer.flush();
		}
		catch (java.io.IOException e) {
			System.err.println("IO error :" + e);
		}
	}

	public void node_write_text_recursive(int depth, Node n, boolean around)
	{
		for (Node i=n ; i!= null ; i=i.next) {
			String newPart = i.toString();
			// wrap around check for Strings only
			if (ts.length() + newPart.length() > 79 &&
				i instanceof StringNode) {
				output_text_and_comment();
				startnewline(depth);
			}
			ts.append(newPart);
			if (i instanceof TokenNode) { // token
				if (i.down != null) {
					if (i.down instanceof TokenNode) {
						ts.append(" {");
						output_text_and_comment();
						startnewline(depth+1);
						node_write_text_recursive(depth+1,i.down,true);
						startnewline(depth);
						ts.append("}");
					}
					else {
						ts.append(" ");
						node_write_text_recursive(depth+1,i.down,false);
						ts.append(";");
					}
				}
				else {
					ts.append(";");
				}
				
			}
			if (around==false && i.next != null) {
				ts.append(" ");
			}
			if (around==true) {
				output_text_and_comment();
				startnewline(depth);
			}
		}
	}

}


