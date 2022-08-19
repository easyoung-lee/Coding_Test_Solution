package lec_0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_1697 {

	static int n, k, result;
	static boolean visited[] = new boolean[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		if(n>=k) {										// n이 k이상일 시 뺀 값만 출력
			System.out.println(n-k);
		}else {											// 아니면 깊이별로 bfs +1, -1, *2  연산 start
			bfs();
			System.out.println(result);			
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		int size;
		int target;
		int a, b, c;

		queue.offer(n);
		visited[n] = true;
		top:
		while (!queue.isEmpty()) {
			result++;
			size = queue.size();						// 각 단계별 깊이를 구분해주는 size
			for (int i = 0; i < size; i++) {
				target = queue.poll();
				a = target - 1;
				b = target + 1;
				c = target * 2;

				if (a == k || b == k || c == k) {		// k 라면 종료..
					break top;
				}
				if (a >= 0 && a < 100001) {				// -1 연산 큐에 넣어줄지 말지 확인
					if (!visited[a]) {
						queue.offer(a);
						visited[a] = true;
					}
				}
				if (b >= 0 && b < 100001) {				// +1 연산 큐에 넣어줄지 말지 확인
					if (!visited[b]) {
						queue.offer(b);
						visited[b] = true;
					}
				}
				if (c >= 0 && c < 100001) {				// *2 연산 큐에 넣어줄지 말지 확인
					if (!visited[c]) {
						queue.offer(c);
						visited[c] = true;
					}
				}
			}
		}
	}

}
