package com.runoob.test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
		static BufferedReader keyboard = new
				BufferedReader(new InputStreamReader(System.in));
	    public static void main(String []args) throws IOException,
	    											FileNotFoundException
	    {	
	    	
	        System.out.println("*****************************************");
	        System.out.println("2.show the graph\n3.calculate the bridge word\n4.create a new "
	        		+ "text accoding the bridge word\n5.calculate the shortest path\n"
	        		+ "6.walk randomly\n0.exit");
	        System.out.println("*****************************************");
	        Graph.GraphOp(args);
	    }
}
