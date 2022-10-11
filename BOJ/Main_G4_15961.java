import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G4_15961 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int sushi[] = new int[n];
		int check[] = new int[d + 1]; // 중복이 될 수 있기에 int 배열로 표시
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			sushi[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 1;
		check[c] = 1; // 쿠폰 스시 eat!
		for (int i = 0; i < k; i++) { // 첫번째 연속으로 먹을 수 있는 k 접시 만큼 초밥의 가짓수 확인
			if (check[sushi[i]] == 0) {
				cnt++;
			}
			check[sushi[i]]++;
		}

		// 첫 번째 연속으로 먹을 수 있는 초밥의 가짓 수
		int result = cnt;

		for (int i = 0, end = n-k; i < end; i++) {
			if (sushi[i] == sushi[i + k]) { // i번째로 먹은 스시랑 i+k번째 먹은 스시가 같다면 continue;
				continue;
			}

			check[sushi[i]]--;
			check[sushi[i+k]]++;

			if (check[sushi[i]] == 0) { // i번째 초밥이 중복되지 않았을때 cnt--
				cnt--;
			}
			if (check[sushi[i+k]] == 1) { // i+k번째 초밥이 중복되지 않았을때 cnt++
				cnt++;
			}

			result = Math.max(result, cnt);
		}
		
		// 회전 초밥이기에 마지막 index가 n-k ~ n-1 사이의 초밥들을 처음 먹었을때 연속된 경우의 수도 생각해야됨
		for (int i = n-k, j = 0, end = n-1; i < end; i++, j++) {
			if (sushi[i] == sushi[j]) { // i번째로 먹은 스시랑 i+k번째 먹은 스시가 같다면 continue;
				continue;
			}

			check[sushi[i]]--;
			check[sushi[j]]++;

			if (check[sushi[i]] == 0) { // i번째 초밥이 중복되지 않았을때 cnt--
				cnt--;
			}
			if (check[sushi[j]] == 1) { // i+k번째 초밥이 중복되지 않았을때 cnt++
				cnt++;
			}

			result = Math.max(result, cnt);
		}

		System.out.println(result);

	}

}
