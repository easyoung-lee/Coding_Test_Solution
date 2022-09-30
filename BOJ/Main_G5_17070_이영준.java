import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_17070_이영준 {

	static int n, graph[][], result;
	static int dir[][] = { { 0, 1 }, { 1, 0 }, { 1, 1 } }; // 가로 세로 대각선
	static int dirIdx[][] = { { 0, 2, -1 }, { 1, 2, -1 }, { 0, 1, 2 } }; // 방향이 가로 세로 대각선일 때 갈 수 있는 idx 설정

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(bf.readLine());
		graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 1, 0); // (시작 x, 시작 y, 가로방향)

		System.out.println(result);
	}

	private static void dfs(int x, int y, int idx) {
		if (x == n - 1 && y == n - 1) {
			result += 1;
			return;
		}
		int nx = 0, ny = 0;
		for (int d : dirIdx[idx]) {
			if (d == -1) {
				continue;
			}
			nx = x + dir[d][0];
			ny = y + dir[d][1];
			switch (d) {
			case 0:
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || graph[nx][ny] == 1) {
					continue;
				}
				dfs(nx, ny, d);
				break;
			case 1:
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || graph[nx][ny] == 1) {
					continue;
				}
				dfs(nx, ny, d);
				break;
			case 2:
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || graph[nx][ny] == 1 || graph[x][ny] == 1
						|| graph[nx][y] == 1) {
					continue;
				}
				dfs(nx, ny, d);
				break;
			default:
				break;
			}
		}
	}
}
