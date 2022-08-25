package lec_0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S2_10971 {

	static int n, graph[][], result;
	static boolean visited[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(bf.readLine());
		graph = new int[n][n];
		result = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) { // 시작노드 -> 전체 노드 반복문
			visited = new boolean[n];
			visited[i] = true;
			dfs(i, i, 0);
		}
		System.out.println(result);
		
	}

	private static void dfs(int start, int cur, int sum) {
		if (check()) { // 노드를 다 탐색했고 마지막 노드에서 시작 노드로 가는 간선이 있을 경우 result 갱신
			if (graph[cur][start] != 0) {
				result = Math.min(result, sum + graph[cur][start]);
			}
			return;
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i] && graph[cur][i] != 0) { // 현재 노드에서 다음노드로 갈 수 있는지 체크
				visited[i] = true;
				dfs(start, i, sum + graph[cur][i]);
				visited[i] = false;

			}
		}

	}

	private static boolean check() { // 전체 노드를 다 탐색했는지
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

}
