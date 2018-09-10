package com.cs.ui.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.Range;


public class WebUtil {
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	
	/**
	 * 
	 * @param inBD
	 * @return
	 */
	public static String bigDecimalToStr(BigDecimal inBD)
	{
		//take 8 decimal value
		String rval = String.format("%.8f", inBD);;
		return rval;
	}
	
	
	/**
	 * 
	 * @param inDou
	 * @return
	 */
	public static BigDecimal doubleToBigDecimal(double inDou)
	{
		BigDecimal rval = new BigDecimal(inDou, MathContext.DECIMAL64);
		return rval;
	}
	
	
	/**
	 * 
	 * @param inIntList
	 * @return
	 */
	public static String[] intToStringArray(List<Integer> inIntList)
	{
		String []rval = new String[inIntList.size()];
		
		for (int i = 0; i < inIntList.size(); i++) {
			rval[i] = Integer.toString(inIntList.get(i));
		}
		
		return rval;
	}
	
	/**
	 * 
	 * @param inStrArray
	 * @return
	 */
	public static int getMaximumInt(String []inStrArray)
	{
		int rval = -1;
		
		if (inStrArray != null) {
			for (String str : inStrArray) {
				int integer = Integer.parseInt(str);
				if (integer > rval) {
					rval = integer;
				}
			}
		}
		return rval;
	}
	
	/**
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
	    String string = bigDecimal.stripTrailingZeros().toPlainString();
	    int index = string.indexOf(".");
	    return index < 0 ? 0 : string.length() - index - 1;
	}
	
	
	/**
	 * @param inRangeSpecifier
	 * @param inMaximumAttrIndex
	 * @param ioErrorMessage
	 * @return
	 */
	public static List<Range<Integer>> extractIntRangeList(
			String inRangeSpecifier,
			int inMaximumAttrIndex,
			StringBuilder ioErrorMessage)
	{
		List<Range<Integer>> rval = new ArrayList<Range<Integer>>();
		
		String []ranges = inRangeSpecifier.split(",");
		for (String range : ranges) {
			int hyphenPos = range.indexOf("-");
			if (hyphenPos < 0) {
				try {
					// Add single-bound range.
					int rangeInd = Integer.parseInt(range);
					Range<Integer> parsedRange = Range.is(rangeInd);
					rval.add(parsedRange);
				} catch (NumberFormatException e) {
					ioErrorMessage.append(e.getMessage());
					rval.clear();
				}
			} else {
				try {
					// Add start of double-bound range.
					String startStr = range.substring(0, hyphenPos);
					int start = Integer.parseInt(startStr);
					
					// Add end of double-bound range
					String endStr = range.substring(hyphenPos + 1);
					int end;
					if (!endStr.equals("*")) {
						end = Integer.parseInt(endStr);
					} else {
						end = inMaximumAttrIndex;
					}
					
					// Add double-bound range.
					Range<Integer> parsedRange = Range.between(start, end);
					rval.add(parsedRange);
				} catch (NumberFormatException e) {
					ioErrorMessage.append(e.getMessage());
					rval.clear();
				}
			}
		}
		
		return rval;
	}
	
	/**
	 * 
	 * @param inStr
	 * @return
	 */
	public static BigDecimal percentStrToBigDecimal(String inStr)
	{
		BigDecimal percentBD = new BigDecimal(inStr.trim().replace("%", "")).divide(BigDecimal.valueOf(100));
		return percentBD;
	}
	
	/**
	 * 
	 * @param inStr
	 * @return
	 */
	public static double percentStrToDouble(String inStr)
	{
		double inStrDou = Double.parseDouble(inStr.trim().replace("%", ""));
		double percentDou = inStrDou/100;
		return percentDou;
	}
	
	/**
	 * 
	 * @param inRangeList
	 * @return
	 */
	public static int[] resolveIntRangeList(List<Range<Integer>> inRangeList)
	{
		// Ensure indices are unique by putting them in a Set. The Set will remove any duplicates.
		Set<Integer> indSet = new TreeSet<Integer>();
		for (Range<Integer> range : inRangeList) {
			for (int i = range.getMinimum(); i <= range.getMaximum(); i++) {
				indSet.add(i);
			}
		}
		
		// Transform Integer Set into ordered integer array.
		int []rval = new int[indSet.size()];
		Iterator<Integer> indIt = indSet.iterator();
		int i = 0;
		while (indIt.hasNext()) {
			rval[i++] = indIt.next();
		}
		Arrays.sort(rval);
		
		return rval;
	}
	
	/**
	 * 
	 * @param inParameters
	 * @return
	 */
	public static Map<String, String> deriveLinkedParameterMap(String inParameters) 
	{
		Map<String, String> rval = new LinkedHashMap<String, String>();
		if (inParameters != null) {
			String[] kvA = inParameters.split(",");
			for (int i = 0; i < kvA.length; i++) {
				// Parse the key and its value. Note: there may be an
				// additional "=" if the value is itself a property key-value assignment.
				String keyVal = kvA[i];
				int eqI = keyVal.indexOf("=");
				String key;
				String val;
				if (eqI >= 0) {
					key = keyVal.substring(0, eqI);
					val = keyVal.substring(eqI + 1);
				} else {
					key = keyVal;
					val = "";
				}
				
				rval.put(key, val);
			}
		}
		return rval;
	}
	
	/**
	 * 
	 * @param inStr
	 * @return
	 */
	public static BigDecimal strToBigDecimal(String inStr)
	{
		BigDecimal currency = new BigDecimal(inStr.replaceAll(",", ""));
		return currency;
	}
	
	/**
	 * 
	 * @param inText
	 * @return
	 */
	public static String convertTildeToSpace(String inText)
	{
		String rval = inText;
		if (rval != null) {
			rval = inText.replace('~', ' ');
		}
		return rval;
	}
}



