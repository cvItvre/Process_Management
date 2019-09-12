package RR;

import java.util.Random;

public class Process extends Thread {
	
	private int processID;
	private int programCounter;
	private int totalProcessTime;
	private int turn;
	private String levelPriority;
	
	public Process(int processID, String priority) {
		this.setProcessID(processID);
		this.setLevelPriority(priority);
		this.setProgramCounter(0);
		this.setTotalProcessTime();
		this.setTurn(processID);
	}
	
	@Override
	public void run() {
		
		System.out.println("-> Process Message: Process " + this.getProcessID() + " started.\tPriority: " + this.getLevelPriority());
		
		while(this.getProgramCounter() < this.getTotalProcessTime()) {
			
			while(this.getTurn() != OS.actualProcess) {
				System.out.println("Process Message: Process " + this.getProcessID() + "\t==>\tState: Suspended.");
				try {
					sleep(10000);
				} catch (InterruptedException e) {
					//System.out.println("Process Message: Process " + this.processID + " wakedup.");
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
				System.out.println("-> Process Message: Process " + this.getProcessID() + " finished.");
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
		this.totalProcessTime = (random.nextInt(5) + 1) * 5000; //5 to 25 seconds, 1 to 5 interactions
	}
	
	private void setTurn(int turn) {
		this.turn = turn;
	}
	
	private int getTurn() {
		return this.turn;
	}
	
	private void setLevelPriority(String priority) {
		this.levelPriority = priority;
	}
	
	public String getLevelPriority() {
		return this.levelPriority;
	}

	@Override
	public String toString() {
		return "Process [processID=" + processID + ", programCounter=" + programCounter + ", totalProcessTime="
				+ totalProcessTime + ", turn=" + turn + "]";
	}

}
