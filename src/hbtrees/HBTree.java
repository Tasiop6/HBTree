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

    public int getSize() {
        return (root != null) ? root.getWeight() : 0;
    }

    public int getMaxHeight() {
        return (root != null) ? root.getHeight() : 0;
    }

    public int getMinHeight() {
        return getMinHeight(root);
    }

    private int getMinHeight(HBTreeNode node) {
        if (node == null) return 0;
        if (node.getLeft() == null && node.getRight() == null) return 1;

        int left = (node.getLeft() != null) ? getMinHeight(node.getLeft()) : Integer.MAX_VALUE;
        int right = (node.getRight() != null) ? getMinHeight(node.getRight()) : Integer.MAX_VALUE;

        return 1 + Math.min(left, right);
    }

    public void printPreOrder() {
        printPreOrder(root);
        System.out.println();  // newline after all keys
    }

    private void printPreOrder(HBTreeNode node) {
        if (node == null){
            return;
        }
        System.out.print(node.getKey() + " ");
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
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

    public boolean insert(int key) {
        if (find(key)) {    // Duplicate check
            return false;
        }
        root = insert(root, key);   // Set insert in root (might return new root from balancing)
        return true;
    }

    private HBTreeNode insert(HBTreeNode node, int key) {
        if (node == null) {     // End of tree-return the node to set as child
            return new HBTreeNode(key);
        }

        if (key < node.getKey()) {
            node.left = insert(node.getLeft(), key);
        } else {
            node.right = insert(node.getRight(), key);
        }

        updateHeightAndWeight(node);  // Updating height and weight after insertion
        return balance(node);         // Balancing node if needed
    }

    private void updateHeightAndWeight(HBTreeNode node) {
        // Height(1 + max of child)
        int leftHeight = (node.getLeft() != null) ? node.getLeft().getHeight() : 0;
        int rightHeight = (node.getRight() != null) ? node.getRight().getHeight() : 0;
        node.height = 1 + Math.max(leftHeight, rightHeight);

        // Weight(1 + weights of children)
        int leftWeight = (node.getLeft() != null) ? node.getLeft().getWeight() : 0;
        int rightWeight = (node.getRight() != null) ? node.getRight().getWeight() : 0;
        node.weight = 1 + leftWeight + rightWeight;
    }

    private HBTreeNode balance(HBTreeNode node) {
        // Difference in height between left and right subtrees
        int balanceFactor = getBalanceFactor(node);

        // Max allowed imbalance based on the number of nodes in the subtree
        int allowedBalance = Math.max(1, (int) Math.floor(log2(node.getWeight())));

        // If the node is too heavy on the left side
        if (balanceFactor > allowedBalance) {
            // Left-Right case check: left child is right-heavy
            if (getBalanceFactor(node.getLeft()) < 0) {
                // Fixing left child first by rotating it left
                node.left = rotateLeft(node.getLeft());
            }
            // Then right rotation on the current node
            return rotateRight(node);
        }

        // If the node is too heavy on the right side
        if (-balanceFactor > allowedBalance) {
            // Right-Left case check: right child is left-heavy
            if (getBalanceFactor(node.getRight()) > 0) {
                // Fixing right child first by rotating it right
                node.right = rotateRight(node.getRight());
            }
            // Then left rotation on the current node
            return rotateLeft(node);
        }

        // Returning node if balanceFactor is within allowedBalance
        return node;
    }

    private HBTreeNode rotateRight(HBTreeNode node) {
        // Left child becomes the new root of this subtree
        HBTreeNode newRoot = node.getLeft();

        // The right child of the new root becomes the left child of the current node
        node.left = newRoot.getRight();

        // Current node to the right of the new root
        newRoot.right = node;

        // Height and weight after the rotation
        updateHeightAndWeight(node);
        updateHeightAndWeight(newRoot);

        return newRoot;
    }

    private HBTreeNode rotateLeft(HBTreeNode node) {
        // Right child becomes the new root of this subtree
        HBTreeNode newRoot = node.getRight();

        // The left child of the new root becomes the right child of the current node
        node.right = newRoot.getLeft();

        // Moving current node to the left of the new root
        newRoot.left = node;

        // Recalculating height and weight after the rotation
        updateHeightAndWeight(node);
        updateHeightAndWeight(newRoot);

        return newRoot;
    }

    private int getBalanceFactor(HBTreeNode node) {
        int leftHeight = (node.getLeft() != null) ? node.getLeft().getHeight() : 0;
        int rightHeight = (node.getRight() != null) ? node.getRight().getHeight() : 0;
        return leftHeight - rightHeight;
    }

    private double log2(int n) {
        return Math.log(n) / Math.log(2);
    }
}
