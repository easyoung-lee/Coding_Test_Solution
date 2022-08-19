package lec_0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_G4_15683_이영준 {

	static class CCTV {			// CCTV 객체 생성
		int x;					// x좌표
		int y;					// y좌표
		int n;					// cctv number

		public CCTV(int x, int y, int n) {
			this.x = x;
			this.y = y;
			this.n = n;
		}
	}

	static int n, m, tmp_map[][], map[][], result, notWatch, cctvN, setDirs[];
	static ArrayList<CCTV> cctvList;
	static int dir[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; 						// 상 우 하 좌
	static int[][][] case_dir = { { { 0 }, { 1 }, { 2 }, { 3 } }, { { 0, 2 }, { 1, 3 } },	// 1번, 2번, 3번, 4번, 5번 cctv 경우의수
			{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, { { 3, 0, 1 }, { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 } },
			{ { 0, 1, 2, 3 } } };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		result = Integer.MAX_VALUE;
		cctvList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {								// 사각지대 개수 갱신
					notWatch++;
				}else if (map[i][j] != 6) {							// cctv 객체 생성
					cctvList.add(new CCTV(i, j, map[i][j]-1));
				}
			}
		}
		cctvN = cctvList.size();									// 맵에 있는 총 cctv 개수
		setDirs = new int[cctvN];

		setDirCCTV(0);												// 순열 start!

		System.out.println(result);

	}

	private static void setDirCCTV(int idx) {
		if (idx == cctvN) {											// 모든 cctv 각각의 각도가 정해졌을때
			tmp_map = new int[n][m];								// map 깊은 복사
			for (int i = 0; i < n; i++) {
				System.arraycopy(map[i], 0, tmp_map[i], 0, m);
			}
			result = Math.min(result, watchCCTV());					// 구한 사각지대 개수 최신화
			return;

		}
		for (int i = 0, end = case_dir[cctvList.get(idx).n].length; i < end; i++) {		// cctv 별 각도 정하는 반복문
			setDirs[idx] = i;															// 전체 cctv의 각도 저장
			setDirCCTV(idx + 1);
		}
	}

	private static int watchCCTV() {
		int cnt = 0;
		int nx = 0;
		int ny = 0;
		int x = 0;
		int y = 0;

		for (int i = 0; i < cctvN; i++) {
			CCTV cctv = cctvList.get(i);
			int[] checkDir = case_dir[cctv.n][setDirs[i]];
			for (int j = 0, end = checkDir.length; j < end; j++) {							// cctv 각도별 dir 설정해주기 위한 반복문
				x = cctv.x;																	// 현재 선택된 cctv의 x, y좌표 설정
				y = cctv.y;

				while (true) {
					nx = x + dir[checkDir[j]][0];
					ny = y + dir[checkDir[j]][1];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m || tmp_map[nx][ny] == 6) {	// 벽에 마주했거나 map 범위 벗어나면 break;
						break;
					}

					if (tmp_map[nx][ny] == 0) {												// 감시가 가능하다면 -1로 값변환 -> '#' 대신
						tmp_map[nx][ny] = -1;
						cnt++;																// 감시 가능한 지역 +1
					}
					x = nx;
					y = ny;

				}
			}
		}
		return notWatch - cnt;																// 전체 사각지역 개수 - 총 감시한 지역 = 감시 못한 사각지대 개수
	}

}
