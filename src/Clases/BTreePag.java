package Clases;

import java.util.Arrays;

public class BTreePag {

    private int elements;
    private int son;
    private int grade;
    private BTreePag child[];
    private int key[];
    private boolean leaf;
    private BTreePag father;

    public BTreePag() {
        //this.father = father;
        this.grade = 3;
        this.son=0;
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
    
   public void insertOrder(int valor, BTreePag chaild){
       int i=0;
       while (this.key[i]>valor) {
           i++;
       }
       for (int j = elements-1; j >= i; j--) {
           key[j+1]=key[j];
       }
       for (int j = son-1; j >=i; j--) {
           child[j+1]=child[j];
       }
       key[i]=valor;
       child[i]=chaild;
       elements++;
       if (!leaf) {
           son++;
       }
   }
   
   public void delateKey(){
       for (int i = 0; i < elements-1; i++) {
           key[i]=key[i+1];
       }
       key[elements-1]=0;
       elements--;
   }

    public String to(){
        return (father==null)?"null":Arrays.toString(key);
    }

    public int getSon() {
        return son;
    }

    public void setSon(int son) {
        this.son = son;
    }
    
}
