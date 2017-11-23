package com.runoob.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FunctionOp {
	static BufferedReader keyboard = new
			BufferedReader(new InputStreamReader(System.in));
	public static void showDirectGraph(String[] newdic,int[][] Edgedata)
    {
    	GraphViz gViz=new GraphViz("E:\\eclipse-java\\workspace\\TEST", "E:\\eclipse-java\\Graphviz\\bin\\dot.exe");
        gViz.start_graph();
        for(int i=0;i<newdic.length;i++)
        	for(int j=0;j<newdic.length;j++)
        	{
        		if(Edgedata[i][j]>0)
        		{
        			gViz.addln(newdic[i]+"->"+newdic[j]+"[label="+(Edgedata[i][j]+"")+"]"+";");
        		}
        	}
        gViz.end_graph();
        try {
            gViz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void queryBridgeWords(String word1,String word2,String[] newdic,int[][] Edgedata)
    {
    	int location1=-1,location2=-1;
        for(int i=0;i<newdic.length;i++)
        {
        	if(newdic[i].equals(word1) || newdic[i].equals(word2))
        	{
        		if(newdic[i].equals(word1))
        			{location1=i;}
        		else
        			{location2=i;}
        	}
        }
        if(location1==-1 || location2 ==-1)
        	{
        	if(location1==-1 && location2 !=-1)
        		System.out.print("No \""+word1+"\" in the graph!");
        	else if(location1!=-1 && location2 ==-1)
        		System.out.print("No \""+word2+"\" in the graph!");
        	else 
        		System.out.print("No \""+word1+"\" and \""+word2+"\" in the graph!");
        	}
        else
        {
        	int conjunction = -1;
        	String con_list[] = new String[1000];
            for(int i=0;i<newdic.length;i++)
            {
            	if(Edgedata[location1][i]>0 && Edgedata[i][location2]>0)
            	{
            		conjunction++;
            		con_list[conjunction]=newdic[i];
            	}
            }
            if(conjunction == -1) System.out.print("No bridge words from \"" +word1+ "\" to \""+ word2+ "\"!");
            else
            {
            	System.out.print("The bridge words from \"" +word1+ "\" to \""+ word2);
            	if(conjunction==0) System.out.print("\" is: ");
            	else  System.out.print("\" are: ");
            	for(int k=0;k<conjunction;k++)
            	{System.out.print(con_list[k]+',');}
            	System.out.print(con_list[conjunction]+'.');
            }
        }
    }
    public static void Conjunction_connect(String word1,String word2,String[] newdic,int[][] Edgedata)
    {
    	int location1=-1,location2=-1;
        for(int i=0;i<newdic.length;i++)
        {
        	if(newdic[i].equals(word1) || newdic[i].equals(word2))
        	{
        		if(newdic[i].equals(word1))
        			{location1=i;}
        		else
        			{location2=i;}
        	}
        }
        if(location1==-1 || location2 ==-1)
        	return ;
        else
        {
        	int conjunction = -1;
        	String con_list[] = new String[1000];
            for(int i=0;i<newdic.length;i++)
            {
            	if(Edgedata[location1][i]>0 && Edgedata[i][location2]>0)
            	{
            		conjunction++;
            		con_list[conjunction]=newdic[i];
            	}
            }
            if(conjunction == -1)  return ;
            else
            {
            	System.out.print(con_list[0]+" ");
            }
        }
    }
    public static void generateNewText(String[] In_text,String[] newdic,int[][] Edgedata)
    {
    	for(int i=0;i<In_text.length-1;i++)
        {
        	System.out.print(In_text[i]+" ");
        	Conjunction_connect(In_text[i],In_text[i+1],newdic,Edgedata);
        }
       System.out.print(In_text[In_text.length-1]);
    }
    static void calcShortestPath(String a,String b,String []newdic,int [][]Edgedata)
    {
   	 int m=-1,n=-1;
   	  for (int i=0;i<newdic.length;i++)
         {
         	if (a.equals(newdic[i]))
         		m=i;
         	if (b.equals(newdic[i]))
         		n=i;
         }
        
	   	GraphViz gViz=new GraphViz("E:\\eclipse-java\\workspace\\TEST", "E:\\eclipse-java\\Graphviz\\bin\\dot.exe");
	    gViz.start_graph();
	    for(int i=0;i<newdic.length;i++)
	    	for(int j=0;j<newdic.length;j++)
	    	{
	    		if(Edgedata[i][j]>0)
	    		{
	    			gViz.addln(newdic[i]+"->"+newdic[j]+"[label="+(Edgedata[i][j]+"")+"]"+";");
	    		}
	    	}
	    
         int A[][]=new int[newdic.length][newdic.length];
         int P[][]=new int[newdic.length][newdic.length];
         int v,w,k;
         for(v=0; v<newdic.length; ++v)           
         {
             for(w=0; w<newdic.length; ++w)
             {
           	  if (Edgedata[v][w]==0 && v!=w)
           		  A[v][w]=1000;
           	  else
           		  A[v][w]=Edgedata[v][w];           
                 P[v][w]=w;                    
             }
         }
         for(k=0; k<newdic.length; ++k)
         {
             for(v=0; v<newdic.length; ++v)
             {
                 for(w=0; w<newdic.length; ++w)
                 {
                     if (A[v][w]>A[v][k]+A[k][w])
                     {  
                         A[v][w]=A[v][k]+A[k][w];    
                         P[v][w]=P[v][k];                
                     }
                 }
             }
         }
            if (m!=n && m!=-1 && n!=-1) {
                     System.out.println("The length is:"+A[m][n]);
                     k=P[m][n];               
                     System.out.print("path: "+newdic[m]);    
                     gViz.addln(newdic[m]+"->"+newdic[k]+"[color=red]"+";");
                     while(k!=n)               
                     {
                   	  System.out.print(" -> "+newdic[k]);    
                   	  gViz.addln(newdic[k]+"->"+newdic[P[k][n]]+"[color=red]"+";");
                         k=P[k][n];           
                     }
                     System.out.print(" ->"+newdic[n]);    
                 System.out.println();}
       	  
       	  else
       	  {
       		  if (m!=-1 && b.equals(""))
       			  for (n=0;n<newdic.length ;n++)
       			  {
       				  if (m!=n) {
                             System.out.println("The length from "+newdic[m]+" to "+newdic[n]+" is:"+A[m][n]);
                             k=P[m][n];               
                             System.out.print("path: "+newdic[m]);    
                             while(k!=n)               
                             {
                           	  System.out.print(" -> "+newdic[k]);    
                                 k=P[k][n];            
                             }
                             System.out.print(" ->"+newdic[n]);    
                         System.out.println();}
       				  else
       					  System.out.println("Can't reach");
       			  }
       		  else
       			  System.out.println("Can't reach");
       	  }
            gViz.end_graph();
    	    try {
    	        gViz.run();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
       		
    }


 static void randomWal(int i,int Edgedata[][],String []newdic)throws IOException 
    {
	 System.out.print("Input your choice(Y/N):");
	 String choice = keyboard.readLine();
   	 String s="";
   	 int j,k=0;
   	 int count=0;
	 int signal=0;
   	 int visit[][]=new int [newdic.length][newdic.length];
   	 try {
   		 FileWriter fw = new FileWriter("hello.txt");    
 	     BufferedWriter bw = new BufferedWriter(fw);
 	    
 	     s=newdic[i];
   	    bw.write(s);
   	
   	 do{
   		 if (choice.equals('N') || choice.equals('n'))
		 {
			 System.out.println("End");
			 break;
		 }
		 j=(int)(Math.random()*(newdic.length));
		 if (Edgedata[i][j]!=0) {
			 count=1;
			 signal=2;
			 s="-->";
			 s+=newdic[j];
			 if (visit[i][j]==1)
			 {
				 visit[i][j]++;
			 }
			 else
				  visit[i][j]=1;
			 bw.write(s);
			bw.flush();
			 if (visit[i][j]==2)
			 {
				// System.out.println();
				 System.out.println("End");
				 break;
			 }
			 i=j;
			
			
		 }
		 else {
			 for (k=0;k<newdic.length;k++)
			 {
				 if (Edgedata[i][k]!=0)
					 count++;
			 }
		 }
		
		 if (count==0 || choice.equals('N') || choice.equals('n'))
		 {
			 System.out.println("End");
			 break;
		 }
		 
		 count=0;
		
		 if (signal==2)
		 {
			 System.out.print("Input your choice(Y/N):");
			 choice = keyboard.readLine();
		 }
		 signal=1;
		 s="";
	 }while(j<newdic.length);   			  		 		 	  
   	bw.close();
	fw.close();
   	}catch (Exception e) {  
        e.printStackTrace();  
    }  
 	
   	 
	   
    }
}
