package Clases;

public class BTree {
    
    private BTreePag root;
    
    public BTree() {
        this.root = null;
    }
    
    public BTreePag getRoot() {
        return root;
    }
    
    public void setRoot(BTreePag root) {
        this.root = root;
    }

    public void insert(int key) {
        if (root == null) {
            root = new BTreePag(null);
            root.insert(key);
        } else {
            if (root.getElements() == root.getGrade()) {
                BTreePag newNodo=new BTreePag(null);
                newNodo.getChild()[0]=root;
                
                split(newNodo, 0, root);
                int i = 0;
                if (newNodo.getKey()[0] < key) {
                    i++;
                }
                insertLeaf(newNodo.getChild()[i], key);
                root=newNodo;
            } else {
                insertLeaf(root, key);
            }
        }
    }
    
    private void insertLeaf(BTreePag nodo, int key) {
        if (nodo.isLeaf()) {
            nodo.insert(key);
        } else {
            int i = nodo.getElements() - 1;
            while (i >= 0 && nodo.getKey()[i] > key) {
                i--;
            }
            i++;
            if (nodo.getChild()[i].getElements() == nodo.getGrade()) {
                
            }
        }
    }
    
    private void split(BTreePag parent, int i, BTreePag child) {
        BTreePag newNodo = new BTreePag(parent);
        for (int j = 0; j < parent.getGrade() / 2; j++) {
            newNodo.insert(child.getKey()[j + parent.getGrade() / 2]);
        }
        if (!child.isLeaf()) {
            for (int j = 0; j < child.getGrade() / 2; j++) {
                newNodo.getChild()[j] = child.getChild()[j + child.getGrade() / 2];
            }
        }
        parent.moveChild(i);
        parent.getChild()[i] = newNodo;
        parent.insert(child.getKey()[child.getGrade() / 2]);
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
