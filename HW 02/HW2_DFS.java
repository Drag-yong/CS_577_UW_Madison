import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class W2_DFS {
	private static HashMap<String, String[]> graph;
	private static HashMap<String, Boolean> visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt(); // Test case

		for (int i = 0; i < t; i++) {
			int n = sc.nextInt();
			graph = new HashMap<>(); // This will store the adjacency list
			visited = new HashMap<>(); // Check if the node is visited or not.
			ArrayList<String> nodes = new ArrayList<>(n); // Entire nodes
			String output = "";

			sc.nextLine();

			for (int j = 0; j < n; j++) {
				String key = sc.next();
				nodes.add(key);
				String[] value = sc.nextLine().trim().split(" ");
				graph.put(key, value);
				visited.put(key, false);
			}

			for (String s : nodes) {
				if (!visited.get(s))
					output += DFS(s, s) + " ";
			}

			System.out.println(output.trim());
		}

		sc.close();
	}

	/**
	 * 
	 * @param output Output String
	 * @param key    Current root node
	 * @return
	 */
	private static String DFS(String output, String key) {
		visited.put(key, true);
		for (int i = 0; i < graph.get(key).length; i++) {
			if (graph.get(key)[i].length() > 0 && !visited.get(graph.get(key)[i])) {
				visited.put(graph.get(key)[i], true);
				output = DFS(output + " " + graph.get(key)[i], graph.get(key)[i]);
			}
		}

		return output;
	}
}
