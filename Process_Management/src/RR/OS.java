package RR;

import java.util.ArrayList;

public class OS extends Thread {
	
	private Queue queue;
	private static int quantum;
	public static int actualProcess;
	
	public OS(int numProcess, ArrayList<String> priorities) {
		this.queue = new Queue();
		this.setQuantum(5000);
		this.createProcess(numProcess, priorities);
		OS.actualProcess = 0;
	}
	
	@Override
	public void run() {
		
		this.startProcesses();
		
		while(this.getQueue().hasProcess()) {
			
			System.out.println("====== NUMBER OF PROCESSES: " + this.getQueue().size() + " ======");
			System.out.println("====== OS Message: Process " + OS.actualProcess + " is executing. ======");
			if (this.getQueue().getCells().get(this.getIndexByID(OS.actualProcess)).getProcess().getTotalProcessTime() >= OS.getQuantum()) {
				try {
					sleep(OS.getQuantum());
				} catch (InterruptedException e) {

				}
			} else {
				try {
					sleep(this.getQueue().getCells().get(OS.actualProcess).getProcess().getTotalProcessTime());
				} catch (InterruptedException e) {

				}
			}
			
			if (this.getQueue().size() > 1) {
				System.out.println("====== OS Message: Pre-Emption!!! ======");
				this.preEmption();
			} else {
				try {
					sleep(this.getQueue().getCells().get(0).getProcess().getTotalProcessTime() - 
							this.getQueue().getCells().get(0).getProcess().getProgramCounter());
					
					OS.actualProcess = -1;
					OS.quantum = this.getQueue().getCells().get(0).getProcess().getTotalProcessTime() - 
							this.getQueue().getCells().get(0).getProcess().getProgramCounter();
					
					while(this.getQueue().getCells().get(0).getProcess().isAlive()) {
						sleep(50);
					}
					
					this.getQueue().takeProcessOutOfQueue(this.getQueue().getCells().get(0).getProcess());
				} catch (InterruptedException e) {
					
				}
			}
			
		}
		
		System.out.println("\n====== OS Message: All the processes have finished. ======");
		
	}
	
	private void createProcess(int numberOfProcess, ArrayList<String> priorities) {
		
		for(int i = 0; i < numberOfProcess; i++) {
			
			if (i == 0) {
				Cell cell = new Cell(new Process(i, priorities.get(i)));
				this.getQueue().getCells().add(cell);
			} else if (i > 0 && i < numberOfProcess - 1) {
				Cell cell = new Cell(new Process(i, priorities.get(i)));
				this.getQueue().getCells().add(cell);
				
				this.getQueue().getCells().get(i - 1).setNextProcess(cell.getProcess());
			} else {
				Cell cell = new Cell(new Process(i, priorities.get(i)));
				this.getQueue().getCells().add(cell);
				
				this.getQueue().getCells().get(i - 1).setNextProcess(cell.getProcess());
				this.getQueue().getCells().get(i).setNextProcess(this.getQueue().getCells().get(0).getProcess());
			}			
			
		}
		
	}
	
	private void preEmption() {
		
		int lastProcess = OS.actualProcess;
		OS.actualProcess = this.getQueue().getCells().get(this.getIndexByID(OS.actualProcess)).getNextProcess().getProcessID();
		
		System.out.println("-> LastProcess: " + lastProcess);
		System.out.println("-> NextProcess: " + OS.actualProcess);
		
		try {
			sleep(100);
		} catch (InterruptedException e) {

		}
		
		if (this.getQueue().getCells().get(this.getIndexByID(lastProcess)).getProcess().getProgramCounter() >= 
				this.getQueue().getCells().get(this.getIndexByID(lastProcess)).getProcess().getTotalProcessTime()) {
			this.getQueue().takeProcessOutOfQueue(this.getQueue().getCells().get(this.getIndexByID(lastProcess)).getProcess());
			
		}
		
		for(int i = 0; i < this.getQueue().size(); i++) {
			if (i != lastProcess) {
				this.getQueue().getCells().get(i).getProcess().interrupt();
			}
		}
		
	}
	
	private void startProcesses() {
		
		for(int i = 0; i < this.getQueue().size(); i++) {
			this.getQueue().getCells().get(i).getProcess().start();
		}
		
	}
	
	private int getIndexByID(int id) {
		for(int i = 0; i < this.getQueue().size(); i++) {
			if (this.getQueue().getCells().get(i).getProcess().getProcessID() == id) {
				return i;
			}
		}
		return 10;
	}
	
	private void setQuantum(int quantum) {
		OS.quantum = quantum;
	}
	
	public static int getQuantum() {
		return OS.quantum;
	}

	public Queue getQueue() {
		return queue;
	}


}
