package com.runoob.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Graph {
		static BufferedReader keyboard = new
				BufferedReader(new InputStreamReader(System.in));
	    public static void GraphOp(String []args) throws IOException,
	    											FileNotFoundException
	    {
	    	String tokenizer=null;
	    	tokenizer=FileOp.File(tokenizer);
	    	String dic[]=tokenizer.replaceAll("[\\u4e00-\\u9fa5]", "").split("\\W+");
	    	
	    	int[] Number = new int[dic.length];  //Number数组为编号数组
	    	for(int i=0;i<dic.length;i++)
	    	{	//System.out.print(dic[i]+' ');
	            for(int j=i+1;j<dic.length;j++)
	            
	                if(dic[i].equals(dic[j]))
	                {
	                	Number[j]++;
	                }
	    	}
	    	int s=0;
	        for(int i=0;i<dic.length;i++)
	        {
	            
	        	if(Number[i]==0)
	                {Number[i]=s;s++;}
	            else
	            {
	            	for(int j=0;j<i;j++)
	            	{
	            		if(dic[j].equals(dic[i]))
	            			Number[i]=Number[j];
	            	}
	            }
	        }
	        List<String> list = new ArrayList<String>();  
	        for (int i=0; i<dic.length; i++) {  
	            if(!list.contains(dic[i])) {  
	                list.add(dic[i]);  
	            }  
	        }  
	        System.out.println("begin");  
	        String[] newdic =  list.toArray(new String[1]); //返回一个去除重复元素的数组   
	       /* for (String elementB:newdic ) {  
	            System.out.print(elementB + " ");  
	        }  */
	        int[][] Edgedata = new int[newdic.length][newdic.length]; //邻接矩阵存储图
	        for(int j=1; j<Number.length; j++)
	        {
	        	Edgedata[Number[j-1]][Number[j]]++;
	        }
	        
	        System.out.print("Input the choice you want to achieve(End with 0):\n");
	        String choice=keyboard.readLine();
	        while (!choice.equals("0"))
	        {
	        	if (choice.equals("2"))
	        		FunctionOp.showDirectGraph(newdic,Edgedata);
	        	else if(choice.equals("3"))
	        	{
	        		System.out.print("Please input two words:\nword1:");
	                String word1 = keyboard.readLine();
	                System.out.print("word2:");
	                String word2 = keyboard.readLine();
	                FunctionOp.queryBridgeWords(word1,word2,newdic,Edgedata);
	                System.out.println();
	        	}
	        	else if (choice.equals("4"))
	        	{
	        		  System.out.print("Please input your text:");
	        	        String In_text[] = keyboard.readLine().split("\\W+");
	        	        FunctionOp.generateNewText(In_text,newdic,Edgedata);
	        	       System.out.println();
	        	}
	        	else if (choice.equals("5"))
	        	{
	        		  System.out.print("Input the first string:");
	        	        String a = keyboard.readLine();
	        	        System.out.print("Input the second string:");
	        	        String b = keyboard.readLine();
	        	        FunctionOp.calcShortestPath(a,b,newdic,Edgedata);
	        	}
	        	else if (choice.equals("6"))
	        	{
	        		FunctionOp.randomWal((int)(Math.random()*(newdic.length)),Edgedata,newdic);
	        	}
	        	else
	        		System.out.println("Error! Please input again!");
	        	 System.out.print("Input the choice you want to achieve(End with 0):");
	             choice=keyboard.readLine();	
	        }
	        System.out.println("End Successfully");
	    }
}

