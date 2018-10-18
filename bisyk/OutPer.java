package com.gmail.t.bisyk;

public class OutPer implements Runnable{
	private Action ac;

	public Action getAc() {
		return ac;
	}

	public OutPer(Action ac) {
		super();
		this.ac = ac;
	}

	@Override
	public void run() {
		ac.outPer();
	}

		
}
