package lec_0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_1987 {

	static int r, c, nx, ny, result;
	static char map[][];
	static int dir[][] = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		result = 0;
		map = new char[r][c];
		boolean alpha[] = new boolean[26];
		for (int i = 0; i < r; i++) {
			String tmp = bf.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}

		dfs(0, 0, 0, alpha);
		System.out.println(result);

	}

	private static void dfs(int x, int y, int cnt, boolean alpha[]) {
		if ((alpha[map[x][y]-'A'])) {
			result = Math.max(result, cnt);
			return;
		}

		for (int i = 0; i < 4; i++) {
			nx = x + dir[i][0];
			ny = y + dir[i][1];
			if (nx < 0 || nx >= r || ny < 0 || ny >= c)
				continue;
			alpha[map[x][y]-'A'] = true;
			dfs(nx, ny, cnt+1, alpha);
			alpha[map[x][y]-'A'] = false;
//			alpha[map[nx][ny]-'A'] = false;
		}
	}

}
