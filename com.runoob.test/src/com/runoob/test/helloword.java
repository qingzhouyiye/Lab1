package com.runoob.test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class helloword {
	static BufferedReader keyboard = new
			BufferedReader(new InputStreamReader(System.in));
    public static void main(String []args) throws IOException,
    											FileNotFoundException
    {
    	String tokenizer;
    	System.out.print("Input the path:");
    	String read=keyboard.readLine();
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
        	Edgedata[Number[j-1]][Number[j]]++;
        }
       
        System.out.println("*****************************************");
        System.out.println("2.show the graph\n3.calculate the bridge word\n4.create a new "
        		+ "text accoding the bridge word\n5.calculate the shortest path\n"
        		+ "6.walk radomly\n0.exit");
        System.out.println("*****************************************");
        System.out.print("Input the choice you want to achieve(End with 0):");
        String choice=keyboard.readLine();
        while (!choice.equals("0"))
        {
        	if (choice.equals("2"))
        		showDirectGraph(newdic,Edgedata);
        	else if(choice.equals("3"))
        	{
        		System.out.print("Please input two words:\nword1:");
                String word1 = keyboard.readLine();
                System.out.print("word2:");
                String word2 = keyboard.readLine();
                queryBridgeWords(word1,word2,newdic,Edgedata);
                System.out.println();
        	}
        	else if (choice.equals("4"))
        	{
        		  System.out.print("Please input your text:");
        	        String In_text[] = keyboard.readLine().split("\\W+");
        	        generateNewText(In_text,newdic,Edgedata);
        	       System.out.println();
        	}
        	else if (choice.equals("5"))
        	{
        		  System.out.print("Input the first string:");
        	        String a = keyboard.readLine();
        	        System.out.print("Input the second string:");
        	        String b = keyboard.readLine();
        	        calcShortestPath(a,b,newdic,Edgedata);
        	}
        	else if (choice.equals("6"))
        	{
        		 randomWal((int)(Math.random()*(newdic.length)),Edgedata,newdic);
        	}
        	else
        		System.out.println("Error! Please input again!");
        	 System.out.print("Input the choice you want to achieve(End with 0):");
             choice=keyboard.readLine();	
        }
        System.out.println("End Successfully");
    }
    
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
            	for(int k=0;k<conjunction-1;k++)
            	{System.out.print(con_list[conjunction]+',');}
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
        
	   	GraphViz gViz=new GraphViz("E:\\eclipse-java\\workspace\\TEST", \"E:\\eclipse-java\\Graphviz\\bin\\dot.exe");
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
class  GraphViz{
    private String runPath = "";
    private String dotPath = ""; 
    private String runOrder="";
    private String dotCodeFile="dotcode.txt";
    private String resultGif="dotpic";
    private StringBuilder graph = new StringBuilder();

    Runtime runtime=Runtime.getRuntime();

    public void run() {
        File file=new File(runPath);
        file.mkdirs();
        writeGraphToFile(graph.toString(), runPath);
        creatOrder();
        try {
            runtime.exec(runOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatOrder(){
        runOrder+=dotPath+" ";
        runOrder+=runPath;
        runOrder+="\\"+dotCodeFile+" ";
        runOrder+="-T gif ";
        runOrder+="-o ";
        runOrder+=runPath;
        runOrder+="\\"+resultGif+".png";
        System.out.println(runOrder);
    }

    public void writeGraphToFile(String dotcode, String filename) {
        try {
            File file = new File(filename+"\\"+dotCodeFile);
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(dotcode.getBytes());
            fos.close();
        } catch (java.io.IOException ioe) { 
            ioe.printStackTrace();
        }
     }  

    public GraphViz(String runPath,String dotPath) {
        this.runPath=runPath;
        this.dotPath=dotPath;
    }

    public void add(String line) {
        graph.append("\t"+line);
    }

    public void addln(String line) {
        graph.append("\t"+line + "\n");
    }

    public void addln() {
        graph.append('\n');
    }

    public void start_graph() {
        graph.append("digraph G {\n") ;
    }

    public void end_graph() {
        graph.append("}") ;
    }   
}

