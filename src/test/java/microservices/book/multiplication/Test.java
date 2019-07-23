package microservices.book.multiplication;

public class Test {
    @org.junit.Test
    public void test(){
        isPerfectSquare(9);
    }

    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int l = 1;
        int r = num / 2 - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (mid * mid < num) {
                l = mid + 1;
            } else if (mid * mid > num) {
                r = mid;
            } else {
                return true;
            }
        }
        return false;
    }
}
