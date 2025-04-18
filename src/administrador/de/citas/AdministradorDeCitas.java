package administrador.de.citas;

import Clases.BTree;

public class AdministradorDeCitas {

    public static void main(String[] args) {
        BTree tree = new BTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(6);
        tree.insert(12);
       tree.insert(30);
        tree.insert(33);
        tree.insert(35);
        tree.insert(7);
        tree.insert(17);
        tree.printTree(tree.getRoot());
    }

}
