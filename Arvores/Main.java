import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        RedBlackTree redBlackTree = new RedBlackTree();
        int[] numbers = generateNumbers(100000);

        long avlInsertTime = avlTree.medirTempoInsercao(numbers);
        long redBlackInsertTime = redBlackTree.medirTempoInsercao(numbers);

        System.out.println("Tempo de inserção na AVL Tree: " + avlInsertTime + " ms");
        System.out.println("Tempo de inserção na Red-Black Tree: " + redBlackInsertTime + " ms");

        long avlRemoveTime = avlTree.medirTempoRemocao(numbers);
        long redBlackRemoveTime = redBlackTree.medirTempoInsercao(numbers);

        System.out.println("Tempo de remoção na AVL Tree: " + avlRemoveTime + " ms");
        System.out.println("Tempo de remoção na Red-Black Tree: " + redBlackRemoveTime + " ms");
    }

    private static int[] generateNumbers(int count) {
        int[] numbers = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            numbers[i] = random.nextInt(200000) - 100000;
        }
        return numbers;
    }
}
