package lec_0810;

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

public class Main_문제은행_1661_이영준_BT {

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

		for (int i = 0; i < x; i++) { 					// graph 갱신
			tmp = bf.readLine();
			for (int j = 0; j < y; j++) {
				graph[i][j] = tmp.charAt(j) - '0';
			}
		}
		graph[startX][startY] = 1;						// 시작지점 방문표시

		dfs(startPair, endPair); 						// dfs 탐색 시작!

		System.out.println(graph[endX][endY]/2);		// 2를 더했기에 출력값은 나누기 2를 해주면 된다.
	}

	private static void dfs(Pair startPair, Pair endPair) {
		for (int i = 0; i < 4; i++) {
			nx = startPair.x + dx[i];
			ny = startPair.y + dy[i];
			
			// 범위를 벗어나거나 벽을 만난경우 (값이 1일 때)
			if (nx < 0 || nx >= x || ny < 0 || ny >= y || graph[nx][ny] == 1) {
				continue;
			}
			
			// 방문한 적 없는 길
			if (graph[nx][ny] == 0) {
				graph[nx][ny] = graph[startPair.x][startPair.y] + 2;			// 벽(값이 1)과 구분하기 위해서 2를 더해주었다.
				if (nx == endPair.x && ny == endPair.y) {
					return; 													// 목적지 도착!! 
				}
				dfs(new Pair(nx, ny), endPair);
			} else if (graph[nx][ny] > 1) {										// 백트레킹 이후 방문한 적 있는 길을 지나갈 때
				if(graph[nx][ny] > (graph[startPair.x][startPair.y] + 2)) {		// 갱신하려는 값보다 현재 graph값이 큰경우 지나가게 해준다.
					graph[nx][ny] = graph[startPair.x][startPair.y] + 2;
					if (nx == endPair.x && ny == endPair.y) {
						return;													// 목적지 도착!! 
					}
					dfs(new Pair(nx, ny), endPair);
				}
			}
		}
	}
}
