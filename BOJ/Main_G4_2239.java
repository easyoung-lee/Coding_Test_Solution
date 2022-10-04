import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_G4_2239 {

	static int map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String str = bf.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = (int) (str.charAt(j) - '0');
			}
		}

		recursive(0, 0);
	}

	private static void recursive(int x, int y) {
		// 1개의 행이 끝났을 경우
		if (y == 9) {
			recursive(x + 1, 0);
			return;
		}

		// 전체 행이 끝났을 경우
		if (x == 9) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			// 출력 초과.. 해결
			System.exit(0);
			return;
		}

		if (map[x][y] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (!check(x, y, i)) {
					map[x][y] = i;
					recursive(x, y + 1);
					map[x][y] = 0;
				}
			}
		} else {
			recursive(x, y + 1);
		}

	}

	private static boolean check(int x, int y, int val) {

		// 행 검사
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == val) {
				return true;
			}
		}

		// 열 검사
		for (int i = 0; i < 9; i++) {
			if (map[i][y] == val) {
				return true;
			}
		}

		// 3x3 그래프의 첫 start x, y 좌표를 설정해준다. 현재 위치 기준..
		int start_x = (x / 3) * 3;
		int start_y = (y / 3) * 3;
		for (int i = start_x, end = start_x + 3; i < end; i++) {
			for (int j = start_y, end2 = start_y + 3; j < end2; j++) {
				if (map[i][j] == val) {
					return true;
				}
			}
		}

		// 중복되는 값이 없으면 중복 없음 return!
		return false;
	}

}
