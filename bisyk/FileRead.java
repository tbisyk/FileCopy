package com.gmail.t.bisyk;

import java.io.IOException;
	

public class FileRead implements Runnable{
	private Action ac;

	public FileRead(Action ac) {
		super();
		this.ac = ac;
	}

	@Override
	public void run() {
		try {
			ac.fileCopy(ac.getFileOut());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
