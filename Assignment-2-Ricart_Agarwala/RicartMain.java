import java.util.*;
public class RicartMain {

	public static void main(String[] args) {
		// main method
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the name of nodes:-  ");
		String names = sc.nextLine();
		names = names.replace(" ","");
		
		int i, len = names.length();
		ArrayList<Process> processList = new ArrayList<Process>();
		
		for( i = 0; i < len; i++ ) {
			// creating the process type objects
			processList.add(new Process(processList, len, names.charAt(i)));
		}
		
		for( i = 0; i < len; i++ ) {
			// initiating 
			processList.get(i).start();
		}
	}

}
