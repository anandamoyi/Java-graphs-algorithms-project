import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Vaccines {
	
	public class Country{
		private int ID;
		private int vaccine_threshold;
		private int vaccine_to_receive;
		private ArrayList<Integer> allies_ID;
		private ArrayList<Integer> allies_vaccine; 
		public Country() {
			this.allies_ID = new ArrayList<Integer>();
			this.allies_vaccine = new ArrayList<Integer>();
			this.vaccine_threshold = 0;
			this.vaccine_to_receive = 0;
		}
		public int get_ID() {
			return this.ID;
		}
		public int get_vaccine_threshold() {
			return this.vaccine_threshold;
		}
		public ArrayList<Integer> get_all_allies_ID() {
			return allies_ID;
		}
		public ArrayList<Integer> get_all_allies_vaccine() {
			return allies_vaccine;
		}
		public int get_allies_ID(int index) {
			return allies_ID.get(index);
		}
		public int get_allies_vaccine(int index) {
			return allies_vaccine.get(index);
		}
		public int get_num_allies() {
			return allies_ID.size();
		}
		public int get_vaccines_to_receive() {
			return vaccine_to_receive;
		}
		public void set_allies_ID(int value) {
			allies_ID.add(value);
		}
		public void set_allies_vaccine(int value) {
			allies_vaccine.add(value);
		}
		public void set_ID(int value) {
			this.ID = value;
		}
		public void set_vaccine_threshold(int value) {
			this.vaccine_threshold = value;
		}
		public void set_vaccines_to_receive(int value) {
			this.vaccine_to_receive = value;
		}
		
	}
	
	public int vaccines(Country[] graph){
		//Computing the exceed in vaccines per country.
		
		if(graph.length == 1) {
			return 0;
		}
		LinkedList<Country> queue = new LinkedList<Country>();
		int[] visited = new int[graph.length];
		queue.add(graph[0]);
		visited[0] = graph[0].ID;
		int immune_countries = graph.length - 1;
		
		while(queue.size() != 0) {
			Country country = queue.remove();
		    for(int i = 0; i <= country.allies_ID.size() - 1;i++) {
		    	int index = country.get_allies_ID(i) - 1;
		    	Country ally_country = graph[index];
		    	if(visited[index] == 0) {
		    	ally_country.vaccine_to_receive -= country.get_allies_vaccine(i);
		    	if(ally_country.vaccine_to_receive < ally_country.vaccine_threshold) {
	    			  queue.add(ally_country);
	    			  visited[index] = ally_country.ID;
	    			  immune_countries--;
	    			  if(immune_countries == 0) {
	    				  return 0;
	    			  }
			     
			
           }
		    	}
	}
}
		return immune_countries;
}

	public void test(String filename) throws FileNotFoundException {
		Vaccines hern = new Vaccines();
		Scanner sc = new Scanner(new File(filename));
		int num_countries = sc.nextInt();
		Country[] graph = new Country[num_countries];
		for (int i=0; i<num_countries; i++) {
			graph[i] = hern.new Country(); 
		}
		for (int i=0; i<num_countries; i++) {
			if (!sc.hasNext()) {
                sc.close();
                sc = new Scanner(new File(filename + ".2"));
            }
			int amount_vaccine = sc.nextInt();
			graph[i].set_ID(i+1);
			graph[i].set_vaccine_threshold(amount_vaccine);
			int other_countries = sc.nextInt();
			for (int j =0; j<other_countries; j++) {
				int neighbor = sc.nextInt();
				int vaccine = sc.nextInt();
				graph[neighbor -1].set_allies_ID(i+1);
				graph[neighbor -1].set_allies_vaccine(vaccine);
				graph[i].set_vaccines_to_receive(graph[i].get_vaccines_to_receive() + vaccine);
			}
		}
		sc.close();
		int answer = vaccines(graph);
		System.out.println(answer);
	}

	public static void main(String[] args) throws FileNotFoundException{
		Vaccines vaccines = new Vaccines();
		vaccines.test(args[0]); // run 'javac Vaccines.java' to compile, then run 'java Vaccines testfilename'
	}

}

