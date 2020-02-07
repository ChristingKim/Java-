import java.util.Comparator;

public class AVLTree<T extends Comparable<T>> {

    private AVLTreeNode<T> mRoot;

    class AVLTreeNode<T extends Comparable<T>>{
        T key;
        int height;
        AVLTreeNode<T> left;
        AVLTreeNode<T> right;

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right){
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public AVLTreeNode<T> getLeft() {
            return left;
        }

        public void setLeft(AVLTreeNode<T> left) {
            this.left = left;
        }

        public AVLTreeNode<T> getRight() {
            return right;
        }

        public void setRight(AVLTreeNode<T> right) {
            this.right = right;
        }
    }

    public AVLTree(){
        mRoot = null;
    }

    private int height(AVLTreeNode<T> tree){
        if(tree != null){
            return tree.height;
        }

        return 0;
    }

    public int height(){
        height(mRoot);
    }

    private int max(int a, int b){
        return a > b ? a : b;
    }

    private void preOrder(AVLTreeNode<T> tree){
        if(tree != null){
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder(){
        preOrder(mRoot);
    }

    private void inOrder(AVLTreeNode<T> tree){
        if(tree != null){
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder(){
        inOrder(mRoot);
    }

    private void postOrder(AVLTreeNode<T> tree){
        if(tree != null){
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    public void postOrder(){
        postOrder(mRoot);
    }

    private AVLTreeNode<T> search(AVLTreeNode<T> x, T key){

        if(x == null){
            return x;
        }

        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            return search(x.left, key);
        }else if(cmp > 0){
            return search(x.right, key);
        }else{
            return x;
        }
    }

    public AVLTreeNode<T> search(T key){
        return search(mRoot, key);
    }

    private AVLTreeNode<T> iterativeSearch(AVLTreeNode<T> x, T key){
        while(x != null){
            int cmp = key.compareTo(x.key);

            if(cmp < 0){
                x = x.left;
            }else if(cmp > 0){
                x = x.right;
            }else{
                return x;
            }
        }

        return x;
    }

    public AVLTreeNode<T> iterativeSearch(T key){
        return iterativeSearch(mRoot, key);
    }

    private AVLTreeNode<T> minimum(AVLTreeNode<T> tree){
        if(tree == null){
            return null;
        }

        while(tree.left != null){
            tree = tree.left;
        }

        return tree;
    }

    public T minimum(){
        AVLTreeNode<T> p = minimum(mRoot);
        if(p != null){
            return p.key;
        }
        return null;
    }

    private AVLTreeNode<T> maximum(AVLTreeNode<T> tree){
        if(tree == null){
            return null;
        }

        while(tree.right != null){
            tree = tree.right;
        }

        return tree;
    }

    public T maximum(){
        AVLTreeNode<T> p = maximum(mRoot);
        if(p != null){
            return p.key;
        }

        return null;
    }

    /**
     * LL:左左旋转
     */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2){
        AVLTreeNode<T> k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;

        return k1;
    }

    /**
     * RR:右右旋转
     */
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k1){
        AVLTreeNode<T> k2;

        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.left), height(k2.right)) + 1;

        return k2;
    }

    /**
     *LR:左右双旋转
     */
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> k3){

        k3.left = rightRightRotation(k3.left);

        return leftLeftRotation(k3);
    }

    /**
     * RL:右左双旋转
     */
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> k1){
        k1.right = leftLeftRotation(k1.right);

        return rightRightRotation(k1);
    }

    private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key){
        if(tree == null){
            tree = new AVLTreeNode<T>(key, null, null);
            if(tree == null){
                System.out.println("ERROR: create avltree node failed!");
                return null;
            }
        }else{
            int cmp = key.compareTo(tree.key);

            if(cmp < 0){
                tree.left = insert(tree.left, key);

                if(height(tree.left) - height(tree.right) == 2){
                    if(key.compareTo(tree.left.key) < 0){
                        tree = leftLeftRotation(tree);
                    }else{
                        tree = leftRightRotation(tree);
                    }
                }
            }else if(cmp > 0){
                tree.right = insert(tree.right, key);

                if(height(tree.right) - height(tree.left) == 2){
                    if(key.compareTo(tree.right.key) > 0){
                        tree = rightRightRotation(tree);
                    }else{
                        tree = rightLeftRotation(tree);
                    }
                }
            }else{
                System.out.print("添加失败：不允许添加相同的结点！");
            }
        }

        tree.height = max(height(tree.left), height(tree.right)) + 1;

        return tree;
    }

    public void insert(T key){
        mRoot = insert(mRoot, key);
    }

    private AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z){

        if(tree == null || z == null){
            return null;
        }

        int cmp = z.key.compareTo(tree.key);
        if(cmp < 0){
            tree.left = remove(tree.left, z);
            if(height(tree.right) - height(tree.left) == 2){
                AVLTreeNode<T> r = tree.right;
                if(height(r.left) > height(r.right)){
                    tree = rightLeftRotation(tree);
                }else{
                    tree = rightRightRotation(tree);
                }
            }
        }else if(cmp > 0){
            tree.right = remove(tree.right, z);
            if(height(tree.left) - height(tree.right) == 2){
                AVLTreeNode<T> l = tree.left;
                if(height(l.right) > height(l.left))
                    tree = leftRightRotation(tree);
                else
                    tree = leftLeftRotation(tree);
            }
        }else{
            if((tree.left != null) && (tree.right != null)){
                if(height(tree.left) > height(tree.right)){
                    AVLTreeNode<T> max = maximum(tree.left);
                    tree.key = max.key;
                    tree.left = remove(tree.left, max);
                }else{
                    AVLTreeNode<T> min = minimum(tree.right);
                    tree.key = min.key;
                    tree.right = remove(tree.right, min);
                }
            }else{
                AVLTreeNode<T> tmp = tree;
                tree = (tree.left != null) ? tree.left:tree.right;
                tmp = null;
            }
        }

        return tree;
    }

    public void remove(T key){
        AVLTreeNode<T> z;

        if((z = search(mRoot, key)) != null){
            mRoot = remove(mRoot, z);
        }
    }

    private void destroy(AVLTreeNode<T> tree){
        if(tree == null){
            return;
        }
        if(tree.left != null)
            destroy(tree.left);
        if(tree.right != null)
            destroy(tree.right);

        tree = null;
    }

    public void destroy(){
        destroy(mRoot);
    }

}
