import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Main_G5_10026 {

	static class Pair { // 좌표, 색상값을 가지는 class
		int x;
		int y;
		char color;

		public Pair(int x, int y, char color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

	static int n, result1, result2, bCnt;
	static char map[][];
	static ArrayList<Pair> list;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상 하 좌 우

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(bf.readLine());
		map = new char[n][n];
		list = new ArrayList<>();
		String str;
		char tmp = 0;
		for (int i = 0; i < n; i++) {
			str = bf.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = str.charAt(j);
				if (tmp != map[i][j]) { // 저장된 현재 color와 탐색한 color가 다를시 Pair(i, j, color)를 list에 저장해줌
					tmp = map[i][j];
					list.add(new Pair(i, j, map[i][j]));
				}
			}
			tmp = 0; // 행이 끝났을때 비교해줄 현재 color를 초기화 시켜준다.
		}
		bfs1(); // 적록색약 아닌경우
		result1 += bCnt; // result1 -> 적색과 녹색의 각각 집합 수 / bCnt -> 파란색 집합의 수
		bfs2(); // 적록색약인 경우
		result2 += bCnt; // result2 -> 적색+녹색의 집합 수 / bCnt -> bfs1에서 구해준 파란색 집합의 수
		sb.append(result1).append(" ").append(result2).append("\n");
		System.out.println(sb);
	}

	private static void bfs1() {
		Deque<int[]> queue = new ArrayDeque<>();
		boolean visited[][] = new boolean[n][n];
		char nowColor = 0;
		for (Pair pair : list) { // 입력받을 때 저장해둔 Pair list idx만큼 반복문 돌림. 즉, 이 리스트요소가 첫 start 지점으로 bfs를 돌린다.
			if (!visited[pair.x][pair.y]) { // 방문하지 않은 경우에만 출발지점으로 queue 실행!
				int arr[] = { pair.x, pair.y };
				nowColor = pair.color;
				queue.offer(arr);
				visited[arr[0]][arr[1]] = true;
			} else {
				continue;
			}
			while (!queue.isEmpty()) {
				int curNode[] = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = curNode[0] + dir[i][0];
					int ny = curNode[1] + dir[i][1];

					if (nx < 0 || nx >= n || ny < 0 || ny >= n)
						continue;
					if (nowColor == map[nx][ny] && !visited[nx][ny]) {
						int nextNode[] = { nx, ny };
						queue.offer(nextNode);
						visited[nx][ny] = true;
					}
				}
			}
			if (nowColor == 'B') { // 파란색인 경우 +1
				bCnt++;
			} else { // 적색이나 녹색인 경우 +1
				result1++;
			}
		}
	}

	private static void bfs2() {
		Deque<int[]> queue = new ArrayDeque<>();
		boolean visited[][] = new boolean[n][n];
		for (Pair pair : list) {
			if (!visited[pair.x][pair.y] && pair.color != 'B') { // 출발 노드가 방문되지 않았고 출발 좌표값이 파란색이 아닌경우에만 실행
				int arr[] = { pair.x, pair.y };
				queue.offer(arr);
				visited[arr[0]][arr[1]] = true;
			} else {
				continue;
			}
			while (!queue.isEmpty()) {
				int curNode[] = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nx = curNode[0] + dir[i][0];
					int ny = curNode[1] + dir[i][1];

					if (nx < 0 || nx >= n || ny < 0 || ny >= n)
						continue;
					if (map[nx][ny] != 'B' && !visited[nx][ny]) { // 해당 좌표값이 파란색이 아니고 방문을 안했다면
						int nextNode[] = { nx, ny };
						queue.offer(nextNode);
						visited[nx][ny] = true;
					}
				}
			}
			result2++; // 적색+녹색 집합인 경우 +1
		}
	}
}
