import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_S1_11286 {

	static class Pair implements Comparable<Pair> {
		int x, abs;

		public Pair(int x, int abs) {
			super();
			this.x = x;
			this.abs = abs;
		}

		@Override
		public int compareTo(Pair o) {

			if (abs == o.abs) {
				return x - o.x;
			}

			return abs - o.abs;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(bf.readLine());
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			int cmd = Integer.parseInt(bf.readLine());

			if (cmd == 0) {
				if (queue.isEmpty()) {
					sb.append(0);
				} else {
					Pair pair = queue.poll();
					sb.append(pair.x);
				}
				sb.append("\n");
			} else {
				queue.offer(new Pair(cmd, Math.abs(cmd)));
			}

		}
		System.out.println(sb);

	}

}