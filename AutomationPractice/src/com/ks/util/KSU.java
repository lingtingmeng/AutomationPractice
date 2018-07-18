package com.ks.util;

public class KSU {

	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	public static void out(String inMsg)
	{
		System.out.print(inMsg+"\n");	
	}
	
	public static void msgOut(String inMsg)
	{
		System.out.print("========== "+inMsg+" ==========\n");	
	}
	
	public static void testMsgOut(String inMsg)
	{
		System.out.print(" \n");
		System.out.print("<<<<<<<<<<<<<<<<<<<< "+inMsg+" >>>>>>>>>>>>>>>>>>>>\n");
		System.out.print(" \n");
	}
	
	public static void info(String inMsg)
	{
		System.out.print("INFO: "+inMsg+"\n");	
	}
	
	public static void warn(String inMsg)
	{
		System.out.print("WARN: "+inMsg+"\n");	
	}
	
	public static void error(String inMsg)
	{
		System.out.print("!!!ERROR: "+inMsg+"\n");	
	}
	
}
