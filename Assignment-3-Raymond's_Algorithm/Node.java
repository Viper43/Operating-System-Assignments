import java.util.LinkedList;
public class Node extends Thread {
	
	char name;
	boolean token, cs;
	Node parent;
	
	LinkedList<Node> queue = new LinkedList<Node>();
	
	public Node(char name) {
		
		this.name = name;
		this.parent = null;	
	}
	
	public void setParent(Node parent) {
		
		this.parent = parent;
		if ( this.parent == null ) {
			
			token = true;
		}
		else {
			token = cs = false;
		}
	}
	
	@Override
	public void run()
	{
		try {
			request(true);
		}
		catch( final Exception e) {
			e.printStackTrace();
		}
	}

	public void request(boolean req) {
		
		if( req ) {
			this.queue.add(this);
			System.out.println(this.name + " Wants to enter the critical section");
		}
		
		if( !this.parent.queue.contains(this))
			this.parent.queue.add(this);
		
		if( !this.parent.token )
			this.parent.request(false);
		
		else
			this.parent.invoke();
		
	}
	
	synchronized public void invoke() {
		
		RaymondMain.printStatus();
		
		if( this.cs ) {
			while( this.cs ) {
				
			}
		}
		
		if( this.token ) {
			
			if( this.queue.getFirst() == this )
				this.criticalSection();
			
			else {
				this.parent = this.queue.getFirst();
				this.parent.parent = null;
				
				this.token = false;
				this.parent.token = true;
				
				System.out.println(this.parent.name + " has the token ");
				
				this.queue.removeFirst();
				if( this.queue.size() != 0 )
					this.parent.queue.add(this);
				this.parent.invoke();
			}
		}
		
	}
	
	synchronized public void criticalSection() {
		
		System.out.println("Process " + this.name + " is entering Critical Section");
		
		this.cs = true;
		this.queue.removeFirst();
		
		try {
			Thread.sleep((long)( Math.random() * 5000 ));
		}
		catch( final Exception e ) {
			e.printStackTrace();
		}
		
		System.out.println("Process " + this.name + " is coming out of Critical Section");
		
		this.cs = false;
		
		if( this.queue.size() != 0 )
			this.invoke();
	}
	
	
}