// $Id$
// class with utility functions


package com.machinima.lmpc.io.base;


public class Util {

	public static String toString(int digits, char format, float value)
	{
		return toString(digits, format, (double)value);
	}

	public static String toString(int digits, char format, double value)
	{
		StringBuffer text = new StringBuffer();

		if (value < 0.0) {
			text.append("-");
			value = -value;
		}

		int intPart = (int)value;
		double fracPart = value - intPart;

		String intPartString = new Integer(intPart).toString();
		int intPartDigits = intPartString.length();

		text.append(intPartString);
		if (value != 0.0 && intPart == 0) {
			digits++;
		}

		text.append('.');

		for (int i=(format=='f' ? 0 : intPartDigits) ;
			i<digits ; i++) {
			fracPart *= 10.0;
		}

		long fracPartLong = Math.round(fracPart);
		String fracPartString = new Long(fracPartLong).toString();
		int fracPartDigits = fracPartString.length();

		for (	int i=(format=='f' ? 0 : intPartDigits) ;
			i < digits - fracPartDigits;
			i++) {
			text.append('0');
		}

		text.append(fracPartString);
			
		return new String(text);
	}

}
