class AVLTree {
    Node root;

    class Node {
        int data;
        Node left, right;
        int height;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
            this.height = 1;
        }
    }

    int altura(Node no) {
        if (no == null) {
            return 0;
        }
        return no.height;
    }

    int getBalance(Node no) {
        if (no == null) {
            return 0;
        }
        return altura(no.left) - altura(no.right);
    }

    Node rotacaoDireita(Node y) {
        if (y == null) {
            return y;
        }
        Node x = y.left;
        if (x == null) {
            return y;
        }
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(altura(y.left), altura(y.right));
        x.height = 1 + Math.max(altura(x.left), altura(x.right));

        return x;
    }

    Node rotacaoEsquerda(Node x) {
        if (x == null) {
            return x;
        }
        Node y = x.right;
        if (y == null) {
            return x;
        }
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(altura(x.left), altura(x.right));
        y.height = 1 + Math.max(altura(y.left), altura(y.right));

        return y;
    }

    Node inserir(Node no, int dado) {
        if (no == null) {
            return new Node(dado);
        }

        if (dado < no.data) {
            no.left = inserir(no.left, dado);
        } else if (dado > no.data) {
            no.right = inserir(no.right, dado);
        } else {
            return no;
        }

        no.height = 1 + Math.max(altura(no.left), altura(no.right));

        int balance = getBalance(no);

        if (balance > 1 && dado < no.left.data) {
            return rotacaoDireita(no);
        }

        if (balance < -1 && dado > no.right.data) {
            return rotacaoEsquerda(no);
        }

        if (balance > 1 && dado > no.left.data) {
            no.left = rotacaoEsquerda(no.left);
            return rotacaoDireita(no);
        }

        if (balance < -1 && dado < no.right.data) {
            no.right = rotacaoDireita(no.right);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    Node minValueNode(Node no) {
        Node current = no;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    Node remover(Node raizAtual, int dado) {
        if (raizAtual == null) {
            return raizAtual;
        }

        if (dado < raizAtual.data) {
            raizAtual.left = remover(raizAtual.left, dado);
        } else if (dado > raizAtual.data) {
            raizAtual.right = remover(raizAtual.right, dado);
        } else {
            if ((raizAtual.left == null) || (raizAtual.right == null)) {
                Node temp = null;
                if (raizAtual.left == null) {
                    temp = raizAtual.right;
                } else {
                    temp = raizAtual.left;
                }

                if (temp == null) {
                    temp = raizAtual;
                    raizAtual = null;
                } else {
                    raizAtual = temp;
                }
            } else {
                Node temp = minValueNode(raizAtual.right);
                raizAtual.data = temp.data;
                raizAtual.right = remover(raizAtual.right, temp.data);
            }
        }

        if (raizAtual == null) {
            return raizAtual;
        }

        raizAtual.height = 1 + Math.max(altura(raizAtual.left), altura(raizAtual.right));

        int balance = getBalance(raizAtual);

        if (balance > 1 && dado < raizAtual.left.data) {
            return rotacaoDireita(raizAtual);
        }

        if (balance < -1 && dado > raizAtual.right.data) {
            return rotacaoEsquerda(raizAtual);
        }

        if (balance > 1 && dado > raizAtual.left.data) {
            raizAtual.left = rotacaoEsquerda(raizAtual.left);
            return rotacaoDireita(raizAtual);
        }

        if (balance < -1 && dado < raizAtual.right.data) {
            raizAtual.right = rotacaoDireita(raizAtual.right);
            return rotacaoEsquerda(raizAtual);
        }

        return raizAtual;
    }

    long medirTempoInsercao(int[] numbers) {
        long startTime = System.currentTimeMillis();
        for (int number : numbers) {
            root = inserir(root, number);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    long medirTempoRemocao(int[] numbers) {
        long startTime = System.currentTimeMillis();
        for (int number : numbers) {
            root = remover(root, number);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
