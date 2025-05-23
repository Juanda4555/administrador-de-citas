package Clases;

import java.util.Arrays;

public class BTreePag {

    private int elements;
    private int grade;
    private BTreePag child[];
    private int key[];
    private boolean leaf;
    private BTreePag father;

    public BTreePag(BTreePag father) {
        this.father = father;
        this.grade = 3;
        this.elements = 0;
        this.child = new BTreePag[grade];
        this.key = new int[grade];
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void insert(int key) {
        int i = elements - 1;
        while (i >= 0 && this.key[i] > key) {
            this.key[i + 1] = this.key[i];
            i--;
        }
        this.key[i + 1] = key;
        this.elements++;
    }
    
    public boolean search(int date){
        for (int i = 0; i < key.length; i++) {
            if (key[i]==date) {
                return true;
            }
        }
        return false;
    }
    
    public void dalatChild(BTreePag nodo){
        for (int i = 0; i < child.length; i++) {
            child[i]=null;
        }
    }
    
    public void moveChild(int position){
        if (child[position]!=null) {
            int positionChild=elements-1;
            while (positionChild!=position && positionChild>=0) {
                child[positionChild+1]=child[positionChild];
                positionChild--;
            }
        }
    }
    
    public void copyKeys(BTreePag newArry, int positionStart, int positionFinal){
        for (int i = positionStart; i < positionFinal; i++) {
            insert(newArry.getKey()[i]);
            child[i]=newArry.getChild()[i];
        }
    }
    
    public void copyChild(BTreePag newArry, int positionStart, int positionFinal){
        for (int i = positionStart; i < positionFinal; i++) {
            child[i]=newArry.getChild()[i];
        }
    }
    
    public int position(BTreePag nodo, int elemento){
        for (int i = 0; i < nodo.getKey().length; i++) {
            if (nodo.getKey()[i]==elemento) {
                return i;
            }
        }
        return -1;
    }

    public String to(){
        return (father==null)?"null":Arrays.toString(key);
    }
    
}
