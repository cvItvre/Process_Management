package RR;

import java.util.ArrayList;

public class Queue {
	
	private ArrayList<Cell> cells;
	
	public Queue() {
		cells = new ArrayList<>();
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}
	
	public void takeProcessOutOfQueue(Process process) {
		if (this.size() > 1) {
			for(int i = 0; i < this.cells.size(); i++) {
				if (this.cells.get(i).getProcess().getProcessID() == process.getProcessID()) {
					if (i > 0) {
						this.cells.get(i - 1).setNextProcess(this.cells.get(i).getNextProcess());
						this.cells.remove(i);
					} else {						
						this.cells.get(this.size() - 1).setNextProcess(this.cells.get(0).getNextProcess());						
						this.cells.remove(0);	
					}
				}
			}
		} else {
			this.getCells().remove(0);
		}
	}
	
	public boolean hasProcess() {
		return this.getCells().size() > 0 ? true : false;
	}
	
	public int size() {
		return this.cells.size();
	}
	
}
