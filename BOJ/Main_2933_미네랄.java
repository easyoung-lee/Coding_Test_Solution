import java.io.*;
import java.util.*;

public class Main_2933_미네랄 {

	static class Pair implements Comparable<Pair> {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair o) {
			if (y == o.y) {
				return Integer.compare(o.x, x);
			}
			return Integer.compare(y, o.y); // 내림차순
		}

		@Override
		public boolean equals(Object o) {
			Pair p = (Pair) o;

			// name은 상관없이, id만 같으면 true를 리턴합니다.
			if (p.x == this.x) {
				return true;
			}
			return false;
		}
	}

	static int n, m, cluster_num = 1;
	static char map[][];
	static int cluster_levl[][];
	static int dir[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } }; // 우, 좌, 상, 하

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		cluster_levl = new int[n][m];
		// List<Pair> locList = new ArrayList<Pair>();
		int stick_size = 0;
		int stick_length[];
		for (int i = 0; i < n; i++) {
			String str = bf.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		stick_size = Integer.parseInt(bf.readLine());
		stick_length = new int[stick_size];
		st = new StringTokenizer(bf.readLine(), " ");
		for (int i = 0; i < stick_size; i++) {
			stick_length[i] = Integer.parseInt(st.nextToken());
		}

		int d = 1;
		for (int leng : stick_length) {
			d ^= 1; // 왼쪽, 오른쪽 방향 반복 설정
			fight(n - leng, d);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

	private static void fight(int leng, int d) {
		Pair pair;
		if (d == 0) {
			pair = new Pair(leng, 0);
		} else {
			pair = new Pair(leng, m - 1);
		}

		// 막데기 던져 미네랄 찾기
		while (true) {
			if (map[pair.x][pair.y] == 'x') {
				map[pair.x][pair.y] = '.';
				cluster_levl[pair.x][pair.y] = 0;
				break;
			}
			pair.x += dir[d][0];
			pair.y += dir[d][1];

			if (pair.y >= m || pair.y < 0) { // 미네랄이 없으면 함수 종료
				return;
			}
		}

		// 미네랄 주변 클러스터 찾기
		int x, y, nx, ny;
		List<Pair> x_list = new ArrayList<Pair>(); // 파괴된 미네랄 주변에 있는 또 다른 미네랄 좌표들
		for (int i = 0; i < 4; i++) {
			x = pair.x + dir[i][0];
			y = pair.y + dir[i][1];

			if (x < 0 || x >= n || y < 0 || y >= m || map[x][y] == '.') {
				continue;
			}
			x_list.add(new Pair(x, y));
		}

		if (x_list.size() == 1) { // 클러스터가 1개일경우 함수 종료
			return;
		} else { // 클러스터가 2개 이상인 경우 함수 종료
			int end = x_list.size();
			List<Pair> c_list[] = new ArrayList[x_list.size()]; // x_list 좌표의 클러스터 좌표들
			for (int i = 0; i < end; i++) {
				c_list[i] = new ArrayList<Pair>();
			}

			for (int i = 0; i < end; i++) { // 파괴된 미네랄 주변에 존재하는 다른 미네랄 좌표들 size만큼 반복
				Pair targetPair = x_list.get(i);
				Deque<Pair> queue = new ArrayDeque<>();
				cluster_levl[targetPair.x][targetPair.y] = cluster_num;
				queue.offer(targetPair);
				c_list[i].add(targetPair);
				while (!queue.isEmpty()) { // 클러스터 찾기 and 표시
					Pair curPair = queue.poll();
					for (int j = 0; j < 4; j++) {
						nx = curPair.x + dir[j][0];
						ny = curPair.y + dir[j][1];

						if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == '.') {
							continue;
						}

						if (cluster_levl[nx][ny] != cluster_num) {
							cluster_levl[nx][ny] = cluster_num;
							queue.offer(new Pair(nx, ny));
							c_list[i].add(new Pair(nx, ny));
						}
					}
				}
				Collections.sort(c_list[i]);
				List<Pair> moveList = new ArrayList<Pair>(); // 해당 클러스터의 제일 밑바닥 좌표들
				int idxNum = c_list[i].get(0).y;
				int maxX = c_list[i].get(0).x;
				int curX = maxX;
				moveList.add(c_list[i].get(0));

				for (Pair cur : c_list[i]) {
					if (cur.y != idxNum) {
						moveList.add(cur);
						idxNum = cur.y;
						maxX = Math.max(maxX, cur.x);
						curX = cur.x;
					} else if (curX - cur.x > 1) {
						moveList.add(cur);
						curX = cur.x;
					}
				}
				if (maxX != n - 1) {
					int num = 0;
					top: while (true) {
						num++;
						for (Pair tmp : moveList) {
							if ((tmp.x + num >= n) || (map[tmp.x + num][tmp.y] == 'x'
									&& cluster_levl[tmp.x + num][tmp.y] != cluster_num)) {
								num--;
								break top;
							}
						}
					}
					for (Pair cur : c_list[i]) { // 클러스터 좌표들을 num만큼 아래로 이동한다.
						map[cur.x][cur.y] = '.';
						map[cur.x + num][cur.y] = 'x';
						cluster_levl[cur.x][cur.y] = 0;
						cluster_levl[cur.x + num][cur.y] = cluster_num;
					}
					break;
				}
				cluster_num++; // 클러스터 구분하는 값
			}
			cluster_num++; // 클러스터 구분하는 값

		}
	}

}