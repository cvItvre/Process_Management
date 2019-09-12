package RR;

public class Cell {
	
	private Process process;
	private Process nextProcess;
	
	public Cell(Process process) {
		this.setProcess(process);
	}

	public Process getProcess() {
		return process;
	}

	private void setProcess(Process process) {
		this.process = process;
	}

	public Process getNextProcess() {
		return nextProcess;
	}

	public void setNextProcess(Process nextProcess) {
		this.nextProcess = nextProcess;
	}
	
	

}
