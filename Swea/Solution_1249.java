import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1249 {

	static class Pair implements Comparable<Pair> {
		int x;
		int y;
		int v;

		public Pair(int x, int y, int v) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
		}

		@Override
		public int compareTo(Pair o) {
			return Integer.compare(v, o.v);
		}

	}

	static int n, map[][], dp[][];
	static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 우, 하, 좌, 상

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(bf.readLine());
			map = new int[n][n];
			dp = new int[n][n];
			String str;
			for (int i = 0; i < n; i++) {
				Arrays.fill(dp[i], Integer.MAX_VALUE);
			}
			dp[0][0] = 0;
			for (int i = 0; i < n; i++) {
				str = bf.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = str.charAt(j)-'0';
				}
			}
			dijkstra(new Pair(0, 0, 0));

			sb.append("#").append(test_case).append(" ").append(dp[n - 1][n - 1]).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra(Pair pair) {
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		queue.offer(pair);
		int nx, ny;
		while (!queue.isEmpty()) {
			Pair curPair = queue.poll();
			if (curPair.x == n - 1 && curPair.y == n - 1) {
				return;
			}
			if (dp[curPair.x][curPair.y] < curPair.v) {
				continue;
			}
			for (int i = 0; i < 4; i++) {
				nx = curPair.x + dir[i][0];
				ny = curPair.y + dir[i][1];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
					continue;
				}
				if (dp[nx][ny] > map[nx][ny] + curPair.v) {
					dp[nx][ny] = map[nx][ny] + curPair.v;
					queue.offer(new Pair(nx, ny, dp[nx][ny]));
				}

			}
		}
	}

}
