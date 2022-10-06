import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class HW04_Greedy {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < T; i++) {
			int[] cache = new int[Integer.parseInt(br.readLine())];
			int request = Integer.parseInt(br.readLine());
			int[] pages = new int[request];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < request; j++) {
				pages[j] = Integer.parseInt(st.nextToken());
			}

			int pageFault = 0;
			for (int j = 0; j < request; j++) {
				int index = maxIndex(cache, pages, j);

				if (!isExistInTheCache(cache, pages, j)) {
					cache[index] = pages[j];
					pageFault++;
				}
			}

			bw.append(pageFault + "\n");
		}

		bw.flush();
		
		br.close();
		bw.close();
	}

	private static boolean isExistInTheCache(int[] cache, int[] pages, int pageIndex) {
		for (int i = 0; i < cache.length; i++)
			if (cache[i] == pages[pageIndex])
				return true;
		return false;
	}

	/**
	 * It tells which cache index has the furthest in the future paging
	 * 
	 * @param cache     cache
	 * @param pages     future requests
	 * @param pageIndex showing the current index of the page
	 * @return
	 */
	private static int maxIndex(int[] cache, int[] pages, int pageIndex) {
		int[] cacheIndex = distance(cache, pages, pageIndex);

		int output = 0;
		int max = cacheIndex[0];

		for (int i = 1; i < cache.length; i++) {
			if (cacheIndex[i] > max) {
				max = cacheIndex[i];
				output = i;
			}
		}

		return output;
	}

	/**
	 * It tells the length from the future paging
	 * 
	 * @param cache
	 * @param pages     future requests
	 * @param pageIndex showing the current index of the page
	 * @return int array which stores the index of page which is the same value with
	 *         the cache
	 */
	private static int[] distance(int[] cache, int[] pages, int pageIndex) {
		int[] output = new int[cache.length];

		for (int i = 0; i < cache.length; i++) {
			output[i] = Integer.MAX_VALUE; // This shows if there is no future call, it returns the maximum value
			for (int j = pageIndex; j < pages.length; j++)
				if (cache[i] == pages[j]) {
					output[i] = j;
					break;
				}
		}
		return output;
	}
}
