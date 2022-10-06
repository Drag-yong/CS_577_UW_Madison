import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class HW04_Greedy {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < T; i++) {
			int cacheLength = Integer.parseInt(br.readLine());
			int[] cache = new int[cacheLength];
//			LinkedList<Integer> cache = new LinkedList<>();
			int request = Integer.parseInt(br.readLine());
//			int[] pages = new int[request];
			ArrayList<Integer> pages = new ArrayList<>(request);

//			for (int j : cache)
//				j = Integer.MAX_VALUE;
			for (int j = 0; j < cacheLength; j++)
				cache[j] = Integer.MAX_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < request; j++) {
//				pages[j] = Integer.parseInt(st.nextToken());
				pages.add(Integer.parseInt(st.nextToken()));
			}

			int pageFault = 0;
			for (int j = 0; j < request; j++) {
				if (!isExistInTheCache(cache, pages, j, cacheLength)) {
					int index = maxIndex(cache, pages, j, cacheLength, request);
//					cache[index] = pages[j];
					cache[index] = pages.get(j);
					pageFault++;
				}
			}

			bw.append(pageFault + "\n");
		}

		bw.flush();

		br.close();
		bw.close();
	}

//	private static boolean isExistInTheCache(int[] cache, int[] pages, int pageIndex, int cacheLength) {
//		for (int i = 0; i < cacheLength; i++)
//			if (cache[i] == pages[pageIndex])
//				return true;
//		return false;
//	}
	private static boolean isExistInTheCache(int[] cache, ArrayList<Integer> pages, int pageIndex, int cacheLength) {
		for (int i = 0; i < cacheLength; i++)
			if (cache[i] == pages.get(pageIndex))
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
//	private static int maxIndex(int[] cache, int[] pages, int pageIndex, int cacheLength, int pagesLength) {
//		// It tells the length from the future paging
//		int[] cacheIndex = new int[cacheLength];
//
//		for (int i = 0; i < cacheLength; i++) {
//			if (cacheIndex[i] < pageIndex) {
//				cacheIndex[i] = Integer.MAX_VALUE; // This shows if there is no future call, it returns the maximum
//													// value
//				for (int j = pageIndex; j < pagesLength; j++)
//					if (cache[i] == pages[j]) {
//						cacheIndex[i] = j;
//						break;
//					}
//			}
//		}
//
//		int output = 0;
//		int max = cacheIndex[0];
//
//		for (int i = 1; i < cacheLength; i++) {
//			if (cacheIndex[i] > max) {
//				max = cacheIndex[i];
//				output = i;
//			}
//		}
//
//		return output;
//	}

	private static int maxIndex(int[] cache, ArrayList<Integer> pages, int pageIndex, int cacheLength,
			int pagesLength) {
		// It tells the length from the future paging
		int[] cacheIndex = new int[cacheLength];

		for (int i = 0; i < cacheLength; i++) {
			if (cacheIndex[i] < pageIndex) {
				cacheIndex[i] = Integer.MAX_VALUE; // This shows if there is no future call, it returns the maximum
													// value
				for (int j = pageIndex; j < pagesLength; j++)
					if (cache[i] == pages.get(j)) {
						cacheIndex[i] = j;
						break;
					}
			}
		}

		int output = 0;
		int max = cacheIndex[0];

		for (int i = 1; i < cacheLength; i++) {
			if (cacheIndex[i] > max) {
				max = cacheIndex[i];
				output = i;
			}
		}

		return output;
	}
}