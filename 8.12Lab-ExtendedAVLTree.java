public class ExtendedAVLTree extends AVLTree {
    
    @Override
    protected BSTNode makeNewNode(int key) {
        return new ExtendedAVLNode(key);
    }
    
    @Override
    protected void insertNode(BSTNode node) {
        super.insertNode(node);
        
        BSTNode current = node;
        while (current != null) {
            ((ExtendedAVLNode)current).updateSubtreeKeyCount();
            current = current.getParent();
        }
    }
    
    @Override
    protected boolean removeNode(BSTNode nodeToRemove) {
        BSTNode parent = nodeToRemove.getParent();
        
        boolean result = super.removeNode(nodeToRemove);
        
        if (result) {
            if (parent != null) {
                BSTNode current = parent;
                while (current != null) {
                    ((ExtendedAVLNode)current).updateSubtreeKeyCount();
                    current = current.getParent();
                }
            }
            else if (getRoot() != null) {
                updateSubtreeCountsInTree((ExtendedAVLNode)getRoot());
            }
        }
        
        return result;
    }
    
    private void updateSubtreeCountsInTree(ExtendedAVLNode node) {
        if (node == null) {
            return;
        }
        
        if (node.getLeft() != null) {
            updateSubtreeCountsInTree((ExtendedAVLNode)node.getLeft());
        }
        
        if (node.getRight() != null) {
            updateSubtreeCountsInTree((ExtendedAVLNode)node.getRight());
        }
        
        node.updateSubtreeKeyCount();
    }
    
    @Override
    public int getNthKey(int n) {
        if (n < 0 || getRoot() == null || n >= ((ExtendedAVLNode)getRoot()).getSubtreeKeyCount()) {
            throw new IllegalArgumentException("Invalid index: " + n);
        }
        
        return findNthKey(n, (ExtendedAVLNode)getRoot());
    }
    
    private int findNthKey(int n, ExtendedAVLNode node) {
        int leftSize = 0;
        if (node.getLeft() != null) {
            leftSize = ((ExtendedAVLNode)node.getLeft()).getSubtreeKeyCount();
        }
        
        if (n == leftSize) {
            return node.getKey();
        }
        else if (n < leftSize) {
            return findNthKey(n, (ExtendedAVLNode)node.getLeft());
        }
        else {
            return findNthKey(n - leftSize - 1, (ExtendedAVLNode)node.getRight());
        }
    }
}
