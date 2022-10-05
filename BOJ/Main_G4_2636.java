import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_G4_2636 {

	static int n, m, map[][], cnt, time;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cnt++;
				}
			}
		}

		bfs();
		System.out.println(time);
		System.out.println(cnt);
	}

	private static void bfs() {
		Deque<Point> queue = new ArrayDeque<>();
		int before = 0;
		int current = 0;
		int num = 0;
		while(cnt != 0) {
			before = current;			
			current--;
			num = 0;
			queue.offer(new Point(0, 0));
			map[0][0] = current;
			int nx, ny;
			while (!queue.isEmpty()) {
				Point point = queue.poll();
				for (int i = 0; i < 4; i++) {
					nx = point.x + dir[i][0];
					ny = point.y + dir[i][1];
					
					if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
						if(map[nx][ny] == before || map[nx][ny] == 0) {
							map[nx][ny] = current;
							queue.offer(new Point(nx, ny));
						}else if(map[nx][ny] == 1) {
							map[nx][ny] = current;
							num++;
						}
					}
				}
			}
			time++;
			cnt -= num;
		}
		cnt = num;
	}

}
