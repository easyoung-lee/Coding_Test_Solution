import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G5_2293 {

	static int n, k;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int dp[] = new int[k + 1];
		int coin, idx;

		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			coin = Integer.parseInt(bf.readLine());
			idx = 0;
			for (int j = coin; j <= k; j++) {
				dp[j] += dp[idx++];
			}
		}

		System.out.println(dp[k]);

	}

}
