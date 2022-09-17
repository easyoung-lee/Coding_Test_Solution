package myTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_G5_2447 {

	static int n;
	static char map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(bf.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = '*';
			}
		}

		recursive(0, 0, n);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void recursive(int r, int c, int size) {
		int next_size = size / 3;

		check(r, c, next_size);

		if (size == 3) {
			return;
		} else {
			recursive(r, c, next_size);
			recursive(r, c + next_size, next_size);
			recursive(r, c + next_size * 2, next_size);
			recursive(r + next_size, c, next_size);
			recursive(r + next_size, c + next_size * 2, next_size);
			recursive(r + next_size * 2, c, next_size);
			recursive(r + next_size * 2, c + next_size, next_size);
			recursive(r + next_size * 2, c + next_size * 2, next_size);
		}
	}

	private static void check(int r, int c, int size) {
		for (int i = r+size, end = r+size*2; i < end; i++) {
			for (int j = c+size, end2 = c+size*2; j < end2; j++) {
				map[i][j] = ' ';
			}
		}
	}

}
