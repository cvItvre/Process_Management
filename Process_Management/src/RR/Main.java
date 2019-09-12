package RR;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<String> priorities = new ArrayList<String>();
		priorities.add("Low");
		priorities.add("High");
		priorities.add("Low");
		priorities.add("High");
		priorities.add("Low");
		priorities.add("High");
		
		OS os = new OS(6, priorities);
		os.start();

	}

}
