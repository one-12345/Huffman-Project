import java.util.*;
import java.io.*;

class Node {
	String symbol;
	int freq;
	Node left;
	Node right;
	String binary;

	public Node (String symbol, int freq, Node left, Node right, String binary) {
		this.symbol = symbol;
		this.freq = freq;
		this.left = left;
		this.right = right;
		this.binary = binary;
	}
}
public class BuildTreeAndLookupTable {
	public static void main (String[] args) throws FileNotFoundException {
        String filename = args[0];
        Scanner scan = new Scanner(new FileReader(filename));

        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<Integer> frequencies = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            symbols.add(line.charAt(0) + "");
            frequencies.add(Integer.parseInt(line.substring(2)));
        }
        symbols.add("EOF");
        frequencies.add(1);

		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

		for (int i = 0; i < symbols.size(); i++) {
			Node temp = new Node(symbols.get(i), frequencies.get(i), null, null, "");
			pq.add(temp);
		}
		
		Node root = new Node("null", 0, null, null, "");
		while (true) {
			Node n1 = pq.poll();
			Node n2 = pq.poll();

			if (pq.isEmpty()) {
				root.left = n1;
				root.right = n2;
                root.freq = n1.freq + n2.freq;
                System.out.println(root.symbol + " and " + root.freq + " and " + root.left.symbol + " and " + root.left.freq + " and " + root.right.symbol + " and " + root.right.freq);
				break;
			}

			Node parent = new Node("null", n1.freq + n2.freq, n1, n2, "");
            System.out.println(parent.symbol + " and " + parent.freq + " and " + parent.left.symbol + " and " + parent.left.freq + " and " + parent.right.symbol + " and " + parent.right.freq);
			pq.add(parent);
		}

        ArrayList<String> lookupTableSymbols = new ArrayList<>();
        ArrayList<String> lookupTableBinaryStrings = new ArrayList<>();

		Queue<Node> bfs = new LinkedList<>();
		bfs.add(root);
		while (!bfs.isEmpty()) {
			Node current = bfs.poll();
			if (current.symbol == "null") {
				current.left.binary = current.binary + "0";
				current.right.binary = current.binary + "1";
				bfs.add(current.left);
				bfs.add(current.right);
			}
			else {
				System.out.println(current.symbol);
                lookupTableSymbols.add(current.symbol);
                lookupTableBinaryStrings.add(current.binary);
			}
		}
		for (int i = 0; i < lookupTableSymbols.size(); i++) {
			System.out.println(lookupTableSymbols.get(i) + " " + lookupTableBinaryStrings.get(i));
		}
		scan.close();
	}
}