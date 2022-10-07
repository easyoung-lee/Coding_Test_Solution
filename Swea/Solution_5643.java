import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5643 {

	static int n, m, result, val;
	static boolean visit[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(bf.readLine());
			m = Integer.parseInt(bf.readLine());
			
			ArrayList<Integer> downList[] = new ArrayList[n+1];
			ArrayList<Integer> upList[] = new ArrayList[n+1];
			
			for (int i = 0; i <= n; i++) {
				downList[i] = new ArrayList<Integer>();
				upList[i] = new ArrayList<Integer>();
			}
			
			int a, b;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				downList[b].add(a);
				upList[a].add(b);
			}
			result = 0;
			for (int i = 1; i <= n; i++) {
				visit = new boolean[n+1];
				// down check
				check(i, downList);
				// up check
				check(i, upList);
				
				if(val == n-1) {
					result++;
				}
				val = 0;
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	private static void check(int idx, ArrayList<Integer>[] list) {
		if(list[idx].isEmpty()) {
			return;
		}
		for (int v : list[idx]) {
			if(visit[v]) {
				continue;
			}
			visit[v] = true;
			val++;
			check(v, list);
		}
		
	}

}
