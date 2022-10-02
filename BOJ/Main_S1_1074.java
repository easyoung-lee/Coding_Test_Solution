import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_1074 {

	static int n, x, y, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, n);

		if (x == 0 && y == 0) {
			System.out.println(0);
		} else {
			dfs(x, y, size);
			System.out.println(cnt);
		}
	}

	private static void dfs(int i, int j, int size) {
		if (size == 1) {
			return;
		}
		int ns = size / 2;
		
		if (i < ns && j < ns) {
			dfs(i, j, ns);
		} else if (i < ns && j >= ns) {
			cnt += (int) Math.pow(size, 2) / 4;
			dfs(i, j - ns, ns);
		} else if (i >= ns && j < ns) {
			dfs(i - ns, j, ns);
			cnt += (int) Math.pow(size, 2) / 4 * 2;
		} else {
			dfs(i - ns, j - ns, ns);
			cnt += (int) Math.pow(size, 2) / 4 * 3;
		}
	}

}
