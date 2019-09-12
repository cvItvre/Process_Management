package RR;

import java.util.Random;

public class Process extends Thread {
	
	private int processID;
	private int programCounter;
	private int totalProcessTime;
	private int turn;
	
	public Process(int processID) {
		this.setProcessID(processID);
		this.setProgramCounter(0);
		this.setTotalProcessTime();
		this.setTurn(processID);
	}
	
	@Override
	public void run() {
		
		System.out.println("Process Message: Process " + this.getProcessID() + " started.");
		
		while(this.getProgramCounter() < this.getTotalProcessTime()) {
			
			while(this.getTurn() != OS.actualProcess) {
				System.out.println("Process Message: Process " + this.getProcessID() + " suspended.");
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("Process Message: Process " + this.processID + " wakedup.");
				}
			}
			
			while(OS.actualProcess == this.turn) { //Executing Process
				try {
					sleep(100);
				} catch (InterruptedException e) {

				}
			}
			
			this.programCounter += OS.getQuantum();
			if (this.getProgramCounter() >= this.getTotalProcessTime()) {
				System.out.println("Process Message: Process " + this.getProcessID() + " finished.");
			}
			
		}
		
	}
	
	public int getProcessID() {
		return this.processID;
	}
	
	private void setProcessID(int id) {
		this.processID = id;
	}
	
	public int getProgramCounter() {
		return this.programCounter;
	}
	
	private void setProgramCounter(int pc) {
		this.programCounter = pc;
	}
	
	public int getTotalProcessTime() {
		return this.totalProcessTime;
	}
	
	private void setTotalProcessTime() {
		Random random = new Random();
		this.totalProcessTime = (this.processID + 1) * 5000; //10 to 15 seconds, 2 to 3 interactions
	}
	
	private void setTurn(int turn) {
		this.turn = turn;
	}
	
	private int getTurn() {
		return this.turn;
	}

	@Override
	public String toString() {
		return "Process [processID=" + processID + ", programCounter=" + programCounter + ", totalProcessTime="
				+ totalProcessTime + ", turn=" + turn + "]";
	}

}
