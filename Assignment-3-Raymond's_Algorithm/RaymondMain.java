import java.util.ArrayList;
import java.util.Scanner;

public class RaymondMain {

	public static ArrayList<Node> nodeList = new ArrayList<Node>();
	
	synchronized public static void printStatus() {
		
		System.out.println("The lists of the nodes are:\n");
		for(Node i : nodeList ) {
			System.out.print( i.name + " : " );
			if( i.queue.size() == 0 ) {
				System.out.println("The queue is empty.");
			}
			else {
				for(Node j : i.queue ) {
					System.out.print( j.name + " " );
				}
			}
			System.out.println();	
		}	
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);		
		
		System.out.println("Enter the name of nodes:-     ");
		String names = sc.nextLine();
		names = names.replace(" ","");
		int i, len = names.length();
		
		for( i = 0; i < len; i++ ) {
			
			nodeList.add(new Node( names.charAt(i) ));
		}
		
		System.out.println("Enter the name of the node having the token:-  ");
		char root = sc.next().charAt(0);
		nodeList.get(names.indexOf(root)).setParent(null);
		
		for( Node j : nodeList ) {
			
			if ( j.name != root ) {
				System.out.println("Enter the parent of " + j.name + ":" );
				char parent = sc.next().charAt(0);
				j.setParent( nodeList.get( names.indexOf(parent) ) );
			}
		}
		
		printStatus();
		for( Node j : nodeList ) {
			
			if(j.parent != null) {
				j.start();
			}
		}
	}
}
