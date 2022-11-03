import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class HW08_DynamicProgramming2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int test_case = Integer.parseInt(br.readLine());

		for (int T = 0; T < test_case; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int itemSize = Integer.parseInt(st.nextToken());
			int capacity = Integer.parseInt(st.nextToken());

			int[][] items = new int[itemSize + 1][2];

			for (int i = 1; i <= itemSize; i++) {
				st = new StringTokenizer(br.readLine());
				items[i][0] = Integer.parseInt(st.nextToken()); // weight
				items[i][1] = Integer.parseInt(st.nextToken()); // value
			}

			int[][] knapsack = new int[itemSize + 1][capacity + 1];

			for (int i = 1; i <= itemSize; i++) // item number
				for (int j = 1; j <= capacity; j++) // capacity
					knapsack[i][j] = j - items[i][0] >= 0
							? Math.max(knapsack[i - 1][j - items[i][0]] + items[i][1], knapsack[i - 1][j])
							: knapsack[i - 1][j];

			bw.append(knapsack[itemSize][capacity] + "\n");
		}

		bw.flush();

		br.close();
		bw.close();
	}
}
