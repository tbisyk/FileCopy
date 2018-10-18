package com.gmail.t.bisyk;

import java.io.IOException;

public class FileWriter implements Runnable {
	private Action ac;

	public FileWriter(Action ac) {
		super();
		this.ac = ac;
	}

	public FileWriter() {
		super();
	}

	@Override
	public void run() {
		try {
			ac.fileWriter(ac.getFolderIn());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
