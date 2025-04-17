public class ExtendedAVLNode extends AVLNode {
    private int subtreeKeyCount;

    public ExtendedAVLNode(int key) {
        super(key);
        subtreeKeyCount = 1;
    }

    public int getSubtreeKeyCount() {
        return subtreeKeyCount;
    }
    
    public void updateSubtreeKeyCount() {
        int count = 1;
        
        if (getLeft() != null) {
            count += ((ExtendedAVLNode)getLeft()).getSubtreeKeyCount();
        }
        
        if (getRight() != null) {
            count += ((ExtendedAVLNode)getRight()).getSubtreeKeyCount();
        }
        
        subtreeKeyCount = count;
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
