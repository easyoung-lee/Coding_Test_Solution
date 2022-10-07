import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_14503 {

	static int n, m, map[][], d;
	static int dir[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 북 동 남 서

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		boolean visit[][] = new boolean[n][m];
		st = new StringTokenizer(bf.readLine(), " ");
		Point curPoint = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		d = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		int cmd = 1;
		int nx, ny, nd;

		top: while (true) {

			if (cmd == 1) {
				// 청소하기
				cnt++;
				visit[curPoint.x][curPoint.y] = true;
				cmd++;
			}

			// 왼쪽방향부터 탐색!
			if (cmd == 2) {
				nd = d;
				for (int i = 0; i < 4; i++) {
					if (nd == 0) {
						nd = 3;
					} else {
						nd--;
					}
					nx = curPoint.x + dir[nd][0];
					ny = curPoint.y + dir[nd][1];
					
					// 2-1
					if (!visit[nx][ny] && map[nx][ny] == 0) {
						d = nd;
						cmd = 1;
						curPoint.x = nx;
						curPoint.y = ny;
						continue top;
					}
				}

				// 없음..후진!
				nx = curPoint.x - dir[d][0];
				ny = curPoint.y - dir[d][1];
				
				if(map[nx][ny] == 1) {
					break top;
				}else {
					curPoint.x = nx;
					curPoint.y = ny;
					continue top;
				}
			}

		}
		System.out.println(cnt);
	}

}
