package Clases;

public class BTree {

    private BTreePag root;

    public BTree() {
        this.root = new BTreePag(null);
    }

    public BTreePag getRoot() {
        return root;
    }

    public void setRoot(BTreePag root) {
        this.root = root;
    }

    public void insert(int key) {
        this.root = BTree.this.insert(root, key);
    }

    private BTreePag insert(BTreePag nodo, int key) {
        if (nodo.isLeaf()) {
            nodo.insert(key);
        } else {
            boolean found = false;
            for (int i = 0; i <= nodo.getElements() - 1; i++) {
                if (key < nodo.getKey()[i]) {
                    found = true;
                    BTree.this.insert(nodo.getChild()[i], key);
                    break;
                }
            }
            if (!found) {
                insert(nodo.getChild()[nodo.getElements()], key);
            }
        }
        return split(nodo);
    }

    private BTreePag split(BTreePag nodo) {
        if (nodo.getElements() == nodo.getGrade()) {
            if (nodo.getFather() == null) {
                BTreePag c = nodo;
                nodo = new BTreePag(null);
                nodo.insert(c.getKey()[c.getGrade() / 2]);
                nodo.getChild()[0] = new BTreePag(nodo);
                nodo.getChild()[1] = new BTreePag(nodo);
                nodo.getChild()[0].copyKeys(c, 0, c.getGrade() / 2);
                nodo.getChild()[1].copyKeys(c, (c.getGrade() / 2) + 1, c.getGrade());
                nodo.getChild()[0].copyChild(c, 0, c.getGrade() / 2);
                nodo.getChild()[1].copyChild(c, (c.getGrade() / 2) + 1, c.getGrade());
                nodo.setLeaf(false);
            } else {
                int elem = nodo.getKey()[nodo.getGrade() / 2];
                nodo.getFather().insert(elem);
                int index = nodo.getFather().position(nodo.getFather(), elem);
                BTreePag izq = new BTreePag(nodo.getFather());
                BTreePag der = new BTreePag(nodo.getFather());
                izq.copyKeys(nodo, 0, nodo.getGrade() / 2);
                izq.copyChild(nodo, 0, nodo.getGrade() / 2);
                der.copyKeys(nodo, (nodo.getGrade() / 2) + 1, nodo.getGrade());
                der.copyChild(nodo, (nodo.getGrade() / 2) + 1, nodo.getGrade());
                if (nodo.getFather().getElements() == nodo.getFather().getGrade()) {
                    split(nodo.getFather());
                }
                nodo.getFather().getChild()[index+1] = der;
                nodo.getFather().getChild()[index] = izq;
            }
        }
        return nodo;
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
