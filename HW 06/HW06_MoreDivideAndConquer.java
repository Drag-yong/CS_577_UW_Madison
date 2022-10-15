import java.util.Scanner;

public class HW06_MoreDivideAndConquer {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int i = 0; i < test_case; i++) {
			int n = sc.nextInt();

			// arr[0] = q list
			// arr[1] = p list
			// arr[0 or 1][value] = index;
			// indices are from 1 to n, not 0 to n - 1
			int[][] arr = new int[2][1000001];
			for (int j = 1; j <= n; j++)
				arr[0][sc.nextInt()] = j;
			for (int j = 1; j <= n; j++)
				arr[1][sc.nextInt()] = j;

			int[][] arrCompressed = new int[2][n];
			int order = 0;
			for (int j = 0; j < 1000000; j++)
				if (arr[0][j] != 0)
					arrCompressed[0][arr[0][j] - 1] = order++;

			order = 0;
			for (int j = 0; j < 1000000; j++)
				if (arr[1][j] != 0)
					arrCompressed[1][arr[1][j] - 1] = order++;

			int[] point = new int[n];
			for (int j = 0; j < n; j++)
				point[arrCompressed[0][j]] = arrCompressed[1][j];

			System.out.println(divideCount(point, 0, n - 1));
		}

		sc.close();
	}

	////////////////////
	// private method //
	////////////////////

	private static int divideCount(int[] arr, int lt, int rt) {
		if (lt >= rt)
			return 0;
		int m = (lt + rt) / 2;
		int output = divideCount(arr, lt, m);
		output += divideCount(arr, m + 1, rt);
		output += mergeCount(arr, lt, m, rt);
		return output;
	}

	private static int mergeCount(int[] arr, int lt, int m, int rt) {
		int n1 = m - lt + 1;
		int n2 = rt - m;
		int[] L = new int[n1];
		int[] R = new int[n2];

		for (int i = 0; i < n1; i++)
			L[i] = arr[lt + i];
		for (int i = 0; i < n2; i++)
			R[i] = arr[m + 1 + i];

		int l = 0;
		int r = 0;
		int k = lt;
		int output = 0;
		while (l < n1 && r < n2)
			if (L[l] <= R[r])
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
}
