package Clases;

import java.util.Arrays;

public class BTree {

    private BTreePag root;

    public BTree() {
        this.root = new BTreePag();
    }

    public BTreePag getRoot() {
        return root;
    }

    public void setRoot(BTreePag root) {
        this.root = root;
    }
    public void insert(int key){
        insert(root, key);
    }

    public BTreePag insert(BTreePag nodo, int key) {
        if (nodo.isLeaf()) {
            nodo.insertOrder(key, null);
        } else {
            boolean found = false;
            for (int i = 0; i <= nodo.getElements() - 1; i++) {
                if (key < nodo.getKey()[i]) {
                    found = true;
                    updatePag(nodo, insert(nodo.getChild()[i], key));
                    break;
                }
            }
            if (!found) {
                updatePag(nodo, insert(nodo.getChild()[nodo.getElements()], key));
            }
        }
        if (nodo.getElements() == nodo.getGrade()) {
            return split(nodo);
        } else {
            return null;
        }
    }

    public BTreePag split(BTreePag nodo) {
        BTreePag newNodo = new BTreePag();
        int j = 0;
        for (int i = (nodo.getGrade() / 2) - 1; i < nodo.getGrade(); i++) {
            newNodo.insertOrder(i, newNodo);
            nodo.getKey()[i] = 0;
            if (!nodo.isLeaf()) {
                newNodo.getChild()[j] = nodo.getChild()[i];
                nodo.getChild()[i] = null;
                nodo.setSon(nodo.getSon() + 1);
            }
        }
        return newNodo;
    }

    private void updatePag(BTreePag nodo, BTreePag child) {
        int pivo = child.getKey()[0];
        nodo.insertOrder(pivo, child);
        child.delateKey();
    }

    public void printTree(BTreePag nodo) {
        if (nodo != null) {
            for (int i = 0; i < nodo.getElements(); i++) {
                // Recorre los hijos antes de imprimir una clave
                if (!nodo.isLeaf()) {
                    printTree(nodo.getChild()[i]);
                }
                System.out.print(nodo.getKey()[i] + " ");
            }
            // Imprime el último hijo si el nodo no es hoja
            if (!nodo.isLeaf()) {
                printTree(nodo.getChild()[nodo.getElements()]);
            }
        }
    }
}
