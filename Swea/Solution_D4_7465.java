package lec_0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution_D4_7465 {

	static int n, m, parents[], result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st =  new StringTokenizer(bf.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			make();
			for (int i = 0; i < m; i++) {
				st =  new StringTokenizer(bf.readLine());
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			HashSet<Integer> set = new HashSet<>();
			for (int i = 1; i <= n; i++) {
				set.add(parents[i]);
			}
			result = set.size();
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void make() {
		parents = new int[n+1];
		for (int i = 1, end = n+1; i < end; i++) {
			parents[i] = i;
		}
	}
	public static int find(int a) {
		if(parents[a] == a)	return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot)	return false;
		
		int tmp = parents[bRoot];
		parents[bRoot] = aRoot;
		for (int i = 1; i <= n; i++) {
			if(parents[i]==tmp) {
				parents[i] = aRoot;
			}
		}
		return true;
	}

}
