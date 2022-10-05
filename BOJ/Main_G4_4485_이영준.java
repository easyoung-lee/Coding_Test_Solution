import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_G4_4485_이영준 {

	static class Pair implements Comparable<Pair> {
		int x, y, val;

		public Pair(int x, int y, int val) {
			super();
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Main_G4_4485_이영준.Pair o) {
			return val - o.val;
		}

	}

	static int n, graph[][];
	static int dir[][] = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } }; // 하 우 좌 상

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		while(true) {
			cnt++;
			n = Integer.parseInt(bf.readLine());
			if(n == 0) {
				break;
			}else {
				graph = new int[n][n];
				int dp[][] = new int[n][n];
				for (int i = 0; i < n; i++) {
					st = new StringTokenizer(bf.readLine(), " ");
					for (int j = 0; j < n; j++) {
						graph[i][j] = Integer.parseInt(st.nextToken());
					}
					Arrays.fill(dp[i], Integer.MAX_VALUE);
				}
				dijkstra(dp);				
				sb.append("Problem ").append(cnt).append(":").append(" ").append(dp[n - 1][n - 1]).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void dijkstra(int[][] dp) {
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		queue.offer(new Pair(0, 0, graph[0][0]));
		dp[0][0] = graph[0][0];

		int nx, ny;
		while (!queue.isEmpty()) {
			Pair curPair = queue.poll();

			if (curPair.x == n - 1 && curPair.y == n - 1) {
				return;
			}
			if (dp[curPair.x][curPair.y] < curPair.val) {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				nx = curPair.x + dir[i][0];
				ny = curPair.y + dir[i][1];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
					continue;
				}

				if (dp[nx][ny] > curPair.val + graph[nx][ny]) {
					dp[nx][ny] = curPair.val + graph[nx][ny];
					queue.offer(new Pair(nx, ny, dp[nx][ny]));
				}

			}
		}

	}

}
