package lec_0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair { // 인덱스 값을 받는 클래스 생성
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_문제은행_1661_이영준 {

	static int graph[][];
	static int dx[] = { -1, 1, 0, 0 }; // 상하좌우
	static int dy[] = { 0, 0, -1, 1 }; // 상하좌우
	static int x, y, nx, ny;

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine());
		int startY = Integer.parseInt(st.nextToken()) - 1;
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int endY = Integer.parseInt(st.nextToken()) - 1;
		int endX = Integer.parseInt(st.nextToken()) - 1;
		Pair startPair = new Pair(startX, startY);
		Pair endPair = new Pair(endX, endY);
		graph = new int[x][y];
		String tmp;

		for (int i = 0; i < x; i++) { // graph 갱신
			tmp = bf.readLine();
			for (int j = 0; j < y; j++) {
				graph[i][j] = tmp.charAt(j) - '0';
			}
		}

		bfs(startPair, endPair); // bfs 탐색 시작!

		System.out.println(graph[endX][endY]);
	}

	private static void bfs(Pair startPair, Pair endPair) {
		Queue<Pair> queue = new LinkedList<Pair>();
		queue.offer(startPair);

		outer: while (!queue.isEmpty()) {
			Pair current = queue.poll();

			for (int i = 0; i < 4; i++) {
				nx = current.x + dx[i]; // 현재 인덱스에서 사방 위치 탐색하기
				ny = current.y + dy[i];

				if (nx < 0 || nx >= x || ny < 0 || ny >= y) { // graph 범위가 벗어날 경우 continue
					continue;
				}

				if (graph[nx][ny] == 0) { // 길을 지나갈 수 있는 경우 -> graph 값 +1, 해당 인덱스 queue에 넣기
					queue.offer(new Pair(nx, ny));
					graph[nx][ny] = graph[current.x][current.y] + 1;
					if (nx == endPair.x && ny == endPair.y) {
						break outer; // 목적지 갱신 후 while문 빠져나가기
					}
				}
			}
		}
	}

}
