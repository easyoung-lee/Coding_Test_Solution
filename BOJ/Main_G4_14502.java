import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_G4_14502 {

	static int n, m, result, map[][], numbers[];
	static ArrayList<Point> z_list;
	static ArrayList<Point> v_list;
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		z_list = new ArrayList<>();
		numbers = new int[3];
		v_list = new ArrayList<>();
		result = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					z_list.add(new Point(i, j));
				} else if (map[i][j] == 2) {
					v_list.add(new Point(i, j));
				}
			}
		}

		comb(0, 0);
		
		System.out.println(result);
	}

	private static void comb(int cnt, int start) {
		if (cnt == 3) {
			bfs();
			return;
		}
		for (int i = start, end = z_list.size(); i < end; i++) {
			numbers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	private static void bfs() {
		int newMap[][] = new int[n][m];
		for (int i = 0; i < n; i++) {
			System.arraycopy(map[i], 0, newMap[i], 0, m);
		}
		for (int idx : numbers) {
			Point point = z_list.get(idx);
			newMap[point.x][point.y] = 1;
		}

		Deque<Point> queue = new ArrayDeque<Point>();
		for (Point point : v_list) {
			queue.offer(point);
		}

		int nx, ny;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point curPoint = queue.poll();
				for (int j = 0; j < 4; j++) {
					nx = curPoint.x + dir[j][0];
					ny = curPoint.y + dir[j][1];

					if (nx >= 0 && nx < n && ny >= 0 && ny < m && newMap[nx][ny] == 0) {
						newMap[nx][ny] = 2;
						queue.offer(new Point(nx, ny));
					}
				}
			}
		}
		int cnt = 0;
		for (Point point : z_list) {
			if(newMap[point.x][point.y] == 0) {
				cnt++;
			}
		}
		result = Math.max(result, cnt);
	}

}
