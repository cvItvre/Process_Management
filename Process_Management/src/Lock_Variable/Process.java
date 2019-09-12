package Lock_Variable;

import java.util.Random;

public class Process extends Thread {
	
	private int processID;
	private int programCounter;
	private int totalProgramCommands;
	
	public Process(int processID) {
		this.setProcessID(processID);
		this.setProgramCounter(0);
		this.setTotalProgramCommands();
	}
	
	@Override
	public void run() {
		
		System.out.println("Process " + this.getProcessID() + " started.");
		
		while(this.getProgramCounter() < this.getTotalProgramCommands()) {
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(OS.turn) {
				try {
					sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			OS.turn = true;
			this.enterCriticalSection();
			
			Random rand = new Random();
			this.setProgramCounter(this.getProgramCounter() + (rand.nextInt(10) + 30));
			
			if (this.getProgramCounter() >= this.getTotalProgramCommands()) {
				System.out.println("Process " + this.getProcessID() + " finished.");
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
	
	public int getTotalProgramCommands() {
		return this.totalProgramCommands;
	}
	
	private void setTotalProgramCommands() {
		Random random = new Random();
		this.totalProgramCommands = random.nextInt(131) + 70;
	}
	
	private void enterCriticalSection() {
		
		System.out.println("Process " + this.getProcessID() + " entered the crtitical section.");
		
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Process " + this.getProcessID() + " quitted the crtitical section.");
		OS.turn = false;
		
	}

}
