import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class HW05_DivideAndConquer {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++) {
			int length = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] ranking = new int[length];
			for (int i = 0; i < length; i++) {
				ranking[i] = Integer.parseInt(st.nextToken());
			}

			bw.append(divide(ranking, 0, length - 1) + "\n");
		}

		bw.flush();

		br.close();
		bw.close();
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
