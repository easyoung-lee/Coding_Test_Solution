import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_S1_9205 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		top:
		for (int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(bf.readLine());
			ArrayList<Point> list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(bf.readLine(), " ");
				list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			st = new StringTokenizer(bf.readLine(), " ");
			list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

			// 시작 위치, 목적지 추가
			n += 2;
			boolean check[][] = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				Point from = list.get(i);
				for (int j = i + 1; j < n; j++) {
					Point to = list.get(j);
					if(Math.abs(from.x-to.x)+Math.abs(from.y-to.y) <= 1000) {
						// 이동 가능 체크
						check[i][j] = true;
						check[j][i] = true;
					}
				}
			}
			
			// 플로이드 워셜 algorithm
			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if(check[i][k] && check[k][j]) {
							check[i][j] = true;
						}
					}
				}
				
				// 목적지까지 갈 수 있으면 happy append 후 다음 test case로 이동..
				if(check[0][n-1]) {
					sb.append("happy").append("\n");
					continue top;
				}
			}
			// 목적지까지 갈 수 없으면 sad append..
			sb.append("sad").append("\n");
		}
		// 출력
		System.out.println(sb);
	}

}
