package lec_0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준_7576_토마토_솔루션
class Pair {
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_G5_7576_이영준 {

	static int x, y, cnt, days, graph[][];
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		graph = new int[x][y];
		days = -1;
		cnt = 0;
		Queue<Pair> queue = new LinkedList<Pair>();
		for (int i = 0; i < x; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < y; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 0) { 					// 안익은 토마토 개수 갱신
					cnt++;
				} else if (graph[i][j] == 1) { 				// 익은 토마토 queue에 offer해주기
					queue.offer(new Pair(i, j));
				}
			}
		}

		bfs(queue); 										// 익은 토마토를 가진 queue 인자로 넘겨주기
	}

	private static void bfs(Queue<Pair> queue) {
		int size = 0, nx = 0, ny = 0;
		Queue<Pair> q = new LinkedList<Pair>();
		q = queue;
		while (!q.isEmpty()) {
			size = q.size(); 								// 안익은 토마토에 영향을 줄 수 있는 익은 토마토의 총 개수 (1일이 지날 때마다 갱신)
			for (int i = 0; i < size; i++) {
				Pair p = q.poll();
				for (int j = 0; j < 4; j++) {
					nx = p.x + dir[j][0];
					ny = p.y + dir[j][1];

					if (nx < 0 || nx >= x || ny < 0 || ny >= y || graph[nx][ny] != 0) {
						continue;
					}

					if (graph[nx][ny] == 0) { 				// 안익은 토마토 발견 시 익은 토마토로 바꿔주기
						q.offer(new Pair(nx, ny));
						graph[nx][ny] = 1;
						cnt--;								 // 안익은 토마토의 총 개수 -1
					}
				}
			}
			days++; 										// size의 반복문이 종료되면 days+1 갱신
		}

		if (cnt > 0) { 										// 안익은 토마토가 있다면 -1 return
			days = -1;
		}
		System.out.println(days);

	}

}
