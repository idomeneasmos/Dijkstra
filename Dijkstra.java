import java.util.*;
import java.util.concurrent.TimeUnit;

public class Dijkstra {
	public static int[] starts= {1,3,1,2,2,4,4,9,5,5,6,7,7,9,8,10,9};
	public static int[] ends= {3,4,2,5,4,5,9,6,6,8,8,6,8,7,10,7,10};
	//public static int[] values= {1,2,8,1,3,1,5,2,2,3,1,2,2,1,10,3,7};
	public static int[] values= {3,5,7,6,1,4,9,2,2,3,1,2,2,1,10,3,7};

	
	
	
	public static int[] func(int startpoint, int endpoint) throws InterruptedException {
		ArrayList<Integer> visited =new ArrayList<>();
		visited.add(startpoint);
		ArrayList<int[]> graph =new ArrayList<>();
		ArrayList<int[]> visitedplaces =new ArrayList<>();
		ArrayList<int[]> checkarrows =new ArrayList<>();
		int[] tempar={1,1,0};
		visitedplaces.add(tempar);
		for (int i=0;i<starts.length;i++) {
			int[] arrow= {starts[i],ends[i], values[i]};
			graph.add(arrow);
		}
		while(!visited.contains(endpoint)) {
			for(int pointer:visited) {
				for(int[] ar:graph) {
					if(ar[0]==pointer || ar[1]==pointer) {
						int valueofstart=0;
						for (int[] strv:visitedplaces) {
							if (strv[1]==pointer) {
								valueofstart=strv[2];
							}
						}
						int[] tempar2= {ar[0],ar[1], ar[2]+valueofstart};
						checkarrows.add(tempar2);
					}
				}
			}
			int min=2^30;
			int[] minar= {0,0,0};
			for (int[] ar:checkarrows) {
				if (min>ar[2]) {
					min=ar[2];
					minar=ar;
				}
			}
			if (visited.contains(minar[0])) {
				visitedplaces.add(minar);
				visited.add(minar[1]);
			}
			else if (visited.contains(minar[1])) {
				int[] minarch= {minar[1], minar[0], minar[2]};
				visitedplaces.add(minarch);
				visited.add(minarch[1]);
			}
			
			ArrayList<int[]> tempgraph =new ArrayList<>();
			for (int[] ar:graph) {
				tempgraph.add(ar);
			}


			
			for(int[] ar:tempgraph){
				
				if(visited.contains(ar[0]) && visited.contains(ar[1])){
					graph.remove(graph.indexOf(ar));
					
				}
			}
			tempgraph.clear();
			checkarrows.clear();

			/*
			for (int num:minar) {
				System.out.print(" "+num);
			}
			System.out.print("\n\n1\n");
			
			for (int[] ar:graph) {
				for (int num:ar) {
					System.out.print(" "+num);
				}
				System.out.print("\n");
			}
			
			System.out.print("\n\n2\n");
			
			for (int[] ar:tempgraph) {
				for (int num:ar) {
					System.out.print(" "+num);
				}
				System.out.print("\n");
			}
			System.out.print("\n\n3\n");
			*/
		}
		
		int pointer=endpoint;
		ArrayList<Integer> answer =new ArrayList<>();
		while (pointer!=startpoint) {
			for (int[] ar:visitedplaces) {
				if(ar[1]==pointer) {
					answer.add(pointer);
					pointer=ar[0];
					visitedplaces.remove(ar);
					break;
				}
			}
		}
		answer.add(pointer);
		int[] answerarray= new int[answer.size()];
		for (int i=0;i<answer.size();i++) {
			answerarray[answer.size()-i-1]=answer.get(i);
		}
		return answerarray;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {

		int startpoint=1;
		int endpoint=10;
		
		/*
		for(int i=0;i<startpoints.length;i++) {
			System.out.printf("%s %s %s\n", startpoints[i], endpoints[i], values[i]);
		}
		*/
		int[] answer= func(startpoint, endpoint);
		for (int num:answer) {
			System.out.print(num+" ");
		}
		System.out.print("\n");
	}
}
