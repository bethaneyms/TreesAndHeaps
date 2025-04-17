public class ExtendedRBTNode extends RBTNode {
    private int subtreeKeyCount;
    
    public ExtendedRBTNode(int key) {
        super(key);
        subtreeKeyCount = 1;
    }
    
    @Override
    public int getSubtreeKeyCount() {
        return subtreeKeyCount;
    }
    
    public void updateSubtreeKeyCount() {
        subtreeKeyCount = 1;
        
        if (getLeft() != null) {
            ExtendedRBTNode leftChild = (ExtendedRBTNode) getLeft();
            subtreeKeyCount += leftChild.getSubtreeKeyCount();
        }
        
        if (getRight() != null) {
            ExtendedRBTNode rightChild = (ExtendedRBTNode) getRight();
            subtreeKeyCount += rightChild.getSubtreeKeyCount();
        }
    }
    
    @Override
    public void setLeft(BSTNode left) {
        super.setLeft(left);
        updateSubtreeKeyCount();
    }
    
    @Override
    public void setRight(BSTNode right) {
        super.setRight(right);
        updateSubtreeKeyCount();
    }
}
