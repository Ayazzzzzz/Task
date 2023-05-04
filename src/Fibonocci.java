public class Fibonocci {

    public class Fibonacci {
        public static int[] fibonacci(int n) {
            if (n == 1) {
                return new int[]{0};
            } else if (n == 2) {
                return new int[]{0, 1};
            } else {
                int[] fibSequence = new int[n];
                fibSequence[0] = 0;
                fibSequence[1] = 1;
                for (int i = 2; i < n; i++) {
                    fibSequence[i] = fibSequence[i - 1] + fibSequence[i - 2];
                }
                return fibSequence;
            }
        }

        public static void main(String[] args) {
            System.out.println("dasd");
            int n = 10;
            int[] fibonacciSequence = fibonacci(n);
            for (int num : fibonacciSequence) {
                System.out.print(num + " ");
            }
        }
    }
}
