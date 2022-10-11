import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1011 {

	static int n, x, map[][], result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(bf.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j]  = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;			
			check();
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	private static void check() {
		int cnt, val;
		boolean check, made; // 경사가 한칸 내려가 있는 경우 , 경사로를 만들수 있는 경우
		// 가로 탐색
		top:
		for (int i = 0; i < n; i++) {
			cnt = 0;
			val = map[i][0];
			check = false;
			made = true;
			for (int j = 0; j < n; j++) {
				if(!check) { // 정상 탐색
					if(val == map[i][j]) {
						cnt++;
					}else if(val + 1 == map[i][j]){
						if(cnt>=x) {
							val = map[i][j];
							cnt = 1;
						}else {
							continue top;
						}
						
					}else if(val - 1 == map[i][j]){
						val = map[i][j];
						cnt = 1;
						check = true; // 한칸 내려간 경사를 만난 경우 check
						made = false;
					}else {
						continue top;
					}					
				}else { // 한칸 내려간 경사를 만난 후 탐색
					if(val == map[i][j]) {
						cnt++;
						if(cnt == x) {
							cnt = 0;
							check = false;
							made = true;
						}
					}else {
						continue top;
					}
				}
			}
			if(made) {
				result++;				
			}
		}
		
		// 세로 탐색
				top:
				for (int i = 0; i < n; i++) {
					cnt = 0;
					val = map[0][i];
					check = false;
					made = true;
					for (int j = 0; j < n; j++) {
						if(!check) { // 정상 탐색
							if(val == map[j][i]) {
								cnt++;
							}else if(val + 1 == map[j][i]){
								if(cnt>=x) {
									val = map[j][i];
									cnt = 1;
								}else {
									continue top;
								}
								
							}else if(val - 1 == map[j][i]){
								val = map[j][i];
								cnt = 1;
								check = true; // 한칸 내려간 경사를 만난 경우 check
								made = false;
							}else {
								continue top;
							}					
						}else { // 한칸 내려간 경사를 만난 후 탐색
							if(val == map[j][i]) {
								cnt++;
								if(cnt == x) {
									cnt = 0;
									check = false;
									made = true;
								}
							}else {
								continue top;
							}
						}
					}
					if(made) {
						result++;				
					}
				}
	}

}
