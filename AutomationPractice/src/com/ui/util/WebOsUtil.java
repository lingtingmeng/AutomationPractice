package com.ui.util;

public final class WebOsUtil {
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	   private static String OS = null;
	   
	   /**
	    * 
	    * @return
	    */
	   public static String getOsName()
	   {
	      if(OS == null) { OS = System.getProperty("os.name"); }
	      return OS;
	   }
	   
	   /**
	    * 
	    * @return
	    */
	   public static boolean isWindows()
	   {
	      return getOsName().startsWith("Windows");
	   }
	   
	   /**
	    * 
	    * @return
	    */
	   public static boolean isMac() {
		   return getOsName().startsWith("Mac");
	   }

	   /**
	    * 
	    * @return
	    */
	   public static boolean isLinux() {
		   return getOsName().startsWith("Linux");
	   }
}