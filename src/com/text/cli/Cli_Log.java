package com.text.cli;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Cli_Log // AIM -> Print multiple lines from a single large file(500MB+) present under a folder with 'line number', 'time taken', 'size of the file', 'speed'
{			 
	public static void main(String[] args) throws IOException, NullPointerException, FileNotFoundException 
    { 
		 /* Input ->
		  * 2020-01-13T01:20:30 2020-01-17T05:55:30 C:\\Users\\hp\\Desktop\\com.text.cli\\LogFile\\000001.log
		  * the file used for this was not present under "LogFile" but was inside "com.text.cli", named -"test.log"
		  */	
		 
		 /* Boundary Conditions ->
		  * 1. Print lines from 'f' <D> 
		  * 2. Print lines till 't' <D>
		  * 3. Stop the process if 'f' is not present anywhere <D>
		  * 4. Stop the process if 't' is not present anywhere <D> 
		  * 5. Stop the process if 'f' & 't' is not present anywhere <D> 
		  * 6. Throw error if 'dir' is wrong <D>
		  * 7. Stop the process if 'f' and 't' is exchanged <D>
		  */	
		
		  Scanner in = new Scanner(System.in);
	      System.out.print("LogExtractor.exe -f "); 
	      System.out.print("-t "); 
	      System.out.print("-i "); 
	      String f = in.next();	     
	      String t = in.next();	      
	      String dir = in.next();
	
    	  File fr = new File(dir);
    	  BufferedReader br = null;
    	  
    	  long i = 1; // to find about the number of lines printed from one file
    	  
    	  int checker = 0; // if 'f' is present inside or not, 't' is present inside or not
    	  
    	  List<String> allLines = new LinkedList<>(); // to get all lines in a list
    	  // It helps to check if 'f' or 't' or both are present inside of any one file or not
    	  
    	  List<String> li = Files.readAllLines(fr.toPath()); // to get all lines present under file
		  allLines.addAll(li);
    	      	  
    	  try
    	  {
    		  br = new BufferedReader(new FileReader(fr));
    		  String line = br.readLine();
    		  long b = fr.length(); // size of files in bytes
    		  long b1 = b/(1000*1000); // size in MegaBytes
    	      
    	      double Starttime = System.nanoTime()/(1000*1000*1000); // time in milli-sec
     	      			   
			  for(int x = 0; x < allLines.size(); x++) //to check "f" is present or not
			  {
				  String d = allLines.get(x);
				  if(d.contains(f)) {checker=1;}	  
			  }	  
			  if(checker==0) {System.exit(0);} //if not present, Stop the process
			  
			  for(int x = 0; x < allLines.size(); x++) //to check "t" is present or not
			  {
				  String d = allLines.get(x);
				  if(d.contains(t)) {checker=2;} 
			  }	  
			  if(checker==0||checker==1) {System.exit(0);} //if not present, Stop the process  	      
    	      
    	      
    		  while(fr!=null)	 
    		  {   
    			  if(line.contains(f) || !line.contains(t))
    			  {    				  
		    		  if(line.startsWith(f) || !line.startsWith(t))
		    		  {	
		        	      double time1 = (System.nanoTime()/(1000*1000*1000)) - Starttime; // time in sec
		        	      
		    			  System.out.println(line + "  " + i + "line " + time1 + "sec " + b1 + "MegaBytes " + Math.round(b1/time1) + "Mbps");
		    			  i++;   			  
		    			  
		    			  line = br.readLine(); // to read every line between the range
		    		  }
		    		  else if(line.startsWith("/n")) {}
		    		  else if(line.startsWith("")) {}
		    		  else {}
		    	
		    		  line = br.readLine(); // commenting this line prints the "/n" in console
    			  }
    			  else //if reverse input is entered, it will be executed
    			  {
    				  System.out.println("Unwanted source or destination entered!!");
    				  System.exit(0);
    			  }
    		  } 
    		  in.close(); // closing scanner class 
    	  }
    	  catch(NullPointerException | IOException e) // to catch the exception // "e" was used to print the exception if needed
    	  {
    		  System.out.println("");
    	  }	
    	  finally
    	  {
    		  br.close(); // closing buffered reader
    	  } 
     }      
 }