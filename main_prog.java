import java.util.Scanner;

public class main_prog {

    // For calculating Step 0 -> a^b
    public static long higher_power(long a, long b) {
        long value1 = 1;
        while (b > 0) {
            if (b % 2 != 0) {
                value1 *= a;
                b -= 1;
            } else {
                a = a * a;
                b /= 2;
            }
        }
        return value1;
    }

    // For calculating Step 1 -> a^b mod n
    public static long modular_higher_power(long a, long b, long n) {
        long value1;
//        System.out.println("The value of a : " + a);
        long value = higher_power(a, b);
        value1 = (value % n);
        return (value1);
    }

    // For calculating z in Step 2 from 2^y*z
    public static int two_factorize(long x) {
        int z = 0;
        while (x > 0) {
            if ((x % 2) == 0) {
                x = x / 2;
                z++;
            } else {
                break;
            }
        }
        return z;
    }

    public static boolean Miller_Rabin_test(long n) {
        int b = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int a = (int) (Math.sqrt(n));

        for (int i = 2; i <= b; i++) {
            int low = 2;
            int high = a;
            while (low <= high) {
                int mid = ((low + high) / 2);
                if (higher_power(mid, i) == n) {
                    System.out.println("The number is composite");
                    return false;               // return to main

                } else if (n > higher_power(mid, i)) {
                    low = mid + 1;

                } else {
                    high = mid - 1;
                }
            }
        }

        long min = 2;
        long max = n;
        long rnd = (long) Math.random() * (max - min) + min;
//        System.out.println("Random number: " + rnd);

        if (modular_higher_power(rnd, n - 1, n) != 1)
            return false;
        else {
            int y = two_factorize(n - 1);
            long z = (n - 1) / higher_power(2, y);
            long remainder = modular_higher_power(rnd, z, n);
            if (remainder == n - 1 || remainder == 1) {
                System.out.println("The number is prime");
                return true;
            } else {
                while (z != n - 1) {
                    remainder = modular_higher_power(remainder, 2, n);
                    z *= 2;
                    if (remainder == 1)
                        return false;
                    if (remainder == n - 1)
                        return true;
                }
            }

            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Value of n ? : ");
        long n = sc.nextLong();
        System.out.print("Value of r ? : ");
        int r = sc.nextInt();
        sc.close();

        int counter = 0;
        for (int i = 1; i <= r; i++) {
            boolean result = Miller_Rabin_test(n);
            if (!result) {
                System.out.println("FINAL OUTPUT : THE NUMBER IS A COMPOSITE");
                return;
            } else
                counter++;
        }
        if (counter == r) {
            System.out.println("FINAL OUTPUT : THE NUMBER IS A PRIME");
        }
    }
}
