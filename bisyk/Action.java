package com.gmail.t.bisyk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Action {
	private File fileOut;
	private File folderIn;
	private int start = 1;
	private double con;
	private int readByte = 0;
	private byte[] bt = new byte[1024 * 1024 * 50];

	public Action(File fileOut, File folderIn) {
		super();
		this.fileOut = fileOut;
		this.folderIn = folderIn;
	}

	public File getFileOut() {
		return fileOut;
	}

	public void setFileOut(File fileOut) {
		this.fileOut = fileOut;
	}

	public File getFolderIn() {
		return folderIn;
	}

	public void setFolderIn(File folderIn) {
		this.folderIn = folderIn;
	}

	public synchronized void fileCopy(File fileOut) throws IOException {
		Thread thr = Thread.currentThread();

		con = (double) (1024 * 1024 * 50) / fileOut.length() * 100;

		try (FileInputStream fis = new FileInputStream(fileOut)) {
			for (;;) {
				for (; start != 1;) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						thr.interrupt();
					}
				}
				readByte = fis.read(bt);
				if (readByte<0) {
					fis.close();
					start = 2;
					notifyAll();
					thr.interrupt();
					break;
				}
				System.out.println("1  " + readByte);
				start = 2;
				notifyAll();

			}
		} catch (IOException e) {
			throw e;
		}
	}

	public synchronized void outPer() {
		Thread thr = Thread.currentThread();
		double out = 0;
		for (;;) {
			for (; start != 2;) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
					thr.interrupt();
				}
			}
			if (readByte<0) {
				start = 3;
				notifyAll();
				thr.interrupt();
				break;
			}
			out += con;
			System.out.println(readByte);
			if (out > 100) {
				System.out.println(100 + "%");
			} else {
				System.out.printf("%.2f", out);
				System.out.println("%");
			}
			start = 3;
			notifyAll();
		}
	}

	public synchronized void fileWriter(File foldelIn) throws IOException {
		Thread thr = Thread.currentThread();
		try (FileOutputStream fos = new FileOutputStream(folderIn)) {
			for (;;) {
				for (; start != 3;) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						thr.interrupt();
					}
				}
				if (readByte<0) {
					fos.close();
					start = 2;
					notifyAll();
					thr.interrupt();
					break;
				}
				System.out.println("2  " + readByte);
				System.out.println();
				fos.write(bt, 0, readByte);
				start = 1;
				notifyAll();
			}
		} catch (IOException e) {
			throw e;
		}
	}

}
