import java.util.*;

public class BSTChecker {
    public static Node checkBSTValidity(Node rootNode) {
        HashSet<Node> ancestors = new HashSet<>();
        return checkBSTValidityHelper(rootNode, Integer.MIN_VALUE, Integer.MAX_VALUE, ancestors);
    }
    
    private static Node checkBSTValidityHelper(Node node, int minVal, int maxVal, Set<Node> ancestors) {
        if (node == null) {
            return null;
        }
        
        if (node.key <= minVal || node.key >= maxVal) {
            return node;
        }
        
        ancestors.add(node);
        
        if (node.left != null && ancestors.contains(node.left)) {
            return node;  
        }
        
        if (node.right != null && ancestors.contains(node.right)) {
            return node; 
        }
        
        Node leftViolation = checkBSTValidityHelper(node.left, minVal, node.key, ancestors);
        if (leftViolation != null) {
            return leftViolation;
        }
        
        Node rightViolation = checkBSTValidityHelper(node.right, node.key, maxVal, ancestors);
        if (rightViolation != null) {
            return rightViolation;
        }
        
        ancestors.remove(node);
        
        return null;
    }
}
