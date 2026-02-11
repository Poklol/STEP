import java.util.*;
import java.util.concurrent.*;

public class InvSys {

    private Map<String, Integer> stock = new ConcurrentHashMap<>();
    private Map<String, Queue<Integer>> wait = new ConcurrentHashMap<>();

    public void add(String p, int c) {
        stock.put(p, c);
        wait.put(p, new ConcurrentLinkedQueue<>());
    }

    public synchronized String buy(String p, int u) {
        int s = stock.getOrDefault(p, 0);

        if (s > 0) {
            stock.put(p, s - 1);
            return "Success, remaining: " + (s - 1);
        } else {
            wait.get(p).add(u);
            return "Waitlist position: " + wait.get(p).size();
        }
    }

    public int check(String p) {
        return stock.getOrDefault(p, 0);
    }

    public static void main(String[] args) {

        InvSys is = new InvSys();

        is.add("IPHONE15_256GB", 3);

        System.out.println(is.check("IPHONE15_256GB"));
        System.out.println(is.buy("IPHONE15_256GB", 1));
        System.out.println(is.buy("IPHONE15_256GB", 2));
        System.out.println(is.buy("IPHONE15_256GB", 3));
        System.out.println(is.buy("IPHONE15_256GB", 4));
        System.out.println(is.buy("IPHONE15_256GB", 5));
    }
}
