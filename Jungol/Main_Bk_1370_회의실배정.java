package lec_0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_Bk_1370_회의실배정 {

	public static class Room implements Comparable<Room> {
		int rnum;
		int stime;
		int etime;

		public Room(int rnum, int stime, int etime) {
			super();
			this.rnum = rnum;
			this.stime = stime;
			this.etime = etime;
		}

		@Override
		public int compareTo(Room o) {

			int time = etime - o.etime;
			if (time == 0) {
				time = stime - o.stime;
			}
			return time;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(bf.readLine());
		ArrayList<Room> room = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			room.add(new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		ArrayList<Integer> cnums = new ArrayList<>(n);

		Collections.sort(room);

		cnums.add(room.get(0).rnum);
		int endTime = room.get(0).etime;
		for (int i = 1, end = room.size(); i < end; i++) {
			if (endTime <= room.get(i).stime) {
				cnums.add(room.get(i).rnum);
				endTime = room.get(i).etime;
			}
		}
		System.out.println(cnums.size());
		for (Integer num : cnums) {
			System.out.print(num + " ");
		}
	}
}
