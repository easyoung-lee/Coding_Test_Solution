import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_S2_1012 {

	static int map[][], inputs[][], n, m, k;
	static int dir[][] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(bf.readLine(), " ");
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[n][m];
			inputs = new int[k][2];
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(bf.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				map[x][y] =1;
				inputs[i] = new int[] {x, y};
			}
			
			int cnt = 0;
			for (int pos[] : inputs) {
				if(map[pos[0]][pos[1]] != 1 ) {
					continue;
				}
				bfs(pos);
				cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
	private static void bfs(int[] pos) {
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(pos);
		map[pos[0]][pos[1]] = -1;
		int nx, ny;
		while(!queue.isEmpty()) {
			int curPos[] = queue.poll();
			for (int i = 0; i < 4; i++) {
				nx = curPos[0] + dir[i][0];
				ny = curPos[1] + dir[i][1];
				
				if(nx<0 || nx>=n || ny<0 || ny>=m) {
					continue;
				}
				
				if(map[nx][ny] == 1) {
					map[nx][ny] = -1;
					int nextPos[] = {nx, ny};
					queue.offer(nextPos);
				}
			}
		}
	}
}
