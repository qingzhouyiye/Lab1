package com.runoob.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class Test1 {
	Main hello = new Main();	
	@Before
	public void setUp() throws Exception {
	}
	static BufferedReader keyboard = new
			BufferedReader(new InputStreamReader(System.in));
	@Test
	public  void testQueryBridgeWords() throws IOException,FileNotFoundException{
	    
	    	String tokenizer;
	    	System.out.print("Input the path:(E:\\eclipse-java\\workspace\\TEST\\example.txt)");
	    	String read=keyboard.readLine();
	    	//String read ="E:\\eclipse-java\\workspace\\TEST\\Lab1.txt";
	    	BufferedReader inFile = new 
	    			BufferedReader(new FileReader(read));
	    	tokenizer= inFile.readLine();
	    
	    	do {
	    		tokenizer += inFile.readLine();
	    		}
	    	while(inFile.ready());
	    	
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
	        	Edgedata[Number[j-1]][Number[j]]++ ;
	        }
	        String In_text[] = keyboard.readLine().split("\\W+");
	        FunctionOp.generateNewText(In_text,newdic,Edgedata);
		//fail("Not yet implemented");
	}
	
}
