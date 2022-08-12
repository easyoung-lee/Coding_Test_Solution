package lec_0804;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution_1952_이영준 {
	static int prices[], plans[];
	static int result;
	static int npr = 4;
	static int npl = 12;

	public static void main(String args[]) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T;
		T = Integer.parseInt(bf.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			prices = new int[npr];
			plans = new int[npl];
			st = new StringTokenizer(bf.readLine(), " ");
			for (int i = 0; i < npr; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine(), " ");
			for (int i = 0; i < npl; i++) {
				plans[i] = Integer.parseInt(st.nextToken());
			}
			result = Math.min(result, prices[3]);
			
			int total_price = 0;
			nTTr(0, total_price);

			sb.append("#" + test_case + " " + result);
			System.out.println(sb.toString());
			sb.setLength(0);
			result = Integer.MAX_VALUE;
		}
	}

	private static void nTTr(int cnt, int total_price) {

//		if(total_price>result)	return;
		
		if (cnt >= npl) {
			result = Math.min(result, total_price);
			return;
		}

		if (plans[cnt] == 0) {
			nTTr(cnt + 1, total_price);
		} else {
			
			nTTr(cnt + 1, total_price + (plans[cnt] * prices[0]));
			nTTr(cnt + 1, total_price + prices[1]);
			nTTr(cnt + 3, total_price + prices[2]);
		}
	}
}