import java.util.Scanner;

public class HW05_DivideAndConquer {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int test_case = 0; test_case < T; test_case++) {
			int length = sc.nextInt();
			int[] ranking = new int[length];
			for (int i = 0; i < length; i++) {
				ranking[i] = sc.nextInt();
			}

			System.out.println(divide(ranking, 0, length - 1));
		}

		sc.close();
	}

	private static int divide(int[] arr, int left, int right) {
		if (left >= right)
			return 0;

		int output = 0;
		int middle = (left + right) / 2;

		output += divide(arr, left, middle);
		output += divide(arr, middle + 1, right);
		output += mergeCount(arr, left, middle, right);
		return output;
	}

	private static int mergeCount(int[] arr, int left, int middle, int right) {
		int n1 = middle - left + 1;
		int n2 = right - middle;
		int[] L = new int[n1];
		int[] R = new int[n2];

		for (int l = 0; l < n1; l++)
			L[l] = arr[left + l];
		for (int r = 0; r < n2; r++)
			R[r] = arr[middle + 1 + r];

		int l, r, output;
		l = r = output = 0;
		int k = left;
		while (l < n1 && r < n2)
			if (L[l] <= R[r])
				arr[k++] = L[l++];
//			else if (L[l] == R[r]) {
//				arr[k++] = L[l++];
//				arr[k++] = R[r++];
//			}
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
