import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_S1_2178 {

	static int n, m, map[][], result;
	static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // 우 하 좌 상

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		String str;

		for (int i = 0; i < n; i++) {
			str = bf.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bfs(0, 0);
		System.out.println(map[n - 1][m - 1]);
	}

	private static void bfs(int x, int y) {
		Deque<Point> queue = new ArrayDeque<Point>();
		Point point = new Point(x, y);
		boolean visit[][] = new boolean[n][m];
		queue.offer(point);
		visit[point.x][point.y] = true;

		int nx, ny;
		while (!queue.isEmpty()) {
				Point curPoint = queue.poll();
				for (int j = 0; j < 4; j++) {
					nx = curPoint.x + dir[j][0];
					ny = curPoint.y + dir[j][1];

					if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 1 && !visit[nx][ny]) {
						visit[nx][ny] = true;
						map[nx][ny] = map[curPoint.x][curPoint.y] + 1;
						queue.offer(new Point(nx, ny));
					}
				}
		}
	}

}
