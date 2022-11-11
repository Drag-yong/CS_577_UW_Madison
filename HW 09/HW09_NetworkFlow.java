import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HW09_NetworkFlow {
	static int N; // # nodes
	static int E; // # edges
	static int[][] graph; // It stores the capacity [from][to]
	static boolean[] visited; // Check if it is visited while using DFS

	public static void main(String[] args) throws NumberFormatException, IOException {
		// Standard input and output
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int t = 0; t < test_case; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // # nodes
			E = Integer.parseInt(st.nextToken()); // # edges
			graph = new int[N + 1][N + 1]; // It stores the capacity [from][to]

			// Making graph
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				graph[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] += Integer
						.parseInt(st.nextToken());
			}

			bw.append(fordFulkerson() + "\n");
		}

		bw.flush();

		br.close();
		bw.close();
	}

	/**
	 * Ford-Fulkerson algorithm which uses residual graph
	 * 
	 * @return max flow
	 */
	private static int fordFulkerson() {
		int output = 0; // This will store the max flow
		visited = new boolean[N + 1];
		visited[1] = true;

		// This will store the path that you passed if you get a true from finding the
		// path.
		ArrayList<Edge> path = new ArrayList<Edge>(N - 1);

		// Ford-Fulkerson algorithm
		while (stPath(1, path)) {
			int min = Integer.MAX_VALUE;

			for (Edge e : path)
				min = graph[e.u][e.v] < min ? graph[e.u][e.v] : min;

			output += min;

			for (Edge e : path) {
				graph[e.u][e.v] -= min;
				graph[e.v][e.u] += min;
			}

			visited = new boolean[N + 1];
			visited[1] = true;
			path.clear();
		}

		return output;
	}

	/**
	 * Find if there is a path or not by using DFS.
	 * 
	 * @param cur  from
	 * @param path store the edge from the source to sink
	 * @return true if there is a path from 1 (source) to n (sink)
	 */
	private static boolean stPath(int cur, ArrayList<Edge> path) {
		if (cur == N) // Meet the sink
			return true;

		for (int i = 1; i <= N; i++) {
			// Check if there is an edge from the cur to i
			if (graph[cur][i] > 0 && !visited[i]) {
				// add the path
				visited[i] = true;
				path.add(new Edge(cur, i));

				if (stPath(i, path))
					return true;

				// remove the path
				visited[i] = false;
				path.remove(path.size() - 1);
			}
		}

		return false;
	}

	private static class Edge {
		public int u;
		public int v;

		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
}