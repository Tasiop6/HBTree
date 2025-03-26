package hbtrees;

public class HBTreeNode {
    int key;
    int height;
    int weight;
    HBTreeNode left;
    HBTreeNode right;

    public HBTreeNode(int key) {
        this.key = key;
        this.height = 1;
        this.weight = 1;
        this.left = null;
        this.right = null;
    }

    public int getKey() {
        return key;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public HBTreeNode getLeft() {
        return left;
    }

    public HBTreeNode getRight() {
        return right;
    }
}
