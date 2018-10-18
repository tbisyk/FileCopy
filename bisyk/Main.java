package com.gmail.t.bisyk;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		File fileOut = new File("D:\\TestSource\\34.avi");
		File folderIn = new File("D:\\TestDestenation\\34.avi"); 
		
		Action ac = new Action(fileOut, folderIn);
		
		FileRead fr = new FileRead(ac);
		OutPer op = new OutPer(ac);
		FileWriter fw = new FileWriter(ac);

		ExecutorService es = Executors.newFixedThreadPool(3);

		es.execute(fr);
		es.execute(op);
		es.execute(fw);
		
		es.shutdown();
	}
}
