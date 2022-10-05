import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main_S1_1931 {

	static class Pair implements Comparable<Pair> {
		int s, e;

		public Pair(int s, int e) {
			super();
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(Main_S1_1931.Pair o) {
			if (e == o.e) {
				return s - o.s;
			}
			return e - o.e;
		}

	}

	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		ArrayList<Pair> list = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			list.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		
		int result = 1;
		Pair curPair = list.get(0);
		for (int i = 1; i < n; i++) {
			Pair pair = list.get(i);
			if(curPair.e <= pair.s) {
				result++;
				curPair = pair;
			}
		}
		
		System.out.println(result);

	}

}
