import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class Process extends Thread {					//class of processes

	int  timeStamp, noOfNodes;
	char name;
	boolean criticalSection, requested;
	
	ArrayList<Integer> requestList = new ArrayList<Integer>();			// list of requests
	ArrayList<Process> processList = new ArrayList<Process>();			// list of processes
	Set<Process> grantSet = new HashSet<Process>();						// set to hold the nodes who gave access
	
	Process(ArrayList<Process> processList, int noOfNodes, char name) {
		
		// Constructor
		
		timeStamp = 0;
		criticalSection = requested = false;
		this.noOfNodes = noOfNodes;
		this.name = name;
		this.processList = processList;
	}
	
	@Override
	public void run() {
		
		while( true ) {
			
			try {
				// requesting for critical section
				request();
				Thread.sleep((long)(Math.random()*5000));
			}
			catch(final Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	void request() {			// request method
		
		System.out.println(this.name + " is requesting  ");
		this.timeStamp += 1;
		this.requested = true;
		
		for(Process i : processList) { 
			
			if(i != this )
				
				// calling the grant method of the nodes who are being requested
				
				i.grant(this);
		}
		
		while ( this.grantSet.size() != noOfNodes - 1  ) {
			// wait until access from all nodes arrives
		}
		
		criticalSec(this);
		System.out.println(this.name + " is out of Critical Section..........");
		
		this.grantSet.clear();
		this.criticalSection = false;
		this.requested = false;
		
		for(Integer i : this.requestList ) {
			
			//Giving access to all the nodes in the requesting list
			processList.get(i).grantSet.add(this);
			
		}
		
		this.requestList.clear();
	}
	
	void grant(Process p) {
		
		if( this.criticalSection )
			// if the granting node is in critical section
			this.requestList.add(processList.indexOf(p));
			
		
		else if( this.requested ) {
			// if the granting node is requesting
			
			if( p.timeStamp < this.timeStamp )
				p.grantSet.add(this);
			
			else if( p.timeStamp > this.timeStamp )
				this.requestList.add(processList.indexOf(p));
			
			else {
				
				if( processList.indexOf(p) < processList.indexOf(this) )
					p.grantSet.add(this);
				
				else
					this.requestList.add(processList.indexOf(p));
				
			}
		}
			
		else
			// if the granting node is idle
			p.grantSet.add(this);
		
		
	}
	
	void criticalSec(Process p) { 				// critical section
		p.criticalSection = true;
		System.out.println(p.name + " is entering Critical Section..........");
		
	}
}
