import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_IC_1278 {
	
	static int dp[][], n, w, inputs[][];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		inputs = new int[n+1][2];
		dp = new int[n+1][w+1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			inputs[i][0] = Integer.parseInt(st.nextToken());
			inputs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int curW = 0, curV = 0;
		for (int i = 1; i <= n; i++) {
			curW = inputs[i][0];
			curV = inputs[i][1];
			for (int j = 0; j <= w; j++) {
				if(curW > j) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-curW] + curV);
				}
			}
		}
		System.out.println(dp[n][w]);
	}

}