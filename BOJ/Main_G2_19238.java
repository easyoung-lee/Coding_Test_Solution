import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main_G2_19238 {

	static class Pair implements Comparable<Pair> {
		int x, y, idx, dist;

		public Pair(int idx, int x, int y, int dist) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Pair o) {
			if (dist == o.dist) {
				if (x == o.x) {
					return Integer.compare(y, o.y);
				}
				return Integer.compare(x, o.x);
			}
			return Integer.compare(dist, o.dist);
		}
	}

	static int n, m, fuel, map[][];
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 상, 하, 우, 좌
	static boolean check = true;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(bf.readLine(), " ");
		Point taxiPoint = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

		List<Pair> peoplePairs = new ArrayList<Pair>(); // 탑승객 위치
		List<Pair> endPairs = new ArrayList<Pair>(); // 도착지점 위치

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			peoplePairs.add(new Pair(i, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0));
			endPairs.add(new Pair(i, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0));
		}

		int cnt = 0;
		while (check && cnt++ != m) {
			bfs(peoplePairs, endPairs, taxiPoint);
		}

		if (check) {
			System.out.println(fuel);
		} else {
			System.out.println(-1);
		}
	}

	private static void bfs(List<Pair> peoplePairs, List<Pair> endPairs, Point taxiPoint) {
		// 최단거리 손님 확인하기
		Deque<Point> queue = new ArrayDeque<>();
		int newMap[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			System.arraycopy(map[i], 0, newMap[i], 0, n);
		}

		queue.offer(taxiPoint);
		newMap[taxiPoint.x][taxiPoint.y] = -1;
		int nx, ny, dist = 0;
		while (!queue.isEmpty()) {
			dist++;
			for (int i = 0, end = queue.size(); i < end; i++) {
				Point curPoint = queue.poll();
				for (int j = 0; j < 4; j++) {
					nx = curPoint.x + dir[j][0];
					ny = curPoint.y + dir[j][1];

					if (nx < 0 || nx >= n || ny < 0 || ny >= n || newMap[nx][ny] == 1) {
						continue;
					}

					if (newMap[nx][ny] == 0) {
						queue.offer(new Point(nx, ny));
						newMap[nx][ny] = dist;
					}
				}
			}
		}

		for (Pair cur : peoplePairs) {
			cur.dist = newMap[cur.x][cur.y];
			if (cur.dist == -1) {
				cur.dist = 0;
			} else if (cur.dist == 0 || cur.dist > fuel) {
				check = false;
				return;
			}
		}

		Collections.sort(peoplePairs);

		Pair curPeople = peoplePairs.get(0);

		if (curPeople.dist > fuel) {
			check = false;
			return;
		} else {
			fuel -= curPeople.dist;
			taxiPoint.x = curPeople.x;
			taxiPoint.y = curPeople.y;
			peoplePairs.remove(0);
		}

		// 최단거리 손님 목적지 확인하기
		queue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			System.arraycopy(map[i], 0, newMap[i], 0, n);
		}
		Pair curEndPoint = endPairs.get(curPeople.idx);
		queue.offer(taxiPoint);
		newMap[taxiPoint.x][taxiPoint.y] = -1;
		dist = 0;
		while (!queue.isEmpty()) {
			dist++;
			for (int i = 0, end = queue.size(); i < end; i++) {
				Point curPoint = queue.poll();
				for (int j = 0; j < 4; j++) {
					nx = curPoint.x + dir[j][0];
					ny = curPoint.y + dir[j][1];

					if (nx < 0 || nx >= n || ny < 0 || ny >= n || newMap[nx][ny] == 1) {
						continue;
					}

					if (newMap[nx][ny] == 0) {
						queue.offer(new Point(nx, ny));
						newMap[nx][ny] = dist;
					}
				}
			}
			if (newMap[curEndPoint.x][curEndPoint.y] != 0) {
				curEndPoint.dist = newMap[curEndPoint.x][curEndPoint.y];
				break;
			}
		}

		if (curEndPoint.dist == -1) {
			curEndPoint.dist = 0;
		} else if (curEndPoint.dist == 0 || curEndPoint.dist > fuel) {
			curEndPoint.dist = 0;
		}
		if (curEndPoint.dist == -1) {
			curEndPoint.dist = 0;
		} else if (curEndPoint.dist == 0 || curEndPoint.dist > fuel) {
			check = false;
			return;
		} else {
			fuel += curEndPoint.dist;
			taxiPoint.x = curEndPoint.x;
			taxiPoint.y = curEndPoint.y;
		}
	}
}
