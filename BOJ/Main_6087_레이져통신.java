import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.awt.Point;

public class Main_6087_레이져통신 {
	static char map[][];
	static int n, m, visited[][];
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new int[n][m];
		Point[] points = new Point[2];
		int num = 0;
		// map 입력값 받기
		for (int i = 0; i < n; i++) {
			String str = bf.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'C') {
					points[num++] = new Point(i, j); // points[0] Start, points[1] End
				}
			}
		}

		bfs(points);

		System.out.println(visited[points[1].x][points[1].y] - 2); // Start를 1로 표시했기에 결과값 -1
	}

	private static void bfs(Point[] points) {
		Deque<Point> queue = new ArrayDeque<>();
		Point curPoint;
		queue.offer(points[0]);
		visited[points[0].x][points[0].y] = 1; // Start point 1로 초기화
		int nx = 0, ny = 0;

		while (!queue.isEmpty()) {
			curPoint = queue.poll();
			int val = visited[curPoint.x][curPoint.y] + 1;
			for (int i = 0, end = dir.length; i < end; i++) {
				nx = curPoint.x + dir[i][0];
				ny = curPoint.y + dir[i][1];

				// 경계를 넘거나 벽일경우
				if (!checkPoint(nx, ny)) {
					continue;
				}

				// 방문안한 경우
				if (visited[nx][ny] == 0) {
					visited[nx][ny] = val;
					queue.offer(new Point(nx, ny));
				} else if (visited[nx][ny] < val) { // 방문한 경우
					continue;
				}

				// 방문했는데 거울의 수가 같거나 큰 경우 -> 무시
				top: while (true) {
					nx += dir[i][0];
					ny += dir[i][1];

					if (!checkPoint(nx, ny)) {
						break top;
					}

					if (visited[nx][ny] == 0) {
						visited[nx][ny] = val;
						queue.offer(new Point(nx, ny));
					} else if (visited[nx][ny] < val) {
						break top;
					}
				}
			}

			// EndPoint C가 갱신되면 종료
			if (visited[points[1].x][points[1].y] != 0) {
				break;
			}
		}
	}

	// 경계를 넘거나 벽일경우
	private static boolean checkPoint(int x, int y) {

		if (x < 0 || x >= n || y < 0 || y >= m || map[x][y] == '*') {
			return false;
		}

		return true;
	}
}
