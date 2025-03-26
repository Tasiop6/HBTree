package hbtrees;

public class HBTree {
    HBTreeNode root;

    public HBTree() {
        this.root = null;
    }

    public HBTreeNode getRoot() {
        return root;
    }

    public void setRoot(HBTreeNode root) {
        this.root = root;
    }

    public boolean find(int key) {
        return find(root, key);
    }

    public boolean find(HBTreeNode node, int key) {
        if (node == null) {
            return false;
        }
        if (node.getKey() == key) {
            return true;
        }
        return key < node.getKey()
                ? find(node.getLeft(), key)
                : find(node.getRight(), key);
    }

}
