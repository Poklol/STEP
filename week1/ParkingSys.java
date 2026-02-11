import java.util.*;

public class ParkingSys {

    private String[] arr;
    private long[] in;
    private int n;

    public ParkingSys(int n) {
        this.n = n;
        arr = new String[n];
        in = new long[n];
    }

    private int h(String s) {
        return Math.abs(s.hashCode()) % n;
    }

    public int park(String s) {
        int i = h(s);
        int start = i;

        while (arr[i] != null) {
            i = (i + 1) % n;
            if (i == start) return -1;
        }

        arr[i] = s;
        in[i] = System.currentTimeMillis();
        return i;
    }

    public double exit(String s) {
        int i = h(s);
        int start = i;

        while (arr[i] != null) {
            if (arr[i].equals(s)) {
                long dur = System.currentTimeMillis() - in[i];
                arr[i] = null;
                in[i] = 0;
                return dur / 1000.0;
            }
            i = (i + 1) % n;
            if (i == start) break;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {

        ParkingSys p = new ParkingSys(10);

        int s1 = p.park("ABC1234");
        System.out.println("Parked at: " + s1);

        Thread.sleep(2000);

        double t = p.exit("ABC1234");
        System.out.println("Duration (sec): " + t);
    }
}
