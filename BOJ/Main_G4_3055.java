package lec_0824;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_G4_3055 {
	static class Pair extends Point {
		int val;

		public Pair(int x, int y, int val) {
			super(x, y);
			this.val = val;
		}

	}

	static int r, c, nx, ny;
	static char map[][];
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		String str;
		Point endPos = null;
		Point startPos = null;
		Deque<Point> waterPosList = new ArrayDeque<>();
		Deque<Pair> startPosList = new ArrayDeque<>();
		int result = 0;
		for (int i = 0; i < r; i++) {
			str = bf.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = str.charAt(j);
				switch (map[i][j]) {
				case 'D':
					endPos = new Point(i, j); // 비버 굴 위치 저장
					break;
				case 'S':
					startPosList.offer(new Pair(i, j, 0)); // 고슴도치 시작 위치 저장 + cnt값 설정
					break;
				case '*':
					waterPosList.offer(new Point(i, j)); // 물이 고여있는 위치 저장
					break;
				default:
					break;
				}
			}
		}

		// bfs탐색!
		int sizeOfWater = 0;
		int sizeOfStart = 0;
		top: while (!startPosList.isEmpty() || !waterPosList.isEmpty()) {
			sizeOfWater = waterPosList.size();
			sizeOfStart = startPosList.size();
			for (int i = 0; i < sizeOfWater; i++) { // 물 부터 이동
				Point waterPos = waterPosList.poll();
				for (int j = 0, end = dir.length; j < end; j++) {
					nx = waterPos.x + dir[j][0];
					ny = waterPos.y + dir[j][1];

					if (nx < 0 || nx >= r || ny < 0 || ny >= c)
						continue;

					if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
						map[nx][ny] = '*';
						waterPosList.offer(new Point(nx, ny));
					}
				}
			}

			for (int i = 0; i < sizeOfStart; i++) { // 물이 찬 후 고슴도치 이동
				Pair startPair = startPosList.poll();
				for (int j = 0, end = dir.length; j < end; j++) {
					nx = startPair.x + dir[j][0];
					ny = startPair.y + dir[j][1];

					if (nx < 0 || nx >= r || ny < 0 || ny >= c)
						continue;

					if (map[nx][ny] == 'D') {
						result = startPair.val + 1;
						break top;
					}

					if (map[nx][ny] == '.') {
						map[nx][ny] = 'S';
						startPosList.offer(new Pair(nx, ny, startPair.val + 1));
					}
				}
			}
		}

		if (result == 0) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(result);
		}
	}
}
