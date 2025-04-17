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
//        System.out.println("hijo "+nodo.to());
//        System.out.println("padre "+((nodo.getFather()==null)?"null":nodo.getFather().to()+""));
        if (nodo.getElements() == nodo.getGrade()) {
            if (nodo.getFather() == null) {

                BTreePag c = nodo;
                nodo = new BTreePag(null);
                nodo.insert(c.getKey()[nodo.getGrade() / 2]);
                nodo.getChild()[0] = new BTreePag(nodo);
                nodo.getChild()[1] = new BTreePag(nodo);
                for (int i = 0; i < c.getGrade() / 2; i++) {
                    nodo.getChild()[0].insert(c.getKey()[i]);
                }
                for (int i = (c.getGrade() / 2) + 1; i < c.getGrade(); i++) {
                    nodo.getChild()[1].insert(c.getKey()[i]);
                }
                nodo.setLeaf(false);

            } else {

                int keyAux = nodo.getKey()[nodo.getGrade() / 2];
                nodo.getFather().insert(keyAux);
                int index;

                for (index = 0; index < nodo.getFather().getElements(); index++) {
                    if (nodo.getFather().getKey()[index] == keyAux) {
                        break;
                    }
                }

                nodo.getFather().moveChild(index);

                if (nodo.getFather().getElements() == nodo.getFather().getGrade()) {
                    split(nodo.getFather());
                }

                nodo.getFather().getChild()[index + 1] = new BTreePag(nodo.getFather());

                for (int i = (nodo.getGrade() / 2) + 1; i < nodo.getGrade(); i++) {
                    nodo.getFather().getChild()[index + 1].insert(nodo.getKey()[i]);
                }

                BTreePag aux = nodo;
                nodo.getFather().getChild()[index] = new BTreePag(nodo.getFather());

                for (int i = 0; i < nodo.getGrade() / 2; i++) {
                    nodo.getFather().getChild()[index].insert(nodo.getKey()[i]);
                }

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
