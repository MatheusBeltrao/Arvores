class RedBlackTree {
    No raiz;

    static final int VERMELHO = 1;
    static final int PRETO = 0;

    class No {
        int dado;
        No esquerda, direita, pai;
        int cor;

        No(int dado) {
            this.dado = dado;
            this.esquerda = this.direita = this.pai = null;
            this.cor = VERMELHO;
        }
    }

    No inserir(No raizAtual, int dado) {
        raizAtual = inserirNo(raizAtual, dado);
        raizAtual.cor = PRETO;
        return raizAtual;
    }

    No inserirNo(No raizAtual, int dado) {
        if (raizAtual == null) {
            return new No(dado);
        }

        if (dado < raizAtual.dado) {
            raizAtual.esquerda = inserirNo(raizAtual.esquerda, dado);
            raizAtual.esquerda.pai = raizAtual;
        } else if (dado > raizAtual.dado) {
            raizAtual.direita = inserirNo(raizAtual.direita, dado);
            raizAtual.direita.pai = raizAtual;
        } else {
            return raizAtual;
        }

        if (raizAtual.cor == VERMELHO && raizAtual.pai != null && raizAtual.pai.cor == VERMELHO) {
            raizAtual = corrigirInsercao(raizAtual);
        }

        return raizAtual;
    }

    No corrigirInsercao(No raiz) {
        while (raiz != null && raiz.pai != null && raiz.pai.cor == VERMELHO) {
            No avo = raiz.pai.pai;
            No tio = null;

            if (raiz.pai == avo.esquerda) {
                tio = avo.direita;

                if (tio != null && tio.cor == VERMELHO) {
                    avo.cor = VERMELHO;
                    raiz.pai.cor = PRETO;
                    tio.cor = PRETO;
                    raiz = avo;
                } else {
                    if (raiz == raiz.pai.direita) {
                        raiz = raiz.pai;
                        rotacaoEsquerda(raiz);
                    }

                    raiz.pai.cor = PRETO;
                    avo.cor = VERMELHO;
                    rotacaoDireita(avo);
                }
            } else {
                tio = avo.esquerda;

                if (tio != null && tio.cor == VERMELHO) {
                    avo.cor = VERMELHO;
                    raiz.pai.cor = PRETO;
                    tio.cor = PRETO;
                    raiz = avo;
                } else {
                    if (raiz == raiz.pai.esquerda) {
                        raiz = raiz.pai;
                        rotacaoDireita(raiz);
                    }

                    raiz.pai.cor = PRETO;
                    avo.cor = VERMELHO;
                    rotacaoEsquerda(avo);
                }
            }
        }

        raiz.cor = PRETO;
        return raiz;
    }

    void rotacaoEsquerda(No x) {
        No y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != null) {
            y.esquerda.pai = x;
        }
        y.pai = x.pai;
        if (x.pai == null) {
            this.raiz = y;
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y;
        } else {
            x.pai.direita = y;
        }
        y.esquerda = x;
        x.pai = y;
    }

    void rotacaoDireita(No y) {
        No x = y.esquerda;
        y.esquerda = x.direita;
        if (x.direita != null) {
            x.direita.pai = y;
        }
        x.pai = y.pai;
        if (y.pai == null) {
            this.raiz = x;
        } else if (y == y.pai.direita) {
            y.pai.direita = x;
        } else {
            y.pai.esquerda = x;
        }
        x.direita = y;
        y.pai = x;
    }

    No minValueNode(No raizAtual) {
        No atual = raizAtual;

        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }

        return atual;
    }

    No remover(No raizAtual, int dado) {
        if (raizAtual == null) {
            return raizAtual;
        }

        if (dado < raizAtual.dado) {
            raizAtual.esquerda = remover(raizAtual.esquerda, dado);
        } else if (dado > raizAtual.dado) {
            raizAtual.direita = remover(raizAtual.direita, dado);
        } else {
            if ((raizAtual.esquerda == null) || (raizAtual.direita == null)) {
                No temp = null;
                if (temp == raizAtual.esquerda) {
                    temp = raizAtual.direita;
                } else {
                    temp = raizAtual.esquerda;
                }

                if (temp == null) {
                    temp = raizAtual;
                    raizAtual = null;
                } else {
                    raizAtual = temp;
                }
            } else {
                No temp = minValueNode(raizAtual.direita);
                raizAtual.dado = temp.dado;
                raizAtual.direita = remover(raizAtual.direita, temp.dado);
            }
        }

        if (raizAtual == null) {
            return raizAtual;
        }

        raizAtual.cor = PRETO;

        return raizAtual;
    }

    public long medirTempoInsercao(int[] numbers) {
        return 0;
    }
}
