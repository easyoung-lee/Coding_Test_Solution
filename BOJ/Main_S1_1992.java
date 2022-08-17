package lec_0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_1992 {

	static int n;
	static char map[][];
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(bf.readLine());
		map = new char[n][n];
		String st;
		for (int i = 0; i < n; i++) {
			st = bf.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = st.charAt(j);
			}
		}
		divide(n, 0, 0);
		
		System.out.println(sb.toString());
	}
	private static void divide(int size, int x, int y) {			// 해당 범위에 맞게 check 함수 진행 -> 부합할시 4분할 진행 (재귀 사용) 
		if(check(size, x, y)) {
			return;
		}
		
		int new_size = size/2;
		
		sb.append("(");
		divide(new_size, x, y);
		divide(new_size, x, y+new_size);
		divide(new_size, x+new_size, y);
		divide(new_size, x+new_size, y+new_size);
		sb.append(")");
	}
	private static boolean check(int size, int x, int y) {			// 분할 범위 안에 모든 수가 동일한지 아닌지 확인해주는 check 함수
		char tmp = map[x][y];
		
		for (int i = x, end = x+size; i < end; i++) {
			for (int j = y, end2 = y+size; j < end2; j++) {
				if(map[i][j] != tmp) {
					return false;
				}
			}
		}
		sb.append(tmp);
		return true;
	}
}
