package utils;

import hbtrees.HBTree;
import hbtrees.HBTreeNode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GraphvizExporter {

    public static void exportTree(HBTree tree, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("graph HBTreeGraph {");
            exportNode(writer, tree.getRoot()); // Recursively write nodes
            writer.println("}");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file '" + filename + "'");
        }
    }

    private static void exportNode(PrintWriter writer, HBTreeNode node) {
        if (node == null) return;

        int allowed = Math.max(1, (int) Math.floor(Math.log(node.getWeight()) / Math.log(2)));
        writer.printf("%d [label=\"%d (%d)\"];\n", node.getKey(), node.getKey(), allowed);

        if (node.getLeft() != null) {
            writer.printf("%d -- %d;\n", node.getKey(), node.getLeft().getKey());
            exportNode(writer, node.getLeft());
        }

        if (node.getRight() != null) {
            writer.printf("%d -- %d;\n", node.getKey(), node.getRight().getKey());
            exportNode(writer, node.getRight());
        }
    }
}
