package lec_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_1238 {
	static int n, s, result, visited[];		// 이동경로 입력 수, 시작 노드, 결과값, 방문표시
	static LinkedList<Integer> list[];		// from을 인덱스로 가지고 to를 값으로 가지는 리스트 배열

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(bf.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			list = new LinkedList[101];
			result = Integer.MIN_VALUE;
			for (int i = 0; i < 101; i++) {
				list[i] = new LinkedList<Integer>();
			}
			visited = new int[101];
			st = new StringTokenizer(bf.readLine(), " ");
			for (int i = 0, end = n / 2; i < end; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list[from].add(to);
			}
			bfs();

			System.out.println("#" + test_case + " " + result);
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		int tmp;
		int size;
		int tmp2;
		int cnt = 1;
		int max = 0;

		queue.offer(s);
		visited[s] = cnt;

		while (!queue.isEmpty()) {	// queue.size 만큼 반복문 돌려줌 -> cnt + 1
			size = queue.size();
			cnt++;
			for (int i = 0; i < size; i++) {
				tmp = queue.poll();

				for (int j = 0, end = list[tmp].size(); j < end; j++) {
					tmp2 = list[tmp].get(j);
					if (visited[tmp2] == 0) {	// 혹시나 방문 안한 노드일때 queue에 offer해주고 방문 처리값은 cnt로 표시해준다.
						queue.offer(tmp2);
						visited[tmp2] = cnt;
					}
				}
			}
		}
		for (int i = 1; i < 101; i++) {		// cnt값이 최대인 즉 가장 마지막에 연락된 idx들 중 max(idx) = result 해준다.
			if(visited[max]<= visited[i]) {
				max = i;
			}
		}
		result = max;
	}

}
