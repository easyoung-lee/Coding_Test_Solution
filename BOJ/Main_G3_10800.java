import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G3_10800 {

	static class Ball implements Comparable<Ball> {
		int num, color, size;

		public Ball(int num, int color, int size) {
			super();
			this.num = num;
			this.color = color;
			this.size = size;
		}

		@Override
		public int compareTo(Ball o) {
			return Integer.compare(size, o.size);
		}
	}

	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(bf.readLine());
		Ball balls[] = new Ball[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			balls[i] = new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(balls); // 크기 순으로 오름차순

		int results[] = new int[n]; // 결과값
		int color_sum[] = new int[n + 1]; // 색상별 총합
		int idx = 0, sum = 0;
		for (Ball curBall : balls) {
			while (balls[idx].size < curBall.size) { // 현재 공보다 작은 크기의 공들을 search한다
				color_sum[balls[idx].color] += balls[idx].size;
				sum += balls[idx].size;
				idx++;
			}
			results[curBall.num] = sum - color_sum[curBall.color]; // 이저까지 더한 sum에서 현재 공의 색상과 동일한 공들의 합을 빼준다.
		}

		for (int result : results) {
			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}

}
