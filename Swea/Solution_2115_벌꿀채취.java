import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취 {

	static int result, n, m, c, map[][][], dp[][], subset_result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(bf.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			map = new int[2][n][n];
			int dp_size = n - m + 1;
			dp = new int[n][dp_size];
			int sum = 0;
			int sum2 = 0;
			result = 0;

			// map 입력
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine(), " ");

				// map 행단위로 입력 후 map[0] -> 입력값 / map[1] -> 입력값^2 저장
				for (int j = 0; j < n; j++) {
					map[0][i][j] = Integer.parseInt(st.nextToken());
					map[1][i][j] = map[0][i][j] * map[0][i][j];
				}
				for (int j = 0; j < m; j++) {
					sum += map[0][i][j];
					sum2 += map[1][i][j];
				}

				// dp table 갱신 -> n-m+1 크기, idx m개별로 끊어서 sum을 구한 뒤 C보다 큰경우 아닌경우 나눠서 갱신..
				for (int j = 0; j < dp_size; j++) {
					// c보다 클 경우
					if (sum > c) {
						subset(i, j, j + m, 0, 0); // 합이 c보다 큰경우 조건에 맞게 수익이 max인 경우 판별
						dp[i][j] = subset_result;
						subset_result = 0;
					} else { // 전체 채집이 가능한 경우
						dp[i][j] += sum2;
					}

					if (j != dp_size - 1) {
						sum = sum - map[0][i][j] + map[0][i][m + j];
						sum2 = sum2 - map[1][i][j] + map[1][i][m + j];
					}
				}
				sum = 0;
				sum2 = 0;
			}

			// dp table로 전체 중 2가지 선택하는 경우의 수 확인 -> result 갱신...
			for (int i = 0; i < n - 1; i++) {
				for (int j = 0; j < dp_size; j++) {

					if (j + m < dp_size) {
						for (int j2 = j + m; j2 < dp_size; j2++) {
							result = Math.max(result, dp[i][j] + dp[i][j2]);
						}
					}
					// 나머지 구하기..
					for (int i2 = i + 1; i2 < n; i2++) {
						for (int j2 = 0; j2 < dp_size; j2++) {
							result = Math.max(result, dp[i][j] + dp[i2][j2]);
						}
					}
				}
			}

			// 마지막 행 구하기
			for (int i = 0; i < dp_size; i++) {
				if (i + m < dp_size) {
					for (int j = i + m; j < dp_size; j++) {
						result = Math.max(result, dp[n - 1][i] + dp[n - 1][j]);
					}
				}
			}

			// result 갱신..
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	private static void subset(int i, int cnt, int N, int sum, int sum2) {
		if (sum > c) { // C보다 큰경우 return..
			return;
		}

		if (cnt == N) { // 모든 원소를 이용한 부분 집합 생성 완료
			subset_result = Math.max(subset_result, sum2);
			return;
		}

		// 현재 원소를 선택한 경우
		subset(i, cnt + 1, N, sum + map[0][i][cnt], sum2 + map[1][i][cnt]);

		// 현재 원소를 선택하지 않은 경우
		subset(i, cnt + 1, N, sum, sum2);
	}

}
