package lec_0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

class Pair {
	int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_G5_15686 {

	static int n, m, house_cnt, chik_cnt, result, minValues[], dist[][], numbers[];

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ArrayList<Pair> house_idx = new ArrayList<>();
		ArrayList<Pair> chik_idx = new ArrayList<>();
		result = Integer.MAX_VALUE;
		numbers = new int[m];
		int tmp = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < n; j++) {
				tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) {
					house_idx.add(new Pair(i, j));
					house_cnt++;
				} else if (tmp == 2) {
					chik_idx.add(new Pair(i, j));
				}
			}
		}
		house_cnt = house_idx.size();
		chik_cnt = chik_idx.size();
		minValues = new int[house_cnt];
		dist = new int[house_cnt][chik_cnt];
		for (int i = 0; i < house_cnt; i++) {
			dist[i][0] = Math.abs(house_idx.get(i).x - chik_idx.get(0).x)
					+ Math.abs(house_idx.get(i).y - chik_idx.get(0).y);
			minValues[i] = dist[i][0];
		}
		for (int i = 0; i < house_cnt; i++) {
			for (int j = 1; j < chik_cnt; j++) {
				dist[i][j] = Math.abs(house_idx.get(i).x - chik_idx.get(j).x)
						+ Math.abs(house_idx.get(i).y - chik_idx.get(j).y);
				minValues[i] = Math.min(minValues[i], dist[i][j]);
			}
		}

		combination(chik_cnt, m);

		System.out.println(result);
	}

	private static void combination(int cnt, int m) {
		if (m == 0) {
			int sum = 0;
			int minVal;
			boolean check;
			for (int i = 0; i < house_cnt; i++) {
				minVal = Integer.MAX_VALUE;
				check = true;
				for (int idx : numbers) {
					if (minValues[i] == dist[i][idx]) {
						sum += minValues[i];
						check = false;
						break;
					} else {
						minVal = Math.min(minVal, dist[i][idx]);
					}
				}
				if (check) {
					sum += minVal;
				}
			}
			result = Math.min(result, sum);
			return;
		}

		if (cnt < m) {
			return;
		}
		numbers[m - 1] = cnt - 1;
		combination(cnt - 1, m - 1);
		combination(cnt - 1, m);
	}
}
