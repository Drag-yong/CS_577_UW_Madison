import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HW09_NetworkFlow {
	private static int fordFulkerson(Edge[][] graph) {
		int source = 1;
		int target = graph.length - 1;
		int flow = 0;
		List<Edge> path = new ArrayList<>();
		boolean[] visited = new boolean[target + 1];
		visited[source] = true;

		while (dfs(graph, source, target, path, visited)) {
			int min = Integer.MAX_VALUE;
			for (Edge e : path) {
				if (e.capacity < min) {
					min = e.capacity;
				}
			}
			flow += min;

			for (Edge e : path) {
				graph[e.n1][e.n2].capacity -= min;
				graph[e.n2][e.n1].capacity += min;
			}

			path.clear();
			visited = new boolean[target + 1];
			visited[source] = true;
		}

		return flow;
	}

	private static boolean dfs(Edge[][] graph, int curr, int target, List<Edge> path, boolean[] visited) {
		if (curr == target) {
			return true;
		}

		for (int i = 1; i <= target; i++) {
			if (!visited[i] && graph[curr][i] != null && graph[curr][i].capacity > 0) {
				visited[i] = true;
				path.add(new Edge(curr, i, graph[curr][i].capacity));
				boolean result = dfs(graph, i, target, path, visited);

				if (result) {
					return true;
				}

				path.remove(path.size() - 1);
			}
		}
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int test_case = Integer.parseInt(br.readLine());
		for (int t = 0; t < test_case; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			Edge[][] graph = new Edge[n + 1][n + 1];
			for (int j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				int capacity = Integer.parseInt(st.nextToken());
				if (graph[n1][n2] == null) {
					graph[n1][n2] = new Edge(n1, n2, capacity);
					graph[n2][n1] = new Edge(n1, n2, capacity);
				} else {
					graph[n1][n2].capacity += capacity;
					graph[n2][n1].capacity += capacity;
				}
			}

			bw.append(fordFulkerson(graph) + "\n");
		}

		bw.flush();
		br.close();
		bw.close();
	}

	private static class Edge {
		private final int n1; // start
		private final int n2; // end
		private int capacity;

		public Edge(int n1, int n2, int capacity) {
			this.n1 = n1;
			this.n2 = n2;
			this.capacity = capacity;
		}
	}
}