package Clases;

public class BTreePag {

    private int elements;
    private BTreePag child[];
    private int key[];
    private boolean leaf;
    private BTreePag father;

    public BTreePag(BTreePag father) {
        this.father = father;
        this.elements = 0;
        this.child = new BTreePag[5];
        this.key = new int[5];
        this.leaf = true;
    }

    public int getElements() {
        return elements;
    }

    public void setElements(int elements) {
        this.elements = elements;
    }

    public BTreePag[] getChild() {
        return child;
    }

    public void setChild(BTreePag[] child) {
        this.child = child;
    }

    public int[] getKey() {
        return key;
    }

    public void setKey(int[] key) {
        this.key = key;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public BTreePag getFather() {
        return father;
    }

    public void setFather(BTreePag father) {
        this.father = father;
    }
    
    public void into(int key){
        int i=elements-1;
        while (i>0 && this.key[i]>key) {
            this.key[i+1]=this.key[i];
            i--;
        }
        this.key[i +1]=key;
        this.elements++;
    }

}
