package lec_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251 {
	
	static class Node implements Comparable<Node>{
		int to;
		double w;
		
		public Node(int to, double w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Double.compare(w, o.w);
		}
	}

	static int n, pos[][];
	static double e, graph[][];
	static boolean visited[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(bf.readLine());
			pos = new int[n][2];
			graph = new double[n][n];
			visited = new boolean[n];
			st = new StringTokenizer(bf.readLine(), " ");
			for (int i = 0; i < n; i++) {
				pos[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine(), " ");
			for (int i = 0; i < n; i++) {
				pos[i][1] = Integer.parseInt(st.nextToken());
			}
			e = Double.parseDouble(bf.readLine());
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(i==j) continue;
					graph[i][j] = ((Math.pow(Math.abs(pos[i][0] - pos[j][0]), 2) + Math.pow(Math.abs(pos[i][1] - pos[j][1]), 2))*e);
				}
			}
			
			PriorityQueue<Node> selected = new PriorityQueue<>();
			selected.add(new Node(0, 0.0));
			double result=0;
			int cnt=0;
			Node c= null;
			while(!selected.isEmpty()) {
				Node now = selected.poll();
				
				//이미 방문했으면 컨티뉴
				if(visited[now.to]) continue;

				
				result += now.w;
				visited[now.to] = true;
				
				if (++cnt == n) 	break;
				
				for(int i=0; i<n;i++) {
					Node next = new Node(i, graph[now.to][i]);
					if(visited[i] == true) continue;
					selected.add(next);
				}
			}
			sb.append("#").append(test_case).append(" ").append(Math.round(result)).append("\n");			
		}
		System.out.println(sb);
	}
}
