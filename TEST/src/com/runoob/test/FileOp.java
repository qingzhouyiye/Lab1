package com.runoob.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileOp {
	static BufferedReader keyboard = new
			BufferedReader(new InputStreamReader(System.in));
	public static String File(String tokenizer) throws IOException,FileNotFoundException
	{
		System.out.print("Input the path:(E:\\eclipse-java\\workspace\\TEST\\Lab1.txt)");
    	String read=keyboard.readLine();
    	BufferedReader inFile = new 
    			BufferedReader(new FileReader(read));
    	tokenizer= inFile.readLine();
    
    	do {
    		tokenizer += inFile.readLine();
    		}
    	while(inFile.ready());
    	return tokenizer;
	}
}
