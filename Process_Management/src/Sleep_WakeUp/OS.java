package Sleep_WakeUp;

import java.util.ArrayList;

public class OS extends Thread {
	
	private static ArrayList<Process> processesTable;
	public static boolean criticalSection;
	
	public OS(int numProcess) {
		OS.processesTable = new ArrayList<>();
		OS.criticalSection = false;
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
			OS.processesTable.add(process);
		}
	}
	
	private void startProcess() {
		for(int i = 0; i < OS.processesTable.size(); i++) {
			OS.processesTable.get(i).start();
		}
	}
	
	private boolean someProcessIsAlive() {
		for(int i = 0; i < OS.processesTable.size(); i++) {
			if (OS.processesTable.get(i).isAlive()) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void wakeUpProcesses(int processID) {
		OS.criticalSection = false;
		System.out.println("WakingUp All Processes");
		for(int i = 0; i < OS.processesTable.size(); i++) {
			OS.processesTable.get(i).interrupt();
		}
		
	}

}
