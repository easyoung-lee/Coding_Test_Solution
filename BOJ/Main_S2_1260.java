package lec_0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S2_1260_이영준 {

	static int n, m, v;
	static boolean graph[][], visited[];
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		graph = new boolean[n + 1][n + 1];
		visited = new boolean[n + 1];
		int x = 0;
		int y = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			graph[x][y] = true;
			graph[y][x] = true;
		}

		dfs(v);
		sb.append("\n");
		visited = new boolean[n + 1];
		bfs(v);

		System.out.println(sb.toString());
		sb.setLength(0);
	}

	private static void bfs(int startNode) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int tmp = 0;

		queue.offer(startNode);
		visited[startNode] = true;

		while (!queue.isEmpty()) {
			tmp = queue.poll();
			sb.append(tmp + " ");
			for (int i = 1; i < n + 1; i++) {
				if (graph[tmp][i] && (!visited[i])) {
					queue.offer(i);
					visited[i] = true;
				}
			}

		}

	}

	private static void dfs(int startNode) {
		visited[startNode] = true;
		sb.append(startNode + " ");

		for (int i = 1; i < n + 1; i++) {
			if (graph[startNode][i] && (!visited[i])) {
				dfs(i);
			}
		}
	}

}
