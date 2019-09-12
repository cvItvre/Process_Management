package Lock_Variable;

import java.util.ArrayList;

public class OS extends Thread {
	
	public static boolean turn;
	private ArrayList<Process> processesTable;
	
	public OS(int numProcess) {
		OS.turn = false;
		this.processesTable = new ArrayList<>();
		this.createProcess(numProcess);
	}
	
	@Override
	public void run() {
		
		this.startProcess();
		
		while(this.someProcessIsAlive()) {
			
		}
		
		System.out.println("All the processes have finished.");
		
	}
	
	private void createProcess(int numberOfProcess) {
		for(int i = 0; i < numberOfProcess; i++) {
			Process process = new Process(i);
			this.processesTable.add(process);
		}
	}
	
	private void startProcess() {
		for(int i = 0; i < this.processesTable.size(); i++) {
			this.processesTable.get(i).start();
		}
	}
	
	private boolean someProcessIsAlive() {
		for(int i = 0; i < this.processesTable.size(); i++) {
			if (this.processesTable.get(i).isAlive()) {
				return true;
			}
		}
		
		return false;
	}

}
