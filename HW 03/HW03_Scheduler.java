import java.util.Scanner;

public class HW03_Scheduler {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int testCase = sc.nextInt();

		for (int T = 0; T < testCase; T++) {
			int jobs = sc.nextInt();
			Schedule[] schedule = new Schedule[jobs];
			boolean[] conflictSchedule = new boolean[jobs];

			for (int i = 0; i < jobs; i++)
				schedule[i] = new Schedule(sc.nextInt(), sc.nextInt());

			// Sort the array by the fastest finishing time first
			MergeSort(schedule, 0, jobs - 1);

			int output = 0;

			for (int i = 0; i < jobs; i++) {
				if (!conflictSchedule[i]) {
					output++;
					conflictSchedule[i] = true;
					findConflict(i, schedule[i], schedule, conflictSchedule);
				}
			}

			System.out.println(output);
		}

		sc.close();
	}

	private static void findConflict(int index, Schedule s, Schedule[] schedules, boolean[] conflictSchedule) {
		for (int i = index; i < schedules.length; i++)
			if (!conflictSchedule[i])
				conflictSchedule[i] = schedules[i].isConflict(s);
	}

	/**
	 * This method will allow the array to be sorted by using merge sort This will
	 * divide an array by 2 and merge at last
	 * 
	 * @param arr An array to sort
	 * @param lt  Left point to sort
	 * @param rt  Right point to sort
	 */
	private static void MergeSort(Schedule[] arr, int lt, int rt) {
		if (lt >= rt)
			return;
		int m = lt + (rt - lt) / 2;
		MergeSort(arr, lt, m);
		MergeSort(arr, m + 1, rt);
		Merge(arr, lt, m, rt);
	}

	/**
	 * This method will merge the array by using merge sort algorithm.
	 * 
	 * @param arr An array to be sorted
	 * @param lt  Left point of being sorted
	 * @param m   Middle point of being sorted
	 * @param rt  Right point of being sorted
	 */
	private static void Merge(Schedule[] arr, int lt, int m, int rt) {
		int n1 = m - lt + 1;
		int n2 = rt - m;
		Schedule[] L = new Schedule[n1];
		Schedule[] R = new Schedule[n2];

		for (int l = 0; l < n1; l++)
			L[l] = arr[lt + l];
		for (int r = 0; r < n2; r++)
			R[r] = arr[m + 1 + r];

		int l, r;
		l = r = 0;
		int k = lt;
		while (l < n1 && r < n2)
			if (L[l].compareEnd(R[r]) < 0)
				arr[k++] = L[l++];
			else
				arr[k++] = R[r++];

		while (l < n1)
			arr[k++] = L[l++];

		while (r < n2)
			arr[k++] = R[r++];
	}

	/**
	 * This class is a schedule class which has a start point and an end point.
	 * 
	 * @author Yongsang Park
	 *
	 */
	static class Schedule {
		public int start, end;

		public Schedule(int start, int end) {
			this.start = start;
			this.end = end;
		}

		/**
		 * This method will see if this schedule and another schedule is on conflict or
		 * not. If this end point is less than another schedule's start point, or vice
		 * versa,
		 * 
		 * @param s another schedule
		 * @return true if it is conflict. Else, false
		 */
		public boolean isConflict(Schedule s) {
			return !(this.end <= s.start || this.start >= s.end);
		}

		public int compareStart(Schedule s) {
			if (this.start < s.start)
				return -1;
			else if (this.start > s.start)
				return 1;
			return 0;
		}

		public int compareEnd(Schedule s) {
			if (this.end < s.end)
				return -1;
			else if (this.end > s.end)
				return 1;
			return 0;
		}
	}
}
