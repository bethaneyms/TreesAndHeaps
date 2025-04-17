public class ExtendedRedBlackTree extends RedBlackTree {
    
    @Override
    protected BSTNode makeNewNode(int key) {
        return new ExtendedRBTNode(key);
    }
    
    private void updateSubtreeCountsUpToRoot(BSTNode node) {
        while (node != null) {
            ((ExtendedRBTNode) node).updateSubtreeKeyCount();
            node = node.getParent();
        }
    }
    
    @Override
    protected void insertNode(BSTNode node) {
        super.insertNode(node);
        updateSubtreeCountsUpToRoot(node);
    }
    
    @Override
    protected boolean removeNode(BSTNode node) {
        BSTNode parent = node.getParent();
        boolean result = super.removeNode(node);
        
        if (parent != null) {
            updateSubtreeCountsUpToRoot(parent);
        } else if (getRoot() != null) {
            ((ExtendedRBTNode) getRoot()).updateSubtreeKeyCount();
        }
        
        return result;
    }
    
    @Override
    protected BSTNode rotateLeft(BSTNode x) {
        BSTNode result = super.rotateLeft(x);
        
        if (x != null) {
            ((ExtendedRBTNode) x).updateSubtreeKeyCount();
        }
        
        BSTNode parent = x.getParent();
        if (parent != null) {
            ((ExtendedRBTNode) parent).updateSubtreeKeyCount();
        }
        
        return result;
    }
    
    @Override
    protected BSTNode rotateRight(BSTNode y) {
        BSTNode result = super.rotateRight(y);
        
        if (y != null) {
            ((ExtendedRBTNode) y).updateSubtreeKeyCount();
        }
        
        BSTNode parent = y.getParent();
        if (parent != null) {
            ((ExtendedRBTNode) parent).updateSubtreeKeyCount();
        }
        
        return result;
    }
    
    @Override
    public int getNthKey(int n) {
        if (n < 0 || getRoot() == null || n >= ((ExtendedRBTNode) getRoot()).getSubtreeKeyCount()) {
            throw new IllegalArgumentException("Invalid value for n");
        }
        
        return findNthKey((ExtendedRBTNode) getRoot(), n);
    }
    
    private int findNthKey(ExtendedRBTNode node, int n) {
        if (node == null) {
            return -1;
        }
        
        int leftSize = 0;
        if (node.getLeft() != null) {
            leftSize = ((ExtendedRBTNode) node.getLeft()).getSubtreeKeyCount();
        }
        
        if (n < leftSize) {
            return findNthKey((ExtendedRBTNode) node.getLeft(), n);
        } else if (n == leftSize) {
            return node.getKey();
        } else {
            return findNthKey((ExtendedRBTNode) node.getRight(), n - leftSize - 1);
        }
    }
}
