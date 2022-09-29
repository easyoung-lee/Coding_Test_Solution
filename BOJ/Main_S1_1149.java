package myTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_1149 {
	
	static int n, dp[][], inputs[][];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		inputs = new int[n][3];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < 3; j++) {
				inputs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[n][3];
		
		// 첫 번째 집 cost 설정
		for (int i = 0; i < 3; i++) {
			dp[0][i] = inputs[0][i];
		}
		
		// dp table 갱신 시키기
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				switch (j) {
				case 0:
					dp[i][j] = Math.min(dp[i-1][1], dp[i-1][2]) + inputs[i][j];					
					break;
				case 1:
					dp[i][j] = Math.min(dp[i-1][0], dp[i-1][2]) + inputs[i][j];					
					break;
				case 2:
					dp[i][j] = Math.min(dp[i-1][0], dp[i-1][1]) + inputs[i][j];					
					break;
				}
			}
		}
		
		System.out.println(Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]));
	}
}
