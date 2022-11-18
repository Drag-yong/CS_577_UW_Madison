import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class HW10_MoreNF {
	private static int fordFulkerson(Edge[][] graph) {
		int source = 0;
		int sink = graph.length - 1;
		int flow = 0;
		List<Edge> path = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		visited.add(source);

		while (dfs(graph, source, sink, path, visited)) {
			int min = Integer.MAX_VALUE;
			for (Edge e : path) {
				if (e.capacity < min) {
					min = e.capacity;
				}
			}

			flow += min;
			for (Edge e : path) {
				graph[e.u][e.v].capacity -= min;
				graph[e.v][e.u].capacity += min;
			}

			path.clear();
			visited.clear();
			visited.add(source);
		}
		return flow;
	}

	private static boolean dfs(Edge[][] graph, int curr, int sink, List<Edge> path, Set<Integer> visited) {
		if (curr == sink) {
			return true;
		}

		for (int i = 0; i <= sink; i++) {
			if (!visited.contains(i) && graph[curr][i] != null && graph[curr][i].capacity > 0) {
				visited.add(i);
				path.add(new Edge(curr, i, graph[curr][i].capacity));
				boolean result = dfs(graph, i, sink, path, visited);
				if (result) {
					return true;
				}
				path.remove(path.size() - 1);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {

			int m = sc.nextInt();
			int n = sc.nextInt();
			int q = sc.nextInt();
			Edge[][] graph = new Edge[m + n + 2][m + n + 2];
			for (int j = 0; j < q; j++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				graph[u][v + m] = new Edge(u, v + m, 1);
				graph[v + m][u] = new Edge(v + m, u, 0);
			}

			for (int j = 1; j <= m; j++) {
				graph[0][j] = new Edge(0, j, 1);
				graph[j][0] = new Edge(j, 0, 0);
			}

			for (int j = m + 1; j <= n + m; j++) {
				graph[j][n + m + 1] = new Edge(j, n + m + 1, 1);
				graph[n + m + 1][j] = new Edge(n + m + 1, j, 0);
			}

			int max = fordFulkerson(graph);
			boolean isPerfect = (max == m) && (m == n);
			System.out.println(max + " " + (isPerfect ? "Y" : "N"));
		}

		sc.close();
	}

	private static class Edge {
		public int u;
		public int v;
		public int capacity;

		public Edge(int from, int to, int capacity) {
			this.u = from;
			this.v = to;
			this.capacity = capacity;
		}
	}
}
