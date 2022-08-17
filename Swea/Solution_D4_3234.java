package lec_0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_3234_이영준 {

	static int n, inputs[], result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T;
		T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(bf.readLine());
			result = 0;
			inputs = new int[n];
			st = new StringTokenizer(bf.readLine(), " ");
			for (int i = 0; i < n; i++) {
				inputs[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(inputs);
			do { 												// NextPermutaion으로 순열 구하기
				check(0, 0, 0); 								// 구한 순열 대상으로 저울의 왼쪽, 오른쪽에 놓을 수 있는 모든 경우의 수 구하기
			} while (np(inputs));

			System.out.println("#" + test_case + " " + result);
		}
	}

	private static boolean np(int[] numbers) {
		int N = numbers.length;
		int i = N - 1;
		while (i > 0 && numbers[i - 1] >= numbers[i])
			--i;

		if (i == 0)
			return false;

		int j = N - 1;
		while (numbers[i - 1] >= numbers[j])
			--j;
		swap(i - 1, j);

		int k = N - 1;
		while (i < k)
			swap(i++, k--);

		return true;
	}

	private static void swap(int i, int j) {
		int tmp = inputs[i];
		inputs[i] = inputs[j];
		inputs[j] = tmp;
	}
	
	private static void check(int idx, int left, int right) {
		if (left < right)								// 문제 조건에 맞지않으면 return
			return;

		if (idx == n) { 								// 최종 모든 조건을 갖춘 순열 생성 시 result + 1
			result++;
			return;
		}

		check(idx + 1, left + inputs[idx], right);		// 왼쪽에 놓는 경우 
		check(idx + 1, left, right + inputs[idx]);		// 오른쪽에 놓는 경우
	}
}
