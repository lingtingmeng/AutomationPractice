package com.cs.ks.util;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;

public class KSU {

	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	
	/**
	 * 
	 * @param inMsg
	 */
	public static void out(String inMsg)
	{
		System.out.print(inMsg+"\n");	
	}
	
	/**
	 * 
	 * @param inMsg
	 */
	public static void msgOut(String inMsg)
	{
		System.out.print("========== "+inMsg+" ==========\n");	
	}
	
	/**
	 * 
	 * @param inMsg
	 */
	public static void testMsgOut(String inMsg)
	{
		System.out.print(" \n");
		System.out.print("<<<<<<<<<<<<<<<<<<<< "+inMsg+" >>>>>>>>>>>>>>>>>>>>\n");
		System.out.print(" \n");
	}
	
	/**
	 * 
	 * @param inMsg
	 */
	public static void info(String inMsg)
	{
		System.out.print("INFO: "+inMsg+"\n");	
	}
	
	/**
	 * 
	 * @param inMsg
	 */
	public static void warn(String inMsg)
	{
		System.out.print("WARN: "+inMsg+"\n");	
	}
	
	/**
	 * 
	 * @param inMsg
	 */
	public static void error(String inMsg)
	{
		System.out.print("!!!ERROR: "+inMsg+"\n");	
	}
	
	/**
	 * 
	 * @param inMsg
	 * @param outFile
	 * @throws IOException
	 */
	public static void writeHtmlToFile(String inMsg, String outFile) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
		String pageHtmlBodyStr = inMsg.replaceAll("<", "\n<");
		writer.write(pageHtmlBodyStr);
		writer.close();
	}
	

	/**
	 * 
	 * @param file1
	 * @param file2
	 * @throws IOException
	 */
	public static void compareFiles(File file1, File file2) throws IOException
	{
		boolean areEqual = FileUtils.contentEquals(file1, file2);
		
		if (areEqual==true) {
			KSU.info("File1: "+file1+ " is the same as File2: "+file2);
		} else {
			KSU.error("File1: "+file1+ " is NOT the same as File2: "+file2);
			Assert.assertTrue(areEqual);
		}
	}
	
	/**
	 * 
	 * @param file1Str
	 * @param file2Str
	 * @return
	 * @throws IOException
	 */
	public static boolean compareFilesShowDiff(String file1Str, String file2Str) throws IOException
	{
        BufferedReader reader1 = new BufferedReader(new FileReader(file1Str));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2Str));
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        boolean areEqual = true;
        int lineNum = 1;
        
        while (line1 != null || line2 != null){
            if(line1 == null || line2 == null){
                areEqual = false;
                break;
            }else if(! line1.equalsIgnoreCase(line2)){
                areEqual = false;
                break;
            }
            line1 = reader1.readLine();
            line2 = reader2.readLine();
            //assertEquals(line1, line2);
            lineNum++;
        } 
        
        if (areEqual){
            KSU.msgOut("Two files have same content. - Expected");
        }else{
        	KSU.error("FILE 1: "+file1Str+"\n IS NOT THE SAME AS\nFILE 2: "+file2Str+"\n");
        	KSU.error("Two files have different content. They differ at line: "+lineNum+"\n");    
        	KSU.error("FILE 1 has: "+line1+" at line "+lineNum);
        	KSU.error("FILE 2 has: "+line2+" at line "+lineNum+"\n");
        	Assert.assertTrue(areEqual);
        }
        
        reader1.close();
        reader2.close();
        
		return areEqual;
	}
	
	
	
}
