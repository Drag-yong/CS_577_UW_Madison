import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.Comparator;

public class HW06_MoreDivideAndConquer {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//		Scanner sc = new Scanner(System.in);
//		int test_case = sc.nextInt();
		int test_case = Integer.parseInt(br.readLine());

		for (int i = 0; i < test_case; i++) {
//			int n = sc.nextInt();
			int n = Integer.parseInt(br.readLine());

			int[][] arr = new int[2][n];

			for (int j = 0; j < n; j++)
//				arr[0][j] = sc.nextInt();
				arr[0][j] = Integer.parseInt(br.readLine());
			for (int j = 0; j < n; j++)
//				arr[1][j] = sc.nextInt();
				arr[1][j] = Integer.parseInt(br.readLine());

			Point[] arrPoint = new Point[n];

			for (int j = 0; j < n; j++)
				arrPoint[j] = new Point(arr[0][j], arr[1][j]);

//			Arrays.sort(arrPoint, (p1, p2) -> p1.compareToX1(p2));
			mergeSort(arrPoint, 0, n - 1);

//			System.out.printf("%.0f\n", divideCount(arrPoint, 0, n - 1));
			bw.append(String.format("%.0f\n", divideCount(arrPoint, 0, n - 1)));
		}

//		sc.close();
		br.close();

		bw.flush();
		bw.close();
	}

	////////////////////
	// private method //
	////////////////////

	///////////////
	// mergeSort //
	///////////////

	private static void mergeSort(Point[] arr, int lt, int rt) {
		if (lt >= rt)
			return;
		int m = (lt + rt) / 2;
		mergeSort(arr, lt, m);
		mergeSort(arr, m + 1, rt);
		merge(arr, lt, m, rt);
	}

	private static void merge(Point[] arr, int lt, int m, int rt) {
		int n1 = m - lt + 1;
		int n2 = rt - m;
		Point[] L = new Point[n1];
		Point[] R = new Point[n2];

		for (int i = 0; i < n1; i++)
			L[i] = arr[lt + i];
		for (int i = 0; i < n2; i++)
			R[i] = arr[m + 1 + i];

		int l = 0;
		int r = 0;
		int k = lt;
		while (l < n1 && r < n2)
//			if (L[l].compareToX1(R[r]) <= 0)
			if (L[l].x1 < R[r].x1)
				arr[k++] = L[l++];
			else
				arr[k++] = R[r++];

		while (l < n1)
			arr[k++] = L[l++];
		while (r < n2)
			arr[k++] = R[r++];
	}

	/////////////////
	// merge count //
	/////////////////

	private static double divideCount(Point[] arr, int lt, int rt) {
		if (lt >= rt)
			return 0;
		int m = (lt + rt) / 2;
		double output = divideCount(arr, lt, m);
		output += divideCount(arr, m + 1, rt);
		output += mergeCount(arr, lt, m, rt);
		return output;
	}

	private static double mergeCount(Point[] arr, int lt, int m, int rt) {
		int n1 = m - lt + 1;
		int n2 = rt - m;
		Point[] L = new Point[n1];
		Point[] R = new Point[n2];

		for (int i = 0; i < n1; i++)
			L[i] = arr[lt + i];
		for (int i = 0; i < n2; i++)
			R[i] = arr[m + 1 + i];

		int l = 0;
		int r = 0;
		int k = lt;
		double output = 0;

		while (l < n1 && r < n2)
//			if (L[l].compareToX2(R[r]) <= 0)
			if (L[l].x2 <= R[r].x2)
				arr[k++] = L[l++];
			else {
				output += n1 - l;
				arr[k++] = R[r++];
			}

		while (l < n1)
			arr[k++] = L[l++];
		while (r < n2)
			arr[k++] = R[r++];

		return output;
	}

	///////////////////
	// private class //
	///////////////////

	private static class Point {
//		int x1, x2;
		public int x1, x2;

		public Point(int x1, int x2) {
			this.x1 = x1;
			this.x2 = x2;
		}

//		public int compareToX1(Point p) {
//			return this.x1 - p.x1;
//		}
//
//		public int compareToX2(Point p) {
//			return this.x2 - p.x2;
//		}
	}
}
