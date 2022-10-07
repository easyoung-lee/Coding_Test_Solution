import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_S2_1927 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(bf.readLine());
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			int cmd = Integer.parseInt(bf.readLine());
			
			if(cmd == 0) {
				if(queue.isEmpty()) {
					sb.append(0);
				}else {
					sb.append(queue.poll());
				}
				sb.append("\n");
			}else {
				queue.offer(cmd);
			}
			
		}
		
		System.out.println(sb);
		
	}

}
